package com.example.graduation.apps.weiboToken.controller;

import com.example.graduation.apps.weiboToken.dto.WeiboTokenDTO;
import com.example.graduation.apps.weiboToken.form.CreateForm;
import com.example.graduation.apps.weiboToken.form.UpdateForm;
import com.example.graduation.apps.weiboToken.service.WeiboTokenService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.resultUtils.Result;
import com.example.graduation.utils.resultUtils.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("weiboTokens")
public class WeiboTokenController {
    @Resource
    WeiboTokenService weiboTokenService;

    /**
     * 获取一个用户的所有 token
     */
    @GetMapping("")
    public Result<List<WeiboTokenDTO>> findByUserId(Long userId) {
        return ResultUtil.success(weiboTokenService.findByUserId(userId));
    }

    /**
     * 根据 weiboTokenId 获取 token
     */
    @GetMapping("{weiboTokenId}")
    public Result<WeiboTokenDTO> findByWeiboTokenId(@PathVariable Long weiboTokenId) {
        return ResultUtil.success(weiboTokenService.findByWeiboTokenId(weiboTokenId));
    }

    /**
     * 获取一个用户正在使用/未使用的 token
     */
    @GetMapping("status/{status}")
    public Result<List<WeiboTokenDTO>> findByUserIdAndStatus(Long userId, @PathVariable Integer status) {
        return ResultUtil.success(weiboTokenService.findByUserIdAndStatus(userId, status));
    }

    /**
     * 通过 code 为用户添加 token
     */
    @PostMapping("")
    public Result<Void> create(@RequestBody CreateForm createForm) throws MyException {
        Long userId = createForm.getUserId();
        String code = createForm.getCode();
        return ResultUtil.success(weiboTokenService.create(userId, code));
    }

    /**
     * 更新账号下所有微博 token 信息（微博发生改名）
     */
    @PutMapping("")
    public Result<Void> update(@RequestBody UpdateForm updateForm) throws MyException {
        Long userId = updateForm.getUserId();
        return ResultUtil.success(weiboTokenService.update(userId));
    }

    /**
     * 根据 weiboTokenId 删除 token
     */
    @DeleteMapping("{weiboTokenId}")
    public Result<Void> deleteByTokenId(@PathVariable Long weiboTokenId) throws MyException {
        return ResultUtil.success(weiboTokenService.deleteByTokenId(weiboTokenId));
    }

    /**
     * 更新 token 的 status
     */
    @PutMapping("status")
    public Result<Void> updateStatus(@RequestBody UpdateForm updateForm) throws MyException {
        Long weiboTokenId = updateForm.getWeiboTokenId();
        return ResultUtil.success(weiboTokenService.updateStatus(weiboTokenId));
    }
}
