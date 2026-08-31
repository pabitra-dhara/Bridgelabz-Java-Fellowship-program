package com.bookstore.order.repository;
import com.bookstore.order.entity.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface WishlistRepository extends JpaRepository<WishlistItem,Long>{List<WishlistItem> findByEmail(String email);}
