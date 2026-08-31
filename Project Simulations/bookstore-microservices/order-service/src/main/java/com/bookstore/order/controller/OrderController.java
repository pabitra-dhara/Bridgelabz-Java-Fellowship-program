package com.bookstore.order.controller;

import com.bookstore.order.entity.*;
import com.bookstore.order.repository.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/bookstore_user")
public class OrderController {
    private final CartRepository cart;
    private final WishlistRepository wish;
    private final OrderRepository orders;
    private final RabbitTemplate rabbit;

    public OrderController(CartRepository c, WishlistRepository w, OrderRepository o, RabbitTemplate r) {
        cart = c;
        wish = w;
        orders = o;
        rabbit = r;
    }

    @PostMapping("/add_cart_item/{product_id}")
    public ResponseEntity<?> addCart(@PathVariable Long product_id, @RequestBody(required = false) Map<String, Object> body, Authentication a) {
        CartItem item = new CartItem();
        item.setEmail(a.getName());
        item.setProductId(product_id);
        item.setQuantityToBuy(1);
        if (body != null) {
            item.setProductName(String.valueOf(body.getOrDefault("productName", "")));
            Object price = body.get("price");
            if (price != null) item.setPrice(Double.parseDouble(String.valueOf(price)));
        }
        return ResponseEntity.ok(cart.save(item));
    }

    @PutMapping("/cart_item_quantity/{cartItem_id}")
    public ResponseEntity<?> updateCart(@PathVariable Long cartItem_id, @RequestBody Map<String, Integer> body, Authentication a) {
        CartItem i = cart.findById(cartItem_id).orElseThrow(() -> new NoSuchElementException("Cart item not found"));
        if (!i.getEmail().equals(a.getName())) throw new SecurityException("Not your cart item");
        i.setQuantityToBuy(body.getOrDefault("quantityToBuy", 1));
        return ResponseEntity.ok(cart.save(i));
    }

    @DeleteMapping("/remove_cart_item/{cartItem_id}")
    public ResponseEntity<?> removeCart(@PathVariable Long cartItem_id, Authentication a) {
        CartItem i = cart.findById(cartItem_id).orElseThrow(() -> new NoSuchElementException("Cart item not found"));
        if (!i.getEmail().equals(a.getName())) throw new SecurityException("Not your cart item");
        cart.delete(i);
        return ResponseEntity.ok(Map.of("message", "Successfully removed product from cart"));
    }

    @GetMapping("/get_cart_items")
    public ResponseEntity<?> getCart(Authentication a) {
        return ResponseEntity.ok(cart.findByEmail(a.getName()));
    }

    @PostMapping("/add_wish_list/{product_id}")
    public ResponseEntity<?> addWish(@PathVariable Long product_id, Authentication a) {
        WishlistItem w = new WishlistItem();
        w.setEmail(a.getName());
        w.setProductId(product_id);
        return ResponseEntity.ok(wish.save(w));
    }

    @DeleteMapping("/remove_wishlist_item/{product_id}")
    public ResponseEntity<?> removeWish(@PathVariable Long product_id, Authentication a) {
        wish.findByEmail(a.getName()).stream().filter(x -> x.getProductId().equals(product_id)).findFirst().ifPresent(wish::delete);
        return ResponseEntity.ok(Map.of("message", "Successfully removed product from wish list"));
    }

    @GetMapping("/get_wishlist_items")
    public ResponseEntity<?> getWish(Authentication a) {
        return ResponseEntity.ok(wish.findByEmail(a.getName()));
    }

    @GetMapping("/get_orders")
    public ResponseEntity<?> getOrders(Authentication a) {
        return ResponseEntity.ok(orders.findByEmailOrderByIdDesc(a.getName()));
    }

    @PostMapping("/add/order")
    public ResponseEntity<?> addOrder(@RequestBody Map<String, Object> body, Authentication a) {
        Order order = new Order();
        order.setEmail(a.getName());
        Object raw = body.get("orders");
        if (!(raw instanceof List<?>) || ((List<?>) raw).isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Order must contain at least one item"));
        }
        List<?> list = (List<?>) raw;
            double total = 0;
            for (Object obj : list) {
                if (obj instanceof Map<?, ?> m) {
                    OrderItem item = new OrderItem();
                    item.setProductId(Long.parseLong(String.valueOf(m.get("product_id"))));
                    item.setProductName(String.valueOf(m.get("product_name")));
                    item.setProductQuantity(Integer.parseInt(String.valueOf(m.get("product_quantity"))));
                    item.setProductPrice(Double.parseDouble(String.valueOf(m.get("product_price"))));
                    order.getItems().add(item);
                    total += item.getProductQuantity() * item.getProductPrice();
                }
            }
            order.setTotalAmount(total);
        Order saved = orders.save(order);
        cart.deleteAll(cart.findByEmail(a.getName()));
        rabbit.convertAndSend("bookstore.order.events", Map.of("event", "ORDER_CREATED", "orderId", saved.getId().toString(), "email", a.getName()));
        return ResponseEntity.ok(saved);
    }
}
