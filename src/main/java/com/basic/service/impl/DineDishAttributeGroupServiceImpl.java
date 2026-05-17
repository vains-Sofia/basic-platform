package com.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.domain.PageResult;
import com.basic.domain.entity.DineAttributeGroup;
import com.basic.domain.entity.DineDish;
import com.basic.domain.entity.DineDishAttributeGroup;
import com.basic.domain.request.DineDishAttributeGroupPageRequest;
import com.basic.domain.request.DineDishAttributeGroupRequest;
import com.basic.domain.response.FindDineDishAttributeGroupResponse;
import com.basic.exception.CloudServiceException;
import com.basic.mapper.DineAttributeGroupMapper;
import com.basic.mapper.DineDishAttributeGroupMapper;
import com.basic.mapper.DineDishMapper;
import com.basic.service.DineDishAttributeGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 针对表【order_dish_attribute_group(菜品-属性组关联)】的数据库操作Service实现
 *
 * @author vains
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DineDishAttributeGroupServiceImpl extends ServiceImpl<DineDishAttributeGroupMapper, DineDishAttributeGroup>
        implements DineDishAttributeGroupService {

    private final DineDishMapper dineDishMapper;

    private final DineAttributeGroupMapper dineAttributeGroupMapper;

    @Override
    public List<FindDineDishAttributeGroupResponse> listAll() {
        LambdaQueryWrapper<DineDishAttributeGroup> wrapper = Wrappers.lambdaQuery(DineDishAttributeGroup.class)
                .orderByDesc(DineDishAttributeGroup::getCreateTime);
        return this.list(wrapper).stream()
                .map(e -> {
                    FindDineDishAttributeGroupResponse response = new FindDineDishAttributeGroupResponse();
                    BeanUtils.copyProperties(e, response);
                    return response;
                }).toList();
    }

    @Override
    public PageResult<FindDineDishAttributeGroupResponse> pageQuery(DineDishAttributeGroupPageRequest request) {
        LambdaQueryWrapper<DineDishAttributeGroup> wrapper = Wrappers.lambdaQuery(DineDishAttributeGroup.class)
                .eq(request.getDishId() != null, DineDishAttributeGroup::getDishId, request.getDishId())
                .eq(request.getGroupId() != null, DineDishAttributeGroup::getGroupId, request.getGroupId());

        Page<DineDishAttributeGroup> objectPage = Page.of(request.getCurrent(), request.getSize());
        Page<DineDishAttributeGroup> page = this.page(objectPage, wrapper);

        IPage<FindDineDishAttributeGroupResponse> converted = page.convert(e -> {
            FindDineDishAttributeGroupResponse response = new FindDineDishAttributeGroupResponse();
            BeanUtils.copyProperties(e, response);
            return response;
        });

        return PageResult.of(converted.getCurrent(), converted.getSize(), converted.getTotal(), converted.getRecords());
    }

    @Override
    public FindDineDishAttributeGroupResponse create(DineDishAttributeGroupRequest request) {
        this.validateDishAttributeGroup(request, null);

        DineDishAttributeGroup entity = new DineDishAttributeGroup();
        BeanUtils.copyProperties(request, entity);
        this.save(entity);

        if (log.isDebugEnabled()) {
            log.debug("创建菜品-属性组关联成功，ID：{}", entity.getId());
        }

        FindDineDishAttributeGroupResponse response = new FindDineDishAttributeGroupResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    public FindDineDishAttributeGroupResponse update(Long id, DineDishAttributeGroupRequest request) {
        DineDishAttributeGroup entity = this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("菜品-属性组关联不存在，ID：" + id));

        this.validateDishAttributeGroup(request, id);
        BeanUtils.copyProperties(request, entity);
        this.updateById(entity);

        if (log.isDebugEnabled()) {
            log.debug("更新菜品-属性组关联，ID：{}", entity.getId());
        }

        FindDineDishAttributeGroupResponse response = new FindDineDishAttributeGroupResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    public void delete(Long id) {
        this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("菜品-属性组关联不存在，ID：" + id));
        this.removeById(id);

        if (log.isDebugEnabled()) {
            log.debug("删除菜品-属性组关联，ID：{}", id);
        }
    }

    private void validateDishAttributeGroup(DineDishAttributeGroupRequest request, Long excludeId) {
        DineDish dish = dineDishMapper.selectById(request.getDishId());
        if (dish == null) {
            throw new CloudServiceException("菜品不存在，ID：" + request.getDishId());
        }

        DineAttributeGroup group = dineAttributeGroupMapper.selectById(request.getGroupId());
        if (group == null) {
            throw new CloudServiceException("属性组不存在，ID：" + request.getGroupId());
        }

        if (!Objects.equals(dish.getStoreId(), group.getStoreId())) {
            throw new CloudServiceException("菜品与属性组必须属于同一门店。");
        }

        LambdaQueryWrapper<DineDishAttributeGroup> wrapper = Wrappers.lambdaQuery(DineDishAttributeGroup.class)
                .eq(DineDishAttributeGroup::getDishId, request.getDishId())
                .eq(DineDishAttributeGroup::getGroupId, request.getGroupId())
                .ne(excludeId != null, DineDishAttributeGroup::getId, excludeId);
        if (this.exists(wrapper)) {
            throw new CloudServiceException("菜品已绑定该属性组。");
        }
    }
}
