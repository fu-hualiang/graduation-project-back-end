package com.example.graduation.apps.materialCategory.service.impl;

import com.example.graduation.apps.materialCategory.dto.CategoryDTO;
import com.example.graduation.apps.materialCategory.entity.CategoryEntity;
import com.example.graduation.apps.materialCategory.mapper.CategoryMapper;
import com.example.graduation.apps.materialCategory.service.CategoryService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.MyBeanUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("materialCategoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    CategoryMapper categoryMapper;

    /**
     * 获得用户文本/图片素材的分类
     */
    @Override
    public List<CategoryDTO> findByUserIdAndType(Long userId, Integer type) {
        List<CategoryEntity> categoryEntityList = categoryMapper.findByUserIdAndType(userId, type);
        return MyBeanUtils.BeanBuilder(categoryEntityList, CategoryDTO.class);
    }

    /**
     * 添加用户文本/图片素材的分类
     */
    @Override
    public Void create(Long userId, Integer type, String categoryName) throws MyException {
        CategoryEntity categoryEntity = categoryMapper.findByCategoryName(userId, type, categoryName);
        if (categoryEntity != null) {
            throw new MyException(40000, "已存在");
        }
        categoryEntity = new CategoryEntity(
                null, userId, type, categoryName, new Date().getTime(), 0);
        categoryMapper.create(categoryEntity);
        return null;
    }

    /**
     * 更新用户文本/图片素材的分类
     */
    @Override
    public Void update(Long categoryId, String categoryName) {
        CategoryEntity categoryEntity = categoryMapper.findByCategoryId(categoryId);
        categoryEntity.setMaterialCategoryName(categoryName);
        categoryMapper.update(categoryEntity);
        return null;
    }

    /**
     * 删除用户文本/图片素材的分类
     */
    @Override
    public Void delete(Long categoryId) throws MyException {
        if (categoryMapper.delete(categoryId) != 1) {
            throw new MyException(40000, "删除失败");
        }
        return null;
    }
}
