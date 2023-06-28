package com.binga.binga.services;

import com.binga.binga.dao.UserRepos;
import com.binga.binga.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements  UserService{
    UserRepos userRepos ;

    @Override
    public User AddUser(User user) {
        return userRepos.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepos.findById(id).orElse(null );
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepos.findUserByEmail(login);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepos.findAll();
    }
}
