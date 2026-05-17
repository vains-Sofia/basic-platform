package com.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.basic.domain.PageResult;
import com.basic.domain.entity.DineStore;
import com.basic.domain.request.DineStorePageRequest;
import com.basic.domain.request.DineStoreRequest;
import com.basic.domain.response.FindDineInfoResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * 针对表【store_info(门店表)】的数据库操作Service
 *
 * @author vains
 */
public interface DineStoreService extends IService<DineStore> {

    /**
     * 查询所有店铺信息
     *
     * @return 店铺信息列表
     */
    List<FindDineInfoResponse> listAll();

    /**
     * 分页查询店铺信息
     *
     * @param request 分页请求参数
     * @return 分页结果
     */
    PageResult<FindDineInfoResponse> pageQuery(@Valid DineStorePageRequest request);

    /**
     * 根据ID查询店铺信息
     *
     * @param id 门店ID
     * @return 店铺信息Optional
     */
    Optional<DineStore> getOptById(@NotNull Long id);

    /**
     * 创建店铺信息
     *
     * @param request 店铺信息请求参数
     * @return 创建的店铺信息
     */
    FindDineInfoResponse create(@Valid DineStoreRequest request);

    /**
     * 更新店铺信息
     *
     * @param id      门店ID
     * @param request 店铺信息请求参数
     * @return 更新后的店铺信息
     */
    FindDineInfoResponse update(@NotNull Long id, @Valid DineStoreRequest request);

    /**
     * 删除店铺信息
     *
     * @param id 门店ID
     */
    void delete(Long id);
}