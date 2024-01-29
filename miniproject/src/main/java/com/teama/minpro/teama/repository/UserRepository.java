package com.teama.minpro.teama.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teama.minpro.teama.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
