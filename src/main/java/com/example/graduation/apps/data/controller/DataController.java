package com.example.graduation.apps.data.controller;

import com.example.graduation.apps.data.dto.AccountDTO;
import com.example.graduation.apps.data.dto.UserDTO;
import com.example.graduation.apps.data.service.DataService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.resultUtils.Result;
import com.example.graduation.utils.resultUtils.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("data")
public class DataController {
    @Resource
    DataService dataService;
    @GetMapping("accounts/{weiboId}")
    public Result<List<AccountDTO>> findAccountData(@PathVariable Long weiboId) throws MyException {
        return ResultUtil.success(dataService.findAccountData(weiboId));
    }

    @GetMapping("fans/{weiboId}")
    public Result<List<UserDTO>> findUserData(@PathVariable Long weiboId) throws MyException {
        return ResultUtil.success(dataService.findUserData(weiboId));
    }
}
