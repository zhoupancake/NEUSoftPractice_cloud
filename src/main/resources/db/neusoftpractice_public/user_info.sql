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

 Date: 07/06/2024 10:09:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` int NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1115604128331984896', '2015328754', 'admin1', 1, 'Administrator');
INSERT INTO `user_info` VALUES ('1115604511875919872', '2013567890', 'admin2', 1, 'Administrator');
INSERT INTO `user_info` VALUES ('1115604605748637696', '2016456789', 'admin3', 0, 'Administrator');
INSERT INTO `user_info` VALUES ('1115604677030834176', '2019654321', 'admin4', 1, 'Administrator');
INSERT INTO `user_info` VALUES ('1115604743795765248', '2021123456', 'admin5', 1, 'Administrator');
INSERT INTO `user_info` VALUES ('1115604809029775360', 'idCard', 'admin6', 0, 'Administrator');
INSERT INTO `user_info` VALUES ('1115604864553971712', '2016543210', 'admin7', 1, 'Administrator');
INSERT INTO `user_info` VALUES ('1115604929158836224', 'idCard', 'admin8', 0, 'Administrator');
INSERT INTO `user_info` VALUES ('1115604994338320384', '2015876543', 'admin9', 0, 'Administrator');
INSERT INTO `user_info` VALUES ('1115605045110370304', '2021098765', 'admin10', 1, 'Administrator');
INSERT INTO `user_info` VALUES ('1115613612534132736', '2015876543', 'grid1', 1, 'GridDetector');
INSERT INTO `user_info` VALUES ('1115613655483805696', '2021345678', 'grid2', 1, 'GridDetector');
INSERT INTO `user_info` VALUES ('1115614265843118080', '2018901234 ', 'grid3', 0, 'GridDetector');
INSERT INTO `user_info` VALUES ('1115614307672911872', '2012567890', 'grid4', 1, 'GridDetector');
INSERT INTO `user_info` VALUES ('1115614345065132032', '2019432109', 'grid5', 1, 'GridDetector');
INSERT INTO `user_info` VALUES ('1115614414988374016', '2013678901', 'grid6', 0, 'GridDetector');
INSERT INTO `user_info` VALUES ('1115614457866743808', '2020123456', 'grid7', 1, 'GridDetector');
INSERT INTO `user_info` VALUES ('1115614529362849792', '2017890123', 'grid8', 1, 'GridDetector');
INSERT INTO `user_info` VALUES ('1115614568881582080', '2014567890', 'grid9', 0, 'GridDetector');
INSERT INTO `user_info` VALUES ('1115614627866079232', '2022098765', 'grid10', 1, 'GridDetector');
INSERT INTO `user_info` VALUES ('1115615827189559296', '13800138000', 'super1', 1, 'Supervisor');
INSERT INTO `user_info` VALUES ('1115615864552419328', '13912345678', 'super2', 1, 'Supervisor');
INSERT INTO `user_info` VALUES ('1115615900787011584', '13023456789', 'super3', 0, 'Supervisor');
INSERT INTO `user_info` VALUES ('1115615936350515200', '15988888888', 'super4', 1, 'Supervisor');
INSERT INTO `user_info` VALUES ('1115615978398412800', '18655555555', 'super5', 1, 'Supervisor');
INSERT INTO `user_info` VALUES ('1115616037923975168', '18901234567', 'super6', 0, 'Supervisor');
INSERT INTO `user_info` VALUES ('1115616085944561664', '15099999999', 'super7', 1, 'Supervisor');
INSERT INTO `user_info` VALUES ('1115616117519282176', '14512345678', 'super8', 1, 'Supervisor');
INSERT INTO `user_info` VALUES ('1115616172422721536', '17088888888', 'super9', 0, 'Supervisor');
INSERT INTO `user_info` VALUES ('1115616213161996288', '17676543210', 'super10', 1, 'Supervisor');

SET FOREIGN_KEY_CHECKS = 1;
