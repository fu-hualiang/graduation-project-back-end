package com.example.graduation.task;

import com.example.graduation.apps.data.entity.UserEntity;
import com.example.graduation.apps.spider.object.Account;
import com.example.graduation.exception.MyException;
import com.example.graduation.task.entity.Follower;
import com.example.graduation.utils.PythonScriptUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class ScheduledTask {
    @Value("${python.path}")
    private String pythonPath;

    @Value("${python.script.path}")
    private String pythonScriptPath;
    @Resource
    ObjectMapper objectMapper;
    @Resource
    TaskMapper taskMapper;

    @Scheduled(cron = "0 0 0 * * ?")
    public void collectAccountData() throws MyException {
        // 获取所有token的微博Id
        List<Long> weiboIdList = taskMapper.findAllWeiboId();
        // 存储账户信息
        for (Long weiboId : weiboIdList) {
            String[] args = new String[]{pythonPath, pythonScriptPath + "account_starter.py", String.valueOf(weiboId)};
            Account account;
            try {
                String res = PythonScriptUtils.run(args).get(0);
                account = objectMapper.readValue(res, Account.class);
            } catch (Exception e) {
                e.printStackTrace();
                throw new MyException(40000, "未知错误");
            }
            account.setCreatedAt(new Date().getTime());
            taskMapper.createAccountData(account);
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void collectUserData() throws MyException {
        taskMapper.deleteAllFollowerData();
        taskMapper.deleteAllUserData();
        // 获取所有 token 的微博Id
        List<Long> weiboIdList = taskMapper.findAllWeiboId();
        // 获取并存储每个 token 的所有粉丝
        for (Long weiboId : weiboIdList) {
            String[] args = new String[]{pythonPath, pythonScriptPath + "follower_starter.py", String.valueOf(weiboId)};
            try {
                List<String> followerStringList = PythonScriptUtils.run(args);
                // 存储每个粉丝的信息
                for (String followerString : followerStringList) {
                    Follower follower = objectMapper.readValue(followerString, Follower.class);
                    follower.setDeleted(0);
                    taskMapper.createFollowerData(follower);

                    if (taskMapper.findUserDataByWeiboId(follower.getFollowerId()).size() == 0) {
                        args = new String[]{pythonPath, pythonScriptPath + "user_starter.py", String.valueOf(follower.getFollowerId())};
                        String userString = PythonScriptUtils.run(args).get(0);
                        UserEntity user = objectMapper.readValue(userString, UserEntity.class);
                        taskMapper.createUserData(user);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new MyException(40000, "未知错误");
            }
        }
    }
}
