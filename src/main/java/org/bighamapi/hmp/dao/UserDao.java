package org.bighamapi.hmp.dao;

import org.bighamapi.hmp.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User,String> {

    User findByUsername(String username);
}
