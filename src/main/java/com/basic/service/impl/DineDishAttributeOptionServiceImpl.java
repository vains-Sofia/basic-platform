package com.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.domain.PageResult;
import com.basic.domain.entity.DineAttributeGroup;
import com.basic.domain.entity.DineAttributeOption;
import com.basic.domain.entity.DineDish;
import com.basic.domain.entity.DineDishAttributeOption;
import com.basic.domain.request.DineDishAttributeOptionPageRequest;
import com.basic.domain.request.DineDishAttributeOptionRequest;
import com.basic.domain.response.FindDineDishAttributeOptionResponse;
import com.basic.exception.CloudServiceException;
import com.basic.mapper.DineAttributeGroupMapper;
import com.basic.mapper.DineAttributeOptionMapper;
import com.basic.mapper.DineDishAttributeOptionMapper;
import com.basic.mapper.DineDishMapper;
import com.basic.service.DineDishAttributeOptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 针对表【order_dish_attribute_option(菜品-选项精细化配置)】的数据库操作Service实现
 *
 * @author vains
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DineDishAttributeOptionServiceImpl extends ServiceImpl<DineDishAttributeOptionMapper, DineDishAttributeOption>
        implements DineDishAttributeOptionService {

    private final DineDishMapper dineDishMapper;

    private final DineAttributeOptionMapper dineAttributeOptionMapper;

    private final DineAttributeGroupMapper dineAttributeGroupMapper;

    @Override
    public List<FindDineDishAttributeOptionResponse> listAll() {
        LambdaQueryWrapper<DineDishAttributeOption> wrapper = Wrappers.lambdaQuery(DineDishAttributeOption.class)
                .orderByDesc(DineDishAttributeOption::getCreateTime);
        return this.list(wrapper).stream()
                .map(e -> {
                    FindDineDishAttributeOptionResponse response = new FindDineDishAttributeOptionResponse();
                    BeanUtils.copyProperties(e, response);
                    return response;
                }).toList();
    }

    @Override
    public PageResult<FindDineDishAttributeOptionResponse> pageQuery(DineDishAttributeOptionPageRequest request) {
        LambdaQueryWrapper<DineDishAttributeOption> wrapper = Wrappers.lambdaQuery(DineDishAttributeOption.class)
                .eq(request.getDishId() != null, DineDishAttributeOption::getDishId, request.getDishId())
                .eq(request.getOptionId() != null, DineDishAttributeOption::getOptionId, request.getOptionId());

        Page<DineDishAttributeOption> objectPage = Page.of(request.getCurrent(), request.getSize());
        Page<DineDishAttributeOption> page = this.page(objectPage, wrapper);

        IPage<FindDineDishAttributeOptionResponse> converted = page.convert(e -> {
            FindDineDishAttributeOptionResponse response = new FindDineDishAttributeOptionResponse();
            BeanUtils.copyProperties(e, response);
            return response;
        });

        return PageResult.of(converted.getCurrent(), converted.getSize(), converted.getTotal(), converted.getRecords());
    }

    @Override
    public FindDineDishAttributeOptionResponse create(DineDishAttributeOptionRequest request) {
        this.validateDishAttributeOption(request, null);

        DineDishAttributeOption entity = new DineDishAttributeOption();
        BeanUtils.copyProperties(request, entity);
        this.save(entity);

        if (log.isDebugEnabled()) {
            log.debug("创建菜品-选项精细化配置成功，ID：{}", entity.getId());
        }

        FindDineDishAttributeOptionResponse response = new FindDineDishAttributeOptionResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    public FindDineDishAttributeOptionResponse update(Long id, DineDishAttributeOptionRequest request) {
        DineDishAttributeOption entity = this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("菜品-选项精细化配置不存在，ID：" + id));

        this.validateDishAttributeOption(request, id);
        BeanUtils.copyProperties(request, entity);
        this.updateById(entity);

        if (log.isDebugEnabled()) {
            log.debug("更新菜品-选项精细化配置，ID：{}", entity.getId());
        }

        FindDineDishAttributeOptionResponse response = new FindDineDishAttributeOptionResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    public void delete(Long id) {
        this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("菜品-选项精细化配置不存在，ID：" + id));
        this.removeById(id);

        if (log.isDebugEnabled()) {
            log.debug("删除菜品-选项精细化配置，ID：{}", id);
        }
    }

    private void validateDishAttributeOption(DineDishAttributeOptionRequest request, Long excludeId) {
        DineDish dish = dineDishMapper.selectById(request.getDishId());
        if (dish == null) {
            throw new CloudServiceException("菜品不存在，ID：" + request.getDishId());
        }

        DineAttributeOption option = dineAttributeOptionMapper.selectById(request.getOptionId());
        if (option == null) {
            throw new CloudServiceException("属性选项不存在，ID：" + request.getOptionId());
        }

        DineAttributeGroup group = dineAttributeGroupMapper.selectById(option.getGroupId());
        if (group == null) {
            throw new CloudServiceException("属性选项所属属性组不存在，ID：" + option.getGroupId());
        }

        if (!Objects.equals(dish.getStoreId(), group.getStoreId())) {
            throw new CloudServiceException("菜品与属性选项所属属性组必须属于同一门店。");
        }

        LambdaQueryWrapper<DineDishAttributeOption> wrapper = Wrappers.lambdaQuery(DineDishAttributeOption.class)
                .eq(DineDishAttributeOption::getDishId, request.getDishId())
                .eq(DineDishAttributeOption::getOptionId, request.getOptionId())
                .ne(excludeId != null, DineDishAttributeOption::getId, excludeId);
        if (this.exists(wrapper)) {
            throw new CloudServiceException("菜品已配置该属性选项。");
        }
    }
}
