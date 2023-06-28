package com.binga.binga.services;



import com.binga.binga.entities.User;

import java.util.List;

public interface UserService {
    User AddUser(User user);
    User getUser(Long id);
    User getUserByLogin(String login);
    List<User> getAllUsers();
}
