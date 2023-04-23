package com.example.graduation.apps.material.mapper;

import com.example.graduation.apps.material.entity.MaterialEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MaterialMapper {
    @Select("""
            SELECT COUNT(*)
            FROM material
            WHERE material_category_id = #{materialId} AND deleted<>1""")
    Long count(Long materialId);

    @Select("""
            SELECT *
            FROM material
            WHERE material_id = #{materialId} AND deleted<>1""")
    MaterialEntity findByMaterialId(Long materialId);

    @Select("""
            SELECT *
            FROM material
            WHERE user_id = #{userId} AND material_type = #{type} AND material_category_id = #{categoryId} AND deleted<>1
            ORDER BY created_at DESC
            LIMIT #{offset},#{pageSize}""")
    List<MaterialEntity> findByUserIdAndTypeAndCategoryId(Long userId, Integer type, Long categoryId, Long offset, Long pageSize);

    @Insert("""
            INSERT INTO
            material (user_id, material_type, material_category_id, text, url, created_at, deleted)
            VALUES (#{userId},#{materialType},#{materialCategoryId},#{text},#{url},#{createdAt},#{deleted})""")
    @Options(useGeneratedKeys = true, keyProperty = "materialId", keyColumn = "material_id")
    Long create(MaterialEntity material);

    @Update("""
            UPDATE material
            SET text=#{text}
            WHERE material_id=#{materialId} AND deleted<>1""")
    Long update(MaterialEntity materialEntity);

    @Update("""
            UPDATE material
            SET deleted=1
            WHERE material_id=#{materialId} AND deleted<>1""")
    Long delete(Long materialId);
}
