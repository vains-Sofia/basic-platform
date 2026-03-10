-- `basic-cloud-platform`.sys_basic_user definition

CREATE TABLE `sys_basic_user`
(
    `id`                    bigint NOT NULL COMMENT '自增id',
    `username`              varchar(255) COLLATE utf8mb4_bin                       DEFAULT NULL COMMENT '账号',
    `nickname`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名、昵称',
    `profile`               varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户个人资料页面的 URL。',
    `picture`               varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户个人资料图片的 URL。此 URL 必须指向图像文件（例如，PNG、JPEG 或 GIF 图像文件），而不是指向包含图像的网页。',
    `email`                 varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '用户的首选电子邮件地址。其值必须符合RFC 5322 [RFC5322] addr-spec 语法',
    `email_verified`        tinyint(1) DEFAULT NULL COMMENT '邮箱是否验证过',
    `gender`                tinyint(1) DEFAULT NULL COMMENT '用户性别',
    `password`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
    `birthdate`             date                                                   DEFAULT NULL COMMENT '出生日期，以 ISO 8601-1 [ISO8601‑1] YYYY-MM-DD 格式表示。',
    `phone_number`          varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '手机号',
    `phone_number_verified` tinyint(1) DEFAULT NULL COMMENT '手机号是否已验证',
    `address`               varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户的首选邮政地址',
    `deleted`               tinyint(1) DEFAULT NULL COMMENT '是否已删除',
    `account_platform`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户来源',
    `create_by`             bigint                                                 DEFAULT NULL COMMENT '创建人',
    `update_by`             bigint                                                 DEFAULT NULL COMMENT '修改人',
    `create_name`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '创建人名称',
    `update_name`           varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '修改人名称',
    `create_time`           datetime                                               DEFAULT NULL COMMENT '创建时间',
    `update_time`           datetime                                               DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='基础用户信息表';

INSERT INTO sys_basic_user (id, username, nickname, profile, picture, email, email_verified, gender, password,
                            birthdate, phone_number, phone_number_verified, address, deleted, account_platform,
                            create_by, update_by, create_name, update_name, create_time, update_time)
VALUES (1, 'admin', '云逸', NULL,
        'http://minio.vains.ddns-ip.net:20000/user-picture/aaa.6e9bf775-9537-4b06-a026-637c16e6a32d.jpg',
        '17683906991@163.com', 0, 1, '{bcrypt}$2a$10$PSkQfdN7.MDZ4/8hh2gJleQ4hnl.eppYr7zkU0ZCqR6u9GbaYg0zy',
        '2024-10-01', '17683906991', 1, '地址', 0, 'system', 1, 1, 'system', '云逸', '2024-11-22 23:43:47',
        '2025-05-16 14:03:14');
INSERT INTO sys_basic_user (id, username, nickname, profile, picture, email, email_verified, gender, password,
                            birthdate, phone_number, phone_number_verified, address, deleted, account_platform,
                            create_by, update_by, create_name, update_name, create_time, update_time)
VALUES (1862332106783637506, 'test01', '注册01', NULL,
        'http://minio.vains.ddns-ip.net:20000/user-picture/smileboy.56cdf42f-b42c-4f00-b24e-1caf796ecd5f.png',
        '17683906001@163.com', 0, 1, '{bcrypt}$2a$10$MbAbp69nydLks4BNSV7jsOHwsmrJkSiIB/mYBE7WXdYBnES0LG6v6',
        '2010-05-08', '17683906001', 1, '111111', 0, 'system', 1862332106783637506, 1, '注册01', '云逸',
        '2024-11-29 11:05:49', '2025-05-29 16:41:22');
INSERT INTO sys_basic_user (id, username, nickname, profile, picture, email, email_verified, gender, password,
                            birthdate, phone_number, phone_number_verified, address, deleted, account_platform,
                            create_by, update_by, create_name, update_name, create_time, update_time)
VALUES (1920404587435859970, 'test02', '测试添加', NULL,
        'http://minio.vains.ddns-ip.net:20000/user-picture/blue-girl.2e947cf1-4e73-4f29-a6cf-534b70c97b3f.png',
        '17683906002@163.com', 0, 2, '{bcrypt}$2a$10$RHRRWM73YQI0iYARMakJ9OvHfL/F2LvDpBUzBCZaaKUtQTJcsEflC',
        '2000-05-08', '17683906002', 1, '2222222221', 0, 'system', 1, 1, '云逸', '云逸', '2025-05-08 17:05:08',
        '2025-07-25 10:31:16');
INSERT INTO sys_basic_user (id, username, nickname, profile, picture, email, email_verified, gender, password,
                            birthdate, phone_number, phone_number_verified, address, deleted, account_platform,
                            create_by, update_by, create_name, update_name, create_time, update_time)
VALUES (1934450355763220481, 'cxw', 'ChenXw', NULL,
        'http://minio.vains.ddns-ip.net:20000/user-picture/draw-girl.4a3caa10-60c9-4c49-9423-17c2e1a2ad81.png',
        '513087451@qq.com', 0, 2, '{bcrypt}$2a$10$A9etrG44XVVTy97WqZp7k.f9pW2uXllycwusTwF./ZPvCZCtvycbG', '2025-07-03',
        '', 0, '', 0, 'system', 1934450355763220481, 1, '嗨嗨嗨', '云逸', '2025-06-16 11:18:00', '2025-07-25 10:31:10');
INSERT INTO sys_basic_user (id, username, nickname, profile, picture, email, email_verified, gender, password,
                            birthdate, phone_number, phone_number_verified, address, deleted, account_platform,
                            create_by, update_by, create_name, update_name, create_time, update_time)
VALUES (1990332148267409409, 'test03', '测试添加03', NULL,
        'http://127.0.0.1:9000/user-picture/girl.51f3221e-4f61-440d-aae2-f76d4ec668af.png', '17683906003@163.com', 0, 1,
        '{bcrypt}$2a$10$bRWwBBirW555AUaYeEFPLOJ8KrmTZj.T7pTFfhb4cAhdpWAbg4zdO', '2000-10-01', '17683906003', 1,
        '地址03-改', 0, 'system', 1, 1, '云逸', '云逸', '2025-11-17 16:12:18', '2025-11-20 14:23:01');
INSERT INTO sys_basic_user (id, username, nickname, profile, picture, email, email_verified, gender, password,
                            birthdate, phone_number, phone_number_verified, address, deleted, account_platform,
                            create_by, update_by, create_name, update_name, create_time, update_time)
VALUES (1993515556944551937, 'Register02', '注册02', NULL,
        'http://127.0.0.1:9000/user-picture/tree-girl.023ab588-7307-4cbc-80cd-2236b8a95d8c.png', '17683906101@163.com',
        0, 0, '{bcrypt}$2a$10$7kNxFYQ3hAybR0Isal3Hd.Vb5.jVVFAQO.XKRiJArLZCtBmGXMDgi', NULL, NULL, 0, NULL, 0, 'system',
        1993515556944551937, 1, '注册02', '云逸', '2025-11-26 11:02:02', '2025-11-26 17:27:21');

-- `basic-cloud-platform`.sys_dict_item definition

CREATE TABLE `sys_dict_item`
(
    `id`          bigint                                                 NOT NULL COMMENT '主键',
    `type_code`   varchar(50) COLLATE utf8mb4_bin                        NOT NULL COMMENT '字典类型编码（外键）',
    `item_code`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '字典项键',
    `item_name`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典项值',
    `sort_order`  int                                                             DEFAULT '0' COMMENT '排序值',
    `status`      varchar(10) COLLATE utf8mb4_bin                        NOT NULL DEFAULT 'Y' COMMENT '状态（Y=启用，N=禁用）',
    `i18n_json`   text COLLATE utf8mb4_bin COMMENT '多语言 JSON 值',
    `create_by`   bigint                                                          DEFAULT NULL COMMENT '创建人',
    `update_by`   bigint                                                          DEFAULT NULL COMMENT '修改人',
    `create_time` datetime                                                        DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                                        DEFAULT NULL COMMENT '修改时间',
    `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin           DEFAULT NULL COMMENT '创建人名称',
    `update_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin           DEFAULT NULL COMMENT '修改人名称',
    PRIMARY KEY (`id`),
    KEY           `idx_type_code` (`type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='字典项表';


-- `basic-cloud-platform`.sys_dict_type definition

CREATE TABLE `sys_dict_type`
(
    `id`          bigint                           NOT NULL COMMENT '主键',
    `type_code`   varchar(50) COLLATE utf8mb4_bin  NOT NULL COMMENT '字典类型编码',
    `name`        varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '字典名称',
    `description` text COLLATE utf8mb4_bin COMMENT '类型说明',
    `status`      varchar(10) COLLATE utf8mb4_bin  NOT NULL             DEFAULT 'Y' COMMENT '状态（Y=启用，N=禁用）',
    `create_by`   bigint                                                DEFAULT NULL COMMENT '创建人',
    `update_by`   bigint                                                DEFAULT NULL COMMENT '修改人',
    `create_time` datetime                                              DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                              DEFAULT NULL COMMENT '修改时间',
    `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
    `update_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人名称',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_type_code` (`type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='字典类型表';


-- `basic-cloud-platform`.sys_permission definition

CREATE TABLE `sys_permission`
(
    `id`                  bigint NOT NULL COMMENT '主键id',
    `title`               varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单名称（兼容国际化、非国际化，如果用国际化的写法就必须在根目录的locales文件夹下对应添加）',
    `name`                varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '权限名',
    `permission`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '权限码',
    `path`                varchar(100) COLLATE utf8mb4_bin                       DEFAULT NULL COMMENT '路径',
    `request_method`      varchar(10) COLLATE utf8mb4_bin                        DEFAULT NULL COMMENT 'HTTP请求方式',
    `permission_type`     tinyint(1) NOT NULL COMMENT '0:菜单,1:接口,2:其它',
    `module_name`         varchar(100) COLLATE utf8mb4_bin                       DEFAULT NULL COMMENT '所属模块名字',
    `description`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '描述',
    `need_authentication` tinyint(1) DEFAULT '0' COMMENT '是否需要鉴权',
    `parent_id`           bigint                                                 DEFAULT '0' COMMENT '父节点id',
    `component`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组件路径',
    `redirect`            varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '路由重定向',
    `deleted`             tinyint(1) DEFAULT NULL COMMENT '是否已删除',
    `icon`                varchar(100) COLLATE utf8mb4_bin                       DEFAULT NULL COMMENT '菜单图标',
    `extra_icon`          varchar(100) COLLATE utf8mb4_bin                       DEFAULT NULL COMMENT '右侧图标',
    `enter_transition`    varchar(100) COLLATE utf8mb4_bin                       DEFAULT NULL COMMENT '进场动画（页面加载动画）',
    `leave_transition`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '离场动画（页面加载动画）',
    `frame_src`           varchar(1000) COLLATE utf8mb4_bin                      DEFAULT NULL COMMENT '链接地址（需要内嵌的iframe链接地址）',
    `frame_loading`       varchar(100) COLLATE utf8mb4_bin                       DEFAULT NULL COMMENT '加载动画（内嵌的iframe页面是否开启首次加载动画）',
    `keep_alive`          tinyint(1) DEFAULT NULL COMMENT '缓存页面（是否缓存该路由页面，开启后会保存该页面的整体状态，刷新后会清空状态）',
    `show_link`           tinyint(1) DEFAULT NULL COMMENT '是否显示该菜单',
    `hidden_tag`          tinyint(1) DEFAULT NULL COMMENT '隐藏标签页（当前菜单名称或自定义信息禁止添加到标签页）',
    `fixed_tag`           tinyint(1) DEFAULT NULL COMMENT '固定标签页（当前菜单名称是否固定显示在标签页且不可关闭）',
    `show_parent`         tinyint(1) DEFAULT NULL COMMENT '是否显示父级菜单',
    `rank`                decimal(10, 0)                                         DEFAULT NULL COMMENT '菜单排序',
    `active_path`         varchar(100) COLLATE utf8mb4_bin                       DEFAULT NULL COMMENT '指定激活菜单即可获得高亮，`activePath`为指定激活菜单的`path`',
    `create_by`           bigint                                                 DEFAULT NULL COMMENT '创建人',
    `update_by`           bigint                                                 DEFAULT NULL COMMENT '修改人',
    `create_name`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '创建人名称',
    `update_name`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL COMMENT '修改人名称',
    `create_time`         datetime                                               DEFAULT NULL COMMENT '创建时间',
    `update_time`         datetime                                               DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='RBAC权限表';


-- `basic-cloud-platform`.sys_role definition

CREATE TABLE `sys_role`
(
    `id`          bigint NOT NULL COMMENT '主键id',
    `code`        varchar(50) COLLATE utf8mb4_bin                       DEFAULT NULL COMMENT '角色代码',
    `name`        varchar(50) COLLATE utf8mb4_bin                       DEFAULT NULL COMMENT '角色名称',
    `description` varchar(255) COLLATE utf8mb4_bin                      DEFAULT NULL COMMENT '角色描述',
    `deleted`     tinyint(1) DEFAULT NULL COMMENT '是否已删除',
    `create_by`   bigint                                                DEFAULT NULL COMMENT '创建人',
    `update_by`   bigint                                                DEFAULT NULL COMMENT '修改人',
    `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
    `update_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人名称',
    `create_time` datetime                                              DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                              DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='RBAC角色表';


-- `basic-cloud-platform`.sys_role_permission definition

CREATE TABLE `sys_role_permission`
(
    `id`            bigint NOT NULL COMMENT '角色菜单关联表ID',
    `role_id`       bigint NOT NULL COMMENT '角色ID',
    `permission_id` bigint NOT NULL COMMENT '权限菜单ID',
    `create_by`     bigint                                                DEFAULT NULL COMMENT '创建人',
    `update_by`     bigint                                                DEFAULT NULL COMMENT '修改人',
    `create_name`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
    `update_name`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人名称',
    `create_time`   datetime                                              DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime                                              DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


-- `basic-cloud-platform`.sys_user_role definition

CREATE TABLE `sys_user_role`
(
    `id`          bigint NOT NULL COMMENT '主键id',
    `role_id`     bigint                                                DEFAULT NULL COMMENT '角色ID',
    `user_id`     bigint                                                DEFAULT NULL COMMENT '用户ID',
    `create_by`   bigint                                                DEFAULT NULL COMMENT '创建人',
    `update_by`   bigint                                                DEFAULT NULL COMMENT '修改人',
    `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人名称',
    `update_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人名称',
    `create_time` datetime                                              DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                              DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941019168394715138, 'GENDER', '性别', '性别字典', 'Y', 1, 1, '2025-07-04 14:20:07', '2025-07-04 14:20:07',
        '云逸', '云逸');
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941019564785803266, 'STATUS', '状态', '状态字典', 'Y', 1, 1, '2025-07-04 14:21:41', '2025-07-04 14:21:41',
        '云逸', '云逸');
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941143997022277633, 'HTTP_METHOD', '请求方式', '请求方式字典', 'Y', 1, 1, '2025-07-04 20:56:03',
        '2025-07-18 13:58:55', '云逸', '云逸');
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941144897803587586, 'MODULE', '所属模块', '菜单所属模块字典', 'Y', 1, 1, '2025-07-04 20:39:43',
        '2025-07-04 22:39:43', '云逸', '云逸');
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150375308759041, 'TEST1', '测试字典类型1', '这是一个测试字典类型1', 'Y', NULL, NULL, '2025-07-04 22:01:29',
        '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150375510085633, 'TEST2', '测试字典类型2', '这是一个测试字典类型2', 'Y', NULL, NULL, '2025-07-04 22:02:29',
        '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150375564611585, 'TEST3', '测试字典类型3', '这是一个测试字典类型3', 'Y', NULL, NULL, '2025-07-04 22:03:29',
        '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150375635914753, 'TEST4', '测试字典类型4', '这是一个测试字典类型4', 'Y', NULL, NULL, '2025-07-04 22:04:29',
        '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150375703023617, 'TEST5', '测试字典类型5', '这是一个测试字典类型5', 'Y', NULL, NULL, '2025-07-04 22:05:29',
        '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150375765938177, 'TEST6', '测试字典类型6', '这是一个测试字典类型6', 'Y', NULL, NULL, '2025-07-04 22:06:29',
        '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150375833047041, 'TEST7', '测试字典类型7', '这是一个测试字典类型7', 'Y', NULL, NULL, '2025-07-04 22:07:29',
        '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150375895961602, 'TEST8', '测试字典类型8', '这是一个测试字典类型8', 'Y', NULL, NULL, '2025-07-04 22:08:29',
        '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150375958876161, 'TEST9', '测试字典类型9', '这是一个测试字典类型9', 'Y', NULL, NULL, '2025-07-04 22:09:29',
        '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150376021790722, 'TEST10', '测试字典类型10', '这是一个测试字典类型10', 'Y', NULL, NULL,
        '2025-07-04 22:10:29', '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150376088899585, 'TEST11', '测试字典类型11', '这是一个测试字典类型11', 'Y', NULL, NULL,
        '2025-07-04 22:11:29', '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150376156008449, 'TEST12', '测试字典类型12', '这是一个测试字典类型12', 'Y', NULL, NULL,
        '2025-07-04 22:12:29', '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150376223117313, 'TEST13', '测试字典类型13', '这是一个测试字典类型13', 'Y', NULL, NULL,
        '2025-07-04 22:13:29', '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150376286031874, 'TEST14', '测试字典类型14', '这是一个测试字典类型14', 'Y', NULL, NULL,
        '2025-07-04 22:14:29', '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150376348946434, 'TEST15', '测试字典类型15', '这是一个测试字典类型15', 'Y', NULL, NULL,
        '2025-07-04 22:15:29', '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150376416055297, 'TEST16', '测试字典类型16', '这是一个测试字典类型16', 'Y', NULL, NULL,
        '2025-07-04 22:16:29', '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150376483164161, 'TEST17', '测试字典类型17', '这是一个测试字典类型17', 'Y', NULL, NULL,
        '2025-07-04 22:17:29', '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150376546078722, 'TEST18', '测试字典类型18', '这是一个测试字典类型18', 'Y', NULL, NULL,
        '2025-07-04 22:18:29', '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150376608993282, 'TEST19', '测试字典类型19', '这是一个测试字典类型19', 'Y', NULL, NULL,
        '2025-07-04 22:19:29', '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150376671907841, 'TEST20', '测试字典类型20', '这是一个测试字典类型20', 'Y', NULL, NULL,
        '2025-07-04 22:20:29', '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150376739016706, 'TEST21', '测试字典类型21', '这是一个测试字典类型21', 'Y', NULL, NULL,
        '2025-07-04 22:21:29', '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150376806125569, 'TEST22', '测试字典类型22', '这是一个测试字典类型22', 'Y', NULL, NULL,
        '2025-07-04 22:22:29', '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150376864845826, 'TEST23', '测试字典类型23', '这是一个测试字典类型23', 'Y', NULL, NULL,
        '2025-07-04 22:23:29', '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150376936148993, 'TEST24', '测试字典类型24', '这是一个测试字典类型24', 'Y', NULL, NULL,
        '2025-07-04 22:24:29', '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150376994869249, 'TEST25', '测试字典类型25', '这是一个测试字典类型25', 'Y', NULL, NULL,
        '2025-07-04 22:25:29', '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150377061978113, 'TEST26', '测试字典类型26', '这是一个测试字典类型26', 'Y', NULL, NULL,
        '2025-07-04 22:26:29', '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150377129086977, 'TEST27', '测试字典类型27', '这是一个测试字典类型27', 'Y', NULL, NULL,
        '2025-07-04 22:27:29', '2025-07-04 23:01:29', NULL, NULL);
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1941150377196195842, 'TEST28', '测试字典类型28', '这是一个测试字典类型28', 'Y', NULL, 1, '2025-07-04 22:28:29',
        '2025-11-22 19:17:50', NULL, '云逸');
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1992518138560061441, 'TEST29', '测试字典类型29', 'TEST29', 'Y', 1, 1, '2025-11-23 16:58:38',
        '2025-11-23 16:58:38', '云逸', '云逸');
INSERT INTO sys_dict_type (id, type_code, name, description, status, create_by, update_by, create_time, update_time,
                           create_name, update_name)
VALUES (1992890401763430402, 'GRANT_TYPE', '授权模式', 'OAuth2授权模式', 'Y', 1, 1, '2025-11-24 17:37:53',
        '2025-11-24 17:37:53', '云逸', '云逸');

INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1941021366520418305, 'GENDER', '2', '女性', 2, 'Y', '', 1, 1, '2025-07-04 14:28:51', '2025-07-04 23:37:09',
        '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1941022491478876162, 'GENDER', '1', '男性', 1, 'Y', '', 1, 1, '2025-07-04 14:33:19', '2025-07-04 23:31:56',
        '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1941129951204143105, 'GENDER', '0', '未知性别', 0, 'Y', '', 1, 1, '2025-07-04 21:40:19', '2025-07-04 23:36:02',
        '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1941130101435723778, 'GENDER', '9', '未说明的性别', 3, 'Y', '', 1, 1, '2025-07-04 21:40:55',
        '2025-07-04 23:44:32', '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1941130993341886466, 'STATUS', 'Y', '启用', 0, 'Y', '', 1, 1, '2025-07-04 21:44:28', '2025-07-04 23:36:18',
        '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1941131019975716865, 'STATUS', 'N', '禁用', 1, 'Y', '', 1, 1, '2025-07-04 21:44:34', '2025-07-04 21:44:34',
        '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1941144088470687745, 'HTTP_METHOD', 'GET', 'HTTP GET', 0, 'Y', '', 1, 1, '2025-07-04 22:36:30',
        '2025-07-18 14:00:26', '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1941144135048433665, 'HTTP_METHOD', 'POST', 'HTTP POST', 1, 'Y', '', 1, 1, '2025-07-04 22:36:41',
        '2025-07-18 14:00:34', '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1941144215943974914, 'HTTP_METHOD', 'PUT', 'HTTP PUT', 2, 'Y', '', 1, 1, '2025-07-04 22:37:00',
        '2025-07-18 14:00:41', '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1941144267756212225, 'HTTP_METHOD', 'DELETE', 'HTTP DELETE', 3, 'Y', '', 1, 1, '2025-07-04 22:37:13',
        '2025-07-18 14:00:48', '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1941145170768891906, 'MODULE', 'auth', '授权服务', 0, 'Y', '', 1, 1, '2025-07-04 22:40:48',
        '2025-07-04 22:40:48', '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1941145223994609666, 'MODULE', 'system', '系统服务', 1, 'Y', '', 1, 1, '2025-07-04 22:41:01',
        '2025-07-04 22:41:01', '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1992202055529607170, 'TEST28', '28', '字典项', 1, 'Y', '', 1, 1, '2025-11-22 20:02:38', '2025-11-22 20:02:38',
        '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1992890537277198338, 'GRANT_TYPE', 'password', '密码模式', 0, 'Y', '', 1, 1, '2025-11-24 17:38:25',
        '2025-11-24 17:38:25', '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1992890598971215874, 'GRANT_TYPE', 'email', '邮件模式', 1, 'Y', '', 1, 1, '2025-11-24 17:38:40',
        '2025-11-24 17:38:40', '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1992890680663674881, 'GRANT_TYPE', 'authorization_code', '授权码模式', 2, 'Y', '', 1, 1, '2025-11-24 17:38:59',
        '2025-11-24 17:38:59', '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1992890722547994626, 'GRANT_TYPE', 'refresh_token', '刷新token', 3, 'Y', '', 1, 1, '2025-11-24 17:39:09',
        '2025-11-24 17:39:09', '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1992890876319567873, 'GRANT_TYPE', 'client_credentials', '客户端模式', 4, 'Y', '', 1, 1, '2025-11-24 17:39:46',
        '2025-11-24 17:39:46', '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1992891174664605697, 'GRANT_TYPE', 'urn:ietf:params:oauth:grant-type:jwt-bearer', 'JWT 断言授权模式', 5, 'Y',
        '', 1, 1, '2025-11-24 17:40:57', '2025-11-24 17:40:57', '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1992891294122577922, 'GRANT_TYPE', 'urn:ietf:params:oauth:grant-type:device_code', '设备授权模式', 6, 'Y', '',
        1, 1, '2025-11-24 17:41:25', '2025-11-24 17:41:25', '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1992891408002125825, 'GRANT_TYPE', 'urn:ietf:params:oauth:grant-type:token-exchange', '令牌交换授权模式', 7,
        'Y', '', 1, 1, '2025-11-24 17:41:52', '2025-11-24 17:41:52', '云逸', '云逸');
INSERT INTO sys_dict_item (id, type_code, item_code, item_name, sort_order, status, i18n_json, create_by, update_by,
                           create_time, update_time, create_name, update_name)
VALUES (1995047971718483970, 'MODULE', 'workflow', '流程引擎服务', 2, 'Y', '', 1, 1, '2025-11-30 16:31:17',
        '2025-11-30 16:31:17', '云逸', '云逸');

INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1864241504623833089, '修改权限信息', '修改权限信息', 'permission:updatePermission',
        '/permission/updatePermission', 'PUT', 3, NULL, NULL, 1, 1919661224031989762, '', '', 0, 'ep:bell', 'ep:bell',
        '', '', '', '', 0, 1, 0, 0, 0, 9, NULL, 1, 1, '云逸', '云逸', '2024-12-04 17:35:50', '2025-08-06 09:57:30');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1870104848520814594, '查询权限详情', '查询权限详情', 'permission:permissionDetails',
        '/permission/permissionDetails/{id}', 'GET', 3, NULL, NULL, 1, 1919661224031989762, '', '', 1, 'ep:bell',
        'ep:bell', '', '', '', 'true', 0, 1, 0, 0, 0, 7, NULL, 1, 1, '云逸', '云逸', '2024-12-20 21:51:55',
        '2025-08-06 09:52:40');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1919658873598554113, '系统管理', 'System', 'menu:system', '/system', NULL, 0, NULL, NULL, NULL, 0, '', '', 0,
        'ri:settings-3-line', '', '', '', '', '', 0, 1, 0, 0, 1, 1, NULL, 1, 1, '云逸', '云逸', '2025-05-06 15:41:56',
        '2025-11-22 15:27:58');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1919660730064613377, '用户管理', 'SystemUser', 'menu:system:user', '/system/user/index', NULL, 0, NULL, NULL,
        NULL, 1919658873598554113, '/system/user/index', '', 0, 'ri:admin-line', '', '', '', '', '', 0, 1, 0, 0, 1, 1,
        NULL, 1, 1, '云逸', '云逸', '2025-05-06 15:49:18', '2025-11-22 15:28:14');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1919660995832492033, '角色管理', 'SystemRole', 'menu:system:role', '/system/role/index', NULL, 0, NULL, NULL,
        NULL, 1919658873598554113, '/system/role/index', '', 0, 'ri:admin-fill', '', '', '', '', '', 0, 1, 0, 0, 1, 2,
        NULL, 1, 1, '云逸', '云逸', '2025-05-06 15:50:22', '2025-11-22 15:28:24');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1919661224031989762, '权限管理', 'SystemMenu', 'menu:system:menu', '/system/menu/index', NULL, 0, NULL, NULL,
        NULL, 1919658873598554113, '/system/menu/index', '', 0, 'ep:menu', '', '', '', '', '', 0, 1, 0, 0, 1, 3, NULL,
        1, 1, '云逸', '云逸', '2025-05-06 15:51:16', '2025-11-22 15:28:38');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1919661595269836801, 'menus.pureDept', 'SystemDept', 'menu:system:dept', '/system/dept/index', NULL, 0, NULL,
        NULL, NULL, 1919658873598554113, '/system/dept/index', '', 1, 'ri:git-branch-line', '', '', '', '', '', 0, 1, 0,
        0, 1, 4, NULL, 1, 1, '云逸', '云逸', '2025-05-06 15:52:45', '2025-05-06 15:59:16');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952306933212164097, '字典管理', 'SystemDict', 'system:dict:index', '/system/dict/index', '', 0, 'system', NULL,
        1, 1919658873598554113, '/system/dict/index', '', 0, 'ri:book-2-line', '', '', '', '', '', 1, 1, 0, 0, 0, 4,
        NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:53:40', '2025-11-22 15:34:14');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184129, '修改scope信息', '修改scope信息', 'scope:update', '/scope/update', 'PUT', 3, 'auth', NULL,
        0, 1952657633259577345, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5,
        NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-05 17:36:03');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184130, '重置scope对应的权限', '重置scope对应的权限', 'scope:resetScopePermission',
        '/scope/resetScopePermission', 'PUT', 3, 'auth', NULL, 0, 1952657633259577345, NULL, NULL, 0, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 7, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-05 18:05:16');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184131, '更新客户端信息', '更新客户端信息', 'application:update', '/application/update', 'PUT', 3,
        'auth', NULL, 1, 1955127266340253698, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, 2, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-11-24 13:46:24');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184132, '测试Json请求验证', '测试Json请求验证', 'test:validateJson', '/test/validateJson', 'POST',
        3, 'auth', NULL, 0, 1952661871538835457, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, 2, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-05 17:24:50');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184133, '测试表单验证', '测试表单验证', 'test:validateForm', '/test/validateForm', 'POST', 3,
        'auth', NULL, 0, 1952661871538835457, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, 4, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-05 17:24:50');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184134, '保存scope信息', '保存scope信息', 'scope:save', '/scope/save', 'POST', 3, 'auth', NULL, 0,
        1952657633259577345, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4, NULL,
        1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-05 17:36:03');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184135, '移动端扫码', '移动端扫码', 'qr-code:app:scan', '/qr-code/app/scan', 'POST', 3, 'auth',
        NULL, 0, 1952671270177181698, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        3, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-05 18:01:40');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184136, '移动端确认登录', '移动端确认登录', 'qr-code:app:confirm', '/qr-code/app/confirm', 'POST',
        3, 'auth', NULL, 0, 1952671270177181698, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, 4, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-05 18:01:45');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184137, '保存客户端信息', '保存客户端信息', 'application:save', '/application/save', 'POST', 3,
        'auth', NULL, 1, 1955127266340253698, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, 3, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-11-24 13:46:19');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184138, '测试手机号验证', '测试手机号验证', 'test:validatePhone', '/test/validatePhone', 'GET', 3,
        'auth', NULL, 0, 1952661871538835457, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, 3, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-05 17:24:50');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184139, '测试接口，需要有profile权限', '测试接口，需要有profile权限', 'test:test01', '/test/test01',
        'GET', 3, 'auth', NULL, 0, 1952661871538835457, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, 1, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-05 17:24:50');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184140, '查询所有的scope', '查询所有的scope', 'scope:findScopeList', '/scope/findScopeList', 'GET',
        3, 'auth', NULL, 0, 1952657633259577345, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, 2, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-05 17:36:03');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184141, '查询scope对应的权限', '查询scope对应的权限', 'scope:findPermissionIdsByScope',
        '/scope/findPermissionIdsByScope/*', 'GET', 3, 'auth', NULL, 0, 1952657633259577345, NULL, NULL, 0, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-05 17:36:03');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184142, '根据入参分页查询scope信息', '根据入参分页查询scope信息', 'scope:findByPage',
        '/scope/findByPage', 'GET', 3, 'auth', NULL, 0, 1952657633259577345, NULL, NULL, 0, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-05 17:36:03');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184143, '轮询二维码状态', '轮询二维码状态', 'qr-code:poll', '/qr-code/poll', 'GET', 3, 'auth',
        NULL, 0, 1952671270177181698, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        1, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-05 18:01:35');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184144, '生成二维码', '生成二维码', 'qr-code:init', '/qr-code/init', 'GET', 3, 'auth', NULL, 0,
        1952671270177181698, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL,
        1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-05 18:01:35');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184145, '授权确认页面', '授权确认页面', 'oauth2:consent', '/oauth2/consent', 'GET', 3, 'auth',
        NULL, 0, 0, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9, NULL, 1, 1,
        '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-05 17:24:50');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184146, '登录页面', '登录页面', 'login', '/login', 'GET', 3, 'auth', NULL, 0, 0, NULL, NULL, 1,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL, 1, 1, '云逸', '云逸',
        '2025-08-04 17:54:29', '2025-08-05 18:00:30');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184147, '获取短信验证码', '获取短信验证码', 'getSmsCaptcha', '/getSmsCaptcha', 'GET', 3, 'auth',
        NULL, 0, 1952671616769298434, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        3, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-05 18:03:01');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184148, '获取邮件验证码', '获取邮件验证码', 'getEmailCaptcha', '/getEmailCaptcha', 'GET', 3,
        'auth', NULL, 0, 1952671616769298434, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, 1, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-05 18:02:57');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184149, '获取验证码', '获取验证码', 'getCaptcha', '/getCaptcha', 'GET', 3, 'auth', NULL, 0,
        1952671616769298434, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL,
        1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-05 18:03:01');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184150, '检查是否登录过', '检查是否登录过', 'check:login', '/check/login', 'GET', 3, 'auth', NULL,
        0, 0, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, 1, 1, '云逸',
        '云逸', '2025-08-04 17:54:29', '2025-08-05 18:03:01');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184151, '根据入参分页查询认证信息', '根据入参分页查询认证信息', 'authorization:findByPage',
        '/authorization/findByPage', 'GET', 3, 'auth', NULL, 0, 1952657828596703234, NULL, NULL, 0, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-06 09:43:57');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184152, '根据id查询认证信息详情', '根据id查询认证信息详情', 'authorization:findById',
        '/authorization/findById/*', 'GET', 3, 'auth', NULL, 0, 1952657828596703234, NULL, NULL, 0, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-05 18:03:37');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184153, '根据入参分页查询客户端信息', '根据入参分页查询客户端信息', 'application:findByPage',
        '/application/findByPage', 'GET', 3, 'auth', NULL, 0, 1955106668151087105, NULL, NULL, 0, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-12 11:23:43');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184154, '根据id查询客户端信息', '根据id查询客户端信息', 'application:findById',
        '/application/findById/*', 'GET', 3, 'auth', NULL, 0, 1955127266340253698, NULL, NULL, 0, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-12 12:42:08');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184155, '根据客户端id查询客户端信息', '根据客户端id查询客户端信息', 'application:findByClientId',
        '/application/findByClientId/*', 'GET', 3, 'auth', NULL, 0, 1955106668151087105, NULL, NULL, 0, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-12 11:23:43');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184156, '获取应用卡片列表数据', '获取应用卡片列表数据', 'application:cardListPage',
        '/application/cardListPage', 'GET', 3, 'auth', NULL, 0, 1955106668151087105, '', '', 0, '', '', '', '', '', '',
        0, 1, 0, 0, 0, 4, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-12 11:23:43');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184157, '设备码-设备码验证成功页面', '设备码-设备码验证成功页面', 'activated', '/activated', 'GET',
        3, 'auth', NULL, 0, 0, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 7, NULL,
        1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-05 18:04:03');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184158, '设备码-设备码验证页面', '设备码-设备码验证页面', 'activate', '/activate', 'GET', 3,
        'auth', NULL, 0, 0, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 8, NULL, 1,
        1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-05 18:04:03');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184159, '设备码-设备码验证成功页面', '设备码-设备码验证成功页面', '', '/', 'GET', 3, 'auth', NULL,
        0, 0, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9, NULL, 1, 1, '云逸',
        '云逸', '2025-08-04 17:54:29', '2025-08-05 18:04:03');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184160, '根据id删除scope', '根据id删除scope', 'scope:removeById', '/scope/removeById/*', 'DELETE',
        3, 'auth', NULL, 0, 1952657633259577345, '', '', 0, '', '', '', '', '', '', 0, 1, 0, 0, 0, 6, NULL, 1, 1,
        '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-05 18:05:16');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184161, '踢出登录', '踢出登录', 'authorization:offline', '/authorization/offline', 'DELETE', 3,
        'auth', NULL, 0, 1952657828596703234, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, 3, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:43:57');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184162, '根据数据id删除客户端', '根据数据id删除客户端', 'application:remove',
        '/application/remove/*', 'DELETE', 3, 'auth', NULL, 1, 1955106668151087105, '', '', 0, '', '', '', '', '', '',
        0, 1, 0, 0, 0, 3, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-11-24 13:45:09');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184163, 'Valid when multiple issuers are allowed', 'Valid when multiple issuers are allowed',
        '.well-known:oauth-authorization-server', '/.well-known/oauth-authorization-server/*', 'GET', 3, 'auth', NULL,
        0, 0, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 8, NULL, 1, 1, '云逸',
        '云逸', '2025-08-04 17:54:29', '2025-08-05 18:05:30');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184164, 'Valid when multiple issuers are allowed', 'Valid when multiple issuers are allowed',
        '.well-known:openid-configuration', '/*/.well-known/openid-configuration', 'GET', 3, 'auth', NULL, 0, 0, NULL,
        NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9, NULL, 1, 1, '云逸', '云逸',
        '2025-08-04 17:54:29', '2025-08-05 18:05:30');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184165, '更新用户角色', '更新用户角色', 'user:updateUserRoles', '/user/updateUserRoles', 'PUT', 3,
        'system', NULL, 0, 1919660730064613377, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, 7, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:56:57');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184166, '修改用户信息', '修改用户信息', 'user:updateBasicUser', '/user/updateBasicUser', 'PUT', 3,
        'system', NULL, 0, 1919660730064613377, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, 4, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:49:45');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184167, '重置密码', '重置密码', 'user:resetPassword', '/user/resetPassword', 'PUT', 3, 'system',
        NULL, 0, 1919660730064613377, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        5, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:49:45');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184168, '修改角色信息', '修改角色信息', 'role:updateRole', '/role/updateRole', 'PUT', 3, 'system',
        NULL, 0, 1919660995832492033, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        6, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:57:14');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184169, '更新角色权限', '更新角色权限', 'role:updateRolePermissions',
        '/role/updateRolePermissions', 'PUT', 3, 'system', NULL, 0, 1919660995832492033, NULL, NULL, 0, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-06 09:57:14');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184170, '批量修改权限信息', '批量修改权限信息', 'permission:batchUpdatePermissions',
        '/permission/batchUpdatePermissions', 'PUT', 3, 'system', NULL, 0, 1919661224031989762, NULL, NULL, 0, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, 1, 1, '云逸', '云逸',
        '2025-08-04 17:54:29', '2025-08-06 09:53:21');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184171, '根据字典类型ID查询字典类型', '根据字典类型ID查询字典类型', 'dict:type', '/dict/type/*',
        'GET', 3, 'system', NULL, 0, 1952306933212164097, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, 7, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:53:56');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184172, '更新字典类型', '更新字典类型', 'dict:type', '/dict/type/*', 'PUT', 3, 'system', NULL, 0,
        1952306933212164097, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9, NULL,
        1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:53:56');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184173, '删除字典类型', '删除字典类型', 'dict:type', '/dict/type/*', 'DELETE', 3, 'system', NULL,
        0, 1952306933212164097, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 11,
        NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:53:56');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184174, '根据字典项ID查询字典项', '根据字典项ID查询字典项', 'dict:item', '/dict/item/*', 'GET', 3,
        'system', NULL, 0, 1952306933212164097, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, 8, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:53:56');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184175, '更新字典项', '更新字典项', 'dict:item', '/dict/item/*', 'PUT', 3, 'system', NULL, 0,
        1952306933212164097, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 10, NULL,
        1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:53:56');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184176, '删除字典项', '删除字典项', 'dict:item', '/dict/item/*', 'DELETE', 3, 'system', NULL, 0,
        1952306933212164097, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 12, NULL,
        1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:53:56');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184177, '文件下载预签名', '文件下载预签名', 'common:pre:signed', '/common/pre/signed', 'GET', 3,
        'system', NULL, 0, 0, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4, NULL,
        1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:45:42');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184178, '文件上传预签名', '文件上传预签名', 'common:pre:signed', '/common/pre/signed', 'PUT', 3,
        'system', NULL, 0, 0, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL,
        1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:45:42');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184179, '删除文件预签名', '删除文件预签名', 'common:pre:signed', '/common/pre/signed', 'DELETE', 3,
        'system', NULL, 0, 0, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL,
        1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:45:42');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184180, '添加API接口信息', '添加API接口信息', 'api-endpoint', '/api-endpoint', 'POST', 3, 'system',
        NULL, 0, 1952658732645376001, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        8, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:55:42');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184181, '修改API接口信息', '修改API接口信息', 'api-endpoint', '/api-endpoint', 'PUT', 3, 'system',
        NULL, 0, 1952658732645376001, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        6, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:55:42');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184182, '设置接口状态为忽略', '设置接口状态为忽略', 'api-endpoint:ignore', '/api-endpoint/ignore',
        'PUT', 3, 'system', NULL, 0, 1952658732645376001, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, 7, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:55:42');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184183, '用户注册', '用户注册', 'user:userRegister', '/user/userRegister', 'POST', 3, 'system',
        NULL, 0, 1919660730064613377, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        1, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:46:51');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184184, '添加一条用户信息', '添加一条用户信息', 'user:insertBasicUser', '/user/insertBasicUser',
        'POST', 3, 'system', NULL, 0, 1919660730064613377, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, 3, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:49:45');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184185, '重新发送绑定确认', '重新发送绑定确认', 'third:user:resend-bind-confirmation',
        '/third/user/resend-bind-confirmation', 'POST', 3, 'system', NULL, 0, 0, NULL, NULL, 1, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-06 09:46:56');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184186, '获取增强的三方用户信息', '获取增强的三方用户信息', 'third:user:enhanced-third-user',
        '/third/user/enhanced-third-user', 'POST', 3, 'system', NULL, 0, 0, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-06 09:46:56');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184187, '绑定邮箱', '绑定邮箱', 'third:user:bind-email', '/third/user/bind-email', 'POST', 3,
        'system', NULL, 0, 0, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL,
        1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:46:56');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184188, '添加角色信息', '添加角色信息', 'role:insertRole', '/role/insertRole', 'POST', 3, 'system',
        NULL, 0, 1919660995832492033, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        4, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:57:14');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184189, '添加权限信息', '添加权限信息', 'permission:insertPermission',
        '/permission/insertPermission', 'POST', 3, 'system', NULL, 0, 1919661224031989762, NULL, NULL, 0, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-06 09:53:21');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184190, '获取没有父节点的权限id列表', '获取没有父节点的权限id列表',
        'permission:findNonParentPermissions', '/permission/findNonParentPermissions', 'POST', 3, 'system', NULL, 0,
        1919661224031989762, '', '', 0, '', '', '', '', '', '', 0, 1, 0, 0, 0, 3, NULL, 1, 1, '云逸', '云逸',
        '2025-08-04 17:54:29', '2025-08-06 09:53:21');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184191, '创建字典类型', '创建字典类型', 'dict:type', '/dict/type', 'POST', 3, 'system', NULL, 0,
        1952306933212164097, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL,
        1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:53:56');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184192, '创建字典项', '创建字典项', 'dict:item', '/dict/item', 'POST', 3, 'system', NULL, 0,
        1952306933212164097, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL,
        1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:53:56');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184193, '邮件发送', '邮件发送', 'common:email:sender', '/common/email/sender', 'POST', 3, 'system',
        NULL, 0, 0, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4, NULL, 1, 1,
        '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:47:59');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184194, '扫描系统接口', '扫描系统接口', 'api-endpoint:scan:endpoints',
        '/api-endpoint/scan/endpoints', 'POST', 3, 'system', NULL, 0, 1952658732645376001, NULL, NULL, 0, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-06 09:55:42');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184195, '查询API接口信息列表', '查询API接口信息列表', 'api-endpoint:list', '/api-endpoint/list',
        'POST', 3, 'system', NULL, 0, 1952658732645376001, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, 3, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:55:42');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184196, '批量导入接口到权限表', '批量导入接口到权限表', 'api-endpoint:import',
        '/api-endpoint/import', 'POST', 3, 'system', NULL, 0, 1952658732645376001, NULL, NULL, 0, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 10, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-06 09:55:42');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184197, '根据扫描批次ID导入接口', '根据扫描批次ID导入接口', 'api-endpoint:import:batch',
        '/api-endpoint/import/batch/*', 'POST', 3, 'system', NULL, 0, 1952658732645376001, NULL, NULL, 0, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-06 09:55:42');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184198, '查询用户详情', '查询用户详情', 'user:userDetails', '/user/userDetails/*', 'GET', 3,
        'system', NULL, 0, 1919660730064613377, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, 2, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:49:45');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184199, '获取登录用户信息', '获取登录用户信息', 'user:loginUserinfo', '/user/loginUserinfo', 'GET',
        3, 'system', NULL, 0, 0, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4,
        NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:49:18');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184200, '获取注册时使用的邮箱验证码', '获取注册时使用的邮箱验证码', 'user:getRegisterEmailCode',
        '/user/getRegisterEmailCode/*', 'GET', 3, 'system', NULL, 0, 0, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-06 09:49:18');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184201, '分页查询基础用户信息列表', '分页查询基础用户信息列表', 'user:findByPage',
        '/user/findByPage', 'GET', 3, 'system', NULL, 0, 1919660730064613377, NULL, NULL, 0, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-06 09:49:45');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184202, '三方登录用户确认绑定本地账号', '三方登录用户确认绑定本地账号', 'third:user:confirm',
        '/third/user/confirm', 'GET', 3, 'system', NULL, 0, 0, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, 4, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:49:45');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184203, '检查当前三方用户是否绑定本地用户', '检查当前三方用户是否绑定本地用户',
        'third:user:check-binding', '/third/user/check-binding', 'GET', 3, 'system', NULL, 0, 0, NULL, NULL, 1, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, NULL, 1, 1, '云逸', '云逸',
        '2025-08-04 17:54:29', '2025-08-06 09:49:45');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184204, '获取绑定邮箱验证码', '获取绑定邮箱验证码', 'third:user:bind-email-code',
        '/third/user/bind-email-code/*', 'GET', 3, 'system', NULL, 0, 0, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-06 09:49:45');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184206, '查询角色详情', '查询角色详情', 'role:roleDetails', '/role/roleDetails/*', 'GET', 3,
        'system', NULL, 0, 1919660995832492033, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, 3, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:51:01');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184207, '根据条件查询所有角色列表', '根据条件查询所有角色列表', 'role:findRoles',
        '/role/findRoles', 'GET', 3, 'system', NULL, 0, 1919660995832492033, NULL, NULL, 0, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-06 09:51:01');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184208, '根据用户id查询角色id列表', '根据用户id查询角色id列表', 'role:findRoleIdsByUserId',
        '/role/findRoleIdsByUserId/*', 'GET', 3, 'system', NULL, 0, 1919660730064613377, NULL, NULL, 0, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-06 09:50:46');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184209, '分页查询角色信息列表', '分页查询角色信息列表', 'role:findByPage', '/role/findByPage',
        'GET', 3, 'system', NULL, 0, 1919660995832492033, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, 1, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:51:01');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184210, '查询权限详情', '查询权限详情', 'permission:permissionDetails',
        '/permission/permissionDetails/*', 'GET', 3, 'system', NULL, 0, 1919661224031989762, NULL, NULL, 0, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-06 09:53:21');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184211, '查询权限信息列表', '查询权限信息列表', 'permission:findPermissions',
        '/permission/findPermissions', 'GET', 3, 'system', NULL, 0, 1919661224031989762, NULL, NULL, 0, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-06 09:53:21');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184212, '根据角色id查询权限id列表', '根据角色id查询权限id列表',
        'permission:findPermissionIdsByRoleId', '/permission/findPermissionIdsByRoleId/*', 'GET', 3, 'system', NULL, 0,
        1919661224031989762, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 7, NULL,
        1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:53:21');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184213, '分页查询权限信息列表', '分页查询权限信息列表', 'permission:findByPage',
        '/permission/findByPage', 'GET', 3, 'system', NULL, 0, 1919661224031989762, NULL, NULL, 0, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-06 09:53:21');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184214, '查询所有字典类型', '查询所有字典类型', 'dict:type:page', '/dict/type/page', 'GET', 3,
        'system', NULL, 0, 1952306933212164097, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, 1, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:53:39');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184215, '查询所有字典项', '查询所有字典项', 'dict:type:all', '/dict/type/all', 'GET', 3, 'system',
        NULL, 0, 1952306933212164097, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        2, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:53:42');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184216, '根据字典类型编码查询字典项', '根据字典类型编码查询字典项', 'dict:item:type',
        '/dict/item/type/*', 'GET', 3, 'system', NULL, 0, 1952306933212164097, NULL, NULL, 0, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-06 09:53:56');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184217, '分页查询字典项', '分页查询字典项', 'dict:item:page', '/dict/item/page', 'GET', 3,
        'system', NULL, 0, 1952306933212164097, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, 3, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:53:56');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184218, '查询API扫描记录详情', '查询API扫描记录详情', 'api-scan', '/api-scan/*', 'GET', 3,
        'system', NULL, 0, 1952647718126247937, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, 1, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-08 10:56:56');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184219, '删除API扫描记录信息', '删除API扫描记录信息', 'api-scan', '/api-scan/*', 'DELETE', 3,
        'system', NULL, 0, 1952647718126247937, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, 3, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-08 10:56:56');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184220, '分页查询API扫描记录列表', '分页查询API扫描记录列表', 'api-scan:page', '/api-scan/page',
        'GET', 3, 'system', NULL, 0, 1952647718126247937, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, 2, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-08 10:56:56');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184221, '查询API接口详情', '查询API接口详情', 'api-endpoint', '/api-endpoint/*', 'GET', 3,
        'system', NULL, 0, 1952658732645376001, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, 4, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:55:42');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184222, '删除API接口信息', '删除API接口信息', 'api-endpoint', '/api-endpoint/*', 'DELETE', 3,
        'system', NULL, 0, 1952658732645376001, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, 11, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:55:42');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184223, '分页查询API接口列表', '分页查询API接口列表', 'api-endpoint:page', '/api-endpoint/page',
        'GET', 3, 'system', NULL, 0, 1952658732645376001, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, 2, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:55:42');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184224, '根据扫描批次ID查询接口列表', '根据扫描批次ID查询接口列表', 'api-endpoint:batch',
        '/api-endpoint/batch/*', 'GET', 3, 'system', NULL, 0, 1952658732645376001, NULL, NULL, 0, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29',
        '2025-08-06 09:55:42');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184225, '删除用户信息', '删除用户信息', 'user:removeById', '/user/removeById/*', 'DELETE', 3,
        'system', NULL, 0, 1919660730064613377, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, 8, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:56:57');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184226, '删除角色信息', '删除角色信息', 'role:removeById', '/role/removeById/*', 'DELETE', 3,
        'system', NULL, 0, 1919660995832492033, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, 7, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:57:14');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952307138347184227, '删除权限信息', '删除权限信息', 'permission:removeById', '/permission/removeById/*',
        'DELETE', 3, 'system', NULL, 0, 1919661224031989762, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, 8, NULL, 1, 1, '云逸', '云逸', '2025-08-04 17:54:29', '2025-08-06 09:57:30');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952647718126247937, '接口扫描', 'pureApiScan', 'system:api-scan:index', '/system/api-scan/index', '', 0,
        'system', NULL, 1, 1919658873598554113, '/system/api-scan/index', '', 0, 'ri:scan-2-line', '', '', '', '', '',
        0, 1, 0, 0, 0, 5, NULL, 1, 1, '云逸', '云逸', '2025-08-05 16:27:49', '2025-11-22 15:34:57');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952657129544638465, '平台管理', 'Platform', 'platform', '/platform', '', 0, 'system', NULL, 1, 0, '', '', 0,
        'ri:planet-line', '', '', '', '', '', 0, 1, 0, 0, 1, 2, '', 1, 1, '云逸', '云逸', '2025-08-05 17:05:13',
        '2025-11-22 15:35:39');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952657433640067074, 'menus.application', 'PlatformApplication', 'platform:application:index',
        '/platform/application/index', '', 0, 'auth', NULL, 1, 1952657129544638465, '/platform/application/index', '',
        1, 'ri:apps-line', '', '', '', '', '', 0, 1, 0, 0, 1, 1, '', 1, 1, '云逸', '云逸', '2025-08-05 17:06:25',
        '2025-08-08 15:30:21');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952657633259577345, 'Scope管理', 'PlatformScope', 'platform:scope:index', '/platform/scope/index', '', 0,
        'auth', NULL, 1, 1952657129544638465, '/platform/scope/index', '', 0, 'ep:connection', '', '', '', '', '', 0, 1,
        0, 0, 0, 3, '', 1, 1, '云逸', '云逸', '2025-08-05 17:07:13', '2025-11-22 15:36:22');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952657828596703234, '认证管理', 'Authorization', ':platform:authorization:index',
        '/platform/authorization/index', '', 0, '', NULL, 1, 1952657129544638465, '/platform/authorization/index', '',
        0, 'ri:login-circle-line', '', '', '', '', '', 0, 1, 0, 0, 0, 4, NULL, 1, 1, '云逸', '云逸',
        '2025-08-05 17:08:00', '2025-11-22 15:36:33');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952658080221388801, 'menus.applicationDetail', 'ApplicationDetails', 'platform:application:detail',
        '/platform/application/detail', '', 0, 'auth', NULL, 1, 1952657129544638465, '/platform/application/detail', '',
        1, 'ep:help', '', '', '', '', '', 0, 0, 0, 0, 1, 1, '/platform/application/index', 1, 1, '云逸', '云逸',
        '2025-08-05 17:09:00', '2025-08-12 11:23:43');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952658732645376001, '接口列表', 'ApiEndpoints', 'system:api-scan:endpoints', '/system/api-scan/endpoints', '',
        0, 'system', NULL, 1, 1952647718126247937, '/system/api-scan/endpoints', '', 0, 'fa-solid:cubes', '', '', '',
        '', '', 0, 0, 0, 0, 1, 6, '/system/api-scan/index', 1, 1, '云逸', '云逸', '2025-08-05 17:11:35',
        '2025-11-26 15:31:27');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952661871538835457, '测试接口', 'test', 'test', '/test', '', 0, '', NULL, 1, 0, 'test', '', 0,
        'ri:test-tube-line', '', '', '', '', '', 0, 0, 0, 0, 0, 5, NULL, 1, 1, '云逸', '云逸', '2025-08-05 17:24:04',
        '2025-12-30 15:53:40');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952662576219656194, '登录相关接口', 'login', 'login', 'login', '', 0, '', NULL, 1, 0, '', '', 1,
        'ri:login-box-line', '', '', '', '', '', 0, 0, 0, 0, 0, 4, NULL, 1, 1, '云逸', '云逸', '2025-08-05 17:26:52',
        '2025-08-05 18:03:37');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952671270177181698, '扫码登录', 'scan-qrcode', 'scan-qrcode', 'scan-qrcode', '', 0, '', NULL, 1,
        1952662576219656194, 'scan-qrcode', '', 1, 'ri:qr-scan-2-line', '', '', '', '', '', 0, 0, 0, 0, 0, 1, NULL, 1,
        1, '云逸', '云逸', '2025-08-05 18:01:24', '2025-08-05 18:01:45');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1952671616769298434, '公共接口', 'common', 'common', 'common', '', 0, '', NULL, 1, 0, 'common', '', 1,
        'ri:creative-commons-nd-line', '', '', '', '', '', 0, 1, 0, 0, 0, 5, NULL, 1, 1, '云逸', '云逸',
        '2025-08-05 18:02:47', '2025-08-05 18:03:37');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1955106668151087105, '应用中心', 'PlatformApplication', 'platform:application:index',
        '/platform/application/index', '', 0, 'auth', NULL, 1, 1952657129544638465, '/platform/application/index', '',
        0, 'ri:apps-line', '', '', '', '', '', 0, 1, 0, 0, 0, 2, '', 1, 1, '云逸', '云逸', '2025-08-12 11:18:48',
        '2025-11-22 15:36:09');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1955122763012546562, 'Swagger UI', 'SwaggerUi', 'swagger', '/swagger-ui', '', 1, '', NULL, 1, 0, '', '', 0,
        'ri:book-open-line', '', '', '', 'http://127.0.0.1:5500/swagger-ui/index.html', 'true', 0, 1, 0, 0, 0, 6, '', 1,
        1, '云逸', '云逸', '2025-08-12 12:22:46', '2025-12-30 15:53:49');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1955123033511600129, '监控中心', 'monitor', 'monitor', '/monitor', '', 1, 'system', NULL, 1, 0, '', '', 0,
        'ep:monitor', '', '', '', 'http://127.0.0.1:5500/monitor/', '', 0, 1, 0, 0, 0, 7, '', 1, 1, '云逸', '云逸',
        '2025-08-12 12:23:50', '2025-12-30 15:53:54');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1955127266340253698, '应用详情', 'ApplicationDetails', 'platform:application:detail', 'detail', '', 0, 'auth',
        NULL, 1, 1955106668151087105, '/platform/application/detail', '', 0, 'ep:help', '', '', '', '', '', 0, 0, 0, 0,
        0, 5, '/platform/application/index', 1, 1, '云逸', '云逸', '2025-08-12 12:40:39', '2025-11-26 14:24:34');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1995049788363821057, '流程管理', 'Workflow', 'workflow', '/workflow', '', 0, 'workflow', NULL, 1, 0, '', '', 0,
        'ri:flow-chart', '', '', '', '', 'true', 0, 1, 0, 0, 0, 3, '', 1, 1, '云逸', '云逸', '2025-11-30 16:38:30',
        '2025-12-01 13:40:40');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1995051686886166530, '流程模型管理', 'ProcessModel', 'workflow:model:index', 'model', '', 0, 'workflow', NULL,
        1, 1995049788363821057, '/workflow/model/index', '', 0, 'ri:mini-program-line', '', '', '', '', 'true', 0, 1, 0,
        0, 0, 2, '', 1, 1, '云逸', '云逸', '2025-11-30 16:46:03', '2025-12-24 17:23:06');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1995089581575557122, '流程设计器', 'ProcessDesigner', 'workflow:model:pages:ProcessDesigner', '/designer', '',
        0, '', NULL, 1, 0, '/workflow/model/pages/ProcessDesigner', '', 0, 'fa7-solid:diagram-project', '', '', '', '',
        'true', 0, 0, 0, 0, 0, 8, '/workflow/definition', 1, 1, '云逸', '云逸', '2025-11-30 19:16:38',
        '2025-12-30 15:53:59');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1995089694150676482, '根据 ID 查询流程定义', '根据 ID 查询流程定义', 'process-model', '/process-model/*', 'GET',
        3, 'workflow', NULL, 1, 1995051686886166530, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, 99, NULL, 1, 1, '云逸', '云逸', '2025-11-30 19:17:05', '2025-12-09 15:59:17');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1995089694150676483, '修改流程定义', '修改流程定义', 'process-model', '/process-model/*', 'PUT', 3, 'workflow',
        NULL, 1, 1995051686886166530, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        99, NULL, 1, 1, '云逸', '云逸', '2025-11-30 19:17:05', '2025-12-09 15:59:24');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1995089694150676484, '新增流程定义', '新增流程定义', 'process-model', '/process-model', 'POST', 3, 'workflow',
        NULL, 1, 1995051686886166530, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        99, NULL, 1, 1, '云逸', '云逸', '2025-11-30 19:17:05', '2025-12-09 15:59:37');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1995089694150676485, '发布流程定义（部署）', '发布流程定义（部署）', 'process-model:publish',
        '/process-model/*/publish', 'POST', 3, 'workflow', NULL, 1, 1995051686886166530, NULL, NULL, 0, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 99, NULL, 1, 1, '云逸', '云逸', '2025-11-30 19:17:05',
        '2025-12-09 15:59:47');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1995089694150676486, '启用流程定义', '启用流程定义', 'process-model:enable', '/process-model/*/enable', 'POST',
        3, 'workflow', NULL, 1, 1995051686886166530, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, 99, NULL, 1, 1, '云逸', '云逸', '2025-11-30 19:17:05', '2025-12-09 15:59:55');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1995089694150676487, '禁用流程定义', '禁用流程定义', 'process-model:disable', '/process-model/*/disable',
        'POST', 3, 'workflow', NULL, 1, 1995051686886166530, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, 99, NULL, 1, 1, '云逸', '云逸', '2025-11-30 19:17:05', '2025-12-09 16:00:00');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1995089694150676488, '分页列表查询', '分页列表查询', 'process-model:page', '/process-model/page', 'GET', 3,
        'workflow', NULL, 1, 1995051686886166530, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, 99, NULL, 1, 1, '云逸', '云逸', '2025-11-30 19:17:05', '2025-12-09 16:00:04');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1995089694150676489, '查询流程定义历史', '查询流程定义历史', 'process-model:history:page',
        '/process-model/history/page', 'GET', 3, 'workflow', NULL, 1, 1995051686886166530, NULL, NULL, 0, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 99, NULL, 1, 1, '云逸', '云逸', '2025-11-30 19:17:05',
        '2025-12-09 16:00:12');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1995089694150676490, '删除流程定义', '删除流程定义', 'process-model', '/process-model/*', 'DELETE', 3,
        'workflow', NULL, 1, 1995051686886166530, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, 99, NULL, 1, 1, '云逸', '云逸', '2025-11-30 19:17:05', '2025-12-09 16:00:18');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1995130962327162881, '流程定义历史', 'ModelHistory', 'workflow:model:pages:DefinitionHistory', 'history', '', 0,
        'workflow', NULL, 1, 1995051686886166530, '/workflow/model/pages/DefinitionHistory', '', 0, 'ri:history-line',
        '', '', '', '', 'true', 0, 0, 0, 0, 0, 2, '/workflow/model', 1, 1, '云逸', '云逸', '2025-11-30 22:01:04',
        '2025-12-09 14:33:06');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1995367129987469313, '分页查询流程部署数据', '分页查询流程部署数据', 'process-deployment:page',
        '/process-deployment/page', 'GET', 3, 'workflow', NULL, 1, 1995713964681777153, NULL, NULL, 0, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 1, 1, '云逸', '云逸', '2025-12-01 13:39:31',
        '2025-12-02 12:38:44');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1995367129987469314, '根据 ProcessKey 查询流程定义', '根据 ProcessKey 查询流程定义', 'process-definition:key',
        '/process-definition/key/*', 'GET', 3, 'workflow', NULL, 1, 1995089581575557122, NULL, NULL, 0, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 1, 1, '云逸', '云逸', '2025-12-01 13:39:31',
        '2025-12-01 13:42:10');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1995713964681777153, '流程部署管理', 'ProcessDeployment', 'workflow:deployment:index', 'deployment', '', 0,
        'workflow', NULL, 1, 1995049788363821057, '/workflow/deployment/index', '', 0, 'ri:instance-line', '', '', '',
        '', 'true', 0, 1, 0, 0, 0, 3, '', 1, 1, '云逸', '云逸', '2025-12-02 12:37:43', '2025-12-24 17:23:13');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1995742837330989057, '删除部署的流程', '删除部署的流程', 'process-deployment:undeploy',
        '/process-deployment/*/undeploy', 'DELETE', 3, 'workflow', NULL, 1, 1995713964681777153, NULL, NULL, 0, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, 1, 1, '云逸', '云逸',
        '2025-12-02 14:32:26', '2025-12-02 14:33:29');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1995779456511561729, '流程定义管理', 'ProcessDefinition', 'workflow:definition:index', 'definition', '', 0,
        'workflow', NULL, 1, 1995049788363821057, '/workflow/definition/index', '', 0, 'ep:connection', '', '', '', '',
        'true', 0, 1, 0, 0, 0, 4, '', 1, 1, '云逸', '云逸', '2025-12-02 16:57:57', '2025-12-24 17:23:19');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1995779872049647618, '分页查询部署后的流程定义数据', '分页查询部署后的流程定义数据', 'process-definition:page',
        '/process-definition/page', 'GET', 3, 'workflow', NULL, 1, 1995779456511561729, NULL, NULL, 0, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, 1, 1, '云逸', '云逸', '2025-12-02 16:59:36',
        '2025-12-09 16:00:35');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1996101712949305345, '流程定义详情', 'ProcessDetails', 'workflow:definition:pages:ProcessDetails', 'details',
        '', 0, 'workflow', NULL, 1, 1995779456511561729, '/workflow/definition/pages/ProcessDetails', '', 0, 'ep:view',
        '', '', '', '', 'true', 0, 0, 0, 0, 0, 2, '/workflow/definition', 1, 1, '云逸', '云逸', '2025-12-03 14:18:29',
        '2025-12-09 15:57:48');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1998302432691810305, '回退processKey对应的模型至指定版本', '回退processKey对应的模型至指定版本',
        'process-model:rollback', '/process-model/rollback/*/*', 'PUT', 3, 'workflow', NULL, 0, 1995051686886166530,
        NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 99, NULL, 1, 1, '云逸', '云逸',
        '2025-12-09 16:03:21', '2025-12-09 16:03:40');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1998302432691810306, '改变流程定义状态', '改变流程定义状态', 'process-definition:change-suspension-state',
        '/process-definition/change-suspension-state/*', 'PUT', 3, 'workflow', NULL, 1, 1995779456511561729, NULL, NULL,
        0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 99, NULL, 1, 1, '云逸', '云逸',
        '2025-12-09 16:03:21', '2025-12-09 16:04:56');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1998302432691810307, '根据 ProcessKey 查询流程模型', '根据 ProcessKey 查询流程模型', 'process-model:key',
        '/process-model/key/*', 'GET', 3, 'workflow', NULL, 0, 1995051686886166530, NULL, NULL, 0, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 99, NULL, 1, 1, '云逸', '云逸', '2025-12-09 16:03:21',
        '2025-12-09 16:04:00');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1998302432691810308, '查询流程定义详情', '查询流程定义详情', 'process-definition', '/process-definition/*',
        'GET', 3, 'workflow', NULL, 1, 1995779456511561729, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, 99, NULL, 1, 1, '云逸', '云逸', '2025-12-09 16:03:21', '2025-12-09 16:05:00');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (1998302432691810309, '根据部署后的流程定义ID获取BPMN XML', '根据部署后的流程定义ID获取BPMN XML',
        'process-definition:bpmn', '/process-definition/*/bpmn', 'GET', 3, 'workflow', NULL, 1, 1995779456511561729,
        NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 99, NULL, 1, 1, '云逸', '云逸',
        '2025-12-09 16:03:21', '2025-12-09 16:05:04');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (2003757243079880706, '获取流程表单设计详情', '获取流程表单设计详情', 'process-form', '/process-form/*', 'GET',
        3, 'workflow', NULL, 1, 2003757952085028866, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, 1, NULL, 1, 1, '云逸', '云逸', '2025-12-24 17:18:49', '2025-12-24 17:22:07');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (2003757243079880707, '修改流程表单设计接口', '修改流程表单设计接口', 'process-form', '/process-form/*', 'PUT',
        3, 'workflow', NULL, 1, 2003757952085028866, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, 2, NULL, 1, 1, '云逸', '云逸', '2025-12-24 17:18:49', '2025-12-24 17:22:21');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (2003757243079880708, '删除流程表单设计接口', '删除流程表单设计接口', 'process-form', '/process-form/*',
        'DELETE', 3, 'workflow', NULL, 1, 2003757952085028866, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, 3, NULL, 1, 1, '云逸', '云逸', '2025-12-24 17:18:49', '2025-12-24 17:22:32');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (2003757243079880709, '保存流程表单设计接口', '保存流程表单设计接口', 'process-form', '/process-form', 'POST', 3,
        'workflow', NULL, 1, 2003757952085028866, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, 4, NULL, 1, 1, '云逸', '云逸', '2025-12-24 17:18:49', '2025-12-24 17:22:43');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (2003757243079880710, '分页查询流程表单设计', '分页查询流程表单设计', 'process-form:page', '/process-form/page',
        'GET', 3, 'workflow', NULL, 1, 2003757952085028866, NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, 5, NULL, 1, 1, '云逸', '云逸', '2025-12-24 17:18:49', '2025-12-24 17:22:55');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (2003757952085028866, '表单设计', 'form', 'workflow:form', 'form', '', 0, 'workflow', NULL, 1,
        1995049788363821057, '/workflow/form/index', '', 0, 'ri:file-list-line', '', '', '', '', 'true', 0, 1, 0, 0, 0,
        1, '', 1, 1, '云逸', '云逸', '2025-12-24 17:21:38', '2025-12-24 17:23:24');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (2005905159185788929, '流程任务', 'ProcessTask', 'task', '/task', '', 0, 'workflow', NULL, 1, 0, '', '', 0,
        'ri:todo-line', '', '', '', '', 'true', 0, 1, 0, 0, 0, 4, '', 1, 1, '云逸', '云逸', '2025-12-30 15:33:53',
        '2025-12-30 15:54:10');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (2005905725257445378, '待办列表', 'TaskTodo', 'workflow:todo:index', 'todo', '', 0, 'workflow', NULL, 1,
        2005905159185788929, '/workflow/todo/index', '', 0, 'fa7-solid:tasks', '', '', '', '', 'true', 0, 1, 0, 0, 0, 2,
        '', 1, 1, '云逸', '云逸', '2025-12-30 15:36:07', '2026-01-13 14:32:47');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (2005915926127734785, '表单填写', 'FormSubmit', 'workflow:todo:pages:FormSubmit', 'form', '', 0, '', NULL, 1,
        2005905725257445378, 'workflow/todo/pages/FormSubmit', '', 0, 'ri:information-fill', '', '', '', '', 'true', 0,
        0, 0, 0, 1, 1, '/task/todo', 1, 1, '云逸', '云逸', '2025-12-30 16:16:40', '2025-12-30 16:16:40');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (2009612856632586241, '任务审批', 'TaskApprove', 'workflow:todo:pages:TaskApprove', 'approve', '', 0, '', NULL,
        1, 2005905725257445378, '/workflow/todo/pages/TaskApprove', '', 0, 'fa7-solid:file-circle-check', '', '', '',
        '', 'true', 0, 0, 0, 0, 0, 2, '/task/todo', 1, 1, '云逸', '云逸', '2026-01-09 21:06:57', '2026-01-09 21:25:51');
INSERT INTO sys_permission (id, title, name, permission, `path`, request_method, permission_type, module_name,
                            description, need_authentication, parent_id, component, redirect, deleted, icon, extra_icon,
                            enter_transition, leave_transition, frame_src, frame_loading, keep_alive, show_link,
                            hidden_tag, fixed_tag, show_parent, `rank`, active_path, create_by, update_by, create_name,
                            update_name, create_time, update_time)
VALUES (2010963174553305089, '我的流程', 'MyProcess', 'workflow:mine:index', 'mine', '', 0, 'workflow', NULL, 1,
        2005905159185788929, '/workflow/mine/index', '', 0, 'ep:suitcase', '', '', '', '', 'true', 0, 1, 0, 0, 0, 1, '',
        1, 1, '云逸', '云逸', '2026-01-13 14:32:38', '2026-01-13 14:32:38');


INSERT INTO sys_role (id, code, name, description, deleted, create_by, update_by, create_name, update_name, create_time,
                      update_time)
VALUES (1, 'normal', '普通用户', '普通用户', 0, 1, 1, '云逸', '云逸', '2024-11-25 17:37:41', '2024-11-25 17:37:41');
INSERT INTO sys_role (id, code, name, description, deleted, create_by, update_by, create_name, update_name, create_time,
                      update_time)
VALUES (1864508890354565121, 'addRole01', '添加用户01', '测试添加用户-01', 0, 1, 1, '云逸', '云逸',
        '2024-12-05 11:15:35', '2024-12-05 11:19:59');
INSERT INTO sys_role (id, code, name, description, deleted, create_by, update_by, create_name, update_name, create_time,
                      update_time)
VALUES (1864511627943235585, 'addRole02', '添加用户02', '测试添加用户-02', 0, 1, 1, '云逸', '云逸',
        '2024-12-05 11:26:28', '2024-12-05 11:26:28');
INSERT INTO sys_role (id, code, name, description, deleted, create_by, update_by, create_name, update_name, create_time,
                      update_time)
VALUES (1921501763071176706, 'testRole', '测试角色', '测试角色1', 0, 1, 1, '云逸', '云逸', '2025-05-11 17:44:55',
        '2025-05-11 17:49:14');
INSERT INTO sys_role (id, code, name, description, deleted, create_by, update_by, create_name, update_name, create_time,
                      update_time)
VALUES (1948585988513411073, 'admin', '管理员', '管理员角色', 0, 1, 1, '云逸', '云逸', '2025-07-25 11:27:57',
        '2025-07-25 11:27:57');
INSERT INTO sys_role (id, code, name, description, deleted, create_by, update_by, create_name, update_name, create_time,
                      update_time)
VALUES (1991419386055172098, 'addRole03', '添加用户03', '测试添加1231', 0, 1, 1, '云逸', '云逸', '2025-11-20 16:12:35',
        '2025-11-20 16:12:44');
INSERT INTO sys_role (id, code, name, description, deleted, create_by, update_by, create_name, update_name, create_time,
                      update_time)
VALUES (1991442806297927682, 'addRole04', '添加用户04', '测试04', 1, 1, 1, '云逸', '云逸', '2025-11-20 17:45:39',
        '2025-11-20 17:45:39');

INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1924347637514170369, 1921501763071176706, 1864241504623833089, 1, 1, '云逸', '云逸', '2025-05-19 14:13:24',
        '2025-05-19 14:13:24');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1924347637514170370, 1921501763071176706, 1919660730064613377, 1, 1, '云逸', '云逸', '2025-05-19 14:13:24',
        '2025-05-19 14:13:24');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1924347637514170371, 1921501763071176706, 1919661224031989762, 1, 1, '云逸', '云逸', '2025-05-19 14:13:24',
        '2025-05-19 14:13:24');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1924347637514170372, 1921501763071176706, 1919658873598554113, 1, 1, '云逸', '云逸', '2025-05-19 14:13:24',
        '2025-05-19 14:13:24');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1948569589959176194, 1, 1, 1, 1, '云逸', '云逸', '2025-07-25 10:22:48', '2025-07-25 10:22:48');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1948569589963370497, 1, 1870104848520814594, 1, 1, '云逸', '云逸', '2025-07-25 10:22:48',
        '2025-07-25 10:22:48');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1948569589963370498, 1, 1864241504623833089, 1, 1, '云逸', '云逸', '2025-07-25 10:22:48',
        '2025-07-25 10:22:48');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1948569589963370499, 1, 1919661224031989762, 1, 1, '云逸', '云逸', '2025-07-25 10:22:48',
        '2025-07-25 10:22:48');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1948569589963370500, 1, 1948569373352734721, 1, 1, '云逸', '云逸', '2025-07-25 10:22:48',
        '2025-07-25 10:22:48');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1948569589963370501, 1, 1919660730064613377, 1, 1, '云逸', '云逸', '2025-07-25 10:22:48',
        '2025-07-25 10:22:48');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1948569589963370502, 1, 1919658873598554113, 1, 1, '云逸', '云逸', '2025-07-25 10:22:48',
        '2025-07-25 10:22:48');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1948569710960652290, 1864508890354565121, 1864241504623833089, 1, 1, '云逸', '云逸', '2025-07-25 10:23:16',
        '2025-07-25 10:23:16');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1948569710960652291, 1864508890354565121, 1919660995832492033, 1, 1, '云逸', '云逸', '2025-07-25 10:23:16',
        '2025-07-25 10:23:16');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1948569710960652292, 1864508890354565121, 1870104848520814594, 1, 1, '云逸', '云逸', '2025-07-25 10:23:16',
        '2025-07-25 10:23:16');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1948569710960652293, 1864508890354565121, 1919661224031989762, 1, 1, '云逸', '云逸', '2025-07-25 10:23:16',
        '2025-07-25 10:23:16');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1948569710960652294, 1864508890354565121, 1948569373352734721, 1, 1, '云逸', '云逸', '2025-07-25 10:23:16',
        '2025-07-25 10:23:16');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1948569710960652295, 1864508890354565121, 1919660730064613377, 1, 1, '云逸', '云逸', '2025-07-25 10:23:16',
        '2025-07-25 10:23:16');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1948569710960652296, 1864508890354565121, 1919658873598554113, 1, 1, '云逸', '云逸', '2025-07-25 10:23:16',
        '2025-07-25 10:23:16');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1991441187334332417, 1991419386055172098, 1919660730064613377, 1, 1, '云逸', '云逸', '2025-11-20 17:39:13',
        '2025-11-20 17:39:13');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1991441187334332418, 1991419386055172098, 1952307138347184201, 1, 1, '云逸', '云逸', '2025-11-20 17:39:13',
        '2025-11-20 17:39:13');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1991441187334332419, 1991419386055172098, 1952307138347184198, 1, 1, '云逸', '云逸', '2025-11-20 17:39:13',
        '2025-11-20 17:39:13');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1991441187334332420, 1991419386055172098, 1952307138347184184, 1, 1, '云逸', '云逸', '2025-11-20 17:39:13',
        '2025-11-20 17:39:13');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1991441187334332421, 1991419386055172098, 1952307138347184166, 1, 1, '云逸', '云逸', '2025-11-20 17:39:13',
        '2025-11-20 17:39:13');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1991441187334332422, 1991419386055172098, 1952307138347184167, 1, 1, '云逸', '云逸', '2025-11-20 17:39:13',
        '2025-11-20 17:39:13');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1991441187334332423, 1991419386055172098, 1952307138347184208, 1, 1, '云逸', '云逸', '2025-11-20 17:39:13',
        '2025-11-20 17:39:13');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1991441187334332424, 1991419386055172098, 1952307138347184165, 1, 1, '云逸', '云逸', '2025-11-20 17:39:13',
        '2025-11-20 17:39:13');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1991441187334332425, 1991419386055172098, 1952307138347184225, 1, 1, '云逸', '云逸', '2025-11-20 17:39:13',
        '2025-11-20 17:39:13');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1991441187334332426, 1991419386055172098, 1952307138347184139, 1, 1, '云逸', '云逸', '2025-11-20 17:39:13',
        '2025-11-20 17:39:13');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1991441187334332427, 1991419386055172098, 1952307138347184132, 1, 1, '云逸', '云逸', '2025-11-20 17:39:13',
        '2025-11-20 17:39:13');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1991441187334332428, 1991419386055172098, 1952307138347184138, 1, 1, '云逸', '云逸', '2025-11-20 17:39:13',
        '2025-11-20 17:39:13');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1991441187334332429, 1991419386055172098, 1919658873598554113, 1, 1, '云逸', '云逸', '2025-11-20 17:39:13',
        '2025-11-20 17:39:13');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (1991441187334332430, 1991419386055172098, 1952661871538835457, 1, 1, '云逸', '云逸', '2025-11-20 17:39:13',
        '2025-11-20 17:39:13');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993793, 1948585988513411073, 1952307138347184218, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993794, 1948585988513411073, 1952307138347184201, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993795, 1948585988513411073, 1952307138347184198, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993796, 1948585988513411073, 1952307138347184184, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993797, 1948585988513411073, 1952307138347184166, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993798, 1948585988513411073, 1952307138347184167, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993799, 1948585988513411073, 1952307138347184208, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993800, 1948585988513411073, 1952307138347184165, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993801, 1948585988513411073, 1952307138347184225, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993802, 1948585988513411073, 1952307138347184209, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993803, 1948585988513411073, 1952307138347184207, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993804, 1948585988513411073, 1952307138347184206, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993805, 1948585988513411073, 1952307138347184188, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993806, 1948585988513411073, 1952307138347184169, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993807, 1948585988513411073, 1952307138347184168, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993808, 1948585988513411073, 1952307138347184226, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993809, 1948585988513411073, 1952307138347184213, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993810, 1948585988513411073, 1952307138347184211, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993811, 1948585988513411073, 1952307138347184190, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993812, 1948585988513411073, 1952307138347184210, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993813, 1948585988513411073, 1952307138347184189, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993814, 1948585988513411073, 1952307138347184170, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993815, 1948585988513411073, 1952307138347184212, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993816, 1948585988513411073, 1952307138347184227, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993817, 1948585988513411073, 1864241504623833089, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993818, 1948585988513411073, 1952307138347184214, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993819, 1948585988513411073, 1952307138347184215, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993820, 1948585988513411073, 1952307138347184217, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993821, 1948585988513411073, 1952307138347184216, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993822, 1948585988513411073, 1952307138347184191, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993823, 1948585988513411073, 1952307138347184192, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993824, 1948585988513411073, 1952307138347184171, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993825, 1948585988513411073, 1952307138347184174, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993826, 1948585988513411073, 1952307138347184172, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993827, 1948585988513411073, 1952307138347184175, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993828, 1948585988513411073, 1952307138347184173, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993829, 1948585988513411073, 1952307138347184176, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993830, 1948585988513411073, 1952307138347184220, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993831, 1948585988513411073, 1952307138347184219, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993832, 1948585988513411073, 1952307138347184224, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993833, 1948585988513411073, 1952307138347184223, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993834, 1948585988513411073, 1952307138347184195, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993835, 1948585988513411073, 1952307138347184221, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993836, 1948585988513411073, 1952307138347184194, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993837, 1948585988513411073, 1952307138347184181, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993838, 1948585988513411073, 1952307138347184182, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993839, 1948585988513411073, 1952307138347184180, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993840, 1948585988513411073, 1952307138347184197, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993841, 1948585988513411073, 1952307138347184196, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993842, 1948585988513411073, 1952307138347184222, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993843, 1948585988513411073, 1952307138347184155, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993844, 1948585988513411073, 1952307138347184139, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993845, 1948585988513411073, 1952307138347184152, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993846, 1948585988513411073, 1952307138347184154, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993847, 1948585988513411073, 1952307138347184142, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993848, 1948585988513411073, 1952307138347184153, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993849, 1948585988513411073, 1952307138347184162, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993850, 1948585988513411073, 1952307138347184156, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993851, 1948585988513411073, 1952307138347184140, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993852, 1948585988513411073, 1952307138347184141, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993853, 1948585988513411073, 1952307138347184134, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993854, 1948585988513411073, 1952307138347184129, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993855, 1948585988513411073, 1952307138347184160, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993856, 1948585988513411073, 1952307138347184130, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993857, 1948585988513411073, 1952307138347184151, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993858, 1948585988513411073, 1952307138347184161, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993859, 1948585988513411073, 1952307138347184131, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993860, 1948585988513411073, 1952307138347184137, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993861, 1948585988513411073, 1952307138347184132, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993862, 1948585988513411073, 1952307138347184138, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993863, 1948585988513411073, 1952307138347184133, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993864, 1948585988513411073, 1955122763012546562, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993865, 1948585988513411073, 1955123033511600129, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993866, 1948585988513411073, 1995367129987469314, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993867, 1948585988513411073, 1995130962327162881, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993868, 1948585988513411073, 1995089694150676482, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993869, 1948585988513411073, 1995089694150676483, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993870, 1948585988513411073, 1995089694150676484, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993871, 1948585988513411073, 1995089694150676485, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993872, 1948585988513411073, 1995089694150676486, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993873, 1948585988513411073, 1995089694150676487, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993874, 1948585988513411073, 1995089694150676488, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993875, 1948585988513411073, 1995089694150676489, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993876, 1948585988513411073, 1995089694150676490, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993877, 1948585988513411073, 1998302432691810305, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993878, 1948585988513411073, 1998302432691810307, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993879, 1948585988513411073, 1995367129987469313, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993880, 1948585988513411073, 1995742837330989057, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993881, 1948585988513411073, 1995779872049647618, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993882, 1948585988513411073, 1996101712949305345, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993883, 1948585988513411073, 1998302432691810306, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993884, 1948585988513411073, 1998302432691810308, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993885, 1948585988513411073, 1998302432691810309, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993886, 1948585988513411073, 2003757243079880706, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993887, 1948585988513411073, 2003757243079880707, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993888, 1948585988513411073, 2003757243079880708, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993889, 1948585988513411073, 2003757243079880709, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993890, 1948585988513411073, 2003757243079880710, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993891, 1948585988513411073, 1952658732645376001, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993892, 1948585988513411073, 1955127266340253698, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993893, 1948585988513411073, 1919660730064613377, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993894, 1948585988513411073, 1919660995832492033, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993895, 1948585988513411073, 1919661224031989762, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993896, 1948585988513411073, 1952306933212164097, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993897, 1948585988513411073, 1952647718126247937, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993898, 1948585988513411073, 1955106668151087105, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993899, 1948585988513411073, 1952657633259577345, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993900, 1948585988513411073, 1952657828596703234, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993901, 1948585988513411073, 2003757952085028866, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993902, 1948585988513411073, 1995051686886166530, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993903, 1948585988513411073, 1995713964681777153, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993904, 1948585988513411073, 1995779456511561729, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993905, 1948585988513411073, 1919658873598554113, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993906, 1948585988513411073, 1952657129544638465, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993907, 1948585988513411073, 1995049788363821057, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993908, 1948585988513411073, 1952661871538835457, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993909, 1948585988513411073, 1995089581575557122, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993910, 1948585988513411073, 2005905159185788929, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993911, 1948585988513411073, 2010963174553305089, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993912, 1948585988513411073, 2005905725257445378, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993913, 1948585988513411073, 2005915926127734785, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');
INSERT INTO sys_role_permission (id, role_id, permission_id, create_by, update_by, create_name, update_name,
                                 create_time, update_time)
VALUES (2010963255192993914, 1948585988513411073, 2009612856632586241, 1, 1, '云逸', '云逸', '2026-01-13 14:32:57',
        '2026-01-13 14:32:57');

INSERT INTO sys_user_role (id, role_id, user_id, create_by, update_by, create_name, update_name, create_time,
                           update_time)
VALUES (1921489309037510658, 1, 1862332106783637506, 1, 1, '云逸', '云逸', '2025-05-11 16:55:26',
        '2025-05-11 16:55:26');
INSERT INTO sys_user_role (id, role_id, user_id, create_by, update_by, create_name, update_name, create_time,
                           update_time)
VALUES (1921489309037510659, 1864508890354565121, 1862332106783637506, 1, 1, '云逸', '云逸', '2025-05-11 16:55:26',
        '2025-05-11 16:55:26');
INSERT INTO sys_user_role (id, role_id, user_id, create_by, update_by, create_name, update_name, create_time,
                           update_time)
VALUES (1923297772209012737, 1, 1920404587435859970, 1, 1, '云逸', '云逸', '2025-05-16 16:41:37',
        '2025-05-16 16:41:37');
INSERT INTO sys_user_role (id, role_id, user_id, create_by, update_by, create_name, update_name, create_time,
                           update_time)
VALUES (1923297772209012738, 1864511627943235585, 1920404587435859970, 1, 1, '云逸', '云逸', '2025-05-16 16:41:37',
        '2025-05-16 16:41:37');
INSERT INTO sys_user_role (id, role_id, user_id, create_by, update_by, create_name, update_name, create_time,
                           update_time)
VALUES (1948586077352964098, 1, 1, 1, 1, '云逸', '云逸', '2025-07-25 11:28:18', '2025-07-25 11:28:18');
INSERT INTO sys_user_role (id, role_id, user_id, create_by, update_by, create_name, update_name, create_time,
                           update_time)
VALUES (1948586077352964099, 1948585988513411073, 1, 1, 1, '云逸', '云逸', '2025-07-25 11:28:18',
        '2025-07-25 11:28:18');
INSERT INTO sys_user_role (id, role_id, user_id, create_by, update_by, create_name, update_name, create_time,
                           update_time)
VALUES (1991420063233941506, 1, 1990332148267409409, 1, 1, '云逸', '云逸', '2025-11-20 16:15:17',
        '2025-11-20 16:15:17');
INSERT INTO sys_user_role (id, role_id, user_id, create_by, update_by, create_name, update_name, create_time,
                           update_time)
VALUES (1991420063233941507, 1864508890354565121, 1990332148267409409, 1, 1, '云逸', '云逸', '2025-11-20 16:15:17',
        '2025-11-20 16:15:17');
INSERT INTO sys_user_role (id, role_id, user_id, create_by, update_by, create_name, update_name, create_time,
                           update_time)
VALUES (1991420063233941508, 1864511627943235585, 1990332148267409409, 1, 1, '云逸', '云逸', '2025-11-20 16:15:17',
        '2025-11-20 16:15:17');
INSERT INTO sys_user_role (id, role_id, user_id, create_by, update_by, create_name, update_name, create_time,
                           update_time)
VALUES (1991420063233941509, 1921501763071176706, 1990332148267409409, 1, 1, '云逸', '云逸', '2025-11-20 16:15:17',
        '2025-11-20 16:15:17');
INSERT INTO sys_user_role (id, role_id, user_id, create_by, update_by, create_name, update_name, create_time,
                           update_time)
VALUES (1991420063233941510, 1948585988513411073, 1990332148267409409, 1, 1, '云逸', '云逸', '2025-11-20 16:15:17',
        '2025-11-20 16:15:17');
INSERT INTO sys_user_role (id, role_id, user_id, create_by, update_by, create_name, update_name, create_time,
                           update_time)
VALUES (1991420063233941511, 1991419386055172098, 1990332148267409409, 1, 1, '云逸', '云逸', '2025-11-20 16:15:17',
        '2025-11-20 16:15:17');