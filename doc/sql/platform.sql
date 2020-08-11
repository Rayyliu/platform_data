CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `creat_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `project_name` varchar(255) NOT NULL COMMENT '项目名称',
  `project_description` varchar(255) NOT NULL COMMENT '项目描述',
  `project_module` varchar(255) NOT NULL COMMENT '项目模块',
  `tester` varchar(255) NOT NULL COMMENT '项目测试负责人',
  `is_valid` tinyint(1) NOT NULL DEFAULT '0' COMMENT '项目是由启用',
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creat_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `interface` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '接口id',
  `interface_name` varchar(255) NOT NULL COMMENT '接口名称',
  `project` varchar(255) NOT NULL COMMENT '接口所属项目',
  `path` varchar(255) NOT NULL COMMENT '接口请求路径',
  `method` varchar(255) NOT NULL COMMENT '接口请求类型',
  `mode` varchar(255) NOT NULL COMMENT '数据传输方式，data、json',
  `headerDetail` json DEFAULT NULL COMMENT '请求头详细信息',
  `body` json DEFAULT NULL COMMENT '请求体详细信息',
  `sign` tinyint(1) NOT NULL DEFAULT '0' COMMENT '接口是否需要动态生成sign。0：否定；1：肯定',
  `sign_entity` json DEFAULT NULL COMMENT '签名字段及值',
  `header` tinyint(1) NOT NULL DEFAULT '0' COMMENT '接口是否需要在header里添加动态生成的sign。0：否定；1：肯定',
  `mock` tinyint(1) NOT NULL DEFAULT '0' COMMENT '接口是否需要mock辅助。0：否定；1：肯定',
  `description` varchar(255) NOT NULL COMMENT '接口描述',
  `last_update_user` varchar(255) NOT NULL COMMENT '最近更新的用户',
  `creat_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;