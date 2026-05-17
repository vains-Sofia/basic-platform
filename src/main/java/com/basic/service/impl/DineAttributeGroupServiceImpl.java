package com.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.domain.PageResult;
import com.basic.domain.entity.DineAttributeGroup;
import com.basic.domain.entity.DineAttributeOption;
import com.basic.domain.entity.DineDishAttributeGroup;
import com.basic.domain.request.DineAttributeGroupPageRequest;
import com.basic.domain.request.DineAttributeGroupRequest;
import com.basic.domain.response.FindDineAttributeGroupResponse;
import com.basic.exception.CloudServiceException;
import com.basic.mapper.DineAttributeGroupMapper;
import com.basic.mapper.DineAttributeOptionMapper;
import com.basic.mapper.DineDishAttributeGroupMapper;
import com.basic.service.DineAttributeGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 针对表【order_attribute_group(属性组表)】的数据库操作Service实现
 *
 * @author vains
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DineAttributeGroupServiceImpl extends ServiceImpl<DineAttributeGroupMapper, DineAttributeGroup>
        implements DineAttributeGroupService {

    private final DineAttributeOptionMapper dineAttributeOptionMapper;

    private final DineDishAttributeGroupMapper dineDishAttributeGroupMapper;

    @Override
    public List<FindDineAttributeGroupResponse> listAll() {
        LambdaQueryWrapper<DineAttributeGroup> wrapper = Wrappers.lambdaQuery(DineAttributeGroup.class)
                .orderByDesc(DineAttributeGroup::getCreateTime);
        return this.list(wrapper).stream()
                .map(e -> {
                    FindDineAttributeGroupResponse response = new FindDineAttributeGroupResponse();
                    BeanUtils.copyProperties(e, response);
                    return response;
                }).toList();
    }

    @Override
    public PageResult<FindDineAttributeGroupResponse> pageQuery(DineAttributeGroupPageRequest request) {
        LambdaQueryWrapper<DineAttributeGroup> wrapper = Wrappers.lambdaQuery(DineAttributeGroup.class)
                .like(!ObjectUtils.isEmpty(request.getKeyword()), DineAttributeGroup::getName, request.getKeyword())
                .eq(request.getStoreId() != null, DineAttributeGroup::getStoreId, request.getStoreId());

        Page<DineAttributeGroup> objectPage = Page.of(request.getCurrent(), request.getSize());
        Page<DineAttributeGroup> page = this.page(objectPage, wrapper);

        IPage<FindDineAttributeGroupResponse> converted = page.convert(e -> {
            FindDineAttributeGroupResponse response = new FindDineAttributeGroupResponse();
            BeanUtils.copyProperties(e, response);
            return response;
        });

        return PageResult.of(converted.getCurrent(), converted.getSize(), converted.getTotal(), converted.getRecords());
    }

    @Override
    public FindDineAttributeGroupResponse create(DineAttributeGroupRequest request) {
        this.validateGroupName(request, null);

        DineAttributeGroup entity = new DineAttributeGroup();
        BeanUtils.copyProperties(request, entity);
        this.save(entity);

        if (log.isDebugEnabled()) {
            log.debug("创建订单属性组成功，ID：{}，名称：{}", entity.getId(), entity.getName());
        }

        FindDineAttributeGroupResponse response = new FindDineAttributeGroupResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    public FindDineAttributeGroupResponse update(Long id, DineAttributeGroupRequest request) {
        DineAttributeGroup entity = this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("订单属性组不存在，ID：" + id));

        this.validateGroupName(request, id);
        BeanUtils.copyProperties(request, entity);
        this.updateById(entity);

        if (log.isDebugEnabled()) {
            log.debug("更新订单属性组，ID：{}，名称：{}", entity.getId(), entity.getName());
        }

        FindDineAttributeGroupResponse response = new FindDineAttributeGroupResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    public void delete(Long id) {
        DineAttributeGroup entity = this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("订单属性组不存在，ID：" + id));

        LambdaQueryWrapper<DineAttributeOption> optionWrapper = Wrappers.lambdaQuery(DineAttributeOption.class)
                .eq(DineAttributeOption::getGroupId, id);
        if (dineAttributeOptionMapper.exists(optionWrapper)) {
            throw new CloudServiceException("属性组下存在选项，不能删除。ID：" + id);
        }

        LambdaQueryWrapper<DineDishAttributeGroup> dishGroupWrapper = Wrappers.lambdaQuery(DineDishAttributeGroup.class)
                .eq(DineDishAttributeGroup::getGroupId, id);
        if (dineDishAttributeGroupMapper.exists(dishGroupWrapper)) {
            throw new CloudServiceException("属性组已被菜品绑定，不能删除。ID：" + id);
        }

        this.removeById(id);

        if (log.isDebugEnabled()) {
            log.debug("删除订单属性组，ID：{}，名称：{}", id, entity.getName());
        }
    }

    private void validateGroupName(DineAttributeGroupRequest request, Long excludeId) {
        LambdaQueryWrapper<DineAttributeGroup> wrapper = Wrappers.lambdaQuery(DineAttributeGroup.class)
                .eq(DineAttributeGroup::getStoreId, request.getStoreId())
                .eq(DineAttributeGroup::getName, request.getName())
                .ne(excludeId != null, DineAttributeGroup::getId, excludeId);
        if (this.exists(wrapper)) {
            throw new CloudServiceException("同一门店下属性组名称已存在：" + request.getName());
        }
    }
}
