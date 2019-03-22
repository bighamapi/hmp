package org.bighamapi.hmp.service;

import org.bighamapi.hmp.dao.UserDao;
import org.bighamapi.hmp.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findById(String id) {
        return userDao.findById(id).get();
    }

    public void update(User user) {
        userDao.save(user);
    }

    public void save(User user) {
        userDao.save(user);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
