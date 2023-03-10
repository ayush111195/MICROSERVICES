package com.user.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.user.services.entites.User;

public interface UserRepository extends JpaRepository<User, String> {

}
