package com.example.graduation.apps.materialCategory.service;

import com.example.graduation.apps.materialCategory.dto.CategoryDTO;
import com.example.graduation.exception.MyException;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findByUserIdAndType(Long userId, Integer type);

    Void create(Long userId, Integer type, String categoryName) throws MyException;

    Void update(Long categoryId, String categoryName);

    Void delete(Long categoryId) throws MyException;
}
