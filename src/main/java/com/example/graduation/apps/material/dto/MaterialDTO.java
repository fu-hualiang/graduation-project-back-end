package com.example.graduation.apps.material.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDTO {
    Long materialId;
    Long userId;
    Integer materialType;
    Long materialCategoryId;
    String text;
    String url;
    Long createdAt;
    Integer deleted;
}
