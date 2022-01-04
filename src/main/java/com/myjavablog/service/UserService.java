package com.myjavablog.service;

import java.util.List;

import com.myjavablog.model.InsDetails;
import com.myjavablog.model.User;

public interface UserService {

    public User findUserByEmail(String email) ;
    public User saveUser(User user);
    public List<InsDetails> getInsDetails(String insEmail);
}
