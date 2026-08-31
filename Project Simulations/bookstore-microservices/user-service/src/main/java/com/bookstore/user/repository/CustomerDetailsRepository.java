package com.bookstore.user.repository;

import com.bookstore.user.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long> {
    Optional<CustomerDetails> findByEmail(String email);
}
