package com.basic.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 路由动画
 *
 * @author vains
 */
@Data
@Schema(title = "DynamicRouterTransition", description = "配置路由动画效果的相关属性")
public class DynamicRouterTransition implements Serializable {

    @Schema(title = "当前路由动画名", description = "当前路由动画效果")
    private String name;

    @Schema(title = "进场动画（页面加载动画）", description = "页面进入时的动画效果配置")
    private String enterTransition;

    @Schema(title = "离场动画（页面加载动画）", description = "页面离开时的动画效果配置")
    private String leaveTransition;

}
