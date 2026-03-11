package com.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.basic.domain.BasicEntity;
import com.basic.enums.GenderEnum;
import com.basic.enums.OAuth2AccountPlatformEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 基础用户信息表
 *
 * @author vains
 */
@Data
@TableName(value = "sys_basic_user")
@EqualsAndHashCode(callSuper = true)
public class SysBasicUser extends BasicEntity {

    /**
     * 主键 ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 用户名、昵称
     */
    private String nickname;

    /**
     * 用户个人资料页面的 URL。
     */
    private String profile;

    /**
     * 用户个人资料图片的 URL。此 URL 必须指向图像文件（例如，PNG、JPEG 或 GIF 图像文件），而不是指向包含图像的网页。
     */
    private String picture;

    /**
     * 用户的首选电子邮件地址。其值必须符合RFC 5322 [RFC5322] addr-spec 语法
     */
    private String email;

    /**
     * 邮箱是否验证过
     */
    private Boolean emailVerified;

    /**
     * 用户性别
     */
    private GenderEnum gender;

    /**
     * 密码
     */
    private String password;

    /**
     * 出生日期，以 ISO 8601-1 [ISO8601‑1] YYYY-MM-DD 格式表示。
     */
    private LocalDate birthdate;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 手机号是否已验证
     */
    private Boolean phoneNumberVerified;

    /**
     * 用户的首选邮政地址
     */
    private String address;

    /**
     * 是否已删除
     */
    @TableLogic
    private Integer deleted;

    /**
     * 用户来源
     */
    private OAuth2AccountPlatformEnum accountPlatform;
}