package com.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.basic.domain.PageResult;
import com.basic.domain.entity.DineCategory;
import com.basic.domain.request.DineCategoryPageRequest;
import com.basic.domain.request.DineCategoryRequest;
import com.basic.domain.response.FindDineCategoryResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 针对表【order_category(菜品分类表)】的数据库操作Service
 *
 * @author vains
 */
public interface DineCategoryService extends IService<DineCategory> {

    /**
     * 查询所有菜品分类
     *
     * @return 菜品分类列表
     */
    List<FindDineCategoryResponse> listAll();

    /**
     * 分页查询菜品分类
     *
     * @param request 分页请求参数
     * @return 分页结果
     */
    PageResult<FindDineCategoryResponse> pageQuery(@Valid DineCategoryPageRequest request);

    /**
     * 创建菜品分类
     *
     * @param request 菜品分类请求参数
     * @return 创建的菜品分类信息
     */
    FindDineCategoryResponse create(@Valid DineCategoryRequest request);

    /**
     * 更新菜品分类
     *
     * @param id      菜品分类ID
     * @param request 菜品分类请求参数
     * @return 更新后的菜品分类信息
     */
    FindDineCategoryResponse update(@NotNull Long id, @Valid DineCategoryRequest request);

    /**
     * 删除菜品分类
     *
     * @param id 菜品分类ID
     */
    void delete(Long id);
}