package com.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.domain.PageResult;
import com.basic.domain.entity.SysDictItem;
import com.basic.domain.entity.SysDictType;
import com.basic.domain.request.SysDictItemPageRequest;
import com.basic.domain.request.SysDictItemRequest;
import com.basic.domain.response.FindSysDictItemResponse;
import com.basic.enums.StatusEnum;
import com.basic.exception.CloudServiceException;
import com.basic.mapper.SysDictItemMapper;
import com.basic.mapper.SysDictTypeMapper;
import com.basic.service.SysDictItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 针对表【sys_dict_item(字典项表)】的数据库操作Service实现
 *
 * @author vains
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem>
        implements SysDictItemService {

    private final SysDictTypeMapper sysDictTypeMapper;

    @Override
    public PageResult<FindSysDictItemResponse> pageQuery(SysDictItemPageRequest request) {
        // 条件构造器
        LambdaQueryWrapper<SysDictItem> wrapper = Wrappers.lambdaQuery(SysDictItem.class)
                .eq(!ObjectUtils.isEmpty(request.getStatus()), SysDictItem::getStatus, request.getStatus())
                .eq(!ObjectUtils.isEmpty(request.getTypeCode()), SysDictItem::getTypeCode, request.getTypeCode())
                .like(!ObjectUtils.isEmpty(request.getKeyword()), SysDictItem::getItemCode, request.getKeyword())
                .or()
                .like(!ObjectUtils.isEmpty(request.getKeyword()), SysDictItem::getItemName, request.getKeyword());

        // 分页
        Page<SysDictItem> objectPage = Page.of(request.getCurrent(), request.getSize());

        Page<SysDictItem> dictItemPage = this.page(objectPage, wrapper);

        // 转为响应 bean
        IPage<FindSysDictItemResponse> converted = dictItemPage.convert(e -> {
            FindSysDictItemResponse dictItemResponse = new FindSysDictItemResponse();
            BeanUtils.copyProperties(e, dictItemResponse);
            return dictItemResponse;
        });

        return PageResult.of(converted.getCurrent(), converted.getSize(), converted.getTotal(), converted.getRecords());
    }

    @Override
    public List<FindSysDictItemResponse> listByType(String typeCode) {
        // 验证字典类型是否存在
        LambdaQueryWrapper<SysDictType> wrapper = Wrappers.lambdaQuery(SysDictType.class)
                .eq(SysDictType::getTypeCode, typeCode);
        SysDictType dictType = sysDictTypeMapper.selectOne(wrapper);
        if (dictType == null) {
            throw new CloudServiceException("字典类型不存在，类型编码：" + typeCode);
        }

        LambdaQueryWrapper<SysDictItem> itemWrapper = Wrappers.lambdaQuery(SysDictItem.class)
                .eq(SysDictItem::getStatus, StatusEnum.ENABLE.getCode())
                .eq(SysDictItem::getTypeCode, dictType.getTypeCode())
                .orderByAsc(SysDictItem::getSortOrder);

        List<SysDictItem> dictItems = this.list(itemWrapper);

        return dictItems.stream()
                .map(e -> {
                    FindSysDictItemResponse dictItemResponse = new FindSysDictItemResponse();
                    BeanUtils.copyProperties(e, dictItemResponse);
                    return dictItemResponse;
                }).toList();
    }

    @Override
    public FindSysDictItemResponse create(SysDictItemRequest request) {
        // 验证字典类型是否存在
        LambdaQueryWrapper<SysDictType> wrapper = Wrappers.lambdaQuery(SysDictType.class)
                .eq(SysDictType::getTypeCode, request.getTypeCode());
        SysDictType dictType = sysDictTypeMapper.selectOne(wrapper);
        if (dictType == null) {
            throw new CloudServiceException("字典类型不存在，类型编码：" + request.getTypeCode());
        }

        // 检查同一字典类型下是否存在相同的字典项键
        LambdaQueryWrapper<SysDictItem> itemWrapper = Wrappers.lambdaQuery(SysDictItem.class)
                .ne(SysDictItem::getTypeCode, dictType.getTypeCode())
                .orderByAsc(SysDictItem::getSortOrder);

        List<SysDictItem> existingItems = this.list(itemWrapper);

        boolean keyExists = existingItems.stream()
                .anyMatch(item -> item.getItemCode().equals(request.getItemCode()));

        if (keyExists) {
            throw new CloudServiceException("字典项在类型 [ " + dictType.getTypeCode() + " ] 中已存在：" + request.getItemCode());
        }

        SysDictItem dictItem = new SysDictItem();
        BeanUtils.copyProperties(request, dictItem);
        this.save(dictItem);

        if (log.isDebugEnabled()) {
            log.debug("创建字典项成功，ID：{}，字典类型：{}，字典项键：{}",
                    dictItem.getId(), dictItem.getTypeCode(), dictItem.getItemCode());
        }

        FindSysDictItemResponse dictItemResponse = new FindSysDictItemResponse();
        BeanUtils.copyProperties(dictItem, dictItemResponse);
        return dictItemResponse;
    }

    @Override
    public FindSysDictItemResponse updateDictItem(Long id, SysDictItemRequest request) {
        SysDictItem existingDictItem = this.getOptById(id)
                .orElseThrow(() -> new CloudServiceException("字典项不存在，ID：" + id));

        // 验证字典类型是否存在
        LambdaQueryWrapper<SysDictType> wrapper = Wrappers.lambdaQuery(SysDictType.class)
                .eq(SysDictType::getTypeCode, request.getTypeCode());
        SysDictType dictType = sysDictTypeMapper.selectOne(wrapper);
        if (dictType == null) {
            throw new CloudServiceException("字典类型不存在，类型编码：" + request.getTypeCode());
        }

        // 检查同一字典类型下是否存在相同的字典项键（排除当前记录）
        LambdaUpdateWrapper<SysDictItem> itemWrapper = Wrappers.lambdaUpdate(SysDictItem.class)
                .ne(SysDictItem::getId, id)
                .eq(SysDictItem::getItemCode, request.getItemCode())
                .eq(SysDictItem::getTypeCode, existingDictItem.getTypeCode());

        boolean keyExists = this.exists(itemWrapper);

        if (keyExists) {
            throw new CloudServiceException("字典项键已存在：" + request.getItemCode());
        }

        // 更新字典项信息
        existingDictItem.setTypeCode(request.getTypeCode());
        existingDictItem.setItemCode(request.getItemCode());
        existingDictItem.setItemName(request.getItemName());
        existingDictItem.setSortOrder(request.getSortOrder());
        existingDictItem.setStatus(request.getStatus());
        existingDictItem.setI18nJson(request.getI18nJson());

        this.updateById(existingDictItem);

        if (log.isDebugEnabled()) {
            log.debug("更新字典项成功，ID：{}，字典类型：{}，字典项键：{}",
                    existingDictItem.getId(), existingDictItem.getTypeCode(), existingDictItem.getItemCode());
        }

        FindSysDictItemResponse dictItemResponse = new FindSysDictItemResponse();
        BeanUtils.copyProperties(existingDictItem, dictItemResponse);
        return dictItemResponse;
    }
}




