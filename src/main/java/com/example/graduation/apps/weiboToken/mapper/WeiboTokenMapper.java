package com.example.graduation.apps.weiboToken.mapper;

import com.example.graduation.apps.weiboToken.entity.WeiboTokenEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WeiboTokenMapper {
    /**
     * 添加微博 token
     * @param weiboToken
     */
    @Insert("INSERT INTO " +
            "weibo_token (user_id,weibo_token,create_time,status) " +
            "VALUES (#{userId},#{weiboToken},#{createTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer add(WeiboTokenEntity weiboToken);

    @Select("SELECT * " +
            "FROM weibo_token " +
            "WHERE user_id=#{userId}")
    List<WeiboTokenEntity> findByUserId(Integer userId);
}
