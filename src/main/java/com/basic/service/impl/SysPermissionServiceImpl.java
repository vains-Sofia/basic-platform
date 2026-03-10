package com.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.domain.entity.SysPermission;
import com.basic.mapper.SysPermissionMapper;
import com.basic.service.SysPermissionService;
import org.springframework.stereotype.Service;

/**
 * 针对表【sys_permission(RBAC权限表)】的数据库操作Service实现
 *
 * @author vains
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission>
        implements SysPermissionService {

}




