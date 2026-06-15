package com.fundoonotesapp.fundoo.repository;

import com.fundoonotesapp.fundoo.entity.Label;
import com.fundoonotesapp.fundoo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LabelRepository
        extends JpaRepository<Label, Long> {

    List<Label> findByUser(User user);

    Optional<Label> findByIdAndUser(
            Long id,
            User user);

    boolean existsByNameAndUser(
            String name,
            User user);
}