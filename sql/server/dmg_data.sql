/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : l2tenkai

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2016-10-23 20:49:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dmg_data`
-- ----------------------------
DROP TABLE IF EXISTS `dmg_data`;
CREATE TABLE `dmg_data` (
  `classid` int(10) NOT NULL,
  `newdmg` int(10) DEFAULT NULL,
  `newname` varchar(35) DEFAULT NULL,
  `playerid` int(10) DEFAULT NULL,
  `hwId` varchar(35) DEFAULT NULL,
  `externalIP` varchar(35) DEFAULT NULL,
  `internalIP` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`classid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dmg_data
-- ----------------------------
INSERT INTO `dmg_data` VALUES ('148', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('149', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('150', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('151', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('152', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('153', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('154', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('155', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('156', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('157', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('158', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('159', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('160', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('161', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('162', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('163', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('164', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('165', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('166', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('167', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('168', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('169', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('170', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('171', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('172', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('173', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('174', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('175', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('176', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('177', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('178', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('179', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('180', '0', '', '0', '', '', '');
INSERT INTO `dmg_data` VALUES ('181', '0', '', '0', '', '', '');
