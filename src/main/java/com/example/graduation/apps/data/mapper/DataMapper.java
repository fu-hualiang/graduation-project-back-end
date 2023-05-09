package com.example.graduation.apps.data.mapper;

import com.example.graduation.apps.data.entity.AccountEntity;
import com.example.graduation.apps.data.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DataMapper {
    @Select("""
            SELECT *
            FROM account_data
            WHERE weibo_id = #{weiboId}
            ORDER BY created_at
            LIMIT 14""")
    List<AccountEntity> findAccountData(Long weiboId);

    @Select("""
            SELECT *
            FROM user_data
            WHERE weibo_id = #{weiboId}
            ORDER BY created_at
            LIMIT 14""")
    List<UserEntity> findUserData(Long weiboId);
}
