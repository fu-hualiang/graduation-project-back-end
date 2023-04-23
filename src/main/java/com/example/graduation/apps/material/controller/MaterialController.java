package com.example.graduation.apps.material.controller;

import com.example.graduation.apps.material.dto.MaterialDTO;
import com.example.graduation.apps.material.form.RequestForm;
import com.example.graduation.apps.material.service.MaterialService;
import com.example.graduation.exception.MyException;
import com.example.graduation.utils.resultUtils.Result;
import com.example.graduation.utils.resultUtils.ResultUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("materials")
public class MaterialController {
    @Resource
    MaterialService textMaterialService;
    @Resource
    MaterialService pictureMaterialService;

    /**
     * 获得用户的素材
     */
    @GetMapping("")
    public Result<Map<String, Object>> findByUserIdAndCategoryId(Long userId, Integer type, Long categoryId, Long page, Long pageSize) throws MyException {
        List<MaterialDTO> materialDTOList;
        Long total;
        if (type == 0) {
            materialDTOList = textMaterialService.findByUserIdAndCategoryId(userId, categoryId, page, pageSize);
            total = textMaterialService.count(categoryId);
        } else {
            materialDTOList = pictureMaterialService.findByUserIdAndCategoryId(userId, categoryId, page, pageSize);
            total = pictureMaterialService.count(categoryId);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("list", materialDTOList);
        map.put("total", total);
        return ResultUtil.success(map);
    }

    /**
     * 添加用户的素材
     */
    @PostMapping(value = "")
    public Result<Void> create(@Nullable MultipartFile file, RequestForm requestForm, HttpServletRequest request) throws MyException {
        Long userId = requestForm.getUserId();
        Integer type = requestForm.getType();
        Long categoryId = requestForm.getCategoryId();
        String text = requestForm.getText();
        String filePath = request.getServletContext().getRealPath("/static/pictureMaterial/");
        System.out.println(filePath);
        if (type == 0) {
            textMaterialService.create(userId, categoryId, text, null, null);
        } else {
            pictureMaterialService.create(userId, categoryId, null, file, filePath);
        }
        return ResultUtil.success(null);
    }

    /**
     * 更新用户的素材
     */
    @PutMapping("{materialId}")
    public Result<Void> update(@PathVariable Long materialId, @RequestBody RequestForm requestForm) throws MyException {
        Integer type = requestForm.getType();
        String text = requestForm.getText();
        if (type == 0) {
            textMaterialService.update(materialId, text);
        } else {
            pictureMaterialService.update(materialId, text);
        }
        return ResultUtil.success(null);
    }

    /**
     * 删除用户的素材
     */
    @DeleteMapping("{materialId}")
    public Result<Void> delete(Integer type, @PathVariable Long materialId) throws MyException {
        if (type == 0) {
            textMaterialService.delete(materialId);
        } else {
            pictureMaterialService.delete(materialId);
        }
        return ResultUtil.success(null);
    }
}
