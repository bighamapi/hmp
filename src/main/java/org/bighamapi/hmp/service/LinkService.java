package org.bighamapi.hmp.service;

import org.bighamapi.hmp.dao.LinkDao;
import org.bighamapi.hmp.dao.UserDao;
import org.bighamapi.hmp.pojo.Link;
import org.bighamapi.hmp.pojo.User;
import org.bighamapi.hmp.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkService {

    @Autowired
    private LinkDao linkDao;
    @Autowired
    private IdWorker idWorker;

    public List<Link> findAll(){
        return linkDao.findAll();
    }
    @Cacheable(value = "link",key="#id")
    public Link findById(String id) {
        return linkDao.findById(id).get();
    }

    @CacheEvict(value = "link",key = "#link.id")
    public void update(Link link) {
        linkDao.save(link);
    }

    public void save(Link link) {
        link.setId(idWorker.nextId()+"");
        linkDao.save(link);
    }

    @CacheEvict(value = "link",key = "#id")
    public void deleteById(String id){
        linkDao.deleteById(id);
    }

}
