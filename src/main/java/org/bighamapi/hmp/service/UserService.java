package org.bighamapi.hmp.service;

import org.bighamapi.hmp.dao.UserDao;
import org.bighamapi.hmp.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    @Cacheable(value = "user",key="#id")
    public User findById(String id) {
        return userDao.findById(id).get();
    }

    @CacheEvict(value = "user",key = "#user.id")
    public void update(User user) {
        userDao.save(user);
    }

    public void save(User user) {
        userDao.save(user);
    }

    @Cacheable(value = "user",key="#username")
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        return user;
    }
}
