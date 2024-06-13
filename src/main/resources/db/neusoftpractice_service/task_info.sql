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

 Date: 07/06/2024 10:10:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for task_info
-- ----------------------------
DROP TABLE IF EXISTS `task_info`;
CREATE TABLE `task_info`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `appointer_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `appointee_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `created_time` datetime NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `relative_report_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task_info
-- ----------------------------
INSERT INTO `task_info` VALUES ('1115676903893626880', '1115604128331984896', '1115615978398412800', '2024-03-15 11:28:06', '1', '1115675276176519168');
INSERT INTO `task_info` VALUES ('1115676903893626881', '1115604511875919872', '1115614265843118080', '2024-03-15 15:40:43', '1', '1115675276176519169');
INSERT INTO `task_info` VALUES ('1115676903893626882', '1115604605748637696', '1115614265843118080', '2024-04-27 14:00:02', '1', '1115675276176519170');
INSERT INTO `task_info` VALUES ('1115676903893626883', '1115604677030834176', '1115613612534132736', '2024-03-09 15:20:11', '0', '1115675276176519171');
INSERT INTO `task_info` VALUES ('1115676903893626884', '1115604743795765248', '1115613612534132736', '2024-03-17 14:08:45', '0', '1115675276176519172');

SET FOREIGN_KEY_CHECKS = 1;
