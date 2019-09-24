/*
 Navicat Premium Data Transfer

 Source Server         : myconnect
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : healthclub3

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 16/07/2019 14:47:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for card_types
-- ----------------------------
DROP TABLE IF EXISTS `card_types`;
CREATE TABLE `card_types`  (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `discount` double NULL DEFAULT NULL,
  `effectivetime` int(11) NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `typename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`t_id`) USING BTREE,
  UNIQUE INDEX `UK_3w6t9g10df9fi6et6x7052m01`(`typename`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of card_types
-- ----------------------------
INSERT INTO `card_types` VALUES (1, 0.95, 1, 35, '次卡');
INSERT INTO `card_types` VALUES (2, 0.85, 30, 100, '月卡');
INSERT INTO `card_types` VALUES (3, 0.8, 120, 300, '季卡');
INSERT INTO `card_types` VALUES (4, 0.75, 365, 600, '年卡');

-- ----------------------------
-- Table structure for courses
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses`  (
  `number` int(11) NOT NULL AUTO_INCREMENT,
  `coursefee` double NULL DEFAULT NULL,
  `coursetime` int(11) NULL DEFAULT NULL,
  `ispersonal` bit(1) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `profession_number` int(11) NULL DEFAULT NULL,
  `roomtype_number` int(11) NULL DEFAULT NULL,
  `freecardtype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES (1, 50, 50, b'0', '普通瑜伽', 1, 1, '季卡');
INSERT INTO `courses` VALUES (2, 80, 60, b'1', '进阶瑜伽', 2, 1, '季卡');
INSERT INTO `courses` VALUES (3, 100, 70, b'1', '高阶瑜伽', 3, 1, '年卡');
INSERT INTO `courses` VALUES (4, 50, 50, b'1', '普通健身', 4, 2, '月卡');
INSERT INTO `courses` VALUES (5, 80, 60, b'1', '进阶健身', 5, 2, '季卡');
INSERT INTO `courses` VALUES (6, 90, 40, b'1', '高阶健身', 6, 2, '年卡');
INSERT INTO `courses` VALUES (7, 20, 20, b'1', '健身指导', 7, 2, '月卡');
INSERT INTO `courses` VALUES (9, 60, 60, b'1', '瑜伽课', 1, 2, '季卡');

-- ----------------------------
-- Table structure for customer_files
-- ----------------------------
DROP TABLE IF EXISTS `customer_files`;
CREATE TABLE `customer_files`  (
  `f_id` int(11) NOT NULL,
  `aim` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  `bmi` double NULL DEFAULT NULL,
  `fatrate` double NULL DEFAULT NULL,
  `frequency` int(11) NULL DEFAULT NULL,
  `heartfunction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hibit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hight` double NULL DEFAULT NULL,
  `injury_history` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `recordtime` date NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wight` double NULL DEFAULT NULL,
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_files
-- ----------------------------
INSERT INTO `customer_files` VALUES (4, '增肌', '2000-02-01', 0.5, 0.4, 5, '不正常', '打篮球', 178, '无', '出租车', NULL, '男', 50);

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers`  (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `balance` double NULL DEFAULT NULL,
  `effective_deadline` date NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cardtype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`c_id`) USING BTREE,
  INDEX `FKjng8pxhuii3qvhwokygl69410`(`cardtype`) USING BTREE,
  CONSTRAINT `FKjng8pxhuii3qvhwokygl69410` FOREIGN KEY (`cardtype`) REFERENCES `card_types` (`typename`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO `customers` VALUES (4, 530, '2019-07-17', '陈大仙', '1775492555', '季卡');
INSERT INTO `customers` VALUES (5, 78, '2019-07-17', '虹', '185826655', '月卡');
INSERT INTO `customers` VALUES (6, 1518, '2019-07-08', '大哥', '1833654545', '月卡');
INSERT INTO `customers` VALUES (8, 888, '2019-07-17', '二哥', '123456789', '季卡');
INSERT INTO `customers` VALUES (9, 888, '2019-07-17', '三个', '1885426366', '季卡');
INSERT INTO `customers` VALUES (10, 888, '2019-06-06', '张飞', '654321', '季卡');
INSERT INTO `customers` VALUES (11, 888, '2019-07-17', '刘备', '52417879', '季卡');
INSERT INTO `customers` VALUES (12, 888, '2019-07-17', '武松', '555555', '季卡');
INSERT INTO `customers` VALUES (13, 79865, '2019-09-28', '陈虹材', '17754921939', '月卡');
INSERT INTO `customers` VALUES (14, 80000, '2019-06-10', '撒大声地', '17754829999', NULL);

-- ----------------------------
-- Table structure for instructors
-- ----------------------------
DROP TABLE IF EXISTS `instructors`;
CREATE TABLE `instructors`  (
  `jobnumber` int(11) NOT NULL AUTO_INCREMENT,
  `basicsalary` double NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `courses` int(11) NULL DEFAULT NULL,
  `rankname` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`jobnumber`) USING BTREE,
  INDEX `FK6a3rhh5vo6e2q0htq1e6g1ryw`(`courses`) USING BTREE,
  INDEX `FKgybv4eov7yabf7i1ypj7842no`(`rankname`) USING BTREE,
  CONSTRAINT `FK6a3rhh5vo6e2q0htq1e6g1ryw` FOREIGN KEY (`courses`) REFERENCES `courses` (`number`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKgybv4eov7yabf7i1ypj7842no` FOREIGN KEY (`rankname`) REFERENCES `professionranks` (`profession_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of instructors
-- ----------------------------
INSERT INTO `instructors` VALUES (1, 10000, '李白', '无', '女', 1, 1);
INSERT INTO `instructors` VALUES (2, 9000, '白居易', '无', '男', 2, 2);
INSERT INTO `instructors` VALUES (3, 10000, '王安石', '无', '女', 2, 2);
INSERT INTO `instructors` VALUES (4, 12000, '杜甫', '无', '男', 3, 3);
INSERT INTO `instructors` VALUES (5, 4000, '嬴政', '无', '男', 4, 4);
INSERT INTO `instructors` VALUES (6, 4500, '二乔', '无', '男', 5, 5);
INSERT INTO `instructors` VALUES (7, 6000, '大乔', '无', '女', 6, 6);
INSERT INTO `instructors` VALUES (8, 7000, '武则天', '无', '女', 7, 7);
INSERT INTO `instructors` VALUES (11, 6000, '陈虹材', '无', '男', 4, 4);

-- ----------------------------
-- Table structure for lesson_customer_info
-- ----------------------------
DROP TABLE IF EXISTS `lesson_customer_info`;
CREATE TABLE `lesson_customer_info`  (
  `id` int(11) NOT NULL,
  `c_id` int(11) NOT NULL,
  PRIMARY KEY (`id`, `c_id`) USING BTREE,
  INDEX `FKc06qkkq4thr8b6h0cldegjw1n`(`c_id`) USING BTREE,
  CONSTRAINT `FKc06qkkq4thr8b6h0cldegjw1n` FOREIGN KEY (`c_id`) REFERENCES `customers` (`c_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKgx2eixh19m98nxscoax3h3pi` FOREIGN KEY (`id`) REFERENCES `lessons` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lesson_customer_info
-- ----------------------------
INSERT INTO `lesson_customer_info` VALUES (6, 4);
INSERT INTO `lesson_customer_info` VALUES (7, 4);
INSERT INTO `lesson_customer_info` VALUES (2, 5);
INSERT INTO `lesson_customer_info` VALUES (1, 6);
INSERT INTO `lesson_customer_info` VALUES (2, 6);
INSERT INTO `lesson_customer_info` VALUES (1, 8);
INSERT INTO `lesson_customer_info` VALUES (2, 8);
INSERT INTO `lesson_customer_info` VALUES (5, 8);
INSERT INTO `lesson_customer_info` VALUES (2, 9);
INSERT INTO `lesson_customer_info` VALUES (1, 13);

-- ----------------------------
-- Table structure for lessons
-- ----------------------------
DROP TABLE IF EXISTS `lessons`;
CREATE TABLE `lessons`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NULL DEFAULT NULL,
  `instructor` int(11) NULL DEFAULT NULL,
  `room` int(11) NULL DEFAULT NULL,
  `starttime` datetime(0) NULL DEFAULT NULL,
  `endtime` datetime(0) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK5dcingaf5lqni1q19t80as4vr`(`number`) USING BTREE,
  INDEX `FKkdg3bkaa54mjio86ejupylt55`(`instructor`) USING BTREE,
  INDEX `FKcesf44typjkig0twphds0qtcy`(`room`) USING BTREE,
  CONSTRAINT `FK5dcingaf5lqni1q19t80as4vr` FOREIGN KEY (`number`) REFERENCES `courses` (`number`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKcesf44typjkig0twphds0qtcy` FOREIGN KEY (`room`) REFERENCES `rooms` (`number`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKkdg3bkaa54mjio86ejupylt55` FOREIGN KEY (`instructor`) REFERENCES `instructors` (`jobnumber`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lessons
-- ----------------------------
INSERT INTO `lessons` VALUES (1, 2, 2, 2, '2019-06-05 08:58:32', '2019-06-05 09:58:10', '正在报名');
INSERT INTO `lessons` VALUES (2, 1, 1, 1, '2019-06-05 12:59:16', '2019-06-05 13:59:04', '正在报名');
INSERT INTO `lessons` VALUES (3, 1, 1, 1, '2019-06-13 08:59:53', '2019-06-13 09:59:32', '正在报名');
INSERT INTO `lessons` VALUES (4, 4, 4, 4, '2019-06-13 21:00:17', '2019-06-13 22:00:14', '正在报名');
INSERT INTO `lessons` VALUES (5, 4, 5, 4, '2019-06-12 14:30:00', '2019-06-12 15:20:00', '正在报名');
INSERT INTO `lessons` VALUES (6, 7, 8, 4, '2019-06-14 10:30:00', '2019-06-14 10:50:00', '正在报名');
INSERT INTO `lessons` VALUES (7, 3, 4, 2, '2019-07-10 06:10:00', '2019-07-10 07:20:00', '正在报名');
INSERT INTO `lessons` VALUES (8, 2, 3, 2, '2019-06-06 02:10:00', '2019-06-06 03:10:00', '正在报名');

-- ----------------------------
-- Table structure for professionranks
-- ----------------------------
DROP TABLE IF EXISTS `professionranks`;
CREATE TABLE `professionranks`  (
  `profession_number` int(11) NOT NULL AUTO_INCREMENT,
  `group_classfee` double NULL DEFAULT NULL,
  `personal_calssfee` double NULL DEFAULT NULL,
  `personal_calssfee_commission` float NULL DEFAULT NULL,
  `profession_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rank_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`profession_number`) USING BTREE,
  UNIQUE INDEX `UK_jjtfm0dwfflvpw8y5g6xkjcq4`(`rank_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of professionranks
-- ----------------------------
INSERT INTO `professionranks` VALUES (1, 200, 5000, 1.5, '瑜伽教练', '瑜伽初级');
INSERT INTO `professionranks` VALUES (2, 300, 600, 1.3, '瑜伽教练', '瑜伽中级');
INSERT INTO `professionranks` VALUES (3, 400, 700, 1.4, '瑜伽教练', '瑜伽高级');
INSERT INTO `professionranks` VALUES (4, 200, 220, 1.1, '健身教练', '初级健身');
INSERT INTO `professionranks` VALUES (5, 300, 600, 1.3, '健身教练', '中级健身');
INSERT INTO `professionranks` VALUES (6, 400, 700, 1.4, '健身教练', '高级健身');
INSERT INTO `professionranks` VALUES (7, 200, 300, 1.1, '健身指导师', '健身指导');
INSERT INTO `professionranks` VALUES (9, 500, 600, 1.2, '篮球指导师', '篮球一级');

-- ----------------------------
-- Table structure for records
-- ----------------------------
DROP TABLE IF EXISTS `records`;
CREATE TABLE `records`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double NULL DEFAULT NULL,
  `date` datetime(0) NULL DEFAULT NULL,
  `recordType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `customer` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKp75fyywdr9lr3vg8dgtkxhbup`(`customer`) USING BTREE,
  CONSTRAINT `FKp75fyywdr9lr3vg8dgtkxhbup` FOREIGN KEY (`customer`) REFERENCES `customers` (`c_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of records
-- ----------------------------
INSERT INTO `records` VALUES (1, 800, '2019-06-06 20:50:58', '充值', 5);
INSERT INTO `records` VALUES (2, 900, '2019-06-10 20:51:17', '充值', 8);
INSERT INTO `records` VALUES (3, -60, '2019-06-06 20:51:37', '预定课程', 5);
INSERT INTO `records` VALUES (4, 100, '2019-04-11 23:55:24', '充值', 10);
INSERT INTO `records` VALUES (5, 600, '2019-02-01 23:55:48', '充值', 9);
INSERT INTO `records` VALUES (6, 300, '2018-03-14 23:56:12', '充值', 13);
INSERT INTO `records` VALUES (7, 600, '2019-05-22 23:56:35', '充值', 8);
INSERT INTO `records` VALUES (8, 1000, '2019-07-24 23:56:58', '充值', 6);
INSERT INTO `records` VALUES (9, 1200, '2019-08-07 23:57:15', '充值', 4);
INSERT INTO `records` VALUES (10, 900, '2019-06-08 18:22:53', '充值', 6);
INSERT INTO `records` VALUES (11, 900, '2019-06-08 18:23:03', '充值', 13);
INSERT INTO `records` VALUES (12, 35, '2019-06-08 19:05:00', '购卡', 13);
INSERT INTO `records` VALUES (13, 300, '2019-06-08 19:05:08', '购卡', 13);
INSERT INTO `records` VALUES (14, 300, '2019-06-08 19:05:13', '购卡', 13);
INSERT INTO `records` VALUES (15, 300, '2019-06-08 19:05:21', '购卡', 13);
INSERT INTO `records` VALUES (16, 35, '2019-06-10 10:37:47', '购卡', 6);
INSERT INTO `records` VALUES (17, 35, '2019-06-10 10:37:52', '购卡', 6);
INSERT INTO `records` VALUES (18, 100, '2019-06-10 10:37:58', '购卡', 6);
INSERT INTO `records` VALUES (19, 100, '2019-06-10 11:51:16', '购卡', 13);
INSERT INTO `records` VALUES (20, 50, '2019-06-10 11:51:57', '充值', 5);

-- ----------------------------
-- Table structure for rooms
-- ----------------------------
DROP TABLE IF EXISTS `rooms`;
CREATE TABLE `rooms`  (
  `number` int(11) NOT NULL AUTO_INCREMENT,
  `capacity` int(11) NULL DEFAULT NULL,
  `roomtype` int(11) NULL DEFAULT NULL,
  `roomname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`number`) USING BTREE,
  INDEX `FKb34ghhinrj7a79wphmn14blu9`(`roomtype`) USING BTREE,
  CONSTRAINT `FKb34ghhinrj7a79wphmn14blu9` FOREIGN KEY (`roomtype`) REFERENCES `roomtypes` (`number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rooms
-- ----------------------------
INSERT INTO `rooms` VALUES (1, 50, 1, '瑜伽教室1');
INSERT INTO `rooms` VALUES (2, 60, 1, '瑜伽教室2');
INSERT INTO `rooms` VALUES (3, 70, 1, '瑜伽教室3');
INSERT INTO `rooms` VALUES (4, 50, 2, '健身房1');
INSERT INTO `rooms` VALUES (5, 60, 2, '健身房2');
INSERT INTO `rooms` VALUES (6, 70, 2, '健身房3');

-- ----------------------------
-- Table structure for roomtypes
-- ----------------------------
DROP TABLE IF EXISTS `roomtypes`;
CREATE TABLE `roomtypes`  (
  `number` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roomtypes
-- ----------------------------
INSERT INTO `roomtypes` VALUES (1, '瑜伽教室');
INSERT INTO `roomtypes` VALUES (2, '健身房');
INSERT INTO `roomtypes` VALUES (3, '空教室');
INSERT INTO `roomtypes` VALUES (4, '舞蹈室');
INSERT INTO `roomtypes` VALUES (5, '器材室');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userPass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userName`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', '123456');

-- ----------------------------
-- Table structure for wages
-- ----------------------------
DROP TABLE IF EXISTS `wages`;
CREATE TABLE `wages`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `month` date NULL DEFAULT NULL,
  `monthWage` double NULL DEFAULT NULL,
  `insid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKsfu8uqbgoldqfj11t7osswxw2`(`insid`) USING BTREE,
  CONSTRAINT `FKsfu8uqbgoldqfj11t7osswxw2` FOREIGN KEY (`insid`) REFERENCES `instructors` (`jobnumber`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wages
-- ----------------------------
INSERT INTO `wages` VALUES (1, '2019-05-31', 8000, 1);
INSERT INTO `wages` VALUES (2, '2019-05-31', 9000, 2);
INSERT INTO `wages` VALUES (3, '2019-05-31', 10000, 3);
INSERT INTO `wages` VALUES (12, '2019-06-10', 10200, 1);
INSERT INTO `wages` VALUES (13, '2019-06-10', 9300, 2);
INSERT INTO `wages` VALUES (14, '2019-06-10', 10000, 3);
INSERT INTO `wages` VALUES (15, '2019-06-10', 12000, 4);
INSERT INTO `wages` VALUES (16, '2019-06-10', 4000, 5);
INSERT INTO `wages` VALUES (17, '2019-06-10', 4500, 6);
INSERT INTO `wages` VALUES (18, '2019-06-10', 6000, 7);
INSERT INTO `wages` VALUES (19, '2019-06-10', 7000, 8);

SET FOREIGN_KEY_CHECKS = 1;
