/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80035
 Source Host           : localhost:3306
 Source Schema         : neusoftpractice_data

 Target Server Type    : MySQL
 Target Server Version : 80035
 File Encoding         : 65001

 Date: 09/06/2024 12:05:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for airdata_info
-- ----------------------------
DROP TABLE IF EXISTS `airdata_info`;
CREATE TABLE `airdata_info`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `city_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `date` datetime NOT NULL,
  `pm25` double NOT NULL,
  `pm10` double NULL DEFAULT NULL,
  `so2` double NOT NULL,
  `no2` double NULL DEFAULT NULL,
  `co` double NOT NULL,
  `o3` double NULL DEFAULT NULL,
  `aqi` int NOT NULL,
  `aqi_level` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of airdata_info
-- ----------------------------
INSERT INTO `airdata_info` VALUES ('1115664407388483584', '2', 'No. 23, Jianguo Road, Chaoyang District', '2024-03-22 13:33:07', 28, 61, 5, 49, 0.73, 10, 55, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483585', '2', 'No. 44, Jingshan Xijie, Xicheng District', '2024-04-23 11:29:52', 51, 79, 5, 47, 1.19, 13, 72, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483586', '2', 'No. 19, Xinjiangongmen Road, Haidian District', '2024-04-25 10:32:57', 13, 30, 3, 30, 0.42, 29, 30, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483587', '2', 'Jia 1, Tiantan Dongli, Dongcheng District', '2024-03-28 15:46:56', 15, 37, 3, 23, 0.42, 39, 37, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483588', '2', 'No. 28, Qinghua West Road, Haidian District', '2024-04-15 15:35:09', 10, 27, 2, 18, 0.3, 40, 28, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483589', '2', 'No. 4, Jingshan Qianjie, Dongcheng District', '2024-04-26 13:31:14', 11, 75, 2, 7, 0.25, 51, 61, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483590', '1', 'The Bund, Huangpu District', '2024-03-05 12:58:43', 8, 26, 2, 9, 0.3, 49, 27, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483591', '1', 'No. 1 Century Avenue, Pudong New Area', '2024-04-02 10:55:26', 8, 28, 2, 12, 0.24, 52, 28, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483592', '1', 'No. 218 Anren Street, Huangpu District', '2024-04-26 14:13:26', 9, 35, 2, 13, 0.26, 50, 34, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483593', '1', 'No. 201 Renmin Road, Huangpu District', '2024-03-08 13:08:12', 8, 23, 2, 13, 0.26, 55, 25, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483594', '1', 'Nanjing Road East, Huangpu District', '2024-04-12 15:11:49', 18, 57, 3, 19, 0.38, 38, 49, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483595', '14', 'No. 66 Shenhe North', '2024-04-27 14:06:32', 43, 143, 4, 47, 0.73, 16, 97, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483596', '14', 'No. 130 Beiling West Road', '2024-03-27 15:22:28', 57, 234, 2, 30, 0.54, 39, 142, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483597', '14', 'No. 55 Shengjing Street, Shenbei New District', '2024-03-08 14:39:34', 53, 115, 5, 34, 0.95, 24, 82, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483598', '14', 'No. 46 Shaoshuai Fu Lane, Chaoyang Street, Shenhe District', '2024-04-05 15:56:35', 42, 146, 2, 17, 0.49, 49, 99, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483599', '14', 'No. 301 Shuangyuan Road, Hunnan District', '2024-03-26 13:39:33', 9, 30, 2, 11, 0.27, 55, 30, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483600', '14', 'No. 120 Huanghe North Street', '2024-04-22 14:41:56', 16, 54, 3, 22, 0.38, 42, 50, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483601', '5', 'No. 15 Chenjiaci Road, Yuexiu District', '2024-03-08 11:06:37', 18, 61, 3, 25, 0.42, 40, 55, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483602', '5', 'No. 223 Yigang Road, Haizhu District', '2024-03-19 14:42:02', 26, 46, 2, 24, 0.61, 33, 46, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483603', '5', 'No. 2 Yingyuan Road, Yuexiu District', '2024-03-21 12:02:22', 51, 62, 3, 37, 0.8, 20, 71, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483604', '5', 'No. 2 Zhongshan Road 2, Yuexiu District', '2024-03-21 11:44:03', 77, 93, 5, 51, 1.17, 16, 103, 3);
INSERT INTO `airdata_info` VALUES ('1115664407388483605', '5', 'Shamian Island, Liwan District', '2024-04-23 15:54:17', 84, 109, 8, 54, 1.45, 22, 112, 3);
INSERT INTO `airdata_info` VALUES ('1115664407388483606', '5', 'Baiyun Mountain Scenic Area, Baiyun District', '2024-03-24 12:06:39', 86, 93, 3, 50, 1.34, 20, 115, 3);
INSERT INTO `airdata_info` VALUES ('1115664407388483607', '3', 'Dapeng Town, Longgang District', '2024-04-25 14:04:02', 104, 103, 3, 52, 1.3, 20, 137, 3);
INSERT INTO `airdata_info` VALUES ('1115664407388483608', '3', 'North End of Shenzhen Central Area', '2024-04-25 13:49:58', 132, 106, 2, 59, 1.5, 10, 175, 4);
INSERT INTO `airdata_info` VALUES ('1115664407388483609', '3', 'OCT Scenic Area, Western Shenzhen Downtown', '2024-03-05 15:24:46', 82, 78, 2, 40, 1.08, 30, 110, 3);
INSERT INTO `airdata_info` VALUES ('1115664407388483610', '3', 'No. 33 Yantian Road, Yantian District', '2024-03-21 13:40:07', 27, 60, 5, 33, 0.74, 35, 54, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483611', '3', 'No. 160 Liantang Xianhu Road, Luohu District', '2024-04-27 14:32:17', 6, 48, 2, 7, 0.24, 64, 44, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483612', '4', 'No. 75 Jiefangbei, Yuzhong District', '2024-03-08 14:34:21', 19, 50, 3, 31, 0.53, 27, 47, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483613', '4', 'Intersection of Minzu Road and Zhongshan Road', '2024-04-20 11:13:48', 40, 86, 4, 47, 0.88, 19, 68, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483614', '4', 'Ciqikou Town, Shapingba District', '2024-04-17 14:32:46', 20, 52, 2, 12, 0.38, 55, 48, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483615', '4', 'Tiansheng Three Bridges Scenic Area', '2024-04-27 15:03:42', 13, 36, 2, 17, 0.42, 47, 36, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483616', '4', 'Nanshan Yikeshu Viewing Platform, Nanshan District', '2024-04-07 14:45:48', 17, 50, 3, 23, 0.47, 40, 47, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483617', '4', 'Dazu Rock Carvings Scenic Area, Dazu District', '2024-04-04 11:47:25', 33, 60, 4, 34, 0.62, 37, 54, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483618', '2', 'No. 23, Jianguo Road, Chaoyang District', '2024-03-22 14:02:16', 41, 73, 4, 44, 0.83, 32, 65, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483619', '2', 'No. 44, Jingshan Xijie, Xicheng District', '2024-03-07 12:18:34', 33, 112, 3, 26, 0.53, 52, 81, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483620', '2', 'No. 19, Xinjiangongmen Road, Haidian District', '2024-03-07 12:30:12', 24, 57, 3, 12, 0.44, 58, 46, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483621', '2', 'Jia 1, Tiantan Dongli, Dongcheng District', '2024-03-30 15:53:55', 29, 41, 3, 28, 0.62, 41, 43, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483622', '2', 'No. 28, Qinghua West Road, Haidian District', '2024-04-04 12:20:08', 62, 71, 5, 39, 0.92, 42, 84, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483623', '2', 'No. 4, Jingshan Qianjie, Dongcheng District', '2024-03-30 11:06:30', 74, 81, 6, 39, 1.04, 53, 99, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483624', '1', 'The Bund, Huangpu District', '2024-04-23 14:54:00', 159, 149, 8, 41, 1.7, 62, 204, 5);
INSERT INTO `airdata_info` VALUES ('1115664407388483625', '1', 'No. 1 Century Avenue, Pudong New Area', '2024-04-26 10:32:17', 201, 205, 8, 31, 1.62, 76, 249, 5);
INSERT INTO `airdata_info` VALUES ('1115664407388483626', '1', 'No. 218 Anren Street, Huangpu District', '2024-04-26 13:37:21', 205, 192, 5, 38, 2.28, 27, 255, 5);
INSERT INTO `airdata_info` VALUES ('1115664407388483627', '1', 'No. 201 Renmin Road, Huangpu District', '2024-03-10 10:02:28', 109, 88, 2, 22, 1.5, 28, 141, 3);
INSERT INTO `airdata_info` VALUES ('1115664407388483628', '1', 'Nanjing Road East, Huangpu District', '2024-04-08 12:59:09', 8, 24, 2, 7, 0.27, 68, 33, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483629', '14', 'No. 66 Shenhe North', '2024-03-27 12:59:48', 5, 31, 2, 4, 0.21, 72, 32, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483630', '14', 'No. 130 Beiling West Road', '2024-04-14 10:44:44', 8, 15, 2, 7, 0.27, 64, 22, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483631', '14', 'No. 55 Shengjing Street, Shenbei New District', '2024-04-10 14:22:26', 24, 33, 4, 24, 0.48, 45, 35, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483632', '14', 'No. 46 Shaoshuai Fu Lane, Chaoyang Street, Shenhe District', '2024-03-19 11:39:45', 44, 65, 4, 42, 0.79, 36, 64, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483633', '14', 'No. 301 Shuangyuan Road, Hunnan District', '2024-04-18 11:31:39', 44, 90, 4, 48, 0.81, 33, 70, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483634', '14', 'No. 120 Huanghe North Street', '2024-04-13 11:15:06', 55, 186, 3, 34, 0.66, 41, 119, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483635', '5', 'No. 15 Chenjiaci Road, Yuexiu District', '2024-03-27 10:16:05', 28, 69, 3, 13, 0.38, 66, 55, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483636', '5', 'No. 223 Yigang Road, Haizhu District', '2024-03-17 15:11:51', 27, 49, 2, 19, 0.54, 49, 47, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483637', '5', 'No. 2 Yingyuan Road, Yuexiu District', '2024-04-19 15:33:09', 42, 51, 3, 26, 0.67, 38, 60, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483638', '5', 'No. 2 Zhongshan Road 2, Yuexiu District', '2024-03-30 15:18:09', 77, 75, 3, 34, 0.95, 41, 103, 3);
INSERT INTO `airdata_info` VALUES ('1115664407388483639', '5', 'Shamian Island, Liwan District', '2024-04-07 13:53:43', 129, 107, 4, 39, 1.3, 54, 170, 4);
INSERT INTO `airdata_info` VALUES ('1115664407388483640', '5', 'Baiyun Mountain Scenic Area, Baiyun District', '2024-03-14 14:28:06', 122, 100, 6, 38, 1.06, 66, 162, 4);
INSERT INTO `airdata_info` VALUES ('1115664407388483641', '3', 'Dapeng Town, Longgang District', '2024-04-14 14:18:24', 97, 71, 3, 38, 0.95, 44, 129, 3);
INSERT INTO `airdata_info` VALUES ('1115664407388483642', '3', 'North End of Shenzhen Central Area', '2024-03-15 13:40:43', 66, 101, 2, 53, 0.77, 15, 91, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483643', '3', 'OCT Scenic Area, Western Shenzhen Downtown', '2024-03-27 14:00:01', 70, 99, 2, 53, 0.9, 14, 95, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483644', '3', 'No. 33 Yantian Road, Yantian District', '2024-03-21 14:52:54', 16, 33, 2, 32, 0.38, 38, 31, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483645', '3', 'No. 160 Liantang Xianhu Road, Luohu District', '2024-03-23 11:06:45', 45, 71, 2, 44, 0.66, 28, 65, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483646', '4', 'No. 75 Jiefangbei, Yuzhong District', '2024-04-29 12:54:57', 19, 44, 1, 32, 0.42, 36, 41, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483647', '4', 'Intersection of Minzu Road and Zhongshan Road', '2024-03-12 14:23:33', 6, 14, 2, 11, 0.23, 47, 18, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483648', '4', 'Ciqikou Town, Shapingba District', '2024-03-25 13:23:01', 12, 25, 2, 23, 0.32, 33, 25, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483649', '4', 'Tiansheng Three Bridges Scenic Area', '2024-04-14 13:06:46', 40, 68, 2, 51, 0.64, 11, 61, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483650', '4', 'Nanshan Yikeshu Viewing Platform, Nanshan District', '2024-03-13 11:22:18', 30, 49, 2, 35, 0.5, 29, 46, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483651', '4', 'Dazu Rock Carvings Scenic Area, Dazu District', '2024-03-26 15:04:27', 29, 48, 2, 42, 0.53, 26, 45, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483652', '2', 'No. 23, Jianguo Road, Chaoyang District', '2024-05-01 11:19:45', 60, 98, 2, 61, 0.93, 14, 81, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483653', '2', 'No. 44, Jingshan Xijie, Xicheng District', '2024-03-28 14:50:18', 75, 120, 3, 65, 1.23, 12, 101, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483654', '2', 'No. 19, Xinjiangongmen Road, Haidian District', '2024-04-09 10:10:27', 52, 82, 2, 47, 1.01, 17, 74, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483655', '2', 'Jia 1, Tiantan Dongli, Dongcheng District', '2024-04-28 14:11:29', 7, 20, 2, 14, 0.26, 57, 24, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483656', '2', 'No. 28, Qinghua West Road, Haidian District', '2024-03-20 15:30:14', 17, 39, 2, 38, 0.46, 28, 36, 1);
INSERT INTO `airdata_info` VALUES ('1115664407388483657', '2', 'No. 4, Jingshan Qianjie, Dongcheng District', '2024-04-03 14:57:01', 51, 86, 2, 55, 0.73, 11, 76, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483658', '1', 'The Bund, Huangpu District', '2024-03-09 15:32:51', 88, 131, 2, 64, 0.93, 6, 117, 3);
INSERT INTO `airdata_info` VALUES ('1115664407388483659', '1', 'No. 1 Century Avenue, Pudong New Area', '2024-03-16 14:08:45', 75, 103, 3, 53, 1, 13, 101, 2);
INSERT INTO `airdata_info` VALUES ('1115664407388483660', '1', 'No. 218 Anren Street, Huangpu District', '2024-03-20 12:56:43', 44, 73, 3, 54, 0.89, 12, 65, 2);
INSERT INTO `airdata_info` VALUES ('1115664407392677888', '1', 'No. 201 Renmin Road, Huangpu District', '2024-04-16 13:18:59', 32, 60, 3, 40, 0.72, 26, 53, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677889', '1', 'Nanjing Road East, Huangpu District', '2024-04-04 13:37:40', 5, 16, 2, 6, 0.2, 63, 20, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677890', '14', 'No. 66 Shenhe North', '2024-04-12 15:15:14', 6, 13, 2, 8, 0.22, 61, 19, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677891', '14', 'No. 130 Beiling West Road', '2024-04-19 11:51:48', 6, 16, 2, 10, 0.22, 59, 19, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677892', '14', 'No. 55 Shengjing Street, Shenbei New District', '2024-03-27 10:01:21', 11, 27, 2, 21, 0.28, 51, 27, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677893', '14', 'No. 46 Shaoshuai Fu Lane, Chaoyang Street, Shenhe District', '2024-03-30 15:09:31', 13, 26, 2, 21, 0.31, 52, 27, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677894', '14', 'No. 301 Shuangyuan Road, Hunnan District', '2024-03-15 10:51:01', 16, 38, 3, 19, 0.33, 55, 38, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677895', '14', 'No. 120 Huanghe North Street', '2024-03-07 11:18:05', 17, 37, 2, 24, 0.35, 50, 36, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677896', '5', 'No. 15 Chenjiaci Road, Yuexiu District', '2024-03-25 10:10:19', 28, 51, 3, 41, 0.51, 33, 48, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677897', '5', 'No. 223 Yigang Road, Haizhu District', '2024-04-18 14:26:59', 61, 97, 5, 49, 0.96, 22, 86, 2);
INSERT INTO `airdata_info` VALUES ('1115664407392677898', '5', 'No. 2 Yingyuan Road, Yuexiu District', '2024-03-16 11:24:28', 92, 124, 2, 40, 1.12, 23, 122, 3);
INSERT INTO `airdata_info` VALUES ('1115664407392677899', '5', 'No. 2 Zhongshan Road 2, Yuexiu District', '2024-04-27 10:15:25', 28, 61, 5, 49, 0.73, 10, 55, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677900', '5', 'Shamian Island, Liwan District', '2024-03-06 11:58:22', 51, 79, 5, 47, 1.19, 13, 72, 2);
INSERT INTO `airdata_info` VALUES ('1115664407392677901', '5', 'Baiyun Mountain Scenic Area, Baiyun District', '2024-04-15 11:21:42', 13, 30, 3, 30, 0.42, 29, 30, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677902', '3', 'Dapeng Town, Longgang District', '2024-03-19 14:55:33', 15, 37, 3, 23, 0.42, 39, 37, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677903', '3', 'North End of Shenzhen Central Area', '2024-04-07 12:00:07', 10, 27, 2, 18, 0.3, 40, 28, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677904', '3', 'OCT Scenic Area, Western Shenzhen Downtown', '2024-04-19 14:10:20', 11, 75, 2, 7, 0.25, 51, 61, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677905', '3', 'No. 33 Yantian Road, Yantian District', '2024-04-16 13:30:49', 8, 26, 2, 9, 0.3, 49, 27, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677906', '3', 'No. 160 Liantang Xianhu Road, Luohu District', '2024-04-08 12:03:59', 8, 28, 2, 12, 0.24, 52, 28, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677907', '4', 'No. 75 Jiefangbei, Yuzhong District', '2024-03-04 11:31:24', 9, 35, 2, 13, 0.26, 50, 34, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677908', '4', 'Intersection of Minzu Road and Zhongshan Road', '2024-04-07 13:27:11', 8, 23, 2, 13, 0.26, 55, 25, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677909', '4', 'Ciqikou Town, Shapingba District', '2024-04-17 14:58:43', 18, 57, 3, 19, 0.38, 38, 49, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677910', '4', 'Tiansheng Three Bridges Scenic Area', '2024-04-15 13:27:41', 43, 143, 4, 47, 0.73, 16, 97, 2);
INSERT INTO `airdata_info` VALUES ('1115664407392677911', '4', 'Nanshan Yikeshu Viewing Platform, Nanshan District', '2024-04-21 10:46:49', 57, 234, 2, 30, 0.54, 39, 142, 2);
INSERT INTO `airdata_info` VALUES ('1115664407392677912', '4', 'Dazu Rock Carvings Scenic Area, Dazu District', '2024-04-12 14:00:46', 53, 115, 5, 34, 0.95, 24, 82, 2);
INSERT INTO `airdata_info` VALUES ('1115664407392677913', '2', 'No. 23, Jianguo Road, Chaoyang District', '2024-04-12 14:31:31', 42, 146, 2, 17, 0.49, 49, 99, 2);
INSERT INTO `airdata_info` VALUES ('1115664407392677914', '2', 'No. 44, Jingshan Xijie, Xicheng District', '2024-03-20 12:17:28', 9, 30, 2, 11, 0.27, 55, 30, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677915', '2', 'No. 19, Xinjiangongmen Road, Haidian District', '2024-04-30 13:27:47', 16, 54, 3, 22, 0.38, 42, 50, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677916', '2', 'Jia 1, Tiantan Dongli, Dongcheng District', '2024-04-14 14:48:46', 18, 61, 3, 25, 0.42, 40, 55, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677917', '2', 'No. 28, Qinghua West Road, Haidian District', '2024-04-16 10:13:44', 26, 46, 2, 24, 0.61, 33, 46, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677918', '2', 'No. 4, Jingshan Qianjie, Dongcheng District', '2024-04-16 10:37:01', 51, 62, 3, 37, 0.8, 20, 71, 2);
INSERT INTO `airdata_info` VALUES ('1115664407392677919', '1', 'The Bund, Huangpu District', '2024-03-11 12:40:59', 77, 93, 5, 51, 1.17, 16, 103, 3);
INSERT INTO `airdata_info` VALUES ('1115664407392677920', '1', 'No. 1 Century Avenue, Pudong New Area', '2024-04-26 12:29:39', 84, 109, 8, 54, 1.45, 22, 112, 3);
INSERT INTO `airdata_info` VALUES ('1115664407392677921', '1', 'No. 218 Anren Street, Huangpu District', '2024-03-12 11:18:34', 86, 93, 3, 50, 1.34, 20, 115, 3);
INSERT INTO `airdata_info` VALUES ('1115664407392677922', '1', 'No. 201 Renmin Road, Huangpu District', '2024-03-08 15:16:11', 104, 103, 3, 52, 1.3, 20, 137, 3);
INSERT INTO `airdata_info` VALUES ('1115664407392677923', '1', 'Nanjing Road East, Huangpu District', '2024-04-20 14:24:59', 132, 106, 2, 59, 1.5, 10, 175, 4);
INSERT INTO `airdata_info` VALUES ('1115664407392677924', '14', 'No. 66 Shenhe North', '2024-04-12 11:19:21', 82, 78, 2, 40, 1.08, 30, 110, 3);
INSERT INTO `airdata_info` VALUES ('1115664407392677925', '14', 'No. 130 Beiling West Road', '2024-03-09 10:39:01', 27, 60, 5, 33, 0.74, 35, 54, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677926', '14', 'No. 55 Shengjing Street, Shenbei New District', '2024-05-01 11:34:30', 6, 48, 2, 7, 0.24, 64, 44, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677927', '14', 'No. 46 Shaoshuai Fu Lane, Chaoyang Street, Shenhe District', '2024-03-14 14:00:04', 19, 50, 3, 31, 0.53, 27, 47, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677928', '14', 'No. 301 Shuangyuan Road, Hunnan District', '2024-04-30 14:59:25', 40, 86, 4, 47, 0.88, 19, 68, 2);
INSERT INTO `airdata_info` VALUES ('1115664407392677929', '14', 'No. 120 Huanghe North Street', '2024-04-24 11:08:38', 20, 52, 2, 12, 0.38, 55, 48, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677930', '5', 'No. 15 Chenjiaci Road, Yuexiu District', '2024-03-10 11:04:17', 13, 36, 2, 17, 0.42, 47, 36, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677931', '5', 'No. 223 Yigang Road, Haizhu District', '2024-04-27 11:56:25', 17, 50, 3, 23, 0.47, 40, 47, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677932', '5', 'No. 2 Yingyuan Road, Yuexiu District', '2024-03-07 15:27:13', 33, 60, 4, 34, 0.62, 37, 54, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677933', '5', 'No. 2 Zhongshan Road 2, Yuexiu District', '2024-04-21 14:12:47', 41, 73, 4, 44, 0.83, 32, 65, 2);
INSERT INTO `airdata_info` VALUES ('1115664407392677934', '5', 'Shamian Island, Liwan District', '2024-03-15 10:00:51', 33, 112, 3, 26, 0.53, 52, 81, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677935', '5', 'Baiyun Mountain Scenic Area, Baiyun District', '2024-04-05 11:36:15', 24, 57, 3, 12, 0.44, 58, 46, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677936', '3', 'Dapeng Town, Longgang District', '2024-03-14 11:19:49', 29, 41, 3, 28, 0.62, 41, 43, 1);
INSERT INTO `airdata_info` VALUES ('1115664407392677937', '3', 'North End of Shenzhen Central Area', '2024-03-19 12:17:49', 62, 71, 5, 39, 0.92, 42, 84, 2);
INSERT INTO `airdata_info` VALUES ('1115664407396872192', '3', 'OCT Scenic Area, Western Shenzhen Downtown', '2024-04-04 11:32:51', 74, 81, 6, 39, 1.04, 53, 99, 2);
INSERT INTO `airdata_info` VALUES ('1115664407396872193', '3', 'No. 33 Yantian Road, Yantian District', '2024-03-14 10:11:35', 159, 149, 8, 41, 1.7, 62, 204, 5);
INSERT INTO `airdata_info` VALUES ('1115664407396872194', '3', 'No. 160 Liantang Xianhu Road, Luohu District', '2024-03-15 13:27:48', 201, 205, 8, 31, 1.62, 76, 249, 5);
INSERT INTO `airdata_info` VALUES ('1115664407396872195', '4', 'No. 75 Jiefangbei, Yuzhong District', '2024-03-24 11:07:23', 205, 192, 5, 38, 2.28, 27, 255, 5);
INSERT INTO `airdata_info` VALUES ('1115664407396872196', '4', 'Intersection of Minzu Road and Zhongshan Road', '2024-03-14 10:15:39', 109, 88, 2, 22, 1.5, 28, 141, 3);
INSERT INTO `airdata_info` VALUES ('1115664407396872197', '4', 'Ciqikou Town, Shapingba District', '2024-03-20 13:44:36', 8, 24, 2, 7, 0.27, 68, 33, 1);
INSERT INTO `airdata_info` VALUES ('1115664407396872198', '4', 'Tiansheng Three Bridges Scenic Area', '2024-03-03 15:36:17', 5, 31, 2, 4, 0.21, 72, 32, 1);
INSERT INTO `airdata_info` VALUES ('1115664407396872199', '4', 'Nanshan Yikeshu Viewing Platform, Nanshan District', '2024-04-17 12:56:11', 8, 15, 2, 7, 0.27, 64, 22, 1);
INSERT INTO `airdata_info` VALUES ('1116939963325214720', '1', 'Zhongnanhai', '2024-06-09 12:00:39', 13, 12, 13, 45, 12, 123, 176, 3);

SET FOREIGN_KEY_CHECKS = 1;
