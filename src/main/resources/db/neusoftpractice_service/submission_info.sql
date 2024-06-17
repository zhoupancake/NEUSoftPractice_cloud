/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : neusoftpractice_service

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 17/06/2024 09:37:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for submission_info
-- ----------------------------
DROP TABLE IF EXISTS `submission_info`;
CREATE TABLE `submission_info`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `task_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `related_air_data_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `submitter_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `submitted_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of submission_info
-- ----------------------------
INSERT INTO `submission_info` VALUES ('1115676903893626886', '1115676903893626880', 'test descrption', '1115664407392677898', '1115613655483805696', NULL, '2024-03-16 11:24:28');
INSERT INTO `submission_info` VALUES ('1115676903893626887', '1115676903893626881', 'test descrption', '1115664407396872193', '1115614307672911872', NULL, '2024-03-17 10:11:35');
INSERT INTO `submission_info` VALUES ('1115676903893626888', '1115676903893626882', 'test descrption', '1115664407392677906', '1115614414988374016', NULL, '2024-04-28 12:03:59');
INSERT INTO `submission_info` VALUES ('1802367277614514177', '1115676903893626884', 'test description', '1119654436930580480', '1116545704042319872', NULL, '2024-06-16 23:47:00');

SET FOREIGN_KEY_CHECKS = 1;
