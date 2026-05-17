package com.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.domain.PageResult;
import com.basic.domain.entity.DineTableBinding;
import com.basic.domain.entity.DineTableInfo;
import com.basic.domain.request.DineTableBindingPageRequest;
import com.basic.domain.request.DineTableBindingRequest;
import com.basic.domain.response.FindDineTableBindingResponse;
import com.basic.enums.StatusEnum;
import com.basic.exception.CloudServiceException;
import com.basic.mapper.DineTableBindingMapper;
import com.basic.mapper.DineTableInfoMapper;
import com.basic.service.DineTableBindingService;
import com.basic.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 针对表【order_table_binding(桌台绑定记录)】的数据库操作Service实现
 *
 * @author vains
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DineTableBindingServiceImpl extends ServiceImpl<DineTableBindingMapper, DineTableBinding>
        implements DineTableBindingService {

    private final DineTableInfoMapper dineTableInfoMapper;

    @Override
    public List<FindDineTableBindingResponse> listAll() {
        LambdaQueryWrapper<DineTableBinding> wrapper = Wrappers.lambdaQuery(DineTableBinding.class)
                .orderByDesc(DineTableBinding::getCreateTime);
        return this.list(wrapper).stream()
                .map(e -> {
                    FindDineTableBindingResponse response = new FindDineTableBindingResponse();
                    BeanUtils.copyProperties(e, response);
                    return response;
                }).toList();
    }

    @Override
    public PageResult<FindDineTableBindingResponse> pageQuery(DineTableBindingPageRequest request) {
        LambdaQueryWrapper<DineTableBinding> wrapper = Wrappers.lambdaQuery(DineTableBinding.class)
                .eq(request.getTableId() != null, DineTableBinding::getTableId, request.getTableId())
                .eq(request.getUserId() != null, DineTableBinding::getUserId, request.getUserId())
                .eq(!ObjectUtils.isEmpty(request.getStatus()), DineTableBinding::getStatus, request.getStatus());

        Page<DineTableBinding> objectPage = Page.of(request.getCurrent(), request.getSize());
        Page<DineTableBinding> page = this.page(objectPage, wrapper);

        IPage<FindDineTableBindingResponse> converted = page.convert(e -> {
            FindDineTableBindingResponse response = new FindDineTableBindingResponse();
            BeanUtils.copyProperties(e, response);
            return response;
        });

        return PageResult.of(converted.getCurrent(), converted.getSize(), converted.getTotal(), converted.getRecords());
    }

    @Override
    public FindDineTableBindingResponse create(DineTableBindingRequest request) {
        DineTableInfo tableInfo = dineTableInfoMapper.selectById(request.getTableId());
        if (tableInfo == null) {
            throw new CloudServiceException("桌台不存在，ID：" + request.getTableId());
        }
        this.validateActiveBinding(request.getTableId(), null);

        String loginUserId = SecurityUtils.getLoginUserId();
        if (loginUserId == null) {
            throw new CloudServiceException("当前登录用户不存在，无法创建桌台绑定。");
        }

        DineTableBinding entity = new DineTableBinding();
        entity.setTableId(request.getTableId());
        entity.setUserId(Long.valueOf(loginUserId));
        entity.setStatus(StatusEnum.ENABLE);
        entity.setBindTime(LocalDateTime.now());
        entity.setUnbindTime(null);
        this.save(entity);

        if (log.isDebugEnabled()) {
            log.debug("创建桌位绑定成功，ID：{}", entity.getId());
        }

        FindDineTableBindingResponse response = new FindDineTableBindingResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    public FindDineTableBindingResponse update(Long id, DineTableBindingRequest request) {
        DineTableBinding entity = this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("桌位绑定不存在，ID：" + id));

        DineTableInfo tableInfo = dineTableInfoMapper.selectById(request.getTableId());
        if (tableInfo == null) {
            throw new CloudServiceException("桌台不存在，ID：" + request.getTableId());
        }

        StatusEnum status = request.getStatus() == null ? entity.getStatus() : request.getStatus();
        if (StatusEnum.ENABLE.equals(status)) {
            this.validateActiveBinding(request.getTableId(), id);
        }

        entity.setTableId(request.getTableId());
        entity.setStatus(status);
        if (StatusEnum.ENABLE.equals(status)) {
            entity.setBindTime(entity.getBindTime() == null ? LocalDateTime.now() : entity.getBindTime());
            entity.setUnbindTime(null);
        } else {
            entity.setUnbindTime(LocalDateTime.now());
        }
        this.updateById(entity);

        if (log.isDebugEnabled()) {
            log.debug("更新桌位绑定，ID：{}", entity.getId());
        }

        FindDineTableBindingResponse response = new FindDineTableBindingResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    @Override
    public void delete(Long id) {
        this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("桌位绑定不存在，ID：" + id));
        this.removeById(id);

        if (log.isDebugEnabled()) {
            log.debug("删除桌位绑定，ID：{}", id);
        }
    }

    private void validateActiveBinding(Long tableId, Long excludeId) {
        LambdaQueryWrapper<DineTableBinding> wrapper = Wrappers.lambdaQuery(DineTableBinding.class)
                .eq(DineTableBinding::getTableId, tableId)
                .eq(DineTableBinding::getStatus, StatusEnum.ENABLE)
                .ne(excludeId != null, DineTableBinding::getId, excludeId);
        if (this.exists(wrapper)) {
            throw new CloudServiceException("该桌台已存在绑定中的记录，不能重复绑定。ID：" + tableId);
        }
    }
}
