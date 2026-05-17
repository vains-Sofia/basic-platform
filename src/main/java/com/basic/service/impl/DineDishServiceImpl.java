package com.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.domain.PageResult;
import com.basic.domain.entity.DineCategory;
import com.basic.domain.entity.DineDish;
import com.basic.domain.entity.DineDishAttributeGroup;
import com.basic.domain.entity.DineDishAttributeOption;
import com.basic.domain.request.DineDishPageRequest;
import com.basic.domain.request.DineDishRequest;
import com.basic.domain.response.FindDineDishResponse;
import com.basic.exception.CloudServiceException;
import com.basic.mapper.DineCategoryMapper;
import com.basic.mapper.DineDishAttributeGroupMapper;
import com.basic.mapper.DineDishAttributeOptionMapper;
import com.basic.mapper.DineDishMapper;
import com.basic.service.DineDishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

/**
 * 针对表【order_dish(菜品表)】的数据库操作Service实现
 *
 * @author vains
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DineDishServiceImpl extends ServiceImpl<DineDishMapper, DineDish>
        implements DineDishService {

    private final DineCategoryMapper dineCategoryMapper;

    private final DineDishAttributeGroupMapper dineDishAttributeGroupMapper;

    private final DineDishAttributeOptionMapper dineDishAttributeOptionMapper;

    @Override
    public List<FindDineDishResponse> listAll() {
        LambdaQueryWrapper<DineDish> wrapper = Wrappers.lambdaQuery(DineDish.class)
                .orderByDesc(DineDish::getCreateTime);
        return this.list(wrapper).stream()
                .map(e -> {
                    FindDineDishResponse response = new FindDineDishResponse();
                    BeanUtils.copyProperties(e, response);
                    return response;
                }).toList();
    }

    @Override
    public PageResult<FindDineDishResponse> pageQuery(DineDishPageRequest request) {
        LambdaQueryWrapper<DineDish> wrapper = Wrappers.lambdaQuery(DineDish.class)
                .like(!ObjectUtils.isEmpty(request.getKeyword()), DineDish::getName, request.getKeyword())
                .eq(request.getStoreId() != null, DineDish::getStoreId, request.getStoreId())
                .eq(request.getCategoryId() != null, DineDish::getCategoryId, request.getCategoryId());

        Page<DineDish> objectPage = Page.of(request.getCurrent(), request.getSize());
        Page<DineDish> page = this.page(objectPage, wrapper);

        IPage<FindDineDishResponse> converted = page.convert(e -> {
            FindDineDishResponse response = new FindDineDishResponse();
            BeanUtils.copyProperties(e, response);
            return response;
        });

        return PageResult.of(converted.getCurrent(), converted.getSize(), converted.getTotal(), converted.getRecords());
    }

    @Override
    public FindDineDishResponse create(DineDishRequest request) {
        this.validateDish(request, null);

        DineDish entity = new DineDish();
        BeanUtils.copyProperties(request, entity);
        this.save(entity);

        if (log.isDebugEnabled()) {
            log.debug("创建菜品成功，ID：{}，名称：{}", entity.getId(), entity.getName());
        }

        FindDineDishResponse response = new FindDineDishResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    public FindDineDishResponse update(Long id, DineDishRequest request) {
        DineDish entity = this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("菜品不存在，ID：" + id));

        this.validateDish(request, id);
        BeanUtils.copyProperties(request, entity);
        this.updateById(entity);

        if (log.isDebugEnabled()) {
            log.debug("更新菜品，ID：{}，名称：{}", entity.getId(), entity.getName());
        }

        FindDineDishResponse response = new FindDineDishResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        DineDish entity = this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("菜品不存在，ID：" + id));

        LambdaQueryWrapper<DineDishAttributeGroup> groupWrapper = Wrappers.lambdaQuery(DineDishAttributeGroup.class)
                .eq(DineDishAttributeGroup::getDishId, id);
        dineDishAttributeGroupMapper.delete(groupWrapper);

        LambdaQueryWrapper<DineDishAttributeOption> optionWrapper = Wrappers.lambdaQuery(DineDishAttributeOption.class)
                .eq(DineDishAttributeOption::getDishId, id);
        dineDishAttributeOptionMapper.delete(optionWrapper);

        this.removeById(id);

        if (log.isDebugEnabled()) {
            log.debug("删除菜品，ID：{}，名称：{}", id, entity.getName());
        }
    }

    private void validateDish(DineDishRequest request, Long excludeId) {
        DineCategory category = dineCategoryMapper.selectById(request.getCategoryId());
        if (category == null) {
            throw new CloudServiceException("菜品分类不存在，ID：" + request.getCategoryId());
        }
        if (!Objects.equals(category.getStoreId(), request.getStoreId())) {
            throw new CloudServiceException("菜品所属门店必须与分类所属门店一致。");
        }

        LambdaQueryWrapper<DineDish> wrapper = Wrappers.lambdaQuery(DineDish.class)
                .eq(DineDish::getStoreId, request.getStoreId())
                .eq(DineDish::getName, request.getName())
                .ne(excludeId != null, DineDish::getId, excludeId);
        if (this.exists(wrapper)) {
            throw new CloudServiceException("同一门店下菜品名称已存在：" + request.getName());
        }
    }
}
