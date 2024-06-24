/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : neusoftpractice_public

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 23/06/2024 15:24:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for supervisor_info
-- ----------------------------
DROP TABLE IF EXISTS `supervisor_info`;
CREATE TABLE `supervisor_info`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `birth_year` int NOT NULL,
  `sex` int NOT NULL,
  `city_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supervisor_info
-- ----------------------------
INSERT INTO `supervisor_info` VALUES ('1115615827189559296', '13800138000', 'super1', 2001, 1, 1);
INSERT INTO `supervisor_info` VALUES ('1115615864552419328', '13912345678', 'super2', 2005, 0, 2);
INSERT INTO `supervisor_info` VALUES ('1115615900787011584', '13023456789', 'super3', 1999, 1, 3);
INSERT INTO `supervisor_info` VALUES ('1115615936350515200', '15988888888', 'super4', 1987, 0, 4);
INSERT INTO `supervisor_info` VALUES ('1115615978398412800', '18655555555', 'super5', 2001, 1, 5);
INSERT INTO `supervisor_info` VALUES ('1115616037923975168', '18901234567', 'John', 1993, 1, 2);
INSERT INTO `supervisor_info` VALUES ('1115616085944561664', '15099999999', 'super7', 1995, 1, 7);
INSERT INTO `supervisor_info` VALUES ('1115616117519282176', '14512345678', 'super8', 1993, 0, 8);
INSERT INTO `supervisor_info` VALUES ('1115616172422721536', '17088888888', 'super9', 1993, 1, 2);
INSERT INTO `supervisor_info` VALUES ('1118919022288457728', '13866810017', 'John', 1993, 1, 2);
INSERT INTO `supervisor_info` VALUES ('1118919141389914112', '13866810017', 'John', 1993, 1, 2);
INSERT INTO `supervisor_info` VALUES ('1118920660671680512', '13866810027', 'John', 1993, 1, 2);

SET FOREIGN_KEY_CHECKS = 1;
