create table banner (
	`id` BIGINT(20) NOT NULL COMMENT 'id',
	`name` VARCHAR(20) default null comment '名称',
	`forward_type` varchar(2) default null comment '跳转类型',
	`forward_url` varchar(255) default null comment '跳转地址',
	`image_url` varchar(255) default null comment '图片地址',
	`status` varchar(2) default null comment'当前状态',
	`sort` int(10) default 0 comment '排序',
	`creator` varchar (32) default null comment '创建者姓名',
	`creator_id` bigint(20) default null comment '创建者id',
	`create_time` timestamp comment '创建时间',
	`update_time` timestamp comment '更新时间',
	`delete_time` timestamp comment '停用时间',
  `remark` varchar (64) comment '备注',
  primary key (`id`)
)ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET = utf8 COMMENT 'banner管理';