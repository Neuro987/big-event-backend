package com.learn.bigevent.mapper;

import com.learn.bigevent.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    @Insert("INSERT INTO user(username, password, create_time, update_time) " +
            "VALUES(#{username}, #{encryptedPassword}, NOW(), NOW())")
    void add(String username, String encryptedPassword);

    @Update("UPDATE user set nickname = #{nickname}, email = #{email}, update_time = #{updateTime} " +
            "WHERE id = #{id}")
    void update(User user);

    @Update("UPDATE user set user_pic = #{avatarUrl}, update_time = NOW()" +
            "WHERE id = #{id}")
    void updateAvatar(String avatarUrl, Integer id);

    @Update("UPDATE user set password = #{encryptedPassword}, update_time = NOW()" +
            "WHERE id = #{id}")
    void updateNewPwd(String encryptedPassword, int id);
}
