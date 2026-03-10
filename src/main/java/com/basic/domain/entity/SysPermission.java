package com.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.basic.domain.BasicEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * RBAC 权限表
 *
 * @author vains
 */
@Data
@TableName(value = "sys_permission")
@EqualsAndHashCode(callSuper = true)
public class SysPermission extends BasicEntity {

    /**
     * 主键 ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 菜单名称（兼容国际化、非国际化，如果用国际化的写法就必须在根目录的locales文件夹下对应添加）
     */
    private String title;

    /**
     * 权限名
     */
    private String name;

    /**
     * 权限码
     */
    private String permission;

    /**
     * 路径
     */
    private String path;

    /**
     * HTTP 请求方式
     */
    private String requestMethod;

    /**
     * 0:菜单,1:接口,2:其它
     */
    private Integer permissionType;

    /**
     * 所属模块名字
     */
    private String moduleName;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否需要鉴权
     */
    private Integer needAuthentication;

    /**
     * 父节点 ID
     */
    private Long parentId;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 路由重定向
     */
    private String redirect;

    /**
     * 是否已删除
     */
    @TableLogic
    private Integer deleted;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 右侧图标
     */
    private String extraIcon;

    /**
     * 进场动画（页面加载动画）
     */
    private String enterTransition;

    /**
     * 离场动画（页面加载动画）
     */
    private String leaveTransition;

    /**
     * 链接地址（需要内嵌的iframe链接地址）
     */
    private String frameSrc;

    /**
     * 加载动画（内嵌的iframe页面是否开启首次加载动画）
     */
    private String frameLoading;

    /**
     * 缓存页面（是否缓存该路由页面，开启后会保存该页面的整体状态，刷新后会清空状态）
     */
    private Integer keepAlive;

    /**
     * 是否显示该菜单
     */
    private Integer showLink;

    /**
     * 隐藏标签页（当前菜单名称或自定义信息禁止添加到标签页）
     */
    private Integer hiddenTag;

    /**
     * 固定标签页（当前菜单名称是否固定显示在标签页且不可关闭）
     */
    private Integer fixedTag;

    /**
     * 是否显示父级菜单
     */
    private Integer showParent;

    /**
     * 菜单排序
     */
    private Integer rank;

    /**
     * 指定激活菜单即可获得高亮，`activePath`为指定激活菜单的`path`
     */
    private String activePath;
}