package com.example.graduation.apps.materialCategory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    Long materialCategoryId;
    Long userId;
    Integer materialCategoryType;
    String materialCategoryName;
    Long createdAt;
    Integer deleted;
}
