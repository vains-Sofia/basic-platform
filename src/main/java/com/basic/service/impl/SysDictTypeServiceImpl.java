package com.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.domain.PageResult;
import com.basic.domain.entity.SysDictItem;
import com.basic.domain.entity.SysDictType;
import com.basic.domain.request.SysDictTypePageRequest;
import com.basic.domain.request.SysDictTypeRequest;
import com.basic.domain.response.FindSysDictTypeResponse;
import com.basic.exception.CloudServiceException;
import com.basic.mapper.SysDictItemMapper;
import com.basic.mapper.SysDictTypeMapper;
import com.basic.service.SysDictTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

/**
 * 针对表【sys_dict_type(字典类型表)】的数据库操作Service实现
 *
 * @author vains
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType>
        implements SysDictTypeService {

    private final SysDictItemMapper sysDictItemMapper;

    @Override
    public List<FindSysDictTypeResponse> listAll() {
        LambdaQueryWrapper<SysDictType> wrapper = Wrappers.lambdaQuery(SysDictType.class).orderByDesc(SysDictType::getCreateTime);
        return this.list(wrapper)
                .stream()
                .map(e -> {
                    FindSysDictTypeResponse response = new FindSysDictTypeResponse();
                    BeanUtils.copyProperties(e, response);
                    return response;
                }).toList();
    }

    @Override
    public PageResult<FindSysDictTypeResponse> pageQuery(SysDictTypePageRequest request) {
        // 条件构造器
        LambdaQueryWrapper<SysDictType> wrapper = Wrappers.lambdaQuery(SysDictType.class)
                .like(!ObjectUtils.isEmpty(request.getKeyword()), SysDictType::getName, request.getKeyword())
                .or()
                .like(!ObjectUtils.isEmpty(request.getKeyword()), SysDictType::getTypeCode, request.getKeyword());

        // 分页
        Page<SysDictType> objectPage = Page.of(request.getCurrent(), request.getSize());

        Page<SysDictType> dictTypesPage = this.page(objectPage, wrapper);

        // 转为响应 bean
        IPage<FindSysDictTypeResponse> converted = dictTypesPage.convert(e -> {
            FindSysDictTypeResponse dictTypeResponse = new FindSysDictTypeResponse();
            BeanUtils.copyProperties(e, dictTypeResponse);
            return dictTypeResponse;
        });

        return PageResult.of(converted.getCurrent(), converted.getSize(), converted.getTotal(), converted.getRecords());
    }

    @Override
    public FindSysDictTypeResponse create(SysDictTypeRequest request) {
        // 检查字典类型编码是否已存在
        Wrapper<SysDictType> wrapper = Wrappers.lambdaQuery(SysDictType.class)
                .eq(SysDictType::getTypeCode, request.getTypeCode());
        Optional<SysDictType> existingDictType = this.getOneOpt(wrapper);
        if (existingDictType.isPresent()) {
            throw new CloudServiceException("字典类型编码已存在：" + request.getTypeCode());
        }

        SysDictType dictType = new SysDictType();
        BeanUtils.copyProperties(request, dictType);

        this.save(dictType);

        if (log.isDebugEnabled()) {
            log.debug("创建字典类型成功，ID：{}，类型编码：{}", dictType.getId(), dictType.getTypeCode());
        }

        FindSysDictTypeResponse response = new FindSysDictTypeResponse();
        BeanUtils.copyProperties(dictType, response);
        return response;
    }

    @Override
    public FindSysDictTypeResponse updateDictType(Long id, SysDictTypeRequest request) {
        SysDictType existingDictType = this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("字典类型不存在，ID：" + id));

        // 检查字典类型编码是否已被其他记录使用
        Wrapper<SysDictType> wrapper = Wrappers.lambdaQuery(SysDictType.class)
                .eq(SysDictType::getTypeCode, request.getTypeCode());
        Optional<SysDictType> dictTypeByCode = this.getOneOpt(wrapper);
        if (dictTypeByCode.isPresent() && !dictTypeByCode.get().getId().equals(id)) {
            throw new CloudServiceException("字典类型编码已存在：" + request.getTypeCode());
        }

        // 更新字典类型信息
        existingDictType.setTypeCode(request.getTypeCode());
        existingDictType.setName(request.getName());
        existingDictType.setDescription(request.getDescription());
        existingDictType.setStatus(request.getStatus());

        this.updateById(existingDictType);

        if (log.isDebugEnabled()) {
            log.debug("更新字典类型，ID：{}，类型编码：{}", existingDictType.getId(), existingDictType.getTypeCode());
        }

        // 更新相关的字典项等
        LambdaQueryWrapper<SysDictItem> itemWrapper = Wrappers.lambdaQuery(SysDictItem.class)
                .eq(SysDictItem::getTypeCode, existingDictType.getTypeCode())
                .orderByAsc(SysDictItem::getSortOrder);
        List<SysDictItem> dictItems = this.sysDictItemMapper.selectList(itemWrapper);

        if (dictItems.isEmpty()) {
            if (log.isDebugEnabled()) {
                log.debug("没有找到相关的字典项，类型编码：{}", existingDictType.getTypeCode());
            }
            FindSysDictTypeResponse response = new FindSysDictTypeResponse();
            BeanUtils.copyProperties(existingDictType, response);
            return response;
        }

        // 如果字典项存在，更新它们的类型编码
        for (SysDictItem item : dictItems) {
            item.setTypeCode(existingDictType.getTypeCode());
        }

        this.sysDictItemMapper.updateById(dictItems);

        if (log.isDebugEnabled()) {
            log.debug("更新字典类型相关的字典项，类型编码：{}，数量：{}", existingDictType.getTypeCode(), dictItems.size());
        }

        FindSysDictTypeResponse response = new FindSysDictTypeResponse();
        BeanUtils.copyProperties(existingDictType, response);
        return response;
    }

    @Override
    public void delete(Long id) {
        SysDictType dictType = this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("字典类型不存在，ID：" + id));

        this.removeById(id);

        if (log.isDebugEnabled()) {
            log.debug("删除字典类型，ID：{}，类型编码：{}", id, dictType.getTypeCode());
        }

        // 删除相关的字典项等
        LambdaUpdateWrapper<SysDictItem> wrapper = Wrappers.lambdaUpdate(SysDictItem.class)
                .eq(SysDictItem::getTypeCode, dictType.getTypeCode());
        this.sysDictItemMapper.delete(wrapper);
        if (log.isDebugEnabled()) {
            log.debug("删除字典类型相关的字典项，类型编码：{}", dictType.getTypeCode());
        }
    }
}




