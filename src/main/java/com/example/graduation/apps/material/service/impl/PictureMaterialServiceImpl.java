package com.example.graduation.apps.material.service.impl;

import com.example.graduation.apps.material.dto.MaterialDTO;
import com.example.graduation.apps.material.entity.MaterialEntity;
import com.example.graduation.apps.material.mapper.MaterialMapper;
import com.example.graduation.apps.material.service.MaterialService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.MyBeanUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("pictureMaterialService")
public class PictureMaterialServiceImpl implements MaterialService {
    @Value("${static-path}" + "pictureMaterial/")
    String pictureMaterialPath;
    @Resource
    MaterialMapper materialMapper;

    public Long count(Long categoryId) {
        return materialMapper.count(categoryId);
    }

    @Override
    public List<MaterialDTO> findByUserIdAndCategoryId(Long userId, Long categoryId, Long page, Long pageSize) {
        List<MaterialEntity> materialEntityList = materialMapper.findByUserIdAndTypeAndCategoryId(userId, 1, categoryId, (page - 1) * pageSize, pageSize);
        for (MaterialEntity materialEntity : materialEntityList) {
            materialEntity.setUrl(pictureMaterialPath + materialEntity.getUrl());
        }
        return MyBeanUtils.BeanBuilder(materialEntityList, MaterialDTO.class);
    }

    @Override
    public Void create(Long userId, Long categoryId, String text, MultipartFile file, String filePath) throws MyException {
        // 检查目录
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 生成唯一文件名
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File picture = new File(filePath + fileName);
        // 保存文件
        try {
            file.transferTo(picture);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(40000, "文件上传失败");
        }
        // 文件路径存入数据库
        MaterialEntity material = new MaterialEntity(
                null, userId, 1, categoryId, file.getOriginalFilename(), fileName, new Date().getTime(), 0
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
