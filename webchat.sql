/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : webchat

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2019-06-27 12:49:12
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
INSERT INTO `friends` VALUES ('156154045314', '156154053251', '2019-06-26 16:00:14');
INSERT INTO `friends` VALUES ('156154053251', '156154045314', '2019-06-26 16:00:14');

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
INSERT INTO `message` VALUES ('1561564813654156154045314', '我是xiao', '3', '156154053251', '156154045314', '1', '2019-06-26 16:00:14');
INSERT INTO `message` VALUES ('1561564860548156154045314', '我是xiao', '3', '156154053251', '156154045314', '1', '2019-06-26 16:01:01');
INSERT INTO `message` VALUES ('1561565022000156154045314', '123\n', '1', '156154053251', '156154045314', '1', '2019-06-26 16:03:42');
INSERT INTO `message` VALUES ('1561565085000156154045314', '123\n', '1', '156154053251', '156154045314', '1', '2019-06-26 16:04:45');
INSERT INTO `message` VALUES ('1561565089000156154053251', 'hello\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:04:49');
INSERT INTO `message` VALUES ('1561565096000156154053251', 'yes\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:04:56');
INSERT INTO `message` VALUES ('1561565102000156154045314', 'ok\n', '1', '156154053251', '156154045314', '1', '2019-06-26 16:05:02');
INSERT INTO `message` VALUES ('1561565112000156154053251', 'thanks\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:05:12');
INSERT INTO `message` VALUES ('1561565129000156154045314', 'no\n', '1', '156154053251', '156154045314', '1', '2019-06-26 16:05:29');
INSERT INTO `message` VALUES ('1561565131000156154045314', 'no\n', '1', '156154053251', '156154045314', '1', '2019-06-26 16:05:31');
INSERT INTO `message` VALUES ('1561565132000156154045314', 'no\n', '1', '156154053251', '156154045314', '1', '2019-06-26 16:05:32');
INSERT INTO `message` VALUES ('1561565134000156154045314', 'no\n', '1', '156154053251', '156154045314', '1', '2019-06-26 16:05:34');
INSERT INTO `message` VALUES ('1561565255000156154045314', '123\n', '1', '156154053251', '156154045314', '1', '2019-06-26 16:07:35');
INSERT INTO `message` VALUES ('1561565274000156154053251', '123\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:07:54');
INSERT INTO `message` VALUES ('1561565638000156154053251', '123\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:13:58');
INSERT INTO `message` VALUES ('1561565766000156154053251', '123\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:16:06');
INSERT INTO `message` VALUES ('1561565834000156154053251', '123\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:17:14');
INSERT INTO `message` VALUES ('1561566069000156154053251', '123\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:21:09');
INSERT INTO `message` VALUES ('1561566110000156154053251', '123\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:21:50');
INSERT INTO `message` VALUES ('1561566122000156154053251', '123\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:22:02');
INSERT INTO `message` VALUES ('1561566202000156154053251', '123\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:23:22');
INSERT INTO `message` VALUES ('1561566261000156154053251', '123\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:24:21');
INSERT INTO `message` VALUES ('1561566386000156154053251', '123\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:26:26');
INSERT INTO `message` VALUES ('1561566408000156154053251', '123\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:26:48');
INSERT INTO `message` VALUES ('1561566503000156154053251', '123\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:28:23');
INSERT INTO `message` VALUES ('1561566565000156154053251', '123\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:29:25');
INSERT INTO `message` VALUES ('1561566748000156154053251', '123\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:32:28');
INSERT INTO `message` VALUES ('1561566754000156154053251', '123\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:32:34');
INSERT INTO `message` VALUES ('1561566783000156154045314', '123\n', '1', '156154053251', '156154045314', '1', '2019-06-26 16:33:03');
INSERT INTO `message` VALUES ('1561566789000156154053251', '123\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:33:09');
INSERT INTO `message` VALUES ('1561566803000156154053251', '123\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:33:23');
INSERT INTO `message` VALUES ('1561566812000156154053251', '1233\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:33:32');
INSERT INTO `message` VALUES ('1561566890000156154053251', '123\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:34:50');
INSERT INTO `message` VALUES ('1561567020000156154053251', '123\n', '1', '156154045314', '156154053251', '1', '2019-06-26 16:37:00');
INSERT INTO `message` VALUES ('1561567442000156154045314', '123\n', '1', '156154053251', '156154045314', '1', '2019-06-26 16:44:02');
INSERT INTO `message` VALUES ('1561567514000156154045314', '123\n', '1', '156154053251', '156154045314', '1', '2019-06-26 16:45:14');
INSERT INTO `message` VALUES ('1561567518000156154045314', '123\n', '1', '156154053251', '156154045314', '1', '2019-06-26 16:45:18');
INSERT INTO `message` VALUES ('1561567560000156154045314', '123\n', '1', '156154053251', '156154045314', '1', '2019-06-26 16:46:00');
INSERT INTO `message` VALUES ('1561567565000156154045314', '123\n', '1', '156154053251', '156154045314', '1', '2019-06-26 16:46:05');
INSERT INTO `message` VALUES ('1561567568000156154045314', '123\n', '1', '156154053251', '156154045314', '1', '2019-06-26 16:46:08');
INSERT INTO `message` VALUES ('1561567762000156154045314', '123\n', '1', '156154053251', '156154045314', '1', '2019-06-26 16:49:22');

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
  `gender` varchar(16) DEFAULT '男',
  `address` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  KEY `user_id` (`user_id`),
  CONSTRAINT `normal_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of normal_user
-- ----------------------------
INSERT INTO `normal_user` VALUES ('156154045314', '男', '四川-北京市-延庆区', null);
INSERT INTO `normal_user` VALUES ('156154053251', '女', '北京-北京市-延庆区', '26');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('0', '普通用户');
INSERT INTO `role` VALUES ('1', '管理员');

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
INSERT INTO `sercurity_log` VALUES ('1561540453141561540459032', '156154045314', '2019-06-26 09:14:19', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561544477093', '156154045314', '2019-06-26 10:21:17', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561544527853', '156154045314', '2019-06-26 10:22:08', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561546116038', '156154045314', '2019-06-26 10:48:36', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561547142076', '156154045314', '2019-06-26 11:05:42', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561547249487', '156154045314', '2019-06-26 11:07:29', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561547324265', '156154045314', '2019-06-26 11:08:44', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561547380634', '156154045314', '2019-06-26 11:09:41', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561547417114', '156154045314', '2019-06-26 11:10:17', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561547815105', '156154045314', '2019-06-26 11:16:55', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561547914171', '156154045314', '2019-06-26 11:18:34', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561548419387', '156154045314', '2019-06-26 11:26:59', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561548802747', '156154045314', '2019-06-26 11:33:23', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561548942966', '156154045314', '2019-06-26 11:35:43', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561548991786', '156154045314', '2019-06-26 11:36:32', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561549060704', '156154045314', '2019-06-26 11:37:41', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561560046088', '156154045314', '2019-06-26 14:40:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561561626263', '156154045314', '2019-06-26 15:07:06', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561562391804', '156154045314', '2019-06-26 15:19:52', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561562409240', '156154045314', '2019-06-26 15:20:09', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561563402173', '156154045314', '2019-06-26 15:36:42', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561564064242', '156154045314', '2019-06-26 15:47:44', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561564778580', '156154045314', '2019-06-26 15:59:39', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561565590446', '156154045314', '2019-06-26 16:13:10', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561565779455', '156154045314', '2019-06-26 16:16:19', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561566488113', '156154045314', '2019-06-26 16:28:08', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561567044363', '156154045314', '2019-06-26 16:37:24', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561568071836', '156154045314', '2019-06-26 16:54:32', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561568181164', '156154045314', '2019-06-26 16:56:21', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561568836622', '156154045314', '2019-06-26 17:07:17', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561568963255', '156154045314', '2019-06-26 17:09:23', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561569102181', '156154045314', '2019-06-26 17:11:42', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540453141561569162196', '156154045314', '2019-06-26 17:12:42', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540532511561540542355', '156154053251', '2019-06-26 09:15:42', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540532511561544456022', '156154053251', '2019-06-26 10:20:56', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540532511561546109007', '156154053251', '2019-06-26 10:48:29', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540532511561547244570', '156154053251', '2019-06-26 11:07:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540532511561560054454', '156154053251', '2019-06-26 14:40:54', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540532511561561457468', '156154053251', '2019-06-26 15:04:17', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540532511561562425694', '156154053251', '2019-06-26 15:20:26', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540532511561563069133', '156154053251', '2019-06-26 15:31:09', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540532511561563433622', '156154053251', '2019-06-26 15:37:14', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540532511561564116711', '156154053251', '2019-06-26 15:48:37', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540532511561564786726', '156154053251', '2019-06-26 15:59:47', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540532511561565605026', '156154053251', '2019-06-26 16:13:25', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540532511561566465464', '156154053251', '2019-06-26 16:27:45', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540532511561567059053', '156154053251', '2019-06-26 16:37:39', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540532511561567812287', '156154053251', '2019-06-26 16:50:12', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540532511561568053988', '156154053251', '2019-06-26 16:54:14', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('1561540532511561568186858', '156154053251', '2019-06-26 16:56:27', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('admin1561596700491', 'admin', '2019-06-27 00:51:40', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('admin1561596731549', 'admin', '2019-06-27 00:52:12', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('admin1561601765373', 'admin', '2019-06-27 02:16:05', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('admin1561601841171', 'admin', '2019-06-27 02:17:21', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('admin1561610335545', 'admin', '2019-06-27 04:38:56', '0:0:0:0:0:0:0:1');
INSERT INTO `sercurity_log` VALUES ('admin1561610470525', 'admin', '2019-06-27 04:41:11', '0:0:0:0:0:0:0:1');

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
  `role` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('156154045314', null, 'xiao', '/img/156154045314.png', '18281823545', '123xiao', '0', '1');
INSERT INTO `user` VALUES ('156154053251', '123', 'wang', '/img/user.png', '17883697078', '123xiao', '0', '0');
INSERT INTO `user` VALUES ('admin', 'admin', 'admin', '/img/user.png', '18281823545', 'admin', '1', '0');
