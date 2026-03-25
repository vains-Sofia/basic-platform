package com.basic.configuration.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.basic.domain.BasicEntity;
import com.basic.util.LambdaMethodUtils;
import com.basic.util.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

/**
 * 审计字段自动填充处理
 *
 * @author vains
 */
@Component
public class BasicMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username;
        if (authentication != null) {
            username = authentication.getName();
        } else {
            username = null;
        }
        String loginUserId = SecurityUtils.getLoginUserId();
        if (!ObjectUtils.isEmpty(loginUserId)) {
            long userId = Long.parseLong(loginUserId);

            this.strictInsertFill(metaObject, LambdaMethodUtils.extractMethodToProperty(BasicEntity::getCreateBy),
                    Long.class, userId);
            this.strictInsertFill(metaObject, LambdaMethodUtils.extractMethodToProperty(BasicEntity::getUpdateBy),
                    Long.class, userId);
        }

        this.strictInsertFill(metaObject, LambdaMethodUtils.extractMethodToProperty(BasicEntity::getCreateTime),
                LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, LambdaMethodUtils.extractMethodToProperty(BasicEntity::getUpdateTime),
                LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, LambdaMethodUtils.extractMethodToProperty(BasicEntity::getCreateName),
                String.class, username);
        this.strictInsertFill(metaObject, LambdaMethodUtils.extractMethodToProperty(BasicEntity::getUpdateName),
                String.class, username);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username;
        if (authentication != null) {
            username = authentication.getName();
        } else {
            username = null;
        }

        String loginUserId = SecurityUtils.getLoginUserId();
        if (!ObjectUtils.isEmpty(loginUserId)) {
            long userId = Long.parseLong(loginUserId);

            this.strictInsertFill(metaObject, LambdaMethodUtils.extractMethodToProperty(BasicEntity::getUpdateBy),
                    Long.class, userId);
        }
        this.strictUpdateFill(metaObject, LambdaMethodUtils.extractMethodToProperty(BasicEntity::getUpdateTime),
                LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, LambdaMethodUtils.extractMethodToProperty(BasicEntity::getUpdateName),
                String.class, username);
    }


}