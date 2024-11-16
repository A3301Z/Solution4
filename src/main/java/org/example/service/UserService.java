package org.example.service;

import org.example.dao.UserDao;
import org.example.entity.User;

import java.util.List;
import java.util.UUID;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findById(UUID id) {
        return userDao.findById(id);
    }

    public void save(String username) {
        userDao.save(username);
    }

    public void delete(UUID id) {
        userDao.delete(id);
    }
}
