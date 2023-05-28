package com.example.graduation.apps.weiboContent.controller;

import com.example.graduation.apps.weiboContent.form.RequestForm;
import com.example.graduation.apps.weiboContent.object.WeiboContent;
import com.example.graduation.apps.weiboContent.service.WeiboContentService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.resultUtils.Result;
import com.example.graduation.utils.resultUtils.ResultUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("weiboContents")
public class WeiboContentController {
    @Resource
    WeiboContentService weiboContentService;

    /**
     * 获取用户的微博（最多 5 条）
     */
    @GetMapping("")
    public Result<List<WeiboContent>> find(String weiboToken) throws MyException {
        return ResultUtil.success(weiboContentService.find(weiboToken));
    }

    /**
     * 获取@用户的微博
     */
    @GetMapping("mentions")
    public Result<List<WeiboContent>> findMention(String weiboToken) throws MyException {
        return ResultUtil.success(weiboContentService.findMention(weiboToken));
    }

    /**
     * 发布微博
     */
    @PostMapping("")
    public Result<Void> create(@RequestBody RequestForm form, HttpServletRequest request) throws MyException, UnsupportedEncodingException {
        String path = request.getServletContext().getRealPath("/static/pictureMaterial/");
        String weiboToken = form.getWeiboToken();
        String status = form.getStatus();
        String picture = form.getPicture();
        return ResultUtil.success(weiboContentService.create(weiboToken, status, picture,path));
    }
}
