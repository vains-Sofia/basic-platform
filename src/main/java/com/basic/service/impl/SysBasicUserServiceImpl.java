package com.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basic.domain.entity.SysBasicUser;
import com.basic.mapper.SysBasicUserMapper;
import com.basic.service.SysBasicUserService;
import org.springframework.stereotype.Service;

/**
 * 针对表【sys_basic_user(基础用户信息表)】的数据库操作Service实现
 *
 * @author vains
 */
@Service
public class SysBasicUserServiceImpl extends ServiceImpl<SysBasicUserMapper, SysBasicUser>
        implements SysBasicUserService {

}




