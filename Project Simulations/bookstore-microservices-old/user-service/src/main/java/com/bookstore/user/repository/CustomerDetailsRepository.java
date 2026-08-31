package com.bookstore.user.repository;

import com.bookstore.user.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long> {}
