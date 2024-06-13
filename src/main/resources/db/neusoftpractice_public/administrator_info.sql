/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : neusoftpractice_public

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 07/06/2024 10:08:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for administrator_info
-- ----------------------------
DROP TABLE IF EXISTS `administrator_info`;
CREATE TABLE `administrator_info`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of administrator_info
-- ----------------------------
INSERT INTO `administrator_info` VALUES ('1115604128331984896', '2015328754', 'Ye Xiaoming');
INSERT INTO `administrator_info` VALUES ('1115604511875919872', '2013567890', 'Ying Chiu Wai');
INSERT INTO `administrator_info` VALUES ('1115604605748637696', '2016456789', 'Shawn Gutierrez');
INSERT INTO `administrator_info` VALUES ('1115604677030834176', '2019654321', 'Tamura Rin');
INSERT INTO `administrator_info` VALUES ('1115604743795765248', '2021123456', 'Yeung Ling Ling');
INSERT INTO `administrator_info` VALUES ('1115604809029775360', '2021452435', 'Name');
INSERT INTO `administrator_info` VALUES ('1115604864553971712', '2016543210', 'Loui Tsz Hin');
INSERT INTO `administrator_info` VALUES ('1115604929158836224', '2015789234', 'Name');
INSERT INTO `administrator_info` VALUES ('1115604994338320384', '2015876543', 'Zhou Anqi');
INSERT INTO `administrator_info` VALUES ('1115605045110370304', '2021098765', 'Diane Shaw');

SET FOREIGN_KEY_CHECKS = 1;
