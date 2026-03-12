package com.basic.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 动态路由元数据
 *
 * @author vains
 */
@Data
@Schema(title = "DynamicRouterMeta", description = "动态路由元数据模型")
public class DynamicRouterMeta implements Serializable {

    @Schema(title = "菜单名称", description = "用国际化的写法就必须在根目录的 locales 文件夹下对应添加")
    private String title;

    @Schema(title = "菜单图标", description = "菜单名称")
    private String icon;

    @Schema(title = "右侧图标", description = "右侧图标")
    private String extraIcon;

    @Schema(title = "是否显示该菜单", description = "是否显示该菜单")
    private Boolean showLink;

    @Schema(title = "是否显示父级菜单", description = "是否显示父级菜单")
    private Boolean showParent;

    @Schema(title = "缓存页面", description = "是否缓存该路由页面，开启后会保存该页面的整体状态，刷新后会清空状态")
    private Boolean keepAlive;

    @Schema(title = "内嵌的 iframe链接地址", description = "当路由是 内嵌iframe时使用此字段指定链接地址")
    private String frameSrc;

    @Schema(title = "iframe 加载状态", description = "内嵌iframe 时的加载状态显示方式")
    private String frameLoading;

    @Schema(title = "隐藏标签页", description = "是否在标签栏中隐藏此路由对应的标签页")
    private Boolean hiddenTag;

    @Schema(title = "固定标签页", description = "是否固定标签页，固定后不可关闭")
    private Boolean fixedTag;

    @Schema(title = "活动路径", description = "用于指定菜单高亮的路径")
    private String activePath;

    @Schema(title = "排序序号", description = "菜单的排序序号，数字越小越靠前")
    private Integer sortOrder;

    @Schema(title = "路由动画配置", description = "路由动画配置")
    private DynamicRouterTransition transition;

    @Schema(title = "展示菜单需要的权限", description = "展示菜单需要的权限列表")
    private List<String> auths;

}
