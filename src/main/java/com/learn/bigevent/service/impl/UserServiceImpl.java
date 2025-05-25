package com.learn.bigevent.service.impl;

import com.learn.bigevent.mapper.UserMapper;
import com.learn.bigevent.pojo.User;
import com.learn.bigevent.service.UserService;
import com.learn.bigevent.utils.Md5Util;
import com.learn.bigevent.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(String username, String password) {

        // Encrypt the password
        String encryptedPassword = Md5Util.getMD5String(password);
        // Create a new User object
        userMapper.add(username, encryptedPassword);
    }

    @Override
    public void update(User user) {
        // Update the user information
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        int id = (int) map.get("id");
        userMapper.updateAvatar(avatarUrl, id);
    }

    @Override
    public void updateNewPwd(String newPwd) {
        String encryptedPassword = Md5Util.getMD5String(newPwd);
        Map<String, Object> map = ThreadLocalUtil.get();
        int id = (int) map.get("id");
        userMapper.updateNewPwd(encryptedPassword, id);
    }
}
