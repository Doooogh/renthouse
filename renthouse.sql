/*
Navicat MySQL Data Transfer

Source Server         : renthouse
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : renthouse

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-04-08 10:46:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '房屋标题',
  `large_areas` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '大范围地址',
  `small_areas` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '小范围地址',
  `square_meter` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '房屋平米数',
  `orientation` varchar(4) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '房屋朝向',
  `house_type` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '房屋类型',
  `pubdate` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发布时间',
  `price` int(8) DEFAULT NULL COMMENT '价格',
  `description` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '房屋描述',
  `address` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '详细地址',
  `add_time` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '具体发布时间',
  `landlord_id` int(16) DEFAULT NULL COMMENT '房东id',
  `tenant_id` int(16) DEFAULT NULL,
  `status` int(16) NOT NULL,
  `elevator` int(4) DEFAULT NULL COMMENT '是否有电梯',
  `tv` int(4) DEFAULT NULL COMMENT '是否有电视',
  `fridge` int(4) DEFAULT NULL COMMENT '是否有冰箱',
  `air_conditioner` int(4) DEFAULT NULL COMMENT '是否有空调',
  `broad_band` int(4) DEFAULT NULL COMMENT '是否有宽带',
  `wardrobe` int(4) DEFAULT NULL COMMENT '是否有衣柜',
  `createtime` timestamp NULL DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES ('1', '铁西小区', '海淀', '军博', '48', '南', '2室1厅1卫', '19天发布', '5800', 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', '北京西站铁西小区', '19天前', '1', '1', '2', '0', '1', '0', '1', '1', '1', '2019-03-08 10:22:44', '2019-03-08 10:22:44');
INSERT INTO `house` VALUES ('2', '西客站小区', '亦庄', '万家', '95', '东', '2', '2', '1300', 'xxggggg', '北京亦庄万家', '13天前', '2', '1', '0', '1', '1', '0', '1', '1', '1', '2019-03-08 10:22:48', '2019-03-08 10:22:48');
INSERT INTO `house` VALUES ('3', 'qwe', null, null, null, null, '1室', null, '1500', null, null, null, '1', '1', '1', null, null, null, null, null, null, '2019-02-28 13:39:08', '2019-02-28 13:39:08');
INSERT INTO `house` VALUES ('5', '增光路43号院', '东单', '', '', '', '', '', '100', '', '', '', '1', '2', '1', '0', '0', '0', '1', '1', '1', '2019-02-28 12:22:24', '2019-02-28 12:22:24');
INSERT INTO `house` VALUES ('7', 'qw', null, null, null, null, null, null, null, null, null, null, '2', '1', '2', null, null, null, null, null, null, '2019-02-25 15:43:27', '2019-02-25 15:43:27');
INSERT INTO `house` VALUES ('8', '天安', null, null, null, null, null, null, null, null, null, null, '1', '2', '0', null, null, null, null, null, null, '2019-03-02 11:56:41', '2019-03-02 11:56:41');
INSERT INTO `house` VALUES ('9', 'qwr', null, null, null, null, null, null, null, null, null, null, '2', '1', '1', null, null, null, null, null, null, '2019-02-25 15:43:28', '2019-02-25 15:43:28');
INSERT INTO `house` VALUES ('10', 'rq', null, null, null, null, null, null, null, null, null, null, '2', '2', '2', null, null, null, null, null, null, '2019-02-25 15:43:29', '2019-02-25 15:43:29');
INSERT INTO `house` VALUES ('12', 'q', '东单', null, null, null, null, null, '1600', null, null, null, '2', '1', '1', null, null, null, null, null, null, '2019-02-28 12:30:21', '2019-02-28 12:30:21');
INSERT INTO `house` VALUES ('14', 'tq', '东单', null, null, null, '3室一厅', null, '2000', null, null, null, '1', '4', '1', null, null, null, null, null, null, '2019-02-28 12:30:06', '2019-02-28 12:30:06');
INSERT INTO `house` VALUES ('15', 'qtq', null, null, null, null, null, null, null, null, null, null, '1', '3', '3', null, null, null, null, null, null, '2019-02-25 15:43:31', '2019-02-25 15:43:31');
INSERT INTO `house` VALUES ('16', 'qtqtq', null, null, null, null, null, null, null, null, null, null, '1', '4', '1', null, null, null, null, null, null, '2019-02-25 15:43:32', '2019-02-25 15:43:32');
INSERT INTO `house` VALUES ('17', '天安门', '东单', '门口', '52', '南', '3室一厅', null, '1660', '萨嘎谁干的gas搭嘎玩傻瓜萨嘎是的噶啥的gas的gas的噶十多个萨达刚阿萨德噶十多个萨嘎啥的跟', '北京市天安门', null, '9', '1', '2', '1', '1', '1', '1', '1', '1', '2019-03-06 18:26:11', '2019-03-06 18:26:11');
INSERT INTO `house` VALUES ('18', '增光路43号院', '天通苑', '八十四亩地', '16', '东', '2室2厅', null, '1652', '记录卡时间管理框架', '天通苑北八十四亩地', null, '4', '2', '0', '0', '1', '0', '1', '1', '1', '2019-03-02 13:08:19', '2019-03-02 13:08:19');
INSERT INTO `house` VALUES ('19', '增光路43号院', '东单', '', '', '', '', null, null, '', '', null, '9', '1', '0', '1', '1', '1', '1', '1', '1', '2019-03-02 13:08:20', '2019-03-02 13:08:20');
INSERT INTO `house` VALUES ('20', 'yi东单测试员', '', '', '', '', '', null, null, '', '', null, '10', '1', '0', '1', '1', '1', '1', '1', '1', '2019-03-02 13:08:20', '2019-03-02 13:08:20');
INSERT INTO `house` VALUES ('21', '测试房屋二', '', '', '', '', '', null, null, '', '', null, '1', '1', '0', '0', '1', '0', '1', '1', '1', '2019-03-06 18:22:15', '2019-03-06 18:22:15');
INSERT INTO `house` VALUES ('22', '测试房屋三', '', '', '', '', '', null, null, '', '', null, '1', '1', '1', '1', '1', '1', '1', '1', '1', '2019-03-02 14:01:44', '2019-03-02 14:01:44');

-- ----------------------------
-- Table structure for img
-- ----------------------------
DROP TABLE IF EXISTS `img`;
CREATE TABLE `img` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `src` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图片src',
  `house_id` int(16) DEFAULT NULL COMMENT '房屋id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of img
-- ----------------------------
INSERT INTO `img` VALUES ('25', 'b4685c1f-c27e-4e8d-82fb-b6713cadb4e6.jpg', '4');
INSERT INTO `img` VALUES ('26', '47f52cb4-7702-4517-912d-a1b5a0356494.jpg', '5');
INSERT INTO `img` VALUES ('27', '0834b5fa-d116-4272-af70-39f191a89716.jpg', '7');
INSERT INTO `img` VALUES ('28', 'd48446ab-bb3d-4db6-8440-5d4e77684676.jpg', '1');
INSERT INTO `img` VALUES ('29', '973fb888-1f0a-491f-a57e-e78e0e854efb.jpg', '1');
INSERT INTO `img` VALUES ('31', '959e8013-cafa-48ac-a232-21cd53da7342.jpg', '2');
INSERT INTO `img` VALUES ('32', '81083310-34c7-4f50-adf1-9659f1156cb3.jpg', '2');
INSERT INTO `img` VALUES ('33', '36bd1408-88d5-463d-a2ab-afe79735f760.jpg', '2');
INSERT INTO `img` VALUES ('34', '6eb02e7e-0c57-49c8-87c4-e740db90079b.jpg', '1');
INSERT INTO `img` VALUES ('35', 'bf595e62-adcc-472d-bfdc-5505f221e556.jpg', '17');
INSERT INTO `img` VALUES ('36', '28cab616-4150-42da-a64a-d8e14e6f8233.png', '1');

-- ----------------------------
-- Table structure for landlord
-- ----------------------------
DROP TABLE IF EXISTS `landlord`;
CREATE TABLE `landlord` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` char(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex` char(2) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of landlord
-- ----------------------------
INSERT INTO `landlord` VALUES ('1', '张三', '13283354149', '男');
INSERT INTO `landlord` VALUES ('2', '李四', '12233355555', '女');
INSERT INTO `landlord` VALUES ('3', '王五', '13283354149', '男');
INSERT INTO `landlord` VALUES ('4', '社团管理系统11', '13283354148', '女');
INSERT INTO `landlord` VALUES ('9', '小沈阳', '13283354149', '女');
INSERT INTO `landlord` VALUES ('10', '阿斯钢', '13283354149', '男');

-- ----------------------------
-- Table structure for lanlordhouses
-- ----------------------------
DROP TABLE IF EXISTS `lanlordhouses`;
CREATE TABLE `lanlordhouses` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` char(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `houses` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of lanlordhouses
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `number` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '编号',
  `landlord_id` int(16) DEFAULT NULL COMMENT '房东id',
  `tenant_id` int(16) DEFAULT NULL COMMENT '租客id',
  `house_id` int(16) DEFAULT NULL COMMENT '房子id',
  `tenancy_term` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '租期',
  `price` int(16) DEFAULT NULL COMMENT '价格',
  `status` int(16) DEFAULT NULL COMMENT '状态',
  `createdate` timestamp NULL DEFAULT NULL COMMENT '订单生成日期',
  `updatedate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '订单修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('20', '2f2000ca67f34d9b92c178e922676153', '1', '19', '1', '2', '3', '2', '2019-03-08 10:23:18', '2019-03-11 14:22:13');
INSERT INTO `order` VALUES ('21', '6b7e73a6da264b9397bd79f4a4ba31dd', '1', '19', '1', '3', '5800', '2', '2019-03-11 14:21:45', '2019-03-11 14:21:45');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` int(16) DEFAULT '0' COMMENT '父id',
  `name` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限描述',
  `url` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `permission` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` int(8) DEFAULT NULL COMMENT '类型',
  `img` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图标',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('17', '0', '主页', null, '', '0', 'glyphicon glyphicon-home', null, '2019-03-18 16:24:14');
INSERT INTO `permission` VALUES ('18', '17', '主页', '/house/house/show', 'house:house:show', '1', '', null, '2019-03-18 16:17:45');
INSERT INTO `permission` VALUES ('19', '0', '房屋管理', null, '', '0', 'lnr lnr-menu', null, '2019-03-16 00:40:42');
INSERT INTO `permission` VALUES ('20', '19', '房屋管理', '/house/house', 'house:house:house', '1', null, null, '2019-03-18 13:51:42');
INSERT INTO `permission` VALUES ('21', '20', '添加', null, 'house:house:add', '2', null, null, '2019-03-18 16:54:07');
INSERT INTO `permission` VALUES ('22', '20', '编辑', null, 'house:house:edit', '2', null, null, '2019-03-18 16:54:08');
INSERT INTO `permission` VALUES ('23', '20', '删除', null, 'house:house:remove', '2', null, null, '2019-03-18 16:54:10');
INSERT INTO `permission` VALUES ('24', '0', '客户管理', null, '', '0', 'lnr lnr-menu', null, '2019-03-16 00:40:40');
INSERT INTO `permission` VALUES ('25', '24', '房东管理', '/landlord/landlord', 'landlord:landlord:landlord', '1', null, null, '2019-03-18 13:52:01');
INSERT INTO `permission` VALUES ('26', '24', '租客管理', '/tenant/tenant', 'tenant:tenant:landlord', '1', null, null, '2019-03-18 13:52:07');
INSERT INTO `permission` VALUES ('27', '25', '添加', null, 'landlord:landlord:add', '2', null, null, null);
INSERT INTO `permission` VALUES ('28', '25', '编辑', null, 'landlord:landlord:edit', '2', null, null, null);
INSERT INTO `permission` VALUES ('29', '25', '删除', null, 'landlord:landlord:remove', '2', null, null, null);
INSERT INTO `permission` VALUES ('30', '26', '添加', null, 'tenant:tenant:add', '2', null, null, null);
INSERT INTO `permission` VALUES ('31', '26', '编辑', null, 'tenant:tenant:edit', '2', null, null, null);
INSERT INTO `permission` VALUES ('32', '26', '删除', null, 'tenant:tenant:remove', '2', null, null, null);
INSERT INTO `permission` VALUES ('33', '0', '订单管理', null, '', '0', 'lnr lnr-menu', null, '2019-03-16 00:40:43');
INSERT INTO `permission` VALUES ('34', '33', '新订单', '/order/order/newOrder1', 'order:order:newOrder', '1', null, null, '2019-03-18 15:58:39');
INSERT INTO `permission` VALUES ('35', '33', '查看订单', '/order/order', 'order:order:order', '1', null, null, '2019-03-18 13:52:26');
INSERT INTO `permission` VALUES ('36', '35', '添加', null, 'order:order:add', '2', null, null, null);
INSERT INTO `permission` VALUES ('37', '35', '删除', null, 'order:order:remove', '2', null, null, null);
INSERT INTO `permission` VALUES ('38', '35', '编辑', null, 'order:order:edit', '2', null, null, null);
INSERT INTO `permission` VALUES ('39', '0', '系统管理', null, '', '0', 'lnr lnr-menu', null, '2019-03-16 00:40:46');
INSERT INTO `permission` VALUES ('41', '39', '用户管理', '/user/user', 'user:user:user', '1', null, null, '2019-03-18 13:52:30');
INSERT INTO `permission` VALUES ('42', '41', '添加', null, 'user:user:add', '2', null, null, '2019-03-18 16:39:48');
INSERT INTO `permission` VALUES ('43', '41', '编辑', null, 'user:user:edit', '2', null, null, null);
INSERT INTO `permission` VALUES ('44', '41', '删除', null, 'user:user:remove', '2', null, null, null);
INSERT INTO `permission` VALUES ('45', '39', '角色管理', 'role/role', 'role:role:role', '1', null, null, '2019-03-18 16:11:04');
INSERT INTO `permission` VALUES ('46', '39', '菜单管理', '/permission/permission', 'permission:permission:permission', '1', null, null, '2019-03-18 13:52:43');
INSERT INTO `permission` VALUES ('47', '45', '添加', null, 'role:role:add', '0', null, null, '2019-03-15 22:14:15');
INSERT INTO `permission` VALUES ('48', '45', '删除', null, 'role:role:remove', '0', null, null, '2019-03-15 22:14:32');
INSERT INTO `permission` VALUES ('49', '45', '编辑', null, 'role:role:edit', '0', null, null, '2019-03-15 22:14:42');
INSERT INTO `permission` VALUES ('50', '46', '添加', null, 'permission:permission:add', '2', null, null, null);
INSERT INTO `permission` VALUES ('51', '46', '编辑', null, 'permission:permission:edit', '2', null, null, '2019-03-15 16:18:55');
INSERT INTO `permission` VALUES ('52', '46', '删除', null, 'permission:permission:remove', '2', null, null, null);
INSERT INTO `permission` VALUES ('54', '45', '批量删除', null, 'role:role:batchRemove', '2', null, null, null);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(16) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `sign` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '权限所有',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('15', '管理员', '管理员', null, null);
INSERT INTO `role` VALUES ('16', '中介', '中介', null, null);
INSERT INTO `role` VALUES ('17', '管理员测试', '管理员', null, null);

-- ----------------------------
-- Table structure for role_per
-- ----------------------------
DROP TABLE IF EXISTS `role_per`;
CREATE TABLE `role_per` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `role_id` int(16) DEFAULT NULL,
  `per_id` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=266 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of role_per
-- ----------------------------
INSERT INTO `role_per` VALUES ('134', '17', '49');
INSERT INTO `role_per` VALUES ('135', '17', '48');
INSERT INTO `role_per` VALUES ('136', '17', '47');
INSERT INTO `role_per` VALUES ('137', '17', '44');
INSERT INTO `role_per` VALUES ('138', '17', '43');
INSERT INTO `role_per` VALUES ('139', '17', '42');
INSERT INTO `role_per` VALUES ('140', '17', '38');
INSERT INTO `role_per` VALUES ('141', '17', '37');
INSERT INTO `role_per` VALUES ('142', '17', '36');
INSERT INTO `role_per` VALUES ('143', '17', '34');
INSERT INTO `role_per` VALUES ('144', '17', '32');
INSERT INTO `role_per` VALUES ('145', '17', '31');
INSERT INTO `role_per` VALUES ('146', '17', '30');
INSERT INTO `role_per` VALUES ('147', '17', '29');
INSERT INTO `role_per` VALUES ('148', '17', '28');
INSERT INTO `role_per` VALUES ('149', '17', '27');
INSERT INTO `role_per` VALUES ('150', '17', '23');
INSERT INTO `role_per` VALUES ('151', '17', '22');
INSERT INTO `role_per` VALUES ('152', '17', '21');
INSERT INTO `role_per` VALUES ('153', '17', '20');
INSERT INTO `role_per` VALUES ('154', '17', '18');
INSERT INTO `role_per` VALUES ('155', '17', '45');
INSERT INTO `role_per` VALUES ('156', '17', '41');
INSERT INTO `role_per` VALUES ('157', '17', '35');
INSERT INTO `role_per` VALUES ('158', '17', '33');
INSERT INTO `role_per` VALUES ('159', '17', '26');
INSERT INTO `role_per` VALUES ('160', '17', '25');
INSERT INTO `role_per` VALUES ('161', '17', '24');
INSERT INTO `role_per` VALUES ('162', '17', '19');
INSERT INTO `role_per` VALUES ('163', '17', '17');
INSERT INTO `role_per` VALUES ('164', '17', '46');
INSERT INTO `role_per` VALUES ('165', '17', '52');
INSERT INTO `role_per` VALUES ('166', '17', '51');
INSERT INTO `role_per` VALUES ('167', '17', '50');
INSERT INTO `role_per` VALUES ('168', '17', '39');
INSERT INTO `role_per` VALUES ('169', '17', '-1');
INSERT INTO `role_per` VALUES ('206', '15', '52');
INSERT INTO `role_per` VALUES ('207', '15', '51');
INSERT INTO `role_per` VALUES ('208', '15', '50');
INSERT INTO `role_per` VALUES ('209', '15', '49');
INSERT INTO `role_per` VALUES ('210', '15', '48');
INSERT INTO `role_per` VALUES ('211', '15', '47');
INSERT INTO `role_per` VALUES ('212', '15', '44');
INSERT INTO `role_per` VALUES ('213', '15', '43');
INSERT INTO `role_per` VALUES ('214', '15', '42');
INSERT INTO `role_per` VALUES ('215', '15', '38');
INSERT INTO `role_per` VALUES ('216', '15', '37');
INSERT INTO `role_per` VALUES ('217', '15', '36');
INSERT INTO `role_per` VALUES ('218', '15', '34');
INSERT INTO `role_per` VALUES ('219', '15', '32');
INSERT INTO `role_per` VALUES ('220', '15', '31');
INSERT INTO `role_per` VALUES ('221', '15', '30');
INSERT INTO `role_per` VALUES ('222', '15', '29');
INSERT INTO `role_per` VALUES ('223', '15', '28');
INSERT INTO `role_per` VALUES ('224', '15', '27');
INSERT INTO `role_per` VALUES ('225', '15', '23');
INSERT INTO `role_per` VALUES ('226', '15', '22');
INSERT INTO `role_per` VALUES ('227', '15', '21');
INSERT INTO `role_per` VALUES ('228', '15', '20');
INSERT INTO `role_per` VALUES ('229', '15', '18');
INSERT INTO `role_per` VALUES ('230', '15', '46');
INSERT INTO `role_per` VALUES ('231', '15', '41');
INSERT INTO `role_per` VALUES ('232', '15', '35');
INSERT INTO `role_per` VALUES ('233', '15', '33');
INSERT INTO `role_per` VALUES ('234', '15', '26');
INSERT INTO `role_per` VALUES ('235', '15', '25');
INSERT INTO `role_per` VALUES ('236', '15', '24');
INSERT INTO `role_per` VALUES ('237', '15', '19');
INSERT INTO `role_per` VALUES ('238', '15', '17');
INSERT INTO `role_per` VALUES ('239', '15', '54');
INSERT INTO `role_per` VALUES ('240', '15', '45');
INSERT INTO `role_per` VALUES ('241', '15', '39');
INSERT INTO `role_per` VALUES ('242', '15', '-1');
INSERT INTO `role_per` VALUES ('263', '16', '18');
INSERT INTO `role_per` VALUES ('264', '16', '17');
INSERT INTO `role_per` VALUES ('265', '16', '-1');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `description` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题描述',
  `house_id` int(16) DEFAULT NULL COMMENT '房屋id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', 'test1', '1');
INSERT INTO `tag` VALUES ('2', 'test2', '2');
INSERT INTO `tag` VALUES ('3', 'test3', '3');
INSERT INTO `tag` VALUES ('4', 'test4', '1');

-- ----------------------------
-- Table structure for tenant
-- ----------------------------
DROP TABLE IF EXISTS `tenant`;
CREATE TABLE `tenant` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '租客名字',
  `sex` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '性别',
  `phone` char(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tenant
-- ----------------------------
INSERT INTO `tenant` VALUES ('1', '李四', '男', '13298889666');
INSERT INTO `tenant` VALUES ('2', '小沈阳', '男', '13132132132');
INSERT INTO `tenant` VALUES ('3', '历史推荐', '女', '13283354149');
INSERT INTO `tenant` VALUES ('4', '历史推荐', '男', '13283354149');
INSERT INTO `tenant` VALUES ('8', '学生', '男', '13283354149');
INSERT INTO `tenant` VALUES ('9', '社团管理系统', '男', '18532132164');
INSERT INTO `tenant` VALUES ('10', '历史推荐', '男', '13283354149');
INSERT INTO `tenant` VALUES ('11', '学生', '男', '13211111111');
INSERT INTO `tenant` VALUES ('12', '历史推荐', '男', '13283354149');
INSERT INTO `tenant` VALUES ('13', '历史推荐', '男', '13283354149');
INSERT INTO `tenant` VALUES ('14', '历史推荐', '女', '13283354149');
INSERT INTO `tenant` VALUES ('15', null, null, null);
INSERT INTO `tenant` VALUES ('16', null, null, null);
INSERT INTO `tenant` VALUES ('17', '小伙子', '女', '13283354148');
INSERT INTO `tenant` VALUES ('18', '洗洗', '男', '13532132164');
INSERT INTO `tenant` VALUES ('19', '黄就', '女', '13265498732');
INSERT INTO `tenant` VALUES ('20', '黄就全球', '女', '13265498732');
INSERT INTO `tenant` VALUES ('21', '黄就是个', '女', '13283354149');
INSERT INTO `tenant` VALUES ('22', '张三', '男', '13283354149');
INSERT INTO `tenant` VALUES ('23', '黄就阿斯钢', '女', '13283354149');
INSERT INTO `tenant` VALUES ('24', '爱三国杀', '女', '13283354149');
INSERT INTO `tenant` VALUES ('25', '按时嘎嘎三个', '女', '13265498732');
INSERT INTO `tenant` VALUES ('26', '黄就', '女', '13283354149');
INSERT INTO `tenant` VALUES ('27', '李龙', '女', '13283354149');
INSERT INTO `tenant` VALUES ('28', '按时嘎嘎三个了', '女', '13283354149');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(16) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户昵称',
  `username` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码',
  `phone` char(11) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
  `sex` char(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '性别',
  `age` int(8) DEFAULT NULL COMMENT '年龄',
  `status` int(4) DEFAULT NULL COMMENT '状态',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('28', '系统管理员', 'admin', 'admin', '13283354149', '男', '1122', '1', '2019-03-15 10:24:33', null);
INSERT INTO `user` VALUES ('29', '测试人员', 'testman', '123', '13283354149', '男', '22', '1', '2019-03-18 16:55:20', '2019-03-19 12:23:47');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `user_id` int(16) DEFAULT NULL,
  `role_id` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('24', '28', '15');
INSERT INTO `user_role` VALUES ('26', '29', '16');
