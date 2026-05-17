package com.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.basic.domain.PageResult;
import com.basic.domain.entity.DineAttributeOption;
import com.basic.domain.request.DineAttributeOptionPageRequest;
import com.basic.domain.request.DineAttributeOptionRequest;
import com.basic.domain.response.FindDineAttributeOptionResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 针对表【order_attribute_option(属性选项表)】的数据库操作Service
 *
 * @author vains
 */
public interface DineAttributeOptionService extends IService<DineAttributeOption> {

    /**
     * 查询所有订单属性选项
     *
     * @return 订单属性选项列表
     */
    List<FindDineAttributeOptionResponse> listAll();

    /**
     * 分页查询订单属性选项
     *
     * @param request 分页请求参数
     * @return 分页结果
     */
    PageResult<FindDineAttributeOptionResponse> pageQuery(@Valid DineAttributeOptionPageRequest request);
    /**
     * 创建订单属性选项
     *
     * @param request 订单属性选项请求参数
     * @return 创建的订单属性选项信息
     */
    FindDineAttributeOptionResponse create(@Valid DineAttributeOptionRequest request);

    /**
     * 更新订单属性选项
     *
     * @param id      订单属性选项ID
     * @param request 订单属性选项请求参数
     * @return 更新后的订单属性选项信息
     */
    FindDineAttributeOptionResponse update(@NotNull Long id, @Valid DineAttributeOptionRequest request);

    /**
     * 删除订单属性选项
     *
     * @param id 订单属性选项ID
     */
    void delete(Long id);
}