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

 Date: 17/06/2024 09:33:03
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
  `forecast_aqi_level` int NOT NULL,
  `image_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `city_id` int NOT NULL,
  `location` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report_info
-- ----------------------------
INSERT INTO `report_info` VALUES ('1115675276176519168', '1115615978398412800', 1, '2024-03-14 14:28:06', 'test description', 1, '', 1, 'test location');
INSERT INTO `report_info` VALUES ('1115675276176519169', '1115615900787011584', 1, '2024-03-15 13:40:43', 'test description', 2, '', 2, 'test location');
INSERT INTO `report_info` VALUES ('1115675276176519170', '1115615900787011584', 1, '2024-04-25 14:04:02', 'test description', 3, '', 3, 'test location');
INSERT INTO `report_info` VALUES ('1115675276176519171', '1115615827189559296', 1, '2024-03-08 15:16:11', 'test description', 4, '', 4, 'test location');
INSERT INTO `report_info` VALUES ('1115675276176519172', '1115615827189559296', 1, '2024-03-16 14:08:45', 'test description', 5, '', 5, 'test location');
INSERT INTO `report_info` VALUES ('1115675276176519173', '1115615900787011584', 0, '2024-04-25 13:49:58', 'test description', 6, '', 6, 'test location');
INSERT INTO `report_info` VALUES ('1115675276176519174', '1115615827189559296\r\n', 0, '2024-04-20 14:24:59', 'test description', 1, '', 7, 'test location');
INSERT INTO `report_info` VALUES ('1115675276176519175', '1115615978398412800', 0, '2024-04-07 13:53:43', 'test description', 2, '', 8, 'test location');
INSERT INTO `report_info` VALUES ('1115675276176519176', '1115615978398412800', 0, '2024-04-23 15:54:17', 'test description', 3, '', 1, 'test location');
INSERT INTO `report_info` VALUES ('1116980678797291521', '1115615827189559296', 0, '2024-03-14 13:14:45', 'test Dscription', 1, '', 2, 'test location');

SET FOREIGN_KEY_CHECKS = 1;
