package com.inexture.controller;

import com.inexture.model.ApiResponse;
import com.inexture.model.UserBean;
import com.inexture.model.UserDto;
import com.inexture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class HomeRestController {

    @Autowired
    private UserService userService;


    @RequestMapping("/home")
    public String home()
    {
        return "Welcome";
    }

    @GetMapping("/allusers")
    public ApiResponse<List<UserBean>> listUser(){
      try {
          return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.",userService.findAll());
      }
      catch (Exception e) {
          return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Unauthorized", null);
      }
    }

    @PostMapping("/adduser")
    public ApiResponse<UserBean> saveUser(@RequestBody UserDto user){
        return new ApiResponse<>(HttpStatus.OK.value(), "User saved successfully.",userService.save(user));
    }

    @DeleteMapping("/deleteuser/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.", null);
    }

    @PutMapping("/updateuser/{email}")
    public ApiResponse<UserDto> update(@RequestBody UserDto userDto) throws Exception {
        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.",userService.update(userDto));
    }
}
