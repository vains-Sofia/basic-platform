package com.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.basic.domain.PageResult;
import com.basic.domain.entity.DineTableInfo;
import com.basic.domain.request.DineTableInfoPageRequest;
import com.basic.domain.request.DineTableInfoRequest;
import com.basic.domain.response.FindDineTableInfoResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 针对表【order_table_info(桌台表)】的数据库操作Service
 *
 * @author vains
 */
public interface DineTableInfoService extends IService<DineTableInfo> {

    /**
     * 查询所有订单桌位信息
     *
     * @return 订单桌位信息列表
     */
    List<FindDineTableInfoResponse> listAll();

    /**
     * 分页查询订单桌位信息
     *
     * @param request 分页请求参数
     * @return 分页结果
     */
    PageResult<FindDineTableInfoResponse> pageQuery(@Valid DineTableInfoPageRequest request);

    /**
     * 创建订单桌位信息
     *
     * @param request 订单桌位信息请求参数
     * @return 创建的订单桌位信息
     */
    FindDineTableInfoResponse create(@Valid DineTableInfoRequest request);

    /**
     * 更新订单桌位信息
     *
     * @param id      桌台ID
     * @param request 订单桌位信息请求参数
     * @return 更新后的订单桌位信息
     */
    FindDineTableInfoResponse update(@NotNull Long id, @Valid DineTableInfoRequest request);

    /**
     * 删除订单桌位信息
     *
     * @param id 桌台ID
     */
    void delete(Long id);
}