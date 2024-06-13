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

 Date: 07/06/2024 10:10:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for submission_info
-- ----------------------------
DROP TABLE IF EXISTS `submission_info`;
CREATE TABLE `submission_info`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `task_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `related_air_data_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `submitted_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of submission_info
-- ----------------------------
INSERT INTO `submission_info` VALUES ('1115676903893626886', '1115676903893626880', 'test descrption', '1115664407392677898', NULL, '2024-03-16 11:24:28');
INSERT INTO `submission_info` VALUES ('1115676903893626887', '1115676903893626881', 'test descrption', '1115664407396872193', NULL, '2024-03-17 10:11:35');
INSERT INTO `submission_info` VALUES ('1115676903893626888', '1115676903893626882', 'test descrption', '1115664407392677906', NULL, '2024-04-28 12:03:59');

SET FOREIGN_KEY_CHECKS = 1;
