package com.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.basic.domain.PageResult;
import com.basic.domain.entity.SysDictType;
import com.basic.domain.request.SysDictTypePageRequest;
import com.basic.domain.request.SysDictTypeRequest;
import com.basic.domain.response.FindSysDictTypeResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 针对表【sys_dict_type(字典类型表)】的数据库操作Service
 *
 * @author vains
 */
public interface SysDictTypeService extends IService<SysDictType> {

    /**
     * 查询所有字典类型
     *
     * @return 字典类型列表
     */
    List<FindSysDictTypeResponse> listAll();

    /**
     * 分页查询字典类型
     *
     * @param request 分页请求参数
     * @return 分页结果
     */
    PageResult<FindSysDictTypeResponse> pageQuery(@Valid SysDictTypePageRequest request);

    /**
     * 创建字典类型
     *
     * @param request 字典类型请求参数
     * @return 创建的字典类型信息
     */
    FindSysDictTypeResponse create(@Valid SysDictTypeRequest request);

    /**
     * 更新字典类型
     *
     * @param id      字典类型 ID
     * @param request 字典类型请求参数
     * @return 更新后的字典类型信息
     */
    FindSysDictTypeResponse updateDictType(@NotNull Long id, @Valid SysDictTypeRequest request);

    /**
     * 删除字典类型
     *
     * @param id 字典类型 ID
     */
    void delete(Long id);
}
