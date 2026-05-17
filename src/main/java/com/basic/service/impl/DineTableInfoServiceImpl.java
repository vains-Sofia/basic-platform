package com.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.domain.PageResult;
import com.basic.domain.entity.DineTableInfo;
import com.basic.domain.request.DineTableInfoPageRequest;
import com.basic.domain.request.DineTableInfoRequest;
import com.basic.domain.response.FindDineTableInfoResponse;
import com.basic.exception.CloudServiceException;
import com.basic.mapper.DineTableInfoMapper;
import com.basic.service.DineTableInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 针对表【order_table_info(桌台表)】的数据库操作Service实现
 *
 * @author vains
 */
@Slf4j
@Service
public class DineTableInfoServiceImpl extends ServiceImpl<DineTableInfoMapper, DineTableInfo>
        implements DineTableInfoService {

    @Override
    public List<FindDineTableInfoResponse> listAll() {
        LambdaQueryWrapper<DineTableInfo> wrapper = Wrappers.lambdaQuery(DineTableInfo.class)
                .orderByDesc(DineTableInfo::getCreateTime);
        return this.list(wrapper).stream()
                .map(e -> {
                    FindDineTableInfoResponse response = new FindDineTableInfoResponse();
                    BeanUtils.copyProperties(e, response);
                    return response;
                }).toList();
    }

    @Override
    public PageResult<FindDineTableInfoResponse> pageQuery(DineTableInfoPageRequest request) {
        LambdaQueryWrapper<DineTableInfo> wrapper = Wrappers.lambdaQuery(DineTableInfo.class)
                .like(!ObjectUtils.isEmpty(request.getKeyword()), DineTableInfo::getName, request.getKeyword())
                .eq(request.getStoreId() != null, DineTableInfo::getStoreId, request.getStoreId())
                .eq(request.getTableStatus() != null, DineTableInfo::getTableStatus, request.getTableStatus());

        Page<DineTableInfo> objectPage = Page.of(request.getCurrent(), request.getSize());
        Page<DineTableInfo> page = this.page(objectPage, wrapper);

        IPage<FindDineTableInfoResponse> converted = page.convert(e -> {
            FindDineTableInfoResponse response = new FindDineTableInfoResponse();
            BeanUtils.copyProperties(e, response);
            return response;
        });

        return PageResult.of(converted.getCurrent(), converted.getSize(), converted.getTotal(), converted.getRecords());
    }

    @Override
    public FindDineTableInfoResponse create(DineTableInfoRequest request) {
        this.validateTableName(request, null);

        DineTableInfo entity = new DineTableInfo();
        BeanUtils.copyProperties(request, entity);
        this.save(entity);

        if (log.isDebugEnabled()) {
            log.debug("创建桌位信息成功，ID：{}，名称：{}", entity.getId(), entity.getName());
        }

        FindDineTableInfoResponse response = new FindDineTableInfoResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    public FindDineTableInfoResponse update(Long id, DineTableInfoRequest request) {
        DineTableInfo entity = this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("桌位信息不存在，ID：" + id));

        this.validateTableName(request, id);
        BeanUtils.copyProperties(request, entity);
        this.updateById(entity);

        if (log.isDebugEnabled()) {
            log.debug("更新桌位信息，ID：{}，名称：{}", entity.getId(), entity.getName());
        }

        FindDineTableInfoResponse response = new FindDineTableInfoResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    public void delete(Long id) {
        DineTableInfo entity = this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("桌位信息不存在，ID：" + id));
        this.removeById(id);

        if (log.isDebugEnabled()) {
            log.debug("删除桌位信息，ID：{}，名称：{}", id, entity.getName());
        }
    }

    private void validateTableName(DineTableInfoRequest request, Long excludeId) {
        LambdaQueryWrapper<DineTableInfo> wrapper = Wrappers.lambdaQuery(DineTableInfo.class)
                .eq(DineTableInfo::getStoreId, request.getStoreId())
                .eq(DineTableInfo::getName, request.getName())
                .ne(excludeId != null, DineTableInfo::getId, excludeId);
        if (this.exists(wrapper)) {
            throw new CloudServiceException("同一门店下桌台名称已存在：" + request.getName());
        }
    }
}
