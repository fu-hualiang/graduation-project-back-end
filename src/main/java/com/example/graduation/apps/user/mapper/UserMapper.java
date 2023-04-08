package com.example.graduation.apps.user.mapper;

import com.example.graduation.apps.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("""
            SELECT *
            FROM `user`
            WHERE username = #{username}""")
    UserEntity findByUserName(String username);
}
