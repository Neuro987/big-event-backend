package com.learn.bigevent.service;

import com.learn.bigevent.pojo.User;

public interface UserService {

    User findByUsername(String username);

    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updateNewPwd(String newPwd);
}
