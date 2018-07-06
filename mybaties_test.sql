/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : mybaties_test

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-02-16 22:11:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(54) DEFAULT NULL,
  `author` varchar(54) DEFAULT NULL,
  `publicationdate` date DEFAULT NULL,
  `publication` varchar(150) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `image` varchar(54) DEFAULT NULL,
  `remark` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '疯狂Java讲义', '李刚 编著', '2008-10-01', '电子工业出版社', '74.2', 'java.jpg', '本书来自作者3年的Java培训经历，凝结了3000个小时的授课经验......');
INSERT INTO `book` VALUES ('2', '轻量级Java EE企业应用实战', '李刚 编著', '2007-11-23', '电子工业出版社', '88.6', 'ee.jpg', '本书来自作者3年的Java EE培训经历，凝结了3000个小时的授课经验......');
INSERT INTO `book` VALUES ('3', '疯狂Android讲义', '李刚 编著', '2006-07-01', '电子工业出版社', '56.3', 'android.jpg', '本书来自作者3年的Android培训经历，凝结了3000个小时的授课经验......');
INSERT INTO `book` VALUES ('4', '疯狂Ajax讲义', '李刚 编著', '2005-09-01', '电子工业出版社', '101.1', 'ajax.jpg', '本书来自作者3年的JAjax培训经历，凝结了3000个小时的授课经验......');

-- ----------------------------
-- Table structure for `tb_article`
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES ('1', '疯狂Java讲义', '109.90', '李刚老师经典著作');
INSERT INTO `tb_article` VALUES ('2', '疯狂Android讲义', '99.90', '李刚老师经典著作');
INSERT INTO `tb_article` VALUES ('3', '疯狂IOS讲义', '88.80', '李刚老师经典著作');
INSERT INTO `tb_article` VALUES ('4', 'Spring', '66.00', '李刚老师经典著作');
INSERT INTO `tb_article` VALUES ('5', 'Spring', '111.00', '李刚老师');

-- ----------------------------
-- Table structure for `tb_book`
-- ----------------------------
DROP TABLE IF EXISTS `tb_book`;
CREATE TABLE `tb_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(54) DEFAULT NULL,
  `author` varchar(54) DEFAULT NULL,
  `publicationdate` date DEFAULT NULL,
  `publication` varchar(150) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `image` varchar(54) DEFAULT NULL,
  `remark` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_book
-- ----------------------------
INSERT INTO `tb_book` VALUES ('1', '疯狂Java讲义', '李刚 编著', '2008-10-01', '电子工业出版社', '74.2', 'java.jpg', '本书来自作者3年的Java培训经历，凝结了3000个小时的授课经验......');
INSERT INTO `tb_book` VALUES ('2', '轻量级Java EE企业应用实战', '李刚 编著', '2007-11-23', '电子工业出版社', '88.6', 'ee.jpg', '本书来自作者3年的Java EE培训经历，凝结了3000个小时的授课经验......');
INSERT INTO `tb_book` VALUES ('3', '疯狂Android讲义', '李刚 编著', '2006-07-01', '电子工业出版社', '56.3', 'android.jpg', '本书来自作者3年的Android培训经历，凝结了3000个小时的授课经验......');
INSERT INTO `tb_book` VALUES ('4', '疯狂Ajax讲义', '李刚 编著', '2005-09-01', '电子工业出版社', '101.1', 'ajax.jpg', '本书来自作者3年的JAjax培训经历，凝结了3000个小时的授课经验......');

-- ----------------------------
-- Table structure for `tb_item`
-- ----------------------------
DROP TABLE IF EXISTS `tb_item`;
CREATE TABLE `tb_item` (
  `order_id` int(11) NOT NULL,
  `article_id` int(11) NOT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_item
-- ----------------------------
INSERT INTO `tb_item` VALUES ('1', '1', '1');
INSERT INTO `tb_item` VALUES ('1', '2', '1');
INSERT INTO `tb_item` VALUES ('1', '3', '2');
INSERT INTO `tb_item` VALUES ('2', '1', '1');
INSERT INTO `tb_item` VALUES ('2', '4', '2');

-- ----------------------------
-- Table structure for `tb_order`
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(32) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('1', '6sad623', '888.9', '1');
INSERT INTO `tb_order` VALUES ('2', 'fd34234', '678', '1');

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `loginname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '杰克', 'jack', '123456', '13912323456', '广州');
INSERT INTO `tb_user` VALUES ('3', '李', 'li', '123456', '110110110', '北京');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `loginname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '杰克', 'jack', '123456', '13912323456', '广州');
INSERT INTO `user` VALUES ('3', '李', 'li', '123456', '110110110', '北京');
