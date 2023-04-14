package com.example.graduation.apps.weiboToken.mapper;

import com.example.graduation.apps.weiboToken.entity.WeiboTokenEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WeiboTokenMapper {
    /**
     * 获取一个用户的所有 token 记录
     */
    @Select("""
            SELECT *
            FROM weibo_token
            WHERE user_id=#{userId} AND deleted=0""")
    List<WeiboTokenEntity> findByUserId(Long userId);

    /**
     * 根据 weiboTokenId 获取 token
     */
    @Select("""
            SELECT *
            FROM weibo_token
            WHERE weibo_token_id=#{weiboTokenId} AND deleted=0""")
    WeiboTokenEntity findByWeiboTokenId(Long weiboTokenId);

    /**
     * 获取一个用户正在使用/未使用的 token
     */
    @Select("SELECT * " +
            "FROM weibo_token " +
            "WHERE user_id=#{userId} AND status=#{status} AND deleted=0")
    List<WeiboTokenEntity> findByUserIdAndStatus(Long userId, Integer status);

    /**
     * 根据 token 获取 token 记录
     */
    @Select("SELECT * " +
            "FROM weibo_token " +
            "WHERE weibo_token=#{weiboToken} AND deleted=0")
    WeiboTokenEntity findByWeiboToken(String weiboToken);

    /**
     * 添加微博 token
     */
    @Insert("INSERT INTO " +
            "weibo_token (user_id,weibo_id,weibo_name,weibo_token,created_time,status,deleted) " +
            "VALUES (#{userId},#{weiboId},#{weiboName},#{weiboToken},#{createdTime},#{status},#{deleted})")
    @Options(useGeneratedKeys = true, keyProperty = "weiboTokenId", keyColumn = "weibo_token_id")
    Long add(WeiboTokenEntity weiboToken);

    /**
     * 更新 token 记录（微博昵称）
     */
    @Update("""
            UPDATE weibo_token
            SET weibo_name = #{weiboName}, status = #{status}
            WHERE weibo_token_id = #{weiboTokenId}""")
    Long update(WeiboTokenEntity weiboTokenEntity);

    /**
     * 根据 weiboTokenId 获取 token
     */
    @Select("SELECT * " +
            "FROM weibo_token " +
            "WHERE weibo_token_id=#{weiboTokenId} AND deleted=0")
    WeiboTokenEntity findByTokenId(Long weiboTokenId);

    /**
     * 根据 weiboTokenId 删除 token
     */
    @Update("""
            UPDATE weibo_token
            SET deleted = 1
            WHERE weibo_token_id = #{weiboTokenId}""")
    Long deleteByTokenId(Long weiboTokenId);
}
