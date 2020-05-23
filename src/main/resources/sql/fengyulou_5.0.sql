# Host: localhost  (Version 5.7.27-log)
# Date: 2020-05-24 03:25:47
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "fengyulou_computer"
#

DROP TABLE IF EXISTS `fengyulou_computer`;
CREATE TABLE `fengyulou_computer` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL DEFAULT '0' COMMENT '项目id',
  `host` varchar(255) NOT NULL DEFAULT '' COMMENT '主机',
  `port` varchar(255) NOT NULL DEFAULT '' COMMENT '端口',
  `account` varchar(255) NOT NULL DEFAULT '' COMMENT '账号',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `delete` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0正常 1删除',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `insert_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `delete_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='任务表';

#
# Data for table "fengyulou_computer"
#

INSERT INTO `fengyulou_computer` VALUES (1,2,'123123','22','root','123123',0,1,'2020-05-24 01:56:01','2020-05-24 01:59:07','0000-00-00 00:00:00');

#
# Structure for table "fengyulou_member"
#

DROP TABLE IF EXISTS `fengyulou_member`;
CREATE TABLE `fengyulou_member` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '人员名称',
  `member_label_id` int(11) NOT NULL DEFAULT '0' COMMENT '人员标签id',
  `mobile` varchar(255) DEFAULT '' COMMENT '人员手机',
  `delete` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0正常 1删除',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `insert_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `delete_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='人员表';

#
# Data for table "fengyulou_member"
#

INSERT INTO `fengyulou_member` VALUES (1,'郭超',1,NULL,0,1,'2020-05-24 01:54:58','0000-00-00 00:00:00','0000-00-00 00:00:00'),(2,'杨文黎',1,NULL,0,1,'2020-05-24 01:59:32','0000-00-00 00:00:00','0000-00-00 00:00:00'),(3,'1231',2,NULL,1,1,'2020-05-24 01:59:43','0000-00-00 00:00:00','2020-05-24 01:59:46');

#
# Structure for table "fengyulou_member_label"
#

DROP TABLE IF EXISTS `fengyulou_member_label`;
CREATE TABLE `fengyulou_member_label` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '标签名称',
  `delete` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0正常 1删除',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `insert_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `delete_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='人员标签表';

#
# Data for table "fengyulou_member_label"
#

INSERT INTO `fengyulou_member_label` VALUES (1,'开发',0,1,'2020-05-24 01:54:56','0000-00-00 00:00:00','0000-00-00 00:00:00'),(2,'1111222',1,1,'2020-05-24 01:59:41','2020-05-24 02:00:09','2020-05-24 02:00:15'),(3,'123123',0,1,'2020-05-24 02:00:18','0000-00-00 00:00:00','0000-00-00 00:00:00');

#
# Structure for table "fengyulou_project"
#

DROP TABLE IF EXISTS `fengyulou_project`;
CREATE TABLE `fengyulou_project` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '项目名称',
  `delete` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0正常 1删除',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `insert_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `delete_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='项目表';

#
# Data for table "fengyulou_project"
#

INSERT INTO `fengyulou_project` VALUES (1,'和英',0,1,'0000-00-00 00:00:00','2020-05-24 01:50:12','0000-00-00 00:00:00'),(2,'11111',0,1,'0000-00-00 00:00:00','2020-05-24 01:59:05','0000-00-00 00:00:00'),(3,'3333222',1,1,'0000-00-00 00:00:00','2020-05-24 02:00:27','2020-05-24 02:00:30');

#
# Structure for table "fengyulou_task"
#

DROP TABLE IF EXISTS `fengyulou_task`;
CREATE TABLE `fengyulou_task` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL DEFAULT '0' COMMENT '项目id',
  `sketch` varchar(255) NOT NULL DEFAULT '' COMMENT '任务简述',
  `task_label_id` int(11) NOT NULL DEFAULT '0' COMMENT '任务标签id',
  `status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '任务状态 0未完成 1已完成',
  `finish_time` date DEFAULT NULL COMMENT '完成时间',
  `member_id` int(11) DEFAULT '0' COMMENT '人员id',
  `delete` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0正常 1删除',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `insert_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `delete_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='任务表';

#
# Data for table "fengyulou_task"
#

INSERT INTO `fengyulou_task` VALUES (1,1,'修改和英视频播放进度问题',1,1,'2020-05-24',1,0,1,'2020-05-24 01:55:03','2020-05-24 01:55:42','0000-00-00 00:00:00'),(2,1,'123123',1,0,NULL,1,1,1,'2020-05-24 01:55:12','0000-00-00 00:00:00','2020-05-24 01:55:23');

#
# Structure for table "fengyulou_task_label"
#

DROP TABLE IF EXISTS `fengyulou_task_label`;
CREATE TABLE `fengyulou_task_label` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '标签名称',
  `delete` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0正常 1删除',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `insert_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `delete_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='任务标签表';

#
# Data for table "fengyulou_task_label"
#

INSERT INTO `fengyulou_task_label` VALUES (1,'问题',0,1,'2020-05-24 01:50:19','0000-00-00 00:00:00','0000-00-00 00:00:00');

#
# Structure for table "fengyulou_user"
#

DROP TABLE IF EXISTS `fengyulou_user`;
CREATE TABLE `fengyulou_user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) NOT NULL DEFAULT '' COMMENT '账号',
  `password` varchar(255) NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '姓名',
  `delete` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0正常 1删除',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '类型 0普通用户 1管理员',
  `insert_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `delete_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '删除时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

#
# Data for table "fengyulou_user"
#

INSERT INTO `fengyulou_user` VALUES (1,'郭超','D19BE809FA02E18DB9A34E905F08AB84','郭超',0,1,'0000-00-00 00:00:00','2020-05-24 02:49:38','0000-00-00 00:00:00'),(7,'赖春梅','D19BE809FA02E18DB9A34E905F08AB84','赖春梅',0,0,'2020-05-24 02:05:00','0000-00-00 00:00:00','0000-00-00 00:00:00'),(8,'123133','838D8761CF4E885F7BE17500B46B35E5','12333',0,0,'2020-05-24 02:05:06','2020-05-24 02:05:14','0000-00-00 00:00:00'),(9,'123333','791C4A538D3C7992187D1AD81FE14B06','123333',0,0,'2020-05-24 02:06:03','2020-05-24 02:06:09','0000-00-00 00:00:00'),(10,'123','791C4A538D3C7992187D1AD81FE14B06','123',0,0,'2020-05-24 02:06:16','0000-00-00 00:00:00','0000-00-00 00:00:00');
