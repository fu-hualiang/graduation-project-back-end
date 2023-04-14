package com.example.graduation.apps.user.mapper;

import com.example.graduation.apps.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("""
            SELECT *
            FROM `user`
            WHERE username = #{username}""")
    UserEntity findByUserName(String username);

    @Select("""
            SELECT *
            FROM `user`
            WHERE user_id = #{userId}""")
    UserEntity findByUserId(Long userId);

    @Update("""
            UPDATE user
            SET username=#{username},password=#{password},name=#{name},avatar=#{avatar}
            WHERE user_id = #{id}""")
    Integer update(UserEntity userEntity);
}
