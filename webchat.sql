/*
Navicat MySQL Data Transfer

Source Server         : 123
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : webchat

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-06-24 16:06:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `user_id` varchar(32) NOT NULL,
  KEY `user_id` (`user_id`),
  CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for `broadcast`
-- ----------------------------
DROP TABLE IF EXISTS `broadcast`;
CREATE TABLE `broadcast` (
  `broadcast_id` varchar(32) NOT NULL,
  `broadcast_content` varchar(255) NOT NULL,
  `broadcast_time` datetime NOT NULL,
  `user_id` varchar(32) NOT NULL,
  PRIMARY KEY (`broadcast_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `broadcast_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of broadcast
-- ----------------------------

-- ----------------------------
-- Table structure for `friends`
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `user_id` varchar(32) NOT NULL,
  `friend_id` varchar(32) NOT NULL,
  `add_time` datetime NOT NULL,
  KEY `user_id` (`user_id`),
  KEY `friend_id` (`friend_id`),
  CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`friend_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friends
-- ----------------------------
INSERT INTO `friends` VALUES ('123456789101', '123456789102', '2019-06-22 16:55:03');
INSERT INTO `friends` VALUES ('123456789103', '123456789101', '2019-06-22 16:55:16');

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `message_id` varchar(32) NOT NULL,
  `content` varchar(255) NOT NULL,
  `message_type` int(11) DEFAULT NULL,
  `receiver_id` varchar(32) NOT NULL,
  `sender_id` varchar(32) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `send_date` datetime NOT NULL,
  PRIMARY KEY (`message_id`),
  KEY `message_type` (`message_type`),
  KEY `receiver_id` (`receiver_id`),
  KEY `sender_id` (`sender_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`message_type`) REFERENCES `message_type` (`messagae_type_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `message_ibfk_3` FOREIGN KEY (`sender_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1561337915548123456789101', '我是', '3', '123456789102', '123456789101', null, '0000-00-00 00:00:00');
INSERT INTO `message` VALUES ('1561341545756123456789101', '', '3', '123456789102', '123456789101', '0', '0000-00-00 00:00:00');
INSERT INTO `message` VALUES ('1561342498065123456789101', 'aaa', '3', '123456789102', '123456789101', '0', '2019-06-24 02:14:58');
INSERT INTO `message` VALUES ('21161432051', 'aaa', '3', '123456789102', '123456789101', null, '0000-00-00 00:00:00');

-- ----------------------------
-- Table structure for `message_type`
-- ----------------------------
DROP TABLE IF EXISTS `message_type`;
CREATE TABLE `message_type` (
  `messagae_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `message_type` varchar(32) NOT NULL,
  PRIMARY KEY (`messagae_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message_type
-- ----------------------------
INSERT INTO `message_type` VALUES ('1', '对话消息');
INSERT INTO `message_type` VALUES ('2', '系统消息');
INSERT INTO `message_type` VALUES ('3', '好友消息');
INSERT INTO `message_type` VALUES ('4', '未读消息');

-- ----------------------------
-- Table structure for `normal_user`
-- ----------------------------
DROP TABLE IF EXISTS `normal_user`;
CREATE TABLE `normal_user` (
  `user_id` varchar(32) NOT NULL,
  `gender` varchar(16) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  KEY `user_id` (`user_id`),
  CONSTRAINT `normal_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of normal_user
-- ----------------------------
INSERT INTO `normal_user` VALUES ('123456789102', 's', 'dsadasdasdsa', '321');
INSERT INTO `normal_user` VALUES ('123456789103', 'm', 'dsadasd', null);
INSERT INTO `normal_user` VALUES ('123456789101', 'ss', 'dsad', null);

-- ----------------------------
-- Table structure for `sercurity_log`
-- ----------------------------
DROP TABLE IF EXISTS `sercurity_log`;
CREATE TABLE `sercurity_log` (
  `log_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `login_time` datetime NOT NULL,
  `login_address` varchar(255) NOT NULL,
  PRIMARY KEY (`log_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `sercurity_log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sercurity_log
-- ----------------------------

-- ----------------------------
-- Table structure for `system`
-- ----------------------------
DROP TABLE IF EXISTS `system`;
CREATE TABLE `system` (
  `system_id` varchar(32) NOT NULL,
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`system_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(32) NOT NULL,
  `name` varchar(16) DEFAULT NULL,
  `nickname` varchar(255) NOT NULL,
  `imgurl` varchar(255) DEFAULT '/img/user.png',
  `tel` varchar(16) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('123456789101', 'sss', 'shan', '/img/user.png', null, '123');
INSERT INTO `user` VALUES ('123456789102', 'xxx', 'wen', '/img/user.png', null, '123');
INSERT INTO `user` VALUES ('123456789103', 'mmm', '12345678910w', '/img/user.png', null, '123');
