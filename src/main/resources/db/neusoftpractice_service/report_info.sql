/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : neusoftpractice_service

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 07/06/2024 13:56:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for report_info
-- ----------------------------
DROP TABLE IF EXISTS `report_info`;
CREATE TABLE `report_info`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `submitter_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` int NOT NULL,
  `created_time` datetime NOT NULL,
  `description` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `relative_air_data_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report_info
-- ----------------------------
INSERT INTO `report_info` VALUES ('1115675276176519168', '1115615978398412800', 1, '2024-03-14 14:28:06', 'test description', '1115664407388483640');
INSERT INTO `report_info` VALUES ('1115675276176519169', '1115615900787011584', 1, '2024-03-15 13:40:43', 'test description', '1115664407388483642');
INSERT INTO `report_info` VALUES ('1115675276176519170', '1115615900787011584', 1, '2024-04-25 14:04:02', 'test description', '1115664407388483607');
INSERT INTO `report_info` VALUES ('1115675276176519171', '1115615827189559296', 1, '2024-03-08 15:16:11', 'test description', '1115664407392677922');
INSERT INTO `report_info` VALUES ('1115675276176519172', '1115615827189559296', 1, '2024-03-16 14:08:45', 'test description', '1115664407388483659');
INSERT INTO `report_info` VALUES ('1115675276176519173', '1115615900787011584', 0, '2024-04-25 13:49:58', 'test description', '1115664407388483608');
INSERT INTO `report_info` VALUES ('1115675276176519174', '1115615827189559296\r\n', 0, '2024-04-20 14:24:59', 'test description', '1115664407392677923');
INSERT INTO `report_info` VALUES ('1115675276176519175', '1115615978398412800', 0, '2024-04-07 13:53:43', 'test description', '1115664407388483639');
INSERT INTO `report_info` VALUES ('1115675276176519176', '1115615978398412800', 0, '2024-04-23 15:54:17', 'test description', '1115664407388483605');
INSERT INTO `report_info` VALUES ('1115675276176519177', '1115615827189559296', 0, '2024-04-26 12:29:39', 'test description', '1115664407392677920');

SET FOREIGN_KEY_CHECKS = 1;
