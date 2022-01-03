package com.myjavablog.service;

import com.myjavablog.model.User;

public interface UserService {

    public User findUserByEmail(String email) ;
    public User saveUser(User user);
}
