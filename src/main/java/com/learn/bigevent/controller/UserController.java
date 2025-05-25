package com.learn.bigevent.controller;

import com.learn.bigevent.pojo.Result;
import com.learn.bigevent.pojo.User;
import com.learn.bigevent.service.UserService;
import com.learn.bigevent.utils.JwtUtil;
import com.learn.bigevent.utils.Md5Util;
import com.learn.bigevent.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register(
            @Pattern(regexp = "^\\S{5,16}") String username,
            @Pattern(regexp = "^\\S{5,16}") String password) {

        User u = userService.findByUsername(username);
        if (u == null){
            // register
            userService.register(username, password);
            return Result.success();
        } else {
            // username already exists
            return Result.error("Username already exists!");
        }

    }

    @PostMapping("/login")
    public Result<String> login(
            @Pattern(regexp = "^\\S{5,16}") String username,
            @Pattern(regexp = "^\\S{5,16}") String password) {

        User u = userService.findByUsername(username);
        if (u == null){
            // username not found
            return Result.error("Username not found!");
        } else if (!u.getPassword().equals(Md5Util.getMD5String(password))){
            // password incorrect

            return Result.error("Incorrect password!");
        } else {
            // login successful

            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("username", u.getUsername());
            String token = JwtUtil.genToken(claims);

            // Store token in Redis
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token, token, 1, TimeUnit.DAYS);
            return Result.success(token);
        }
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/) {
        // Parse the token to get user information
        // Map<String, Object> map = JwtUtil.parseToken(token);
        // User user = userService.findByUsername((String) map.get("username"));

        //Use ThreadLocal to get user information
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = map.get("username").toString();
        User user = userService.findByUsername(username);

        if (user == null) {
            return Result.error("User not found");
        } else {
            return Result.success(user);
        }
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token) {
        // Validation
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if (oldPwd == null || newPwd == null || rePwd == null) {
            return Result.error("Invalid parameters");
        } else {
            if (!newPwd.equals(rePwd)) {
                return Result.error("New password and confirmation password do not match");
            }
            // Check if the old password is correct
            Map<String, Object> map = ThreadLocalUtil.get();
            User user = userService.findByUsername(map.get("username").toString());
            if (!user.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
                return Result.error("Incorrect old password");
            }
        }
        // Update password
        userService.updateNewPwd(newPwd);
        // Invalidate the old token
        stringRedisTemplate.delete(token);
        return Result.success();
    }


}
