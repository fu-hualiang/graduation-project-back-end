package com.example.graduation.apps.material.service;

import com.example.graduation.apps.material.dto.MaterialDTO;
import com.example.graduation.exception.MyException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MaterialService {
    Long count(Long categoryId);

    List<MaterialDTO> findByUserIdAndCategoryId(Long userId, Long categoryId, Long page, Long pageSize);

    Void create(Long userId, Long categoryId, String text, MultipartFile file, String filePath) throws MyException;

    Void update(Long materialId, String text);

    Void delete(Long materialId);
}
