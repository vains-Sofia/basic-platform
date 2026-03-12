package com.basic.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 动态路由数据
 *
 * @author vains
 */
@Data
@Schema(title = "DynamicRouter", description = "动态路由数据模型")
public class DynamicRouter implements Serializable {

    @Schema(title = "主键 id", description = "唯一数据 id")
    private Long id;

    @Schema(title = "路由名称", description = "必须唯一并且和当前路由`component`字段对应的页面里用`defineOptions`包起来的`name`保持一致")
    private String name;

    @Schema(title = "路径", description = "路由路径")
    private String path;

    @Schema(title = "组件路径", description = "传component组件路径，那么path可以随便写，如果不传，component组件路径会跟path保持一致")
    private String component;

    @Schema(title = "路由重定向", description = "路由重定向地址")
    private String redirect;

    @Schema(title = "当前路由元数据", description = "当前路由元数据")
    private DynamicRouterMeta meta;

    @Schema(title = "子节点", description = "路由子节点")
    private List<DynamicRouter> children;

}
