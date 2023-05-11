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
            SELECT user_data.*
            FROM follower_data, user_data
            WHERE follower_data.weibo_id = #{weiboId} AND follower_data.follower_id = user_data.weibo_id AND user_data.deleted = 0
            """)
    List<UserEntity> findUserData(Long weiboId);
}
