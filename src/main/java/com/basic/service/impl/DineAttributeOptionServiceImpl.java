package com.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.domain.PageResult;
import com.basic.domain.entity.DineAttributeGroup;
import com.basic.domain.entity.DineAttributeOption;
import com.basic.domain.entity.DineDishAttributeOption;
import com.basic.domain.request.DineAttributeOptionPageRequest;
import com.basic.domain.request.DineAttributeOptionRequest;
import com.basic.domain.response.FindDineAttributeOptionResponse;
import com.basic.exception.CloudServiceException;
import com.basic.mapper.DineAttributeGroupMapper;
import com.basic.mapper.DineAttributeOptionMapper;
import com.basic.mapper.DineDishAttributeOptionMapper;
import com.basic.service.DineAttributeOptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

/**
 * 针对表【order_attribute_option(属性选项表)】的数据库操作Service实现
 *
 * @author vains
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DineAttributeOptionServiceImpl extends ServiceImpl<DineAttributeOptionMapper, DineAttributeOption>
        implements DineAttributeOptionService {

    private final DineAttributeGroupMapper dineAttributeGroupMapper;

    private final DineDishAttributeOptionMapper dineDishAttributeOptionMapper;

    @Override
    public List<FindDineAttributeOptionResponse> listAll() {
        LambdaQueryWrapper<DineAttributeOption> wrapper = Wrappers.lambdaQuery(DineAttributeOption.class)
                .orderByDesc(DineAttributeOption::getCreateTime);
        return this.list(wrapper).stream()
                .map(e -> {
                    FindDineAttributeOptionResponse response = new FindDineAttributeOptionResponse();
                    BeanUtils.copyProperties(e, response);
                    return response;
                }).toList();
    }

    @Override
    public PageResult<FindDineAttributeOptionResponse> pageQuery(DineAttributeOptionPageRequest request) {
        LambdaQueryWrapper<DineAttributeOption> wrapper = Wrappers.lambdaQuery(DineAttributeOption.class)
                .like(!ObjectUtils.isEmpty(request.getKeyword()), DineAttributeOption::getName, request.getKeyword())
                .eq(request.getGroupId() != null, DineAttributeOption::getGroupId, request.getGroupId());

        Page<DineAttributeOption> objectPage = Page.of(request.getCurrent(), request.getSize());
        Page<DineAttributeOption> page = this.page(objectPage, wrapper);

        IPage<FindDineAttributeOptionResponse> converted = page.convert(e -> {
            FindDineAttributeOptionResponse response = new FindDineAttributeOptionResponse();
            BeanUtils.copyProperties(e, response);
            return response;
        });

        return PageResult.of(converted.getCurrent(), converted.getSize(), converted.getTotal(), converted.getRecords());
    }

    @Override
    public FindDineAttributeOptionResponse create(DineAttributeOptionRequest request) {
        this.validateAttributeOption(request, null, null);

        DineAttributeOption entity = new DineAttributeOption();
        BeanUtils.copyProperties(request, entity);
        this.save(entity);

        if (log.isDebugEnabled()) {
            log.debug("创建订单属性选项成功，ID：{}，名称：{}", entity.getId(), entity.getName());
        }

        FindDineAttributeOptionResponse response = new FindDineAttributeOptionResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    public FindDineAttributeOptionResponse update(Long id, DineAttributeOptionRequest request) {
        DineAttributeOption entity = this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("订单属性选项不存在，ID：" + id));

        this.validateAttributeOption(request, id, entity);
        BeanUtils.copyProperties(request, entity);
        this.updateById(entity);

        if (log.isDebugEnabled()) {
            log.debug("更新订单属性选项，ID：{}，名称：{}", entity.getId(), entity.getName());
        }

        FindDineAttributeOptionResponse response = new FindDineAttributeOptionResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    public void delete(Long id) {
        DineAttributeOption entity = this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("订单属性选项不存在，ID：" + id));
        this.removeById(id);

        if (log.isDebugEnabled()) {
            log.debug("删除订单属性选项，ID：{}，名称：{}", id, entity.getName());
        }
    }

    private void validateAttributeOption(DineAttributeOptionRequest request, Long excludeId, DineAttributeOption existing) {
        DineAttributeGroup group = dineAttributeGroupMapper.selectById(request.getGroupId());
        if (group == null) {
            throw new CloudServiceException("属性组不存在，ID：" + request.getGroupId());
        }

        if (existing != null && !Objects.equals(existing.getGroupId(), request.getGroupId())) {
            LambdaQueryWrapper<DineDishAttributeOption> bindingWrapper = Wrappers.lambdaQuery(DineDishAttributeOption.class)
                    .eq(DineDishAttributeOption::getOptionId, existing.getId());
            if (dineDishAttributeOptionMapper.exists(bindingWrapper)) {
                throw new CloudServiceException("属性选项已被菜品绑定，不能修改所属属性组。ID：" + existing.getId());
            }
        }

        LambdaQueryWrapper<DineAttributeOption> wrapper = Wrappers.lambdaQuery(DineAttributeOption.class)
                .eq(DineAttributeOption::getGroupId, request.getGroupId())
                .eq(DineAttributeOption::getName, request.getName())
                .ne(excludeId != null, DineAttributeOption::getId, excludeId);
        if (this.exists(wrapper)) {
            throw new CloudServiceException("同一属性组下选项名称已存在：" + request.getName());
        }
    }
}
