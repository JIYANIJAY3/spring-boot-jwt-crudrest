package com.inexture.service;

import com.inexture.model.UserBean;
import com.inexture.model.UserDto;

import java.util.List;

public interface UserService {

    UserBean save(UserDto user);

    List<UserBean> findAll();

    void delete(int id);

    UserDto update(UserDto userDto) throws Exception;
}
