package com.bookstore.order.repository;
import com.bookstore.order.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface CartRepository extends JpaRepository<CartItem,Long>{List<CartItem> findByEmail(String email);}
