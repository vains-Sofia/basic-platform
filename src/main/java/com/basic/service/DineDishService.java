package com.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.basic.domain.PageResult;
import com.basic.domain.entity.DineDish;
import com.basic.domain.request.DineDishPageRequest;
import com.basic.domain.request.DineDishRequest;
import com.basic.domain.response.FindDineDishResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 针对表【order_dish(菜品表)】的数据库操作Service
 *
 * @author vains
 */
public interface DineDishService extends IService<DineDish> {

    /**
     * 查询所有菜品
     *
     * @return 菜品列表
     */
    List<FindDineDishResponse> listAll();

    /**
     * 分页查询菜品
     *
     * @param request 分页请求参数
     * @return 分页结果
     */
    PageResult<FindDineDishResponse> pageQuery(@Valid DineDishPageRequest request);

    /**
     * 创建菜品
     *
     * @param request 菜品请求参数
     * @return 创建的菜品信息
     */
    FindDineDishResponse create(@Valid DineDishRequest request);

    /**
     * 更新菜品
     *
     * @param id      菜品ID
     * @param request 菜品请求参数
     * @return 更新后的菜品信息
     */
    FindDineDishResponse update(@NotNull Long id, @Valid DineDishRequest request);

    /**
     * 删除菜品
     *
     * @param id 菜品ID
     */
    void delete(Long id);
}