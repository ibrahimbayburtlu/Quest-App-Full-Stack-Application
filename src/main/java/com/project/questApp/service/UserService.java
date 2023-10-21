package com.project.questApp.service;

import com.project.questApp.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User saveOneUser(User newUser);
    User getOneUserById (Long userId);
    User updateOneUser(Long userId,User newUser);
    void deleteOneUser(Long userId);
    User getOneUserByUserName(String userName);
}
