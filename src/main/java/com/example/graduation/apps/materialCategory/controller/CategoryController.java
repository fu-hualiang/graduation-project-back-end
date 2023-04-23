package com.example.graduation.apps.materialCategory.controller;

import com.example.graduation.apps.materialCategory.dto.CategoryDTO;
import com.example.graduation.apps.materialCategory.form.RequestForm;
import com.example.graduation.apps.materialCategory.service.CategoryService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.resultUtils.Result;
import com.example.graduation.utils.resultUtils.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("materialCategories")
public class CategoryController {
    @Resource
    CategoryService categoryService;

    /**
     * 获得用户文本/图片素材的分类
     */
    @GetMapping("")
    public Result<List<CategoryDTO>> findByUserIdAndType(Long userId, Integer type) throws MyException {
        return ResultUtil.success(categoryService.findByUserIdAndType(userId, type));
    }

    /**
     * 添加用户文本/图片素材的分类
     */
    @PostMapping("")
    public Result<Void> create(@RequestBody RequestForm requestForm) throws MyException {
        Long userId = requestForm.getUserId();
        Integer type = requestForm.getType();
        String categoryName = requestForm.getCategoryName();
        return ResultUtil.success(categoryService.create(userId, type, categoryName));
    }

    /**
     * 更新用户文本/图片素材的分类
     */
    @PutMapping("{categoryId}")
    public Result<Void> update(@PathVariable Long categoryId, @RequestBody RequestForm requestForm) throws MyException {
        String categoryName = requestForm.getCategoryName();
        return ResultUtil.success(categoryService.update(categoryId, categoryName));
    }

    /**
     * 删除用户文本/图片素材的分类
     */
    @DeleteMapping("{categoryId}")
    public Result<Void> delete(@PathVariable Long categoryId) throws MyException {
        return ResultUtil.success(categoryService.delete(categoryId));
    }
}
