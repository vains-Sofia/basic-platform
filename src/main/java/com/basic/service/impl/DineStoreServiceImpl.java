package com.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.domain.PageResult;
import com.basic.domain.entity.DineStore;
import com.basic.domain.request.DineStorePageRequest;
import com.basic.domain.request.DineStoreRequest;
import com.basic.domain.response.FindDineStoreResponse;
import com.basic.exception.CloudServiceException;
import com.basic.mapper.DineStoreMapper;
import com.basic.service.DineStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

/**
 * 针对表【store_info(门店表)】的数据库操作Service实现
 *
 * @author vains
 */
@Slf4j
@Service
public class DineStoreServiceImpl extends ServiceImpl<DineStoreMapper, DineStore>
        implements DineStoreService {

    @Override
    public List<FindDineStoreResponse> listAll() {
        LambdaQueryWrapper<DineStore> wrapper = Wrappers.lambdaQuery(DineStore.class)
                .orderByDesc(DineStore::getCreateTime);
        return this.list(wrapper).stream()
                .map(e -> {
                    FindDineStoreResponse response = new FindDineStoreResponse();
                    BeanUtils.copyProperties(e, response);
                    return response;
                }).toList();
    }

    @Override
    public PageResult<FindDineStoreResponse> pageQuery(DineStorePageRequest request) {
        LambdaQueryWrapper<DineStore> wrapper = Wrappers.lambdaQuery(DineStore.class)
                .like(!ObjectUtils.isEmpty(request.getKeyword()), DineStore::getName, request.getKeyword())
                .eq(!ObjectUtils.isEmpty(request.getStatus()), DineStore::getStatus, request.getStatus());

        Page<DineStore> objectPage = Page.of(request.getCurrent(), request.getSize());
        Page<DineStore> page = this.page(objectPage, wrapper);

        IPage<FindDineStoreResponse> converted = page.convert(e -> {
            FindDineStoreResponse response = new FindDineStoreResponse();
            BeanUtils.copyProperties(e, response);
            return response;
        });

        return PageResult.of(converted.getCurrent(), converted.getSize(), converted.getTotal(), converted.getRecords());
    }

    @Override
    public Optional<DineStore> getOptById(Long id) {
        return Optional.ofNullable(this.getById(id));
    }

    @Override
    public FindDineStoreResponse create(DineStoreRequest request) {
        DineStore entity = new DineStore();
        BeanUtils.copyProperties(request, entity);
        this.save(entity);

        if (log.isDebugEnabled()) {
            log.debug("创建店铺信息成功，ID：{}，名称：{}", entity.getId(), entity.getName());
        }

        FindDineStoreResponse response = new FindDineStoreResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    public FindDineStoreResponse update(Long id, DineStoreRequest request) {
        DineStore entity = this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("店铺信息不存在，ID：" + id));

        BeanUtils.copyProperties(request, entity);
        this.updateById(entity);

        if (log.isDebugEnabled()) {
            log.debug("更新店铺信息，ID：{}，名称：{}", entity.getId(), entity.getName());
        }

        FindDineStoreResponse response = new FindDineStoreResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    public void delete(Long id) {
        DineStore entity = this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("店铺信息不存在，ID：" + id));
        this.removeById(id);

        if (log.isDebugEnabled()) {
            log.debug("删除店铺信息，ID：{}，名称：{}", id, entity.getName());
        }
    }
}