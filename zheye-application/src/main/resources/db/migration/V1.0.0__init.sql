-- 用户信息表
create table `t_user` (
    `id` bigint(20) auto_increment not null AUTO_INCREMENT primary key,
    `nickname` varchar(100) not null comment '昵称',
    `email` varchar(100) not null comment '电子邮箱',
    `password` varchar(50) not null comment '密码',
    `description` varchar(500) default null comment '简介',
    `avatar` varchar(1024) default null comment '头像',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 专栏信息表
create table `t_column` (
    `id` bigint(20) not null AUTO_INCREMENT primary key,
    `title` varchar(100) not null comment '标题',
    `description` varchar(500) default null comment '描述',
    `avatar` varchar(1024) default null comment '头像',
    `user_id` bigint(20) not null comment '用户id',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 文章表
create table `t_post` (
    `id` bigint(20) auto_increment not null AUTO_INCREMENT primary key,
    `title` varchar(100) not null comment '标题',
    `excerpt` varchar(2048) default null comment '摘抄',
    `content` text default null comment '内容',
    `image` varchar(4096) default null comment '图片',
    `column_id` bigint(20) not null comment '专栏id',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 上传文件表
create table `t_image` (
   `id` bigint(20) auto_increment not null AUTO_INCREMENT primary key,
   `url` varchar(1024) not null comment '图片地址',
   `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
   `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
