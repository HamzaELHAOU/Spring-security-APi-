package com.binga.binga.dao;

import com.binga.binga.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepos extends JpaRepository<User,Long> {

    User findUserByEmail(String email);
}
