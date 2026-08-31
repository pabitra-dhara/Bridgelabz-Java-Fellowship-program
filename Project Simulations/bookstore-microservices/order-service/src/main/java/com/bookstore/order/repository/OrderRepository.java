package com.bookstore.order.repository;
import com.bookstore.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface OrderRepository extends JpaRepository<Order,Long>{List<Order> findByEmailOrderByIdDesc(String email);}
