package com.example.graduation.apps.weiboUser.object;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeiboUser {
    Long weiboId;
    String weiboName;
    String location;
    String description;
    String weiboAvatar;
    String gender;
    // 粉丝数
    Long followersCount;
    // 关注数
    Long friendsCount;
    // 微博数
    Long statusesCount;
    // 收藏数
    Long favouritesCount;
    // 是否是微博认证用户，即加V用户，true：是，false：否
    Boolean verified;
    // 用户的在线状态，0：不在线、1：在线
    Integer onlineStatus;
    // 用户的互粉数
    Long biFollowersCount;
    Long createdAt;

    @JsonGetter("weiboId")
    public Long getWeiboId() {
        return weiboId;
    }

    @JsonGetter("weiboName")
    public String getWeiboName() {
        return weiboName;
    }

    @JsonGetter("location")
    public String getLocation() {
        return location;
    }

    @JsonGetter("description")
    public String getDescription() {
        return description;
    }

    @JsonGetter("weiboAvatar")
    public String getWeiboAvatar() {
        return weiboAvatar;
    }

    @JsonGetter("gender")
    public String getGender() {
        return gender;
    }

    @JsonGetter("followersCount")
    public Long getFollowersCount() {
        return followersCount;
    }

    @JsonGetter("friendsCount")
    public Long getFriendsCount() {
        return friendsCount;
    }

    @JsonGetter("statusesCount")
    public Long getStatusesCount() {
        return statusesCount;
    }

    @JsonGetter("favouritesCount")
    public Long getFavouritesCount() {
        return favouritesCount;
    }

    @JsonGetter("verified")
    public Boolean getVerified() {
        return verified;
    }

    @JsonGetter("onlineStatus")
    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    @JsonGetter("biFollowersCount")
    public Long getBiFollowersCount() {
        return biFollowersCount;
    }

    @JsonGetter("createdAt")
    public Long getCreatedAt() {
        return createdAt;
    }


    @JsonSetter("id")
    public void setWeiboId(Long weiboId) {
        this.weiboId = weiboId;
    }

    @JsonSetter("screen_name")
    public void setWeiboName(String weiboName) {
        this.weiboName = weiboName;
    }

    @JsonSetter("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonSetter("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonSetter("avatar_hd")
    public void setWeiboAvatar(String weiboAvatar) {
        this.weiboAvatar = weiboAvatar;
    }

    @JsonSetter("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonSetter("followers_count")
    public void setFollowersCount(Long followersCount) {
        this.followersCount = followersCount;
    }

    @JsonSetter("friends_count")
    public void setFriendsCount(Long friendsCount) {
        this.friendsCount = friendsCount;
    }

    @JsonSetter("statuses_count")
    public void setStatusesCount(Long statusesCount) {
        this.statusesCount = statusesCount;
    }

    @JsonSetter("favourites_count")
    public void setFavouritesCount(Long favouritesCount) {
        this.favouritesCount = favouritesCount;
    }

    @JsonSetter("verified")
    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    @JsonSetter("online_status")
    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    @JsonSetter("bi_followers_count")
    public void setBiFollowersCount(Long biFollowersCount) {
        this.biFollowersCount = biFollowersCount;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
