package org.bighamapi.hmp.service;

import org.bighamapi.hmp.dao.UserDao;
import org.bighamapi.hmp.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CacheManager cacheManager;

    @Cacheable(value = "user",key="#id")
    public User findById(String id) {
        return userDao.findById(id).get();
    }


    public void update(User user) {
        user.setId(findAdmin().getId());
        user.setRole(findAdmin().getRole());
        userDao.save(user);
        Cache userCache = cacheManager.getCache("user");
        userCache.clear();
    }
    @Cacheable(value = "user",key="0")
    public User findAdmin(){
        User admin = userDao.findByRole("admin");
        return admin;
    }
    public void save(User user) {
        userDao.save(user);
    }

    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        return user;
    }
}
