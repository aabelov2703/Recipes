package com.alex.recipe.repository;

import com.alex.recipe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);
}
