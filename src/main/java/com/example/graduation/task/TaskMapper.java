package com.example.graduation.task;

import com.example.graduation.apps.data.entity.UserEntity;
import com.example.graduation.apps.spider.object.Account;
import com.example.graduation.task.entity.Follower;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface TaskMapper {

    @Select("""
            SELECT DISTINCT(weibo_id)
            FROM weibo_token
            """)
    public List<Long> findAllWeiboId();

    @Insert("""
            INSERT INTO account_data(weibo_id,weibo_num,following,followers,influence,coverage_degree,activity_degree,propagation_power,total_like_num,total_repost_num,total_comment_num,created_at)
            VALUES(#{weiboId},#{weiboNum},#{following},#{followers},#{influence},#{coverageDegree},#{activityDegree},#{propagationPower},#{totalLikeNum},#{totalRepostNum},#{totalCommentNum},#{createdAt})
            """)
    void createAccountData(Account account);

    @Update("""
            UPDATE follower_data
            SET deleted = 1
            """)
    void deleteAllFollowerData();

    @Insert("""
            INSERT INTO follower_data(weibo_id,follower_id,deleted)
            VALUES(#{weiboId},#{followerId},#{deleted})
            """)
    void createFollowerData(Follower follower);

    @Update("""
            UPDATE user_data
            SET deleted = 1
            """)
    void deleteAllUserData();

    @Select("""
            SELECT *
            FROM user_data
            WHERE weibo_id=#{weiboId} AND deleted=0
            """)
    List<UserEntity> findUserDataByWeiboId(Long weiboId);

    @Insert("""
            INSERT INTO user_data
            (weibo_id,gender,location,verified,birthday,age,constellation,school,company,weibo_num,following,followers,devices,recent_weibo_num,created_at,deleted)
            VALUES
            (#{weiboId},#{gender},#{location},#{verified},#{birthday},#{age},#{constellation},#{school},#{company},#{weiboNum},#{following},#{followers},#{devices},#{recentWeiboNum},#{createdAt},#{deleted})
            """)
    void createUserData(UserEntity user);
}
