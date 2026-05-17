package com.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.basic.domain.PageResult;
import com.basic.domain.entity.DineDishAttributeGroup;
import com.basic.domain.request.DineDishAttributeGroupPageRequest;
import com.basic.domain.request.DineDishAttributeGroupRequest;
import com.basic.domain.response.FindDineDishAttributeGroupResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 针对表【order_dish_attribute_group(菜品-属性组关联)】的数据库操作Service
 *
 * @author vains
 */
public interface DineDishAttributeGroupService extends IService<DineDishAttributeGroup> {

    /**
     * 查询所有菜品-属性组关联
     *
     * @return 菜品-属性组关联列表
     */
    List<FindDineDishAttributeGroupResponse> listAll();

    /**
     * 分页查询菜品-属性组关联
     *
     * @param request 分页请求参数
     * @return 分页结果
     */
    PageResult<FindDineDishAttributeGroupResponse> pageQuery(@Valid DineDishAttributeGroupPageRequest request);

    /**
     * 创建菜品-属性组关联
     *
     * @param request 菜品-属性组关联请求参数
     * @return 创建的菜品-属性组关联信息
     */
    FindDineDishAttributeGroupResponse create(@Valid DineDishAttributeGroupRequest request);

    /**
     * 更新菜品-属性组关联
     *
     * @param id      主键ID
     * @param request 菜品-属性组关联请求参数
     * @return 更新后的菜品-属性组关联信息
     */
    FindDineDishAttributeGroupResponse update(@NotNull Long id, @Valid DineDishAttributeGroupRequest request);

    /**
     * 删除菜品-属性组关联
     *
     * @param id 主键ID
     */
    void delete(Long id);
}