package com.example.graduation.apps.materialCategory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    Long materialCategoryId;
    Long userId;
    Integer materialCategoryType;
    String materialCategoryName;
    Long createdAt;
    Integer deleted;
}
