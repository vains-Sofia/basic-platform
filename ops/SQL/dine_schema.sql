CREATE TABLE `dine_store`
(
    `id`          BIGINT UNSIGNED  NOT NULL COMMENT '门店ID',
    `name`        VARCHAR(64)  NOT NULL COMMENT '门店名称',
    `logo`        VARCHAR(255) NOT NULL                                 DEFAULT '' COMMENT 'Logo URL',
    `status`      VARCHAR(10)  NOT NULL                                 DEFAULT 1 COMMENT '0-停业 1-营业中',
    `create_by`   bigint                                                DEFAULT NULL COMMENT '创建人',
    `update_by`   bigint                                                DEFAULT NULL COMMENT '修改人',
    `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
    `update_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人名称',
    `create_time` datetime                                              DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                              DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='门店表';

CREATE TABLE `dine_category`
(
    `id`          BIGINT UNSIGNED  NOT NULL COMMENT '分类ID',
    `store_id`    BIGINT UNSIGNED  NOT NULL                COMMENT '所属门店ID',
    `name`        VARCHAR(32) NOT NULL COMMENT '分类名称',
    `sort`        INT UNSIGNED     NOT NULL DEFAULT 0      COMMENT '排序值(升序)',
    `status`      VARCHAR(10) NOT NULL                                  DEFAULT 1 COMMENT '0-停用 1-启用',
    `create_by`   bigint                                                DEFAULT NULL COMMENT '创建人',
    `update_by`   bigint                                                DEFAULT NULL COMMENT '修改人',
    `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
    `update_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人名称',
    `create_time` datetime                                              DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                              DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_store_category_name` (`store_id`, `name`),
    KEY           `idx_store_sort` (`store_id`, `sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜品分类表';

CREATE TABLE `dine_dish`
(
    `id`           BIGINT UNSIGNED  NOT NULL COMMENT '菜品ID',
    `store_id`     BIGINT UNSIGNED  NOT NULL                COMMENT '所属门店ID',
    `category_id`  BIGINT UNSIGNED  NOT NULL                COMMENT '所属分类ID',
    `name`         VARCHAR(64)  NOT NULL COMMENT '菜品名称',
    `image`        VARCHAR(255) NOT NULL                                 DEFAULT '' COMMENT '主图URL',
    `images`       JSON         NOT NULL COMMENT '多图URL数组',
    `description`  VARCHAR(256) NOT NULL                                 DEFAULT '' COMMENT '简要描述',
    `price`        INT UNSIGNED     NOT NULL                COMMENT '基准价格(分)',
    `labels`       VARCHAR(255) NOT NULL                                 DEFAULT '' COMMENT '标签，如"招牌,限时"',
    `recommend`    TINYINT      NOT NULL                                 DEFAULT 0 COMMENT '0-普通 1-推荐',
    `cooking_time` INT UNSIGNED     NOT NULL DEFAULT 0      COMMENT '预计制作时长(秒)',
    `unit`         VARCHAR(16)  NOT NULL                                 DEFAULT '份' COMMENT '单位',
    `sales`        INT UNSIGNED     NOT NULL DEFAULT 0      COMMENT '累计销量',
    `status`       VARCHAR(10)  NOT NULL                                 DEFAULT 1 COMMENT '0-停售 1-在售',
    `sort`         INT UNSIGNED     NOT NULL DEFAULT 0      COMMENT '排序(升序)',
    `create_by`    bigint                                                DEFAULT NULL COMMENT '创建人',
    `update_by`    bigint                                                DEFAULT NULL COMMENT '修改人',
    `create_name`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
    `update_name`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人名称',
    `create_time`  datetime                                              DEFAULT NULL COMMENT '创建时间',
    `update_time`  datetime                                              DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_store_dish_name` (`store_id`, `name`),
    KEY            `idx_store_status` (`store_id`, `status`),
    KEY            `idx_category_status_sort` (`category_id`, `status`, `sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜品表';

CREATE TABLE `dine_attribute_group`
(
    `id`          BIGINT UNSIGNED  NOT NULL COMMENT '属性组ID',
    `store_id`    BIGINT UNSIGNED  NOT NULL                COMMENT '所属门店ID',
    `name`        VARCHAR(32) NOT NULL COMMENT '组名，如"温度"',
    `select_type` TINYINT     NOT NULL                                  DEFAULT 1 COMMENT '1-单选 2-多选',
    `sort`        INT UNSIGNED     NOT NULL DEFAULT 0      COMMENT '排序',
    `status`      VARCHAR(10) NOT NULL                                  DEFAULT 1 COMMENT '0-停用 1-启用',
    `create_by`   bigint                                                DEFAULT NULL COMMENT '创建人',
    `update_by`   bigint                                                DEFAULT NULL COMMENT '修改人',
    `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
    `update_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人名称',
    `create_time` datetime                                              DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                              DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_store_group_name` (`store_id`, `name`),
    KEY           `idx_store_status_sort` (`store_id`, `status`, `sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='属性组表';

CREATE TABLE `dine_attribute_option`
(
    `id`               BIGINT UNSIGNED  NOT NULL COMMENT '选项ID',
    `group_id`         BIGINT UNSIGNED  NOT NULL                COMMENT '所属属性组ID',
    `name`             VARCHAR(32) NOT NULL COMMENT '选项名，如"冰"',
    `price_adjustment` INT         NOT NULL                                  DEFAULT 0 COMMENT '价格调整(分)，可为负数',
    `sort`             INT UNSIGNED     NOT NULL DEFAULT 0      COMMENT '排序',
    `status`           VARCHAR(10) NOT NULL                                  DEFAULT 1 COMMENT '0-停用 1-启用',
    `create_by`        bigint                                                DEFAULT NULL COMMENT '创建人',
    `update_by`        bigint                                                DEFAULT NULL COMMENT '修改人',
    `create_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
    `update_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人名称',
    `create_time`      datetime                                              DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime                                              DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_group_option_name` (`group_id`, `name`),
    KEY                `idx_group_status_sort` (`group_id`, `status`, `sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='属性选项表';

CREATE TABLE `dine_dish_attribute_group`
(
    `id`          BIGINT UNSIGNED  NOT NULL,
    `dish_id`     BIGINT UNSIGNED  NOT NULL COMMENT '菜品ID',
    `group_id`    BIGINT UNSIGNED  NOT NULL COMMENT '属性组ID',
    `required`    TINYINT NOT NULL                                      DEFAULT 1 COMMENT '是否必选 0-否 1-是',
    `sort`        INT UNSIGNED     NOT NULL DEFAULT 0       COMMENT '该组在详情页的排序',
    `create_by`   bigint                                                DEFAULT NULL COMMENT '创建人',
    `update_by`   bigint                                                DEFAULT NULL COMMENT '修改人',
    `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
    `update_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人名称',
    `create_time` datetime                                              DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                              DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_dish_group` (`dish_id`, `group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜品-属性组关联';

CREATE TABLE `dine_dish_attribute_option`
(
    `id`                        BIGINT UNSIGNED  NOT NULL,
    `dish_id`                   BIGINT UNSIGNED  NOT NULL COMMENT '菜品ID',
    `option_id`                 BIGINT UNSIGNED  NOT NULL COMMENT '选项ID',
    `is_available`              TINYINT NOT NULL                                      DEFAULT 1 COMMENT '该菜品是否可用此选项',
    `price_adjustment_override` INT                                                   DEFAULT NULL COMMENT '覆盖价格调整，NULL则使用选项默认值',
    `create_by`                 bigint                                                DEFAULT NULL COMMENT '创建人',
    `update_by`                 bigint                                                DEFAULT NULL COMMENT '修改人',
    `create_name`               varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
    `update_name`               varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人名称',
    `create_time`               datetime                                              DEFAULT NULL COMMENT '创建时间',
    `update_time`               datetime                                              DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_dish_option` (`dish_id`, `option_id`),
    KEY                         `idx_dish_id` (`dish_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜品-选项精细化配置';

CREATE TABLE `dine_table_info`
(
    `id`           BIGINT UNSIGNED  NOT NULL COMMENT '桌台ID',
    `store_id`     BIGINT UNSIGNED  NOT NULL                COMMENT '所属门店ID',
    `name`         VARCHAR(32) NOT NULL COMMENT '桌台名称，如"A01"',
    `code`         VARCHAR(64) NOT NULL COMMENT '二维码标识，全局唯一',
    `capacity`     INT UNSIGNED     NOT NULL DEFAULT 0      COMMENT '建议容纳人数',
    `table_status` TINYINT     NOT NULL                                  DEFAULT 0 COMMENT '0-空闲 1-占用 2-留座',
    `create_by`    bigint                                                DEFAULT NULL COMMENT '创建人',
    `update_by`    bigint                                                DEFAULT NULL COMMENT '修改人',
    `create_name`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
    `update_name`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人名称',
    `create_time`  datetime                                              DEFAULT NULL COMMENT '创建时间',
    `update_time`  datetime                                              DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_store_code` (`store_id`, `code`),
    UNIQUE KEY `uk_store_table_name` (`store_id`, `name`),
    KEY            `idx_store_table_status` (`store_id`, `table_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='桌台表';

CREATE TABLE `dine_table_binding`
(
    `id`              BIGINT UNSIGNED  NOT NULL,
    `table_id`        BIGINT UNSIGNED  NOT NULL,
    `user_id`         BIGINT UNSIGNED  NOT NULL                COMMENT '用户ID（系统用户表）',
    `status`          VARCHAR(10) NOT NULL                                  DEFAULT 1 COMMENT '0-解绑 1-绑定中',
    `bind_time`       DATETIME    NOT NULL                                  DEFAULT CURRENT_TIMESTAMP,
    `unbind_time`     DATETIME                                              DEFAULT NULL,
    `active_table_id` BIGINT UNSIGNED GENERATED ALWAYS AS (CASE WHEN `status` = 'Y' THEN `table_id` ELSE NULL END) STORED COMMENT '绑定中桌台唯一约束辅助列',
    `create_by`       bigint                                                DEFAULT NULL COMMENT '创建人',
    `update_by`       bigint                                                DEFAULT NULL COMMENT '修改人',
    `create_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
    `update_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人名称',
    `create_time`     datetime                                              DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime                                              DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_active_table_binding` (`active_table_id`),
    KEY               `idx_table_status` (`table_id`, `status`),
    KEY               `idx_user_bind` (`user_id`, `status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='桌台绑定记录';