package com.inexture.dao;

import com.inexture.model.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserBean,Integer> {
    UserBean findByEmail(String email);
}
