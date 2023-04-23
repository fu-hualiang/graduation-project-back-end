package com.example.graduation.apps.material.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialEntity {
    Long materialId;
    Long userId;
    Integer materialType;
    Long materialCategoryId;
    String text;
    String url;
    Long createdAt;
    Integer deleted;
}
