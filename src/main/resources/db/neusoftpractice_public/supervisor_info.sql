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

 Date: 08/06/2024 11:03:10
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
  `age` int NOT NULL,
  `sex` int NOT NULL,
  `city_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supervisor_info
-- ----------------------------
INSERT INTO `supervisor_info` VALUES ('1115615827189559296', '13800138000', 'super1', 23, 1, 1);
INSERT INTO `supervisor_info` VALUES ('1115615864552419328', '13912345678', 'super2', 19, 0, 2);
INSERT INTO `supervisor_info` VALUES ('1115615900787011584', '13023456789', 'super3', 25, 1, 3);
INSERT INTO `supervisor_info` VALUES ('1115615936350515200', '15988888888', 'super4', 37, 0, 4);
INSERT INTO `supervisor_info` VALUES ('1115615978398412800', '18655555555', 'super5', 23, 1, 5);
INSERT INTO `supervisor_info` VALUES ('1115616037923975168', '18901234567', 'super6', 25, 0, 6);
INSERT INTO `supervisor_info` VALUES ('1115616085944561664', '15099999999', 'super7', 29, 1, 7);
INSERT INTO `supervisor_info` VALUES ('1115616117519282176', '14512345678', 'super8', 31, 0, 8);
INSERT INTO `supervisor_info` VALUES ('1115616172422721536', '17088888888', 'super9', 51, 1, 9);
INSERT INTO `supervisor_info` VALUES ('1115616213161996288', '17676543210', 'super10', 43, 0, 10);

SET FOREIGN_KEY_CHECKS = 1;
