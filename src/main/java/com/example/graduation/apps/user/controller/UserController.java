package com.example.graduation.apps.user.controller;

import com.example.graduation.apps.user.dto.UserDTO;
import com.example.graduation.apps.user.service.UserService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.resultUtils.Result;
import com.example.graduation.utils.resultUtils.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Resource
    UserService userService;

    /**
     * 获取某个用户的信息
     */
    @GetMapping("{userId}")
    public Result<UserDTO> findByUserId(@PathVariable Long userId) throws MyException {
        return ResultUtil.success(userService.findByUserId(userId));
    }

    /**
     * 更新某个用户的信息
     */
    @PutMapping("{userId}")
    public Result<Void> update(@PathVariable Long userId, String name) throws MyException {
        return ResultUtil.success(userService.update(userId, name));
    }
}
