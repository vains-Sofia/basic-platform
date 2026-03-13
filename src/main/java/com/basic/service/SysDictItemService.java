package com.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.basic.domain.PageResult;
import com.basic.domain.entity.SysDictItem;
import com.basic.domain.request.SysDictItemPageRequest;
import com.basic.domain.request.SysDictItemRequest;
import com.basic.domain.response.FindSysDictItemResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 针对表【sys_dict_item(字典项表)】的数据库操作Service
 *
 * @author vains
 */
public interface SysDictItemService extends IService<SysDictItem> {

    /**
     * 分页查询字典项
     *
     * @param request 分页请求参数
     * @return 分页结果
     */
    PageResult<FindSysDictItemResponse> pageQuery(@Valid SysDictItemPageRequest request);

    /**
     * 根据字典类型编码查询字典项
     *
     * @param typeCode 字典类型编码
     * @return 字典项列表
     */
    List<FindSysDictItemResponse> listByType(@NotBlank String typeCode);

    /**
     * 创建字典项
     *
     * @param request 字典项请求参数
     * @return 创建的字典项信息
     */
    FindSysDictItemResponse create(@Valid SysDictItemRequest request);

    /**
     * 更新字典项
     *
     * @param id      字典项 ID
     * @param request 字典项请求参数
     * @return 更新后的字典项信息
     */
    FindSysDictItemResponse updateDictItem(@NotNull Long id, @Valid SysDictItemRequest request);
}
