package com.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.domain.entity.SysRole;
import com.basic.mapper.SysRoleMapper;
import com.basic.service.SysRoleService;
import org.springframework.stereotype.Service;

/**
 * 针对表【sys_role(RBAC角色表)】的数据库操作Service实现
 *
 * @author vains
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {

}




