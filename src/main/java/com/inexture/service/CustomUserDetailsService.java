package com.inexture.service;

import com.inexture.dao.UserDao;
import com.inexture.model.UserBean;
import com.inexture.model.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService,UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserBean userBean = userDao.findByEmail(email);
        System.out.println("userbean"+userBean);
        if(userBean!=null)
        {
            return new User(userBean.getEmail(),userBean.getPassword(),new ArrayList<>());
        }
        else
        {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    @Override
    public UserBean save(UserDto user) {
       UserBean userBean = new UserBean();
       userBean.setEmail(user.getEmail());
        userBean.setFirstname(user.getFirstname());
        userBean.setLastname(user.getLastname());
        userBean.setPassword(user.getPassword());
        userBean.setMobile(user.getMobile());
       return userDao.save(userBean);
    }

    @Override
    public List<UserBean> findAll() {
        return userDao.findAll();
    }

    @Override
    public void delete(int id) {
        userDao.deleteById(id);
    }
    @Override
    public UserDto update(UserDto userDto)  {
        UserBean user = userDao.findByEmail(userDto.getEmail());


        if(user != null && user.getEmail().equals(userDto.getEmail())) {
            user.setFirstname(userDto.getFirstname());
            user.setLastname(userDto.getLastname());
            user.setMobile(userDto.getMobile());
            userDao.save(user);
        }
        else
        {
            System.out.println("email not same");
        }
        return userDto;
    }

}
