package com.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.basic.domain.PageResult;
import com.basic.domain.entity.DineDishAttributeOption;
import com.basic.domain.request.DineDishAttributeOptionPageRequest;
import com.basic.domain.request.DineDishAttributeOptionRequest;
import com.basic.domain.response.FindDineDishAttributeOptionResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 针对表【order_dish_attribute_option(菜品-选项精细化配置)】的数据库操作Service
 *
 * @author vains
 */
public interface DineDishAttributeOptionService extends IService<DineDishAttributeOption> {

    /**
     * 查询所有菜品-选项精细化配置
     *
     * @return 菜品-选项精细化配置列表
     */
    List<FindDineDishAttributeOptionResponse> listAll();

    /**
     * 分页查询菜品-选项精细化配置
     *
     * @param request 分页请求参数
     * @return 分页结果
     */
    PageResult<FindDineDishAttributeOptionResponse> pageQuery(@Valid DineDishAttributeOptionPageRequest request);

    /**
     * 创建菜品-选项精细化配置
     *
     * @param request 菜品-选项精细化配置请求参数
     * @return 创建的菜品-选项精细化配置信息
     */
    FindDineDishAttributeOptionResponse create(@Valid DineDishAttributeOptionRequest request);

    /**
     * 更新菜品-选项精细化配置
     *
     * @param id      主键ID
     * @param request 菜品-选项精细化配置请求参数
     * @return 更新后的菜品-选项精细化配置信息
     */
    FindDineDishAttributeOptionResponse update(@NotNull Long id, @Valid DineDishAttributeOptionRequest request);

    /**
     * 删除菜品-选项精细化配置
     *
     * @param id 主键ID
     */
    void delete(Long id);
}