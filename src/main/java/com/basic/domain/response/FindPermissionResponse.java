package com.basic.domain.response;

import com.basic.enums.PermissionTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 查询权限响应
 *
 * @author vains
 */
@Data
@Schema(name = "FindPermissionResponse", description = "权限信息响应，包含权限的详细信息，包括路由、菜单和接口权限等配置")
public class FindPermissionResponse implements Serializable {

    @Schema(title = "主键 id", description = "权限记录的唯一标识符")
    private Long id;

    @Schema(title = "路由名称", description = "前端路由的唯一名称标识，用于路由跳转")
    private String name;

    @Schema(title = "菜单名称（兼容国际化、非国际化，如果用国际化的写法就必须在根目录的locales文件夹下对应添加）", description = "显示在用户界面上的菜单标题，支持国际化多语言配置")
    private String title;

    @Schema(title = "权限码", description = "用于标识权限的唯一编码，通常采用冒号分隔的形式表示层级关系，如system:user:create表示系统模块下的用户管理中的创建操作权限", example = "system:user:create")
    private String permission;

    @Schema(title = "路径", description = "API 路径或前端路由路径", example = "/api/system/user")
    private String path;

    @Schema(title = "HTTP 请求方式", description = "支持GET、POST、PUT、DELETE等HTTP方法", example = "POST")
    private String requestMethod;

    @Schema(title = "权限类型", description = "包括菜单、按钮、接口等类型")
    private PermissionTypeEnum permissionType;

    @Schema(title = "所属模块名字", description = "权限所属的系统模块", example = "系统管理")
    private String moduleName;

    @Schema(title = "描述", description = "权限的详细描述信息", example = "创建新用户的权限")
    private String description;

    @Schema(title = "是否需要鉴权", description = "标识该权限是否需要进行认证", example = "true")
    private Boolean needAuthentication;

    @Schema(title = "父节点 id", description = "权限树结构中的父级权限ID，用于构建权限层级关系")
    private Long parentId;

    @Schema(title = "组件路径", description = "前端路由对应的组件文件路径，用于动态加载组件")
    private String component;

    @Schema(title = "路由重定向", description = "访问该路由时的重定向目标路径")
    private String redirect;

    @Schema(title = "菜单图标", description = "菜单项显示的图标名称或图标类")
    private String icon;

    @Schema(title = "右侧图标", description = "菜单项右侧显示的额外图标")
    private String extraIcon;

    @Schema(title = "进场动画（页面加载动画）", description = "页面进入时的动画效果配置")
    private String enterTransition;

    @Schema(title = "离场动画（页面加载动画）", description = "页面离开时的动画效果配置")
    private String leaveTransition;

    @Schema(title = "链接地址（需要内嵌的iframe链接地址）", description = "如果菜单需要内嵌外部页面，此字段为iframe的源地址")
    private String frameSrc;

    @Schema(title = "加载动画（内嵌的iframe页面是否开启首次加载动画）", description = "控制 iframe页面加载时是否显示加载动画")
    private String frameLoading;

    @Schema(title = "缓存页面（是否缓存该路由页面，开启后会保存该页面的整体状态，刷新后会清空状态）", description = "控制路由页面是否启用缓存，用于提升用户体验")
    private Boolean keepAlive;

    @Schema(title = "是否显示该菜单", description = "控制菜单项是否在用户界面中显示")
    private Boolean showLink;

    @Schema(title = "隐藏标签页（当前菜单名称或自定义信息禁止添加到标签页）", description = "控制该菜单是否在标签页中显示")
    private Boolean hiddenTag;

    @Schema(title = "固定标签页（当前菜单名称是否固定显示在标签页且不可关闭）", description = "控制该菜单对应的标签页是否固定显示且无法关闭")
    private Boolean fixedTag;

    @Schema(title = "是否显示父级菜单", description = "控制父级菜单是否在面包屑导航中显示")
    private Boolean showParent;

    @Schema(title = "菜单排序", description = "菜单项在同级菜单中的排序权重，数值越小越靠前")
    private Integer rank;

    @Schema(title = "指定激活菜单即可获得高亮", description = "`activePath`为指定激活菜单的`path`，用于高亮显示当前激活的菜单项")
    private String activePath;

    @Schema(title = "创建人名称", description = "创建该权限记录的用户名称")
    private String createName;

    @Schema(title = "修改人名称", description = "最后修改该权限记录的用户名称")
    private String updateName;

    @Schema(title = "创建时间", description = "权限记录的创建时间")
    private LocalDateTime createTime;

    @Schema(title = "修改时间", description = "权限记录的最后修改时间")
    private LocalDateTime updateTime;

}
