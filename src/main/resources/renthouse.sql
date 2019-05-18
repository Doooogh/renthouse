/*
Navicat MySQL Data Transfer

Source Server         : renthouse
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : renthouse

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-05-18 20:58:16
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
  `status` int(16) NOT NULL DEFAULT '0',
  `elevator` int(4) DEFAULT NULL COMMENT '是否有电梯',
  `tv` int(4) DEFAULT NULL COMMENT '是否有电视',
  `fridge` int(4) DEFAULT NULL COMMENT '是否有冰箱',
  `air_conditioner` int(4) DEFAULT NULL COMMENT '是否有空调',
  `broad_band` int(4) DEFAULT NULL COMMENT '是否有宽带',
  `wardrobe` int(4) DEFAULT NULL COMMENT '是否有衣柜',
  `createtime` timestamp NULL DEFAULT NULL,
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES ('28', '阿斯钢桑', '昌平', '啥', '92', '东', '二室一厅', null, '2000', '挺好 的', '天通苑东三旗', null, '24', null, '0', '0', '1', '1', '1', '1', '1', '2019-05-18 15:31:42', null);

-- ----------------------------
-- Table structure for img
-- ----------------------------
DROP TABLE IF EXISTS `img`;
CREATE TABLE `img` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `src` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图片src',
  `house_id` int(16) DEFAULT NULL COMMENT '房屋id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of img
-- ----------------------------
INSERT INTO `img` VALUES ('39', '78236aa3-3b5f-471b-a3ee-71e555e6e7bb.jpg', '28');

-- ----------------------------
-- Table structure for insert_log
-- ----------------------------
DROP TABLE IF EXISTS `insert_log`;
CREATE TABLE `insert_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `insert_type` varchar(32) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `create_uid` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of insert_log
-- ----------------------------
INSERT INTO `insert_log` VALUES ('15', '添加房东', 'landlord', '28', '2019-05-18 15:39:52', null);
INSERT INTO `insert_log` VALUES ('16', '添加房东', 'landlord', '28', '2019-05-15 15:39:57', '2019-05-18 15:51:41');

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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of landlord
-- ----------------------------
INSERT INTO `landlord` VALUES ('24', '管理员', '13283356565', '男');
INSERT INTO `landlord` VALUES ('26', '编辑', '13283356564', '女');

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
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `time` int(11) DEFAULT NULL COMMENT '响应时间',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('11', '28', 'admin', '/用户登录', '178', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-08 17:51:47');
INSERT INTO `log` VALUES ('12', '28', 'admin', '修改权限菜单', '11', 'com.graduation.renthouse.rent.permission.controller.PermissionController.update()', null, '127.0.0.1', '2019-05-08 17:52:06');
INSERT INTO `log` VALUES ('13', '28', 'admin', '修改权限菜单', '11', 'com.graduation.renthouse.rent.permission.controller.PermissionController.update()', null, '127.0.0.1', '2019-05-08 17:52:29');
INSERT INTO `log` VALUES ('14', '28', 'admin', '/用户登录', '739', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-08 17:57:42');
INSERT INTO `log` VALUES ('15', '28', 'admin', '修改权限菜单', '12717', 'com.graduation.renthouse.rent.permission.controller.PermissionController.update()', null, '127.0.0.1', '2019-05-08 17:58:46');
INSERT INTO `log` VALUES ('16', '28', 'admin', '/用户登录', '279', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-08 18:01:31');
INSERT INTO `log` VALUES ('17', '28', 'admin', '修改权限菜单', '17337', 'com.graduation.renthouse.rent.permission.controller.PermissionController.update()', null, '127.0.0.1', '2019-05-08 18:02:00');
INSERT INTO `log` VALUES ('18', '28', 'admin', '修改角色权限', '83', 'com.graduation.renthouse.rent.role.controller.RoleController.update()', null, '127.0.0.1', '2019-05-08 18:02:21');
INSERT INTO `log` VALUES ('19', '-1', '获取用户信息为空', '/用户登出', '14', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', null, '127.0.0.1', '2019-05-08 18:02:23');
INSERT INTO `log` VALUES ('20', '28', 'admin', '/用户登录', '37', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-08 18:02:25');
INSERT INTO `log` VALUES ('21', '-1', '获取用户信息为空', '/用户登出', '12', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', null, '127.0.0.1', '2019-05-08 18:04:38');
INSERT INTO `log` VALUES ('22', '28', 'admin', '/用户登录', '20', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-08 18:04:40');
INSERT INTO `log` VALUES ('23', '28', 'admin', '用户登录', '671', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-08 19:11:37');
INSERT INTO `log` VALUES ('24', '28', 'admin', '用户登录', '137', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 00:16:29');
INSERT INTO `log` VALUES ('25', '28', 'admin', '用户登录', '209', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 00:21:31');
INSERT INTO `log` VALUES ('26', '28', 'admin', '用户登录', '125', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 00:23:49');
INSERT INTO `log` VALUES ('27', '28', 'admin', '用户登录', '116', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 00:34:57');
INSERT INTO `log` VALUES ('28', '28', 'admin', '用户登录', '98', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 00:37:21');
INSERT INTO `log` VALUES ('29', '28', 'admin', '修改角色权限', '40', 'com.graduation.renthouse.rent.role.controller.RoleController.update()', null, '127.0.0.1', '2019-05-09 00:37:35');
INSERT INTO `log` VALUES ('30', '-1', '获取用户信息为空', '用户登出', '11', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', null, '127.0.0.1', '2019-05-09 00:38:24');
INSERT INTO `log` VALUES ('31', '28', 'admin', '用户登录', '111', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 00:39:09');
INSERT INTO `log` VALUES ('32', '28', 'admin', '用户登录', '94', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 00:45:40');
INSERT INTO `log` VALUES ('33', '28', 'admin', '用户登录', '112', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 01:33:36');
INSERT INTO `log` VALUES ('34', '28', 'admin', '用户登录', '118', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 02:16:59');
INSERT INTO `log` VALUES ('35', '28', 'admin', '用户登录', '93', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 02:23:07');
INSERT INTO `log` VALUES ('36', '-1', '获取用户信息为空', '用户登录', '127', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 17:56:05');
INSERT INTO `log` VALUES ('37', '28', 'admin', '用户登录', '30', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 17:56:38');
INSERT INTO `log` VALUES ('38', '28', 'admin', '用户登录', '20', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 17:56:45');
INSERT INTO `log` VALUES ('39', '29', 'testman', '用户登录', '12', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 17:57:35');
INSERT INTO `log` VALUES ('40', '28', 'admin', '用户登录', '12', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 17:58:58');
INSERT INTO `log` VALUES ('41', '28', 'admin', '用户登录', '4', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 17:59:21');
INSERT INTO `log` VALUES ('42', '28', 'admin', '用户登录', '31', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 18:01:19');
INSERT INTO `log` VALUES ('43', '28', 'admin', '用户登录', '34', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 18:09:58');
INSERT INTO `log` VALUES ('44', '-1', '获取用户信息为空', '用户登出', '17', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', null, '127.0.0.1', '2019-05-09 18:15:03');
INSERT INTO `log` VALUES ('45', '29', 'testman', '用户登录', '9', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 18:15:05');
INSERT INTO `log` VALUES ('46', '29', 'testman', '批量删除用户角色', '16', 'com.sun.proxy.$Proxy91.deleteByUserId()', null, '127.0.0.1', '2019-05-09 18:15:31');
INSERT INTO `log` VALUES ('47', '29', 'testman', '批量添加用户角色', '6', 'com.sun.proxy.$Proxy91.batchSave()', null, '127.0.0.1', '2019-05-09 18:15:31');
INSERT INTO `log` VALUES ('48', '29', 'testman', '编辑用户', '68', 'com.graduation.renthouse.rent.user.controller.UserController.update()', null, '127.0.0.1', '2019-05-09 18:15:31');
INSERT INTO `log` VALUES ('49', '-1', '获取用户信息为空', '用户登出', '3', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', null, '127.0.0.1', '2019-05-09 18:15:35');
INSERT INTO `log` VALUES ('50', '29', 'testman', '用户登录', '10', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 18:15:36');
INSERT INTO `log` VALUES ('51', '-1', '获取用户信息为空', '用户登出', '3', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', null, '127.0.0.1', '2019-05-09 18:17:57');
INSERT INTO `log` VALUES ('52', '28', 'admin', '用户登录', '11', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-09 18:17:59');
INSERT INTO `log` VALUES ('53', '28', 'admin', '用户登录', '298', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-10 00:09:29');
INSERT INTO `log` VALUES ('54', '28', 'admin', '用户登录', '115', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-10 14:33:57');
INSERT INTO `log` VALUES ('55', '28', 'admin', '用户登录', '18', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-10 15:15:15');
INSERT INTO `log` VALUES ('56', '28', 'admin', '用户登录', '26', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-10 16:28:48');
INSERT INTO `log` VALUES ('57', '28', 'admin', '修改权限菜单', '10', 'com.graduation.renthouse.rent.permission.controller.PermissionController.update()', null, '127.0.0.1', '2019-05-10 16:55:53');
INSERT INTO `log` VALUES ('58', '-1', '获取用户信息为空', '用户登出', '3', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', null, '127.0.0.1', '2019-05-10 16:55:56');
INSERT INTO `log` VALUES ('59', '28', 'admin', '用户登录', '10', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-10 16:55:57');
INSERT INTO `log` VALUES ('60', '28', 'admin', '用户登录', '17', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-10 18:01:29');
INSERT INTO `log` VALUES ('61', '28', 'admin', '用户登录', '302', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-11 12:26:52');
INSERT INTO `log` VALUES ('62', '28', 'admin', '用户登录', '18', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-11 14:58:40');
INSERT INTO `log` VALUES ('63', '28', 'admin', '用户登录', '113', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-13 18:27:13');
INSERT INTO `log` VALUES ('64', '28', 'admin', '用户登录', '22', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-13 20:57:15');
INSERT INTO `log` VALUES ('65', '28', 'admin', '用户登录', '142', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-14 21:50:42');
INSERT INTO `log` VALUES ('66', '-1', '获取用户信息为空', '用户登出', '14', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', null, '127.0.0.1', '2019-05-14 21:54:50');
INSERT INTO `log` VALUES ('67', '-1', '获取用户信息为空', '用户登录', '15', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-14 21:54:56');
INSERT INTO `log` VALUES ('68', '-1', '获取用户信息为空', '用户登录', '7', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-14 21:55:01');
INSERT INTO `log` VALUES ('69', '-1', '获取用户信息为空', '用户登录', '14', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-14 21:55:05');
INSERT INTO `log` VALUES ('70', '28', 'admin', '用户登录', '17', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-14 21:55:11');
INSERT INTO `log` VALUES ('71', '28', 'admin', '用户登录', '104', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-14 22:02:28');
INSERT INTO `log` VALUES ('72', '28', 'admin', '用户登录', '158', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-14 22:11:55');
INSERT INTO `log` VALUES ('73', '28', 'admin', '用户登录', '112', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-14 22:13:57');
INSERT INTO `log` VALUES ('74', '-1', '获取用户信息为空', '用户登出', '13', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', null, '127.0.0.1', '2019-05-14 22:21:47');
INSERT INTO `log` VALUES ('75', '28', 'admin', '用户登录', '8', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-14 22:21:51');
INSERT INTO `log` VALUES ('76', '-1', '获取用户信息为空', '用户登出', '4', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', null, '127.0.0.1', '2019-05-14 22:23:38');
INSERT INTO `log` VALUES ('77', '28', 'admin', '用户登录', '10', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-14 22:23:47');
INSERT INTO `log` VALUES ('78', '28', 'admin', '用户登录', '110', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-14 22:29:43');
INSERT INTO `log` VALUES ('79', '-1', '获取用户信息为空', '用户登出', '14', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', null, '127.0.0.1', '2019-05-14 22:34:38');
INSERT INTO `log` VALUES ('80', '28', 'admin', '用户登录', '15', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-14 22:35:18');
INSERT INTO `log` VALUES ('81', '-1', '获取用户信息为空', '用户登出', '3', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', null, '127.0.0.1', '2019-05-14 22:35:23');
INSERT INTO `log` VALUES ('82', '28', 'admin', '用户登录', '8', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', null, '127.0.0.1', '2019-05-14 22:45:56');
INSERT INTO `log` VALUES ('83', '-1', '[]', '用户登出', '5', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-14 22:46:01');
INSERT INTO `log` VALUES ('84', '28', 'admin', '用户登录', '9', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 22:46:23');
INSERT INTO `log` VALUES ('85', '-1', '[]', '用户登出', '3', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-14 22:48:06');
INSERT INTO `log` VALUES ('86', '28', 'admin', '用户登录', '10', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 22:48:07');
INSERT INTO `log` VALUES ('87', '-1', '[]', '用户登出', '2', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-14 22:48:44');
INSERT INTO `log` VALUES ('88', '28', 'admin', '用户登录', '8', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 22:48:46');
INSERT INTO `log` VALUES ('89', '-1', '[]', '用户登出', '3', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-14 22:48:53');
INSERT INTO `log` VALUES ('90', '28', 'admin', '用户登录', '9', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 22:48:55');
INSERT INTO `log` VALUES ('91', '28', 'admin', '用户登录', '103', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 23:17:20');
INSERT INTO `log` VALUES ('92', '28', 'admin', '用户登录', '120', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 23:20:55');
INSERT INTO `log` VALUES ('93', '28', 'admin', '用户登录', '10', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 23:22:48');
INSERT INTO `log` VALUES ('94', '-1', '[]', '用户登出', '11', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-14 23:26:26');
INSERT INTO `log` VALUES ('95', '28', 'admin', '用户登录', '8', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 23:26:27');
INSERT INTO `log` VALUES ('96', '-1', '[]', '用户登出', '3', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-14 23:26:54');
INSERT INTO `log` VALUES ('97', '28', 'admin', '用户登录', '9', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 23:26:55');
INSERT INTO `log` VALUES ('98', '-1', '[]', '用户登出', '2', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-14 23:27:47');
INSERT INTO `log` VALUES ('99', '28', 'admin', '用户登录', '9', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 23:27:48');
INSERT INTO `log` VALUES ('100', '-1', '[]', '用户登出', '2', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-14 23:28:11');
INSERT INTO `log` VALUES ('101', '28', 'admin', '用户登录', '9', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 23:28:12');
INSERT INTO `log` VALUES ('102', '-1', '[]', '用户登出', '3', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-14 23:28:35');
INSERT INTO `log` VALUES ('103', '28', 'admin', '用户登录', '8', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 23:28:36');
INSERT INTO `log` VALUES ('104', '-1', '[]', '用户登出', '2', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-14 23:29:08');
INSERT INTO `log` VALUES ('105', '28', 'admin', '用户登录', '8', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 23:29:10');
INSERT INTO `log` VALUES ('106', '28', 'admin', '用户登录', '25', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 23:32:43');
INSERT INTO `log` VALUES ('107', '-1', '[]', '用户登出', '4', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-14 23:35:59');
INSERT INTO `log` VALUES ('108', '28', 'admin', '用户登录', '12', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 23:36:00');
INSERT INTO `log` VALUES ('109', '-1', '[]', '用户登出', '4', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-14 23:40:14');
INSERT INTO `log` VALUES ('110', '28', 'admin', '用户登录', '9', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 23:40:15');
INSERT INTO `log` VALUES ('111', '28', 'admin', '用户登录', '97', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 23:46:31');
INSERT INTO `log` VALUES ('112', '-1', '[]', '用户登出', '11', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-14 23:47:27');
INSERT INTO `log` VALUES ('113', '28', 'admin', '用户登录', '11', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 23:47:31');
INSERT INTO `log` VALUES ('114', '28', 'admin', '用户登录', '94', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 23:50:02');
INSERT INTO `log` VALUES ('115', '28', 'admin', '用户登录', '94', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 23:52:29');
INSERT INTO `log` VALUES ('116', '-1', '[]', '用户登出', '12', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-14 23:57:52');
INSERT INTO `log` VALUES ('117', '28', 'admin', '用户登录', '9', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-14 23:57:53');
INSERT INTO `log` VALUES ('118', '-1', '[]', '用户登出', '2', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-15 00:04:11');
INSERT INTO `log` VALUES ('119', '28', 'admin', '用户登录', '7', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-15 00:04:12');
INSERT INTO `log` VALUES ('120', '29', 'testman', '用户登录', '98', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"testman\",\"123\"]', '127.0.0.1', '2019-05-15 00:21:31');
INSERT INTO `log` VALUES ('121', '29', 'testman', '用户登录', '98', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"testman\",\"123\"]', '127.0.0.1', '2019-05-15 00:26:01');
INSERT INTO `log` VALUES ('122', '29', 'testman', '用户登录', '9', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"testman\",\"123\"]', '127.0.0.1', '2019-05-15 00:26:53');
INSERT INTO `log` VALUES ('123', '-1', '[]', '用户登出', '11', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-15 00:27:10');
INSERT INTO `log` VALUES ('124', '28', 'admin', '用户登录', '8', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-15 00:27:12');
INSERT INTO `log` VALUES ('125', '-1', '[]', '用户登出', '3', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-15 00:28:03');
INSERT INTO `log` VALUES ('126', '28', 'admin', '用户登录', '10', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-15 00:28:05');
INSERT INTO `log` VALUES ('127', '-1', '[]', '用户登出', '5', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-15 00:28:44');
INSERT INTO `log` VALUES ('128', '28', 'admin', '用户登录', '13', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-15 00:28:45');
INSERT INTO `log` VALUES ('129', '28', 'admin', '用户登录', '140', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-17 21:55:41');
INSERT INTO `log` VALUES ('130', '28', 'admin', '用户登录', '11', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-17 21:56:24');
INSERT INTO `log` VALUES ('131', '28', 'admin', '用户登录', '22', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-17 22:39:42');
INSERT INTO `log` VALUES ('132', '28', 'admin', '用户登录', '124', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-17 22:54:19');
INSERT INTO `log` VALUES ('133', '28', 'admin', '用户登录', '118', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-17 23:01:11');
INSERT INTO `log` VALUES ('134', '28', 'admin', '用户登录', '115', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-17 23:03:24');
INSERT INTO `log` VALUES ('135', '28', 'admin', '用户登录', '214', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-17 23:07:28');
INSERT INTO `log` VALUES ('136', '28', 'admin', '用户登录', '125', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-17 23:10:26');
INSERT INTO `log` VALUES ('137', '28', 'admin', '用户登录', '240', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 13:43:03');
INSERT INTO `log` VALUES ('138', '28', 'admin', '用户登录', '11', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 13:53:21');
INSERT INTO `log` VALUES ('139', '28', 'admin', '用户登录', '95', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 14:01:04');
INSERT INTO `log` VALUES ('140', '28', 'admin', '用户登录', '102', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 14:10:10');
INSERT INTO `log` VALUES ('141', '28', 'admin', '用户登录', '99', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 15:08:14');
INSERT INTO `log` VALUES ('142', '28', 'admin', '用户登录', '95', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 15:29:10');
INSERT INTO `log` VALUES ('143', '28', 'admin', '修改角色权限', '22', 'com.graduation.renthouse.rent.role.controller.RoleController.update()', '[{\"name\":\"管理员测试\",\"perIds\":[18,21,22,23,27,28,29,30,31,32,34,37,38,42,43,44,47,48,49,50,51,52,17,20,19,25,26,24,41,46,-1,33,35,39,45],\"roleId\":17,\"sign\":\"管理员\"}]', '127.0.0.1', '2019-05-18 15:35:06');
INSERT INTO `log` VALUES ('144', '28', 'admin', '修改角色权限', '24', 'com.graduation.renthouse.rent.role.controller.RoleController.update()', '[{\"name\":\"管理员\",\"perIds\":[18,21,22,23,27,28,29,30,31,32,34,37,38,42,43,44,47,48,49,54,50,51,52,56,57,17,20,19,25,26,24,41,45,46,39,55,-1,33,35],\"roleId\":15,\"sign\":\"管理员\"}]', '127.0.0.1', '2019-05-18 15:35:14');
INSERT INTO `log` VALUES ('145', '-1', '[]', '用户登出', '10', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-18 15:35:23');
INSERT INTO `log` VALUES ('146', '28', 'admin', '用户登录', '8', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 15:35:24');
INSERT INTO `log` VALUES ('147', '28', 'admin', '用户登录', '100', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 15:47:54');
INSERT INTO `log` VALUES ('148', '28', 'admin', '添加权限菜单', '17', 'com.graduation.renthouse.rent.permission.controller.PermissionController.save()', '[{\"id\":58,\"name\":\"标签管理\",\"parentId\":0,\"permission\":\"\",\"type\":0,\"url\":\"\"}]', '127.0.0.1', '2019-05-18 16:02:37');
INSERT INTO `log` VALUES ('149', '28', 'admin', '用户登录', '101', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 16:05:18');
INSERT INTO `log` VALUES ('150', '28', 'admin', '修改权限菜单', '10', 'com.graduation.renthouse.rent.permission.controller.PermissionController.update()', '[{\"id\":58,\"name\":\"标签管理\",\"parentId\":0,\"permission\":\"\",\"sort\":6,\"type\":0,\"url\":\"\"}]', '127.0.0.1', '2019-05-18 16:05:25');
INSERT INTO `log` VALUES ('151', '28', 'admin', '添加权限菜单', '15', 'com.graduation.renthouse.rent.permission.controller.PermissionController.save()', '[{\"id\":59,\"name\":\"标签管理\",\"parentId\":58,\"permission\":\"tag:tag\",\"sort\":1,\"type\":1,\"url\":\"/tag/tag\"}]', '127.0.0.1', '2019-05-18 16:06:21');
INSERT INTO `log` VALUES ('152', '28', 'admin', '修改权限菜单', '7', 'com.graduation.renthouse.rent.permission.controller.PermissionController.update()', '[{\"id\":59,\"name\":\"标签管理\",\"parentId\":58,\"permission\":\"tag:tag:tag\",\"sort\":1,\"type\":0,\"url\":\"/tag/tag\"}]', '127.0.0.1', '2019-05-18 16:06:57');
INSERT INTO `log` VALUES ('153', '28', 'admin', '添加权限菜单', '8', 'com.graduation.renthouse.rent.permission.controller.PermissionController.save()', '[{\"id\":60,\"name\":\"添加\",\"parentId\":58,\"permission\":\"tag:tag:add\",\"sort\":2,\"type\":2,\"url\":\"/tag/tag/add\"}]', '127.0.0.1', '2019-05-18 16:07:38');
INSERT INTO `log` VALUES ('154', '28', 'admin', '添加权限菜单', '8', 'com.graduation.renthouse.rent.permission.controller.PermissionController.save()', '[{\"id\":61,\"name\":\"删除\",\"parentId\":58,\"permission\":\"tag:tag:remove\",\"sort\":3,\"type\":2,\"url\":\"/tag/tag/remove\"}]', '127.0.0.1', '2019-05-18 16:08:14');
INSERT INTO `log` VALUES ('155', '28', 'admin', '添加权限菜单', '7', 'com.graduation.renthouse.rent.permission.controller.PermissionController.save()', '[{\"id\":62,\"name\":\"编辑\",\"parentId\":58,\"permission\":\"tag:tag:edit\",\"sort\":4,\"type\":2,\"url\":\"/tag/tag/edit\"}]', '127.0.0.1', '2019-05-18 16:08:42');
INSERT INTO `log` VALUES ('156', '28', 'admin', '修改角色权限', '31', 'com.graduation.renthouse.rent.role.controller.RoleController.update()', '[{\"name\":\"管理员\",\"perIds\":[18,21,22,23,27,28,29,30,31,32,34,37,38,42,43,44,47,48,49,54,50,51,52,56,57,17,20,19,25,26,24,41,45,46,39,55,58,59,60,61,62,-1,33,35],\"roleId\":15,\"sign\":\"管理员\"}]', '127.0.0.1', '2019-05-18 16:08:51');
INSERT INTO `log` VALUES ('157', '-1', '[]', '用户登出', '4', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-18 16:08:54');
INSERT INTO `log` VALUES ('158', '28', 'admin', '用户登录', '10', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 16:08:55');
INSERT INTO `log` VALUES ('159', '28', 'admin', '用户登录', '100', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 17:01:38');
INSERT INTO `log` VALUES ('160', '28', 'admin', '用户登录', '106', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 17:39:57');
INSERT INTO `log` VALUES ('161', '28', 'admin', '用户登录', '139', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 17:47:45');
INSERT INTO `log` VALUES ('162', '28', 'admin', '用户登录', '106', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 17:51:53');
INSERT INTO `log` VALUES ('163', '28', 'admin', '用户登录', '105', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 18:07:11');
INSERT INTO `log` VALUES ('164', '28', 'admin', '用户登录', '2168', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 18:09:52');
INSERT INTO `log` VALUES ('165', '28', 'admin', '用户登录', '108', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 19:06:33');
INSERT INTO `log` VALUES ('166', '28', 'admin', '用户登录', '101', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 19:08:23');
INSERT INTO `log` VALUES ('167', '28', 'admin', '用户登录', '100', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 19:23:51');
INSERT INTO `log` VALUES ('168', '28', 'admin', '用户登录', '103', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 19:28:16');
INSERT INTO `log` VALUES ('169', '28', 'admin', '用户登录', '108', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 19:34:37');
INSERT INTO `log` VALUES ('170', '28', 'admin', '用户登录', '91', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 20:02:59');
INSERT INTO `log` VALUES ('171', '28', 'admin', '用户登录', '112', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 20:24:33');
INSERT INTO `log` VALUES ('172', '28', 'admin', '用户登录', '94', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 20:35:43');
INSERT INTO `log` VALUES ('173', '28', 'admin', '用户登录', '9', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 20:35:54');
INSERT INTO `log` VALUES ('174', '28', 'admin', '用户登录', '8', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 20:39:05');
INSERT INTO `log` VALUES ('175', '28', 'admin', '批量删除用户角色', '4', 'com.sun.proxy.$Proxy255.deleteByUserId()', '[29]', '127.0.0.1', '2019-05-18 20:52:10');
INSERT INTO `log` VALUES ('176', '28', 'admin', '批量添加用户角色', '6', 'com.sun.proxy.$Proxy255.batchSave()', '[[{\"roleId\":16,\"userId\":29}]]', '127.0.0.1', '2019-05-18 20:52:10');
INSERT INTO `log` VALUES ('177', '28', 'admin', '编辑用户', '54', 'com.graduation.renthouse.rent.user.controller.UserController.update()', '[{\"age\":22,\"name\":\"测试人员\",\"password\":\"123\",\"phone\":\"13283354149\",\"roleIds\":[16],\"sex\":\"男\",\"status\":0,\"userId\":29,\"username\":\"testman\"}]', '127.0.0.1', '2019-05-18 20:52:10');
INSERT INTO `log` VALUES ('178', '28', 'admin', '批量删除用户角色', '3', 'com.sun.proxy.$Proxy255.deleteByUserId()', '[29]', '127.0.0.1', '2019-05-18 20:52:14');
INSERT INTO `log` VALUES ('179', '28', 'admin', '批量添加用户角色', '6', 'com.sun.proxy.$Proxy255.batchSave()', '[[{\"roleId\":16,\"userId\":29}]]', '127.0.0.1', '2019-05-18 20:52:14');
INSERT INTO `log` VALUES ('180', '28', 'admin', '编辑用户', '34', 'com.graduation.renthouse.rent.user.controller.UserController.update()', '[{\"age\":22,\"name\":\"测试人员\",\"password\":\"123\",\"phone\":\"13283354149\",\"roleIds\":[16],\"sex\":\"男\",\"status\":1,\"userId\":29,\"username\":\"testman\"}]', '127.0.0.1', '2019-05-18 20:52:14');
INSERT INTO `log` VALUES ('181', '28', 'admin', '用户登录', '92', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 20:55:01');
INSERT INTO `log` VALUES ('182', '28', 'admin', '批量删除用户角色', '4', 'com.sun.proxy.$Proxy411.deleteByUserId()', '[29]', '127.0.0.1', '2019-05-18 20:55:13');
INSERT INTO `log` VALUES ('183', '28', 'admin', '批量添加用户角色', '5', 'com.sun.proxy.$Proxy411.batchSave()', '[[{\"roleId\":16,\"userId\":29}]]', '127.0.0.1', '2019-05-18 20:55:13');
INSERT INTO `log` VALUES ('184', '28', 'admin', '编辑用户', '61', 'com.graduation.renthouse.rent.user.controller.UserController.update()', '[{\"age\":22,\"name\":\"测试人员\",\"password\":\"123\",\"phone\":\"13283354149\",\"roleIds\":[16],\"sex\":\"男\",\"status\":0,\"userId\":29,\"username\":\"testman\"}]', '127.0.0.1', '2019-05-18 20:55:13');
INSERT INTO `log` VALUES ('185', '-1', '[]', '用户登出', '11', 'com.graduation.renthouse.rent.index.controller.LoginController.logout()', '[]', '127.0.0.1', '2019-05-18 20:55:19');
INSERT INTO `log` VALUES ('186', '29', 'testman', '用户登录', '8', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"testman\",\"123\"]', '127.0.0.1', '2019-05-18 20:55:23');
INSERT INTO `log` VALUES ('187', '29', 'testman', '用户登录', '4', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"\",\"\"]', '127.0.0.1', '2019-05-18 20:55:27');
INSERT INTO `log` VALUES ('188', '29', 'testman', '用户登录', '8', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"testman\",\"123\"]', '127.0.0.1', '2019-05-18 20:55:30');
INSERT INTO `log` VALUES ('189', '28', 'admin', '用户登录', '9', 'com.graduation.renthouse.rent.index.controller.LoginController.login()', '[\"admin\",\"admin\"]', '127.0.0.1', '2019-05-18 20:55:34');

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order
-- ----------------------------

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
  `sort` int(4) DEFAULT NULL COMMENT '排序',
  `type` int(8) DEFAULT NULL COMMENT '类型',
  `img` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图标',
  `createtime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updatetime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('17', '0', '主页', null, '', '0', '0', 'glyphicon glyphicon-home', null, '2019-05-14 23:51:56');
INSERT INTO `permission` VALUES ('18', '17', '主页', '/house/house/show', 'house:house:show', '1', '1', '', null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('19', '0', '房屋管理', null, '', '1', '0', 'lnr lnr-menu', null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('20', '19', '房屋管理', '/house/house', 'house:house:house', '1', '1', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('21', '20', '添加', null, 'house:house:add', '1', '2', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('22', '20', '编辑', null, 'house:house:edit', '1', '2', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('23', '20', '删除', null, 'house:house:remove', '1', '2', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('24', '0', '客户管理', null, '', '2', '0', 'lnr lnr-menu', null, '2019-05-14 23:39:47');
INSERT INTO `permission` VALUES ('25', '24', '房东管理', '/landlord/landlord', 'landlord:landlord:landlord', '1', '1', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('26', '24', '租客管理', '/tenant/tenant', 'tenant:tenant:tenant', '1', '0', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('27', '25', '添加', null, 'landlord:landlord:add', '1', '2', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('28', '25', '编辑', null, 'landlord:landlord:edit', '1', '2', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('29', '25', '删除', null, 'landlord:landlord:remove', '1', '2', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('30', '26', '添加', null, 'tenant:tenant:add', '1', '2', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('31', '26', '编辑', null, 'tenant:tenant:edit', '1', '2', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('32', '26', '删除', null, 'tenant:tenant:remove', '1', '2', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('33', '0', '订单管理', null, '', '3', '0', 'lnr lnr-menu', null, '2019-05-14 23:39:50');
INSERT INTO `permission` VALUES ('34', '33', '新订单', '/order/order/newOrder1', 'order:order:newOrder', '1', '1', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('35', '33', '查看订单', '/order/order', 'order:order:order', '1', '1', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('36', '35', '添加', null, 'order:order:add', '1', '2', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('37', '35', '删除', null, 'order:order:remove', '1', '2', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('38', '35', '编辑', null, 'order:order:edit', '1', '2', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('39', '0', '系统管理', null, '', '4', '0', 'lnr lnr-menu', null, '2019-05-14 23:39:52');
INSERT INTO `permission` VALUES ('41', '39', '用户管理', '/user/user', 'user:user:user', '1', '1', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('42', '41', '添加', null, 'user:user:add', '1', '2', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('43', '41', '编辑', null, 'user:user:edit', '1', '2', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('44', '41', '删除', null, 'user:user:remove', '1', '2', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('45', '39', '角色管理', 'role/role', 'role:role:role', '1', '1', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('46', '39', '菜单管理', '/permission/permission', 'permission:permission:permission', '1', '1', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('47', '45', '添加', null, 'role:role:add', '1', '0', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('48', '45', '删除', null, 'role:role:remove', '1', '0', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('49', '45', '编辑', null, 'role:role:edit', '1', '0', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('50', '46', '添加', null, 'permission:permission:add', '1', '2', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('51', '46', '编辑', null, 'permission:permission:edit', '1', '2', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('52', '46', '删除', null, 'permission:permission:remove', '1', '2', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('54', '45', '批量删除', null, 'role:role:batchRemove', '1', '2', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('55', '0', '系统工具', '', '', '5', '0', null, null, '2019-05-14 23:39:58');
INSERT INTO `permission` VALUES ('56', '55', '定时任务', '/common/job', 'common:job', '1', '1', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('57', '55', '系统日志', '/common/log', 'common:log', '1', '0', null, null, '2019-05-14 23:39:33');
INSERT INTO `permission` VALUES ('58', '0', '标签管理', '', '', '6', '0', null, null, '2019-05-18 16:05:24');
INSERT INTO `permission` VALUES ('59', '58', '标签管理', '/tag/tag', 'tag:tag:tag', '1', '0', null, null, '2019-05-18 16:06:56');
INSERT INTO `permission` VALUES ('60', '58', '添加', '/tag/tag/add', 'tag:tag:add', '2', '2', null, null, null);
INSERT INTO `permission` VALUES ('61', '58', '删除', '/tag/tag/remove', 'tag:tag:remove', '3', '2', null, null, null);
INSERT INTO `permission` VALUES ('62', '58', '编辑', '/tag/tag/edit', 'tag:tag:edit', '4', '2', null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=472 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of role_per
-- ----------------------------
INSERT INTO `role_per` VALUES ('266', '16', '18');
INSERT INTO `role_per` VALUES ('267', '16', '17');
INSERT INTO `role_per` VALUES ('268', '16', '19');
INSERT INTO `role_per` VALUES ('269', '16', '21');
INSERT INTO `role_per` VALUES ('270', '16', '22');
INSERT INTO `role_per` VALUES ('271', '16', '23');
INSERT INTO `role_per` VALUES ('272', '16', '20');
INSERT INTO `role_per` VALUES ('273', '16', '-1');
INSERT INTO `role_per` VALUES ('354', '17', '18');
INSERT INTO `role_per` VALUES ('355', '17', '21');
INSERT INTO `role_per` VALUES ('356', '17', '22');
INSERT INTO `role_per` VALUES ('357', '17', '23');
INSERT INTO `role_per` VALUES ('358', '17', '27');
INSERT INTO `role_per` VALUES ('359', '17', '28');
INSERT INTO `role_per` VALUES ('360', '17', '29');
INSERT INTO `role_per` VALUES ('361', '17', '30');
INSERT INTO `role_per` VALUES ('362', '17', '31');
INSERT INTO `role_per` VALUES ('363', '17', '32');
INSERT INTO `role_per` VALUES ('364', '17', '34');
INSERT INTO `role_per` VALUES ('365', '17', '37');
INSERT INTO `role_per` VALUES ('366', '17', '38');
INSERT INTO `role_per` VALUES ('367', '17', '42');
INSERT INTO `role_per` VALUES ('368', '17', '43');
INSERT INTO `role_per` VALUES ('369', '17', '44');
INSERT INTO `role_per` VALUES ('370', '17', '47');
INSERT INTO `role_per` VALUES ('371', '17', '48');
INSERT INTO `role_per` VALUES ('372', '17', '49');
INSERT INTO `role_per` VALUES ('373', '17', '50');
INSERT INTO `role_per` VALUES ('374', '17', '51');
INSERT INTO `role_per` VALUES ('375', '17', '52');
INSERT INTO `role_per` VALUES ('376', '17', '17');
INSERT INTO `role_per` VALUES ('377', '17', '20');
INSERT INTO `role_per` VALUES ('378', '17', '19');
INSERT INTO `role_per` VALUES ('379', '17', '25');
INSERT INTO `role_per` VALUES ('380', '17', '26');
INSERT INTO `role_per` VALUES ('381', '17', '24');
INSERT INTO `role_per` VALUES ('382', '17', '41');
INSERT INTO `role_per` VALUES ('383', '17', '46');
INSERT INTO `role_per` VALUES ('384', '17', '-1');
INSERT INTO `role_per` VALUES ('385', '17', '33');
INSERT INTO `role_per` VALUES ('386', '17', '35');
INSERT INTO `role_per` VALUES ('387', '17', '39');
INSERT INTO `role_per` VALUES ('388', '17', '45');
INSERT INTO `role_per` VALUES ('428', '15', '18');
INSERT INTO `role_per` VALUES ('429', '15', '21');
INSERT INTO `role_per` VALUES ('430', '15', '22');
INSERT INTO `role_per` VALUES ('431', '15', '23');
INSERT INTO `role_per` VALUES ('432', '15', '27');
INSERT INTO `role_per` VALUES ('433', '15', '28');
INSERT INTO `role_per` VALUES ('434', '15', '29');
INSERT INTO `role_per` VALUES ('435', '15', '30');
INSERT INTO `role_per` VALUES ('436', '15', '31');
INSERT INTO `role_per` VALUES ('437', '15', '32');
INSERT INTO `role_per` VALUES ('438', '15', '34');
INSERT INTO `role_per` VALUES ('439', '15', '37');
INSERT INTO `role_per` VALUES ('440', '15', '38');
INSERT INTO `role_per` VALUES ('441', '15', '42');
INSERT INTO `role_per` VALUES ('442', '15', '43');
INSERT INTO `role_per` VALUES ('443', '15', '44');
INSERT INTO `role_per` VALUES ('444', '15', '47');
INSERT INTO `role_per` VALUES ('445', '15', '48');
INSERT INTO `role_per` VALUES ('446', '15', '49');
INSERT INTO `role_per` VALUES ('447', '15', '54');
INSERT INTO `role_per` VALUES ('448', '15', '50');
INSERT INTO `role_per` VALUES ('449', '15', '51');
INSERT INTO `role_per` VALUES ('450', '15', '52');
INSERT INTO `role_per` VALUES ('451', '15', '56');
INSERT INTO `role_per` VALUES ('452', '15', '57');
INSERT INTO `role_per` VALUES ('453', '15', '17');
INSERT INTO `role_per` VALUES ('454', '15', '20');
INSERT INTO `role_per` VALUES ('455', '15', '19');
INSERT INTO `role_per` VALUES ('456', '15', '25');
INSERT INTO `role_per` VALUES ('457', '15', '26');
INSERT INTO `role_per` VALUES ('458', '15', '24');
INSERT INTO `role_per` VALUES ('459', '15', '41');
INSERT INTO `role_per` VALUES ('460', '15', '45');
INSERT INTO `role_per` VALUES ('461', '15', '46');
INSERT INTO `role_per` VALUES ('462', '15', '39');
INSERT INTO `role_per` VALUES ('463', '15', '55');
INSERT INTO `role_per` VALUES ('464', '15', '58');
INSERT INTO `role_per` VALUES ('465', '15', '59');
INSERT INTO `role_per` VALUES ('466', '15', '60');
INSERT INTO `role_per` VALUES ('467', '15', '61');
INSERT INTO `role_per` VALUES ('468', '15', '62');
INSERT INTO `role_per` VALUES ('469', '15', '-1');
INSERT INTO `role_per` VALUES ('470', '15', '33');
INSERT INTO `role_per` VALUES ('471', '15', '35');

-- ----------------------------
-- Table structure for sys_task
-- ----------------------------
DROP TABLE IF EXISTS `sys_task`;
CREATE TABLE `sys_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `method_name` varchar(255) DEFAULT NULL COMMENT '任务调用的方法名',
  `is_concurrent` varchar(255) DEFAULT NULL COMMENT '任务是否有状态',
  `description` varchar(255) DEFAULT NULL COMMENT '任务描述',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `bean_class` varchar(255) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `job_status` varchar(255) DEFAULT NULL COMMENT '任务状态',
  `job_group` varchar(255) DEFAULT NULL COMMENT '任务分组',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `spring_bean` varchar(255) DEFAULT NULL COMMENT 'Spring bean',
  `job_name` varchar(255) DEFAULT NULL COMMENT '任务名',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_task
-- ----------------------------
INSERT INTO `sys_task` VALUES ('2', '0/10 * * * * ?', 'run1', '1', '', '4028ea815a3d2a8c015a3d2f8d2a0002', 'com.bootdo.common.task.WelcomeJob', '2017-05-19 18:30:56', '0', 'group1', '2017-05-19 18:31:07', null, '', 'welcomJob');
INSERT INTO `sys_task` VALUES ('8', '*/1 * * * * ? *', null, null, '对于预定超过一周的订单 ，如果没有支付，将把房屋状态改变，以及废除订单', null, 'com.graduation.renthouse.system.job.task.HouseJob', null, '0', '订单、房屋', null, null, null, '房屋预定超时');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `description` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标题描述',
  `house_id` int(16) DEFAULT NULL COMMENT '房屋id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('5', '干净', null);
INSERT INTO `tag` VALUES ('6', '整洁', null);
INSERT INTO `tag` VALUES ('7', '近地铁', null);
INSERT INTO `tag` VALUES ('8', '交通方便', null);
INSERT INTO `tag` VALUES ('9', '人流量少', null);
INSERT INTO `tag` VALUES ('10', '附近 商场多', null);
INSERT INTO `tag` VALUES ('11', '都挺好', null);

-- ----------------------------
-- Table structure for tag_house
-- ----------------------------
DROP TABLE IF EXISTS `tag_house`;
CREATE TABLE `tag_house` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `tag_id` int(16) DEFAULT NULL,
  `house_id` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag_house
-- ----------------------------
INSERT INTO `tag_house` VALUES ('10', '7', '28');
INSERT INTO `tag_house` VALUES ('11', '5', '28');

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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tenant
-- ----------------------------

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
INSERT INTO `user` VALUES ('29', '测试人员', 'testman', '123', '13283354149', '男', '22', '0', '2019-03-18 16:55:20', '2019-05-18 20:55:12');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `user_id` int(16) DEFAULT NULL,
  `role_id` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('24', '28', '15');
INSERT INTO `user_role` VALUES ('32', '29', '16');
