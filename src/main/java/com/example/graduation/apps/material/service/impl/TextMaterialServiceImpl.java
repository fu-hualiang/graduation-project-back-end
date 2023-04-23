package com.example.graduation.apps.material.service.impl;

import com.example.graduation.apps.material.dto.MaterialDTO;
import com.example.graduation.apps.material.entity.MaterialEntity;
import com.example.graduation.apps.material.mapper.MaterialMapper;
import com.example.graduation.apps.material.service.MaterialService;
import com.example.graduation.utils.MyBeanUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service("textMaterialService")
public class TextMaterialServiceImpl implements MaterialService {
    @Resource
    MaterialMapper materialMapper;

    @Override
    public Long count(Long categoryId) {
        return materialMapper.count(categoryId);
    }

    @Override
    public List<MaterialDTO> findByUserIdAndCategoryId(Long userId, Long categoryId, Long page, Long pageSize) {
        List<MaterialEntity> materialEntityList = materialMapper.findByUserIdAndTypeAndCategoryId(userId, 0, categoryId, (page - 1) * pageSize, pageSize);
        return MyBeanUtils.BeanBuilder(materialEntityList, MaterialDTO.class);
    }

    @Override
    public Void create(Long userId, Long categoryId, String text, MultipartFile file, String filePath) {
        MaterialEntity material = new MaterialEntity(
                null, userId, 0, categoryId, text, null, new Date().getTime(), 0
        );
        materialMapper.create(material);
        return null;
    }

    @Override
    public Void update(Long materialId, String text) {
        MaterialEntity materialEntity = materialMapper.findByMaterialId(materialId);
        materialEntity.setText(text);
        materialMapper.update(materialEntity);
        return null;
    }

    @Override
    public Void delete(Long materialId) {
        materialMapper.delete(materialId);
        return null;
    }
}
