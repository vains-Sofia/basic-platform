package com.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.domain.PageResult;
import com.basic.domain.entity.DineCategory;
import com.basic.domain.entity.DineDish;
import com.basic.domain.request.DineCategoryPageRequest;
import com.basic.domain.request.DineCategoryRequest;
import com.basic.domain.response.FindDineCategoryResponse;
import com.basic.exception.CloudServiceException;
import com.basic.mapper.DineCategoryMapper;
import com.basic.mapper.DineDishMapper;
import com.basic.service.DineCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 针对表【order_category(菜品分类表)】的数据库操作Service实现
 *
 * @author vains
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DineCategoryServiceImpl extends ServiceImpl<DineCategoryMapper, DineCategory>
        implements DineCategoryService {

    private final DineDishMapper dineDishMapper;

    @Override
    public List<FindDineCategoryResponse> listAll() {
        LambdaQueryWrapper<DineCategory> wrapper = Wrappers.lambdaQuery(DineCategory.class)
                .orderByDesc(DineCategory::getCreateTime);
        return this.list(wrapper).stream()
                .map(e -> {
                    FindDineCategoryResponse response = new FindDineCategoryResponse();
                    BeanUtils.copyProperties(e, response);
                    return response;
                }).toList();
    }

    @Override
    public PageResult<FindDineCategoryResponse> pageQuery(DineCategoryPageRequest request) {
        LambdaQueryWrapper<DineCategory> wrapper = Wrappers.lambdaQuery(DineCategory.class)
                .like(!ObjectUtils.isEmpty(request.getKeyword()), DineCategory::getName, request.getKeyword())
                .eq(request.getStoreId() != null, DineCategory::getStoreId, request.getStoreId());

        Page<DineCategory> objectPage = Page.of(request.getCurrent(), request.getSize());
        Page<DineCategory> page = this.page(objectPage, wrapper);

        IPage<FindDineCategoryResponse> converted = page.convert(e -> {
            FindDineCategoryResponse response = new FindDineCategoryResponse();
            BeanUtils.copyProperties(e, response);
            return response;
        });

        return PageResult.of(converted.getCurrent(), converted.getSize(), converted.getTotal(), converted.getRecords());
    }

    @Override
    public FindDineCategoryResponse create(DineCategoryRequest request) {
        this.validateCategoryName(request, null);

        DineCategory entity = new DineCategory();
        BeanUtils.copyProperties(request, entity);
        this.save(entity);

        if (log.isDebugEnabled()) {
            log.debug("创建菜品分类成功，ID：{}，名称：{}", entity.getId(), entity.getName());
        }

        FindDineCategoryResponse response = new FindDineCategoryResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    public FindDineCategoryResponse update(Long id, DineCategoryRequest request) {
        DineCategory entity = this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("菜品分类不存在，ID：" + id));

        this.validateCategoryName(request, id);
        BeanUtils.copyProperties(request, entity);
        this.updateById(entity);

        if (log.isDebugEnabled()) {
            log.debug("更新菜品分类，ID：{}，名称：{}", entity.getId(), entity.getName());
        }

        FindDineCategoryResponse response = new FindDineCategoryResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    public void delete(Long id) {
        DineCategory entity = this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("菜品分类不存在，ID：" + id));

        LambdaQueryWrapper<DineDish> dishWrapper = Wrappers.lambdaQuery(DineDish.class)
                .eq(DineDish::getCategoryId, id);
        if (dineDishMapper.exists(dishWrapper)) {
            throw new CloudServiceException("分类下存在菜品，不能删除。ID：" + id);
        }

        this.removeById(id);

        if (log.isDebugEnabled()) {
            log.debug("删除菜品分类，ID：{}，名称：{}", id, entity.getName());
        }
    }

    private void validateCategoryName(DineCategoryRequest request, Long excludeId) {
        LambdaQueryWrapper<DineCategory> wrapper = Wrappers.lambdaQuery(DineCategory.class)
                .eq(DineCategory::getStoreId, request.getStoreId())
                .eq(DineCategory::getName, request.getName())
                .ne(excludeId != null, DineCategory::getId, excludeId);
        if (this.exists(wrapper)) {
            throw new CloudServiceException("同一门店下分类名称已存在：" + request.getName());
        }
    }
}
