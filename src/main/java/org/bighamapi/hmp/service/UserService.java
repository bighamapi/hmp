package org.bighamapi.hmp.service;

import org.bighamapi.hmp.dao.UserDao;
import org.bighamapi.hmp.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CacheManager cacheManager;

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
    public User findAdmin(){
        User admin = userDao.findByRole("admin");
        return admin;
    }
    public void save(User user) {
        userDao.save(user);
    }
    public void newUser(User user){
        user.setRole("admin");
        user.setId("admin");
        if (user.getImage()==null|| "".equals(user.getImage())){
            user.setImage("https://github.com/identicons/b9485e300abe165e5b290960a0327b94.png");
        }
        userDao.save(user);
    }
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        return user;
    }
}
