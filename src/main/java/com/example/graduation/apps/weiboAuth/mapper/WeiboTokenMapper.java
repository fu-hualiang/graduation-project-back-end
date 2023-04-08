package com.example.graduation.apps.weiboAuth.mapper;

import com.example.graduation.apps.weiboAuth.entity.WeiboTokenEntity;
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
            "weibo_token (user_id,weibo_id,weibo_token,created_time,status) " +
            "VALUES (#{userId},#{weiboId},#{weiboToken},#{createdTime},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer add(WeiboTokenEntity weiboToken);

    @Select("SELECT weibo_token.*,weibo_user.weibo_name " +
            "FROM weibo_token,weibo_user " +
            "WHERE user_id=#{userId} AND weibo_token.`status`<>-1 AND weibo_token.weibo_id=weibo_user.weibo_id")
    List<WeiboTokenEntity> findByUserId(Long userId);

    @Select("SELECT * " +
            "FROM weibo_token " +
            "WHERE weibo_token=#{accessToken}")
    WeiboTokenEntity findByToken(String accessToken);
}
