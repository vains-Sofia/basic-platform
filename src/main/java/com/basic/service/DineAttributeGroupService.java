package com.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.basic.domain.PageResult;
import com.basic.domain.entity.DineAttributeGroup;
import com.basic.domain.request.DineAttributeGroupPageRequest;
import com.basic.domain.request.DineAttributeGroupRequest;
import com.basic.domain.response.FindDineAttributeGroupResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 针对表【order_attribute_group(属性组表)】的数据库操作Service
 *
 * @author vains
 */
public interface DineAttributeGroupService extends IService<DineAttributeGroup> {

    /**
     * 查询所有订单属性组
     *
     * @return 订单属性组列表
     */
    List<FindDineAttributeGroupResponse> listAll();

    /**
     * 分页查询订单属性组
     *
     * @param request 分页请求参数
     * @return 分页结果
     */
    PageResult<FindDineAttributeGroupResponse> pageQuery(@Valid DineAttributeGroupPageRequest request);

    /**
     * 创建订单属性组
     *
     * @param request 订单属性组请求参数
     * @return 创建的订单属性组信息
     */
    FindDineAttributeGroupResponse create(@Valid DineAttributeGroupRequest request);

    /**
     * 更新订单属性组
     *
     * @param id      订单属性组ID
     * @param request 订单属性组请求参数
     * @return 更新后的订单属性组信息
     */
    FindDineAttributeGroupResponse update(@NotNull Long id, @Valid DineAttributeGroupRequest request);

    /**
     * 删除订单属性组
     *
     * @param id 订单属性组ID
     */
    void delete(Long id);
}