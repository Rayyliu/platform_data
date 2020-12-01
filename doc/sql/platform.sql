CREATE TABLE `env` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '环境id',
  `env_name` varchar(255) NOT NULL COMMENT '环境名称',
  `url` varchar(255) NOT NULL COMMENT '环境地址',
  `project` varchar(255) NOT NULL COMMENT '所属项目',
  `env_description` varchar(255) NOT NULL COMMENT '环境描述',
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creat_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

CREATE TABLE `execute` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用例id',
  `case_name` varchar(255) NOT NULL COMMENT '用例名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述用例执行场景',
  `valid` tinyint(1) NOT NULL COMMENT '用例是否可以执行：0：否定；1：肯定',
  `interface_id` int(20) NOT NULL COMMENT '用例对应接口id',
  `project` varchar(255) NOT NULL COMMENT '接口所属项目',
  `body` json NOT NULL COMMENT '用例参数集合',
  `response` json NOT NULL COMMENT '实际返回结果',
  `case_execute_result` varchar(255) DEFAULT NULL COMMENT '用例执行结果',
  `assertion_content` json NOT NULL COMMENT '断言内容',
  `assert_result` varchar(255) DEFAULT NULL COMMENT '断言结果',
  `last_execute_time` datetime DEFAULT NULL COMMENT '最近一次用例执行时间',
  `last_execute_user` varchar(255) NOT NULL COMMENT '最近执行的用户',
  `create_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

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
  `sign_attribute` varchar(255) DEFAULT NULL COMMENT '签名属性',
  `sign_entity` json DEFAULT NULL COMMENT '签名属性需要的字段及值',
  `header` tinyint(1) NOT NULL DEFAULT '0' COMMENT '接口是否需要在header里添加动态生成的sign。0：否定；1：肯定',
  `mock` tinyint(1) NOT NULL DEFAULT '0' COMMENT '接口是否需要mock辅助。0：否定；1：肯定',
  `description` varchar(255) NOT NULL COMMENT '接口描述',
  `last_update_user` varchar(255) NOT NULL COMMENT '最近更新的用户',
  `creat_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;

CREATE TABLE `plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_name` varchar(255) DEFAULT NULL,
  `plan_description` varchar(255) DEFAULT NULL,
  `project` varchar(255) DEFAULT NULL,
  `env` varchar(255) DEFAULT NULL,
  `plan_content` varchar(255) DEFAULT NULL,
  `plan_execute_result` json DEFAULT NULL COMMENT '计划执行结果',
  `tester` varchar(255) DEFAULT NULL,
  `execute_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

CREATE TABLE `project` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '项目id',
  `project_name` varchar(255) NOT NULL COMMENT '项目名称',
  `project_description` varchar(255) NOT NULL COMMENT '项目描述',
  `project_module` varchar(255) NOT NULL COMMENT '项目模块',
  `tester` varchar(255) NOT NULL COMMENT '项目测试负责人',
  `valid` tinyint(1) NOT NULL DEFAULT '0' COMMENT '项目是由启用',
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creat_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `creat_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;