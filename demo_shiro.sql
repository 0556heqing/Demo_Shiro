/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : demo_shiro

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-03-20 12:47:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `_menuId` bigint(20) NOT NULL AUTO_INCREMENT,
  `_parentId` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `_url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `_perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `_type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `_icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `_orderNum` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`_menuId`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '0', '系统管理', null, null, '0', 'fa fa-cog', '0');
INSERT INTO `t_menu` VALUES ('2', '1', '用户管理', 'sys/user.html', null, '1', 'fa fa-user', '1');
INSERT INTO `t_menu` VALUES ('3', '1', '角色管理', 'sys/role.html', null, '1', 'fa fa-user-secret', '2');
INSERT INTO `t_menu` VALUES ('4', '1', '菜单管理', 'sys/menu.html', null, '1', 'fa fa-th-list', '3');
INSERT INTO `t_menu` VALUES ('5', '1', 'SQL监控', 'druid/sql.html', null, '1', 'fa fa-bug', '4');
INSERT INTO `t_menu` VALUES ('15', '2', '查看', null, 'sys:user:list,sys:user:info', '2', null, '0');
INSERT INTO `t_menu` VALUES ('16', '2', '新增', null, 'sys:user:save,sys:role:select', '2', null, '0');
INSERT INTO `t_menu` VALUES ('17', '2', '修改', null, 'sys:user:update,sys:role:select', '2', null, '0');
INSERT INTO `t_menu` VALUES ('18', '2', '删除', null, 'sys:user:delete', '2', null, '0');
INSERT INTO `t_menu` VALUES ('19', '3', '查看', null, 'sys:role:list,sys:role:info', '2', null, '0');
INSERT INTO `t_menu` VALUES ('20', '3', '新增', null, 'sys:role:save,sys:menu:perms', '2', null, '0');
INSERT INTO `t_menu` VALUES ('21', '3', '修改', null, 'sys:role:update,sys:menu:perms', '2', null, '0');
INSERT INTO `t_menu` VALUES ('22', '3', '删除', null, 'sys:role:delete', '2', null, '0');
INSERT INTO `t_menu` VALUES ('23', '4', '查看', null, 'sys:menu:list,sys:menu:info', '2', null, '0');
INSERT INTO `t_menu` VALUES ('24', '4', '新增', null, 'sys:menu:save,sys:menu:select', '2', null, '0');
INSERT INTO `t_menu` VALUES ('25', '4', '修改', null, 'sys:menu:update,sys:menu:select', '2', null, '0');
INSERT INTO `t_menu` VALUES ('26', '4', '删除', null, 'sys:menu:delete', '2', null, '0');
INSERT INTO `t_menu` VALUES ('29', '0', 't1', 'http://www.baidu.com', null, '1', null, '0');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `_roleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `_roleName` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `_remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `_createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`_roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=1489391325845 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1489391325844', '菜单', null, '2017-03-13 15:48:46');

-- ----------------------------
-- Table structure for `t_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_roleId` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `_menuId` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('57', '1489391325844', '1');
INSERT INTO `t_role_menu` VALUES ('58', '1489391325844', '4');
INSERT INTO `t_role_menu` VALUES ('59', '1489391325844', '23');
INSERT INTO `t_role_menu` VALUES ('60', '1489391325844', '24');
INSERT INTO `t_role_menu` VALUES ('61', '1489391325844', '25');
INSERT INTO `t_role_menu` VALUES ('62', '1489391325844', '26');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `_userId` bigint(20) NOT NULL AUTO_INCREMENT,
  `_userName` varchar(50) NOT NULL COMMENT '用户名',
  `_password` varchar(100) DEFAULT NULL COMMENT '密码',
  `_email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `_mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `_status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `_createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`_userId`),
  UNIQUE KEY `username` (`_userName`)
) ENGINE=InnoDB AUTO_INCREMENT=1489391345196 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, null, '1', null);
INSERT INTO `t_user` VALUES ('1489391345195', 'heqing', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, null, '1', '2017-03-13 15:49:05');

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_userId` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `_roleId` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('8', '1489391345195', '1489391325844');
