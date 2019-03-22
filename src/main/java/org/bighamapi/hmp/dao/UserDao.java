package org.bighamapi.hmp.dao;

import org.bighamapi.hmp.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,String> {
}
