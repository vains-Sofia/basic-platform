package com.basic.converter;

import com.basic.domain.entity.SysPermission;
import com.basic.domain.model.DynamicRouter;
import com.basic.domain.model.DynamicRouterMeta;
import com.basic.domain.model.DynamicRouterTransition;
import com.basic.enums.PermissionTypeEnum;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 将权限列表转换为表示动态路由器的树形结构
 *
 * @author vains
 */
public class RouterConverter {

    /**
     * 将权限列表转换为表示动态路由器的树形结构。
     * 此方法仅处理 MENU 类型的权限，构建路由器图，
     * 并根据父子关系将它们组织成层级结构。
     *
     * @param permissions 要转换为路由器树的 {@link SysPermission} 对象列表。
     *                    每个 {@link SysPermission} 对象可能代表一个菜单或其他类型的权限。
     * @return 以树形结构排列的 {@link DynamicRouter} 对象列表，其中父路由器包含其各自的子路由器。
     */
    public List<DynamicRouter> convertToRouterTree(List<SysPermission> permissions) {
        // 1. 只处理菜单类型（permissionType = 0）
        List<SysPermission> menuList = permissions.stream()
                .filter(p -> PermissionTypeEnum.isMenuType(p.getPermissionType()))
                .sorted(Comparator.comparingInt(SysPermission::getSortOrder))
                .toList();

        // 2. 构建 id -> router 映射
        Map<Long, DynamicRouter> routerMap = new LinkedHashMap<>();
        for (SysPermission p : menuList) {
            DynamicRouter router = new DynamicRouter();
            router.setId(p.getId());
            router.setName(p.getName());
            router.setPath(p.getPath());
            router.setComponent(p.getComponent());
            router.setRedirect(p.getRedirect());

            // 构建 meta
            DynamicRouterMeta meta = new DynamicRouterMeta();
            meta.setTitle(p.getTitle());
            meta.setIcon(p.getIcon());
            meta.setExtraIcon(p.getExtraIcon());
            meta.setShowLink(p.getShowLink());
            meta.setShowParent(p.getShowParent());
            meta.setKeepAlive(p.getKeepAlive());
            meta.setFrameSrc(p.getFrameSrc());
            meta.setFrameLoading(p.getFrameLoading());
            meta.setHiddenTag(p.getHiddenTag());
            meta.setFixedTag(p.getFixedTag());
            meta.setSortOrder(p.getSortOrder());
            meta.setActivePath(p.getActivePath());

            // 动画
            if (p.getEnterTransition() != null || p.getLeaveTransition() != null) {
                DynamicRouterTransition transition = new DynamicRouterTransition();
                transition.setEnterTransition(p.getEnterTransition());
                transition.setLeaveTransition(p.getLeaveTransition());
                meta.setTransition(transition);
            }

            // 权限码（auths）
            if (StringUtils.hasText(p.getPermission())) {
                meta.setAuths(Collections.singletonList(p.getPermission()));
            }

            router.setMeta(meta);
            router.setChildren(new ArrayList<>());
            routerMap.put(p.getId(), router);
        }

        // 3. 构建树结构
        List<DynamicRouter> tree = new ArrayList<>();
        for (SysPermission p : menuList) {
            Long parentId = p.getParentId();
            DynamicRouter current = routerMap.get(p.getId());

            if (parentId == 0 || !routerMap.containsKey(parentId)) {
                // 根节点
                tree.add(current);
            } else {
                // 子节点
                routerMap.get(parentId).getChildren().add(current);
            }
        }

        // 4. 重新分配 rank （前序遍历）
        AtomicInteger counter = new AtomicInteger(1);
        for (DynamicRouter root : tree) {
            assignRankRecursively(root, counter);
        }

        return tree;
    }

    /**
     * 前序遍历，递归分配 rank
     */
    private void assignRankRecursively(DynamicRouter node, AtomicInteger counter) {
        node.getMeta().setSortOrder(counter.getAndIncrement());
        if (node.getChildren() != null && !node.getChildren().isEmpty()) {
            // 子节点需要保持局部排序
            node.getChildren().sort(Comparator.comparingInt(r -> r.getMeta().getSortOrder()));
            for (DynamicRouter child : node.getChildren()) {
                assignRankRecursively(child, counter);
            }
        }
    }

}
