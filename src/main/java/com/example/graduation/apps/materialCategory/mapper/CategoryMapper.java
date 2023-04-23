package com.example.graduation.apps.materialCategory.mapper;

import com.example.graduation.apps.materialCategory.entity.CategoryEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("""
            SELECT *
            FROM material_category
            WHERE material_category_id = #{categoryId} AND deleted<>1""")
    CategoryEntity findByCategoryId(Long categoryId);

    @Select("""
            SELECT *
            FROM material_category
            WHERE material_category_name = #{categoryName} AND material_category_type = #{type} AND deleted<>1""")
    CategoryEntity findByCategoryName(Long userId, Integer type, String categoryName);

    @Select("""
            SELECT *
            FROM material_category
            WHERE user_id = #{userId} AND material_category_type = #{type} AND deleted<>1""")
    List<CategoryEntity> findByUserIdAndType(Long userId, Integer type);

    @Insert("""
            INSERT INTO
            material_category (user_id, material_category_name, material_category_type, created_at, deleted)
            VALUES (#{userId},#{materialCategoryName},#{materialCategoryType},#{createdAt},#{deleted})""")
    @Options(useGeneratedKeys = true, keyProperty = "materialCategoryId", keyColumn = "material_category_id")
    Long create(CategoryEntity categoryEntity);

    @Update("""
            UPDATE material_category
            SET user_id=#{userId}, material_category_name=#{materialCategoryName}, material_category_type=#{materialCategoryType} ,deleted=#{deleted}
            WHERE material_category_id=#{materialCategoryId} AND deleted<>1""")
    Long update(CategoryEntity categoryEntity);

    @Update("""
            UPDATE material_category
            SET deleted=1
            WHERE material_category_id=#{categoryId} AND deleted<>1""")
    Long delete(Long categoryId);
}
