package com.example.demo.repositories;

<<<<<<< HEAD
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
=======
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
<<<<<<< HEAD
    boolean existsByEmail(String email);
=======
>>>>>>> 89bdafad69f44b0c16102f56f1649a325a2f1bd0
>>>>>>> b24923907923c92842cc0682f3fdb4e692befb48
}
