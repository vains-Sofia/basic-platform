package com.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.basic.domain.PageResult;
import com.basic.domain.entity.DineTableBinding;
import com.basic.domain.request.DineTableBindingPageRequest;
import com.basic.domain.request.DineTableBindingRequest;
import com.basic.domain.response.FindDineTableBindingResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 针对表【order_table_binding(桌台绑定记录)】的数据库操作Service
 *
 * @author vains
 */
public interface DineTableBindingService extends IService<DineTableBinding> {

    /**
     * 查询所有订单桌位绑定
     *
     * @return 订单桌位绑定列表
     */
    List<FindDineTableBindingResponse> listAll();

    /**
     * 分页查询订单桌位绑定
     *
     * @param request 分页请求参数
     * @return 分页结果
     */
    PageResult<FindDineTableBindingResponse> pageQuery(@Valid DineTableBindingPageRequest request);

    /**
     * 创建订单桌位绑定
     *
     * @param request 订单桌位绑定请求参数
     * @return 创建的订单桌位绑定信息
     */
    FindDineTableBindingResponse create(@Valid DineTableBindingRequest request);

    /**
     * 更新订单桌位绑定
     *
     * @param id      主键ID
     * @param request 订单桌位绑定请求参数
     * @return 更新后的订单桌位绑定信息
     */
    FindDineTableBindingResponse update(@NotNull Long id, @Valid DineTableBindingRequest request);

    /**
     * 删除订单桌位绑定
     *
     * @param id 主键ID
     */
    void delete(Long id);
}