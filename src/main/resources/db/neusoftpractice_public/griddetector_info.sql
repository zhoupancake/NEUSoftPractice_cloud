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

 Date: 07/06/2024 10:08:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for griddetector_info
-- ----------------------------
DROP TABLE IF EXISTS `griddetector_info`;
CREATE TABLE `griddetector_info`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `city_id` int NOT NULL,
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of griddetector_info
-- ----------------------------
INSERT INTO `griddetector_info` VALUES ('1115613612534132736', 1, '2015876543', 'grid_test1');
INSERT INTO `griddetector_info` VALUES ('1115613655483805696', 2, '2021345678', 'grid_test2');
INSERT INTO `griddetector_info` VALUES ('1115614265843118080', 3, '2018901234 ', 'grid_test3');
INSERT INTO `griddetector_info` VALUES ('1115614307672911872', 4, '2012567890', 'grid_test4');
INSERT INTO `griddetector_info` VALUES ('1115614345065132032', 5, '2019432109', 'grid_test5');
INSERT INTO `griddetector_info` VALUES ('1115614414988374016', 6, '2013678901', 'grid_test6');
INSERT INTO `griddetector_info` VALUES ('1115614457866743808', 8, '2020123456', 'grid_test7');
INSERT INTO `griddetector_info` VALUES ('1115614529362849792', 9, '2017890123', 'grid_test8');
INSERT INTO `griddetector_info` VALUES ('1115614568881582080', 10, '2014567890', 'grid_test9');
INSERT INTO `griddetector_info` VALUES ('1115614627866079232', 7, '2022098765', 'grid_test10');

SET FOREIGN_KEY_CHECKS = 1;
