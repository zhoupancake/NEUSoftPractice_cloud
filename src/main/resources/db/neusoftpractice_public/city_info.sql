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

 Date: 24/06/2024 14:48:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for city_info
-- ----------------------------
DROP TABLE IF EXISTS `city_info`;
CREATE TABLE `city_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 159 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of city_info
-- ----------------------------
INSERT INTO `city_info` VALUES (1, 'Shanghai', 'Shanghai', 'megaCity');
INSERT INTO `city_info` VALUES (2, 'Beijing', 'Beijing', 'megaCity');
INSERT INTO `city_info` VALUES (3, 'Shenzhen', 'Guangdong', 'megaCity');
INSERT INTO `city_info` VALUES (4, 'Chongqing', 'Chongqing', 'megaCity');
INSERT INTO `city_info` VALUES (5, 'Guangzhou', 'Guangdong', 'megaCity');
INSERT INTO `city_info` VALUES (6, 'Chengdu', 'Sichuan', 'megaCity');
INSERT INTO `city_info` VALUES (7, 'Tianjin', 'Tianjin', 'megaCity');
INSERT INTO `city_info` VALUES (8, 'Wuhan', 'Hubei', 'bigCity');
INSERT INTO `city_info` VALUES (9, 'Dongguan', 'Guangdong', 'bigCity');
INSERT INTO `city_info` VALUES (10, 'Xi\'an', 'Shaanxi', 'bigCity');
INSERT INTO `city_info` VALUES (11, 'Hangzhou', 'Zhejiang', 'bigCity');
INSERT INTO `city_info` VALUES (12, 'Foshan', 'Guangdong', 'bigCity');
INSERT INTO `city_info` VALUES (13, 'Nanjing', 'Jiangsu', 'bigCity');
INSERT INTO `city_info` VALUES (14, 'Shenyang', 'Liaoning', 'bigCity');
INSERT INTO `city_info` VALUES (15, 'Qingdao', 'Shandong', 'bigCity');
INSERT INTO `city_info` VALUES (16, 'Jinan', 'Shandong', 'bigCity');
INSERT INTO `city_info` VALUES (17, 'Changsha', 'Hunan', 'bigCity');
INSERT INTO `city_info` VALUES (18, 'Harbin', 'Heilongjiang', 'bigCity');
INSERT INTO `city_info` VALUES (19, 'Zhengzhou', 'Henan', 'bigCity');
INSERT INTO `city_info` VALUES (20, 'Kunming', 'Yunnan', 'bigCity');
INSERT INTO `city_info` VALUES (21, 'Dalian', 'Liaoning', 'bigCity');
INSERT INTO `city_info` VALUES (22, 'Nanning', 'Guangxi', 'Type I Large City');
INSERT INTO `city_info` VALUES (23, 'Shijiazhuang', 'Hebei', 'Type I Large City');
INSERT INTO `city_info` VALUES (24, 'Xiamen', 'Fujian', 'Type I Large City');
INSERT INTO `city_info` VALUES (25, 'Taiyuan', 'Shanxi', 'Type I Large City');
INSERT INTO `city_info` VALUES (26, 'Suzhou', 'Jiangsu', 'Type I Large City');
INSERT INTO `city_info` VALUES (27, 'Guiyang', 'Guizhou', 'Type I Large City');
INSERT INTO `city_info` VALUES (28, 'Hefei', 'Anhui', 'Type I Large City');
INSERT INTO `city_info` VALUES (29, 'Urumqi', 'Xinjiang', 'Type I Large City');
INSERT INTO `city_info` VALUES (30, 'Ningbo', 'Zhejiang', 'Type I Large City');
INSERT INTO `city_info` VALUES (31, 'Wuxi', 'Jiangsu', 'Type I Large City');
INSERT INTO `city_info` VALUES (32, 'Fuzhou', 'Fujian', 'Type I Large City');
INSERT INTO `city_info` VALUES (33, 'Changchun', 'Jilin', 'Type I Large City');
INSERT INTO `city_info` VALUES (34, 'Nanchang', 'Jiangxi', 'Type I Large City');
INSERT INTO `city_info` VALUES (35, 'Changzhou', 'Jiangsu', 'Type I Large City');
INSERT INTO `city_info` VALUES (36, 'Lanzhou', 'Gansu', 'Type II Large City');
INSERT INTO `city_info` VALUES (37, 'Zhongshan', 'Guangdong', 'Type II Large City');
INSERT INTO `city_info` VALUES (38, 'Huizhou', 'Guangdong', 'Type II Large City');
INSERT INTO `city_info` VALUES (39, 'Shantou', 'Guangdong', 'Type II Large City');
INSERT INTO `city_info` VALUES (40, 'Linyi', 'Shandong', 'Type II Large City');
INSERT INTO `city_info` VALUES (41, 'Zibo', 'Shandong', 'Type II Large City');
INSERT INTO `city_info` VALUES (42, 'Wenzhou', 'Zhejiang', 'Type II Large City');
INSERT INTO `city_info` VALUES (43, 'Hohhot', 'Inner Mongolia', 'Type II Large City');
INSERT INTO `city_info` VALUES (44, 'Shaoxing', 'Zhejiang', 'Type II Large City');
INSERT INTO `city_info` VALUES (45, 'Tangshan', 'Hebei', 'Type II Large City');
INSERT INTO `city_info` VALUES (46, 'Haikou', 'Hainan', 'Type II Large City');
INSERT INTO `city_info` VALUES (47, 'Liuzhou', 'Guangxi', 'Type II Large City');
INSERT INTO `city_info` VALUES (48, 'Xuzhou', 'Jiangsu', 'Type II Large City');
INSERT INTO `city_info` VALUES (49, 'Yantai', 'Shandong', 'Type II Large City');
INSERT INTO `city_info` VALUES (50, 'Luoyang', 'Henan', 'Type II Large City');
INSERT INTO `city_info` VALUES (51, 'Handan', 'Hebei', 'Type II Large City');
INSERT INTO `city_info` VALUES (52, 'Zhuhai', 'Guangdong', 'Type II Large City');
INSERT INTO `city_info` VALUES (53, 'Baotou', 'Inner Mongolia', 'Type II Large City');
INSERT INTO `city_info` VALUES (54, 'Baoding', 'Hebei', 'Type II Large City');
INSERT INTO `city_info` VALUES (55, 'Langfang', 'Hebei', 'Type II Large City');
INSERT INTO `city_info` VALUES (56, 'Datong', 'Shanxi', 'Type II Large City');
INSERT INTO `city_info` VALUES (57, 'Jiangmen', 'Guangdong', 'Type II Large City');
INSERT INTO `city_info` VALUES (58, 'Ganzhou', 'Jiangxi', 'Type II Large City');
INSERT INTO `city_info` VALUES (59, 'Xining', 'Qinghai', 'Type II Large City');
INSERT INTO `city_info` VALUES (60, 'Nantong', 'Jiangsu', 'Type II Large City');
INSERT INTO `city_info` VALUES (61, 'Yinchuan', 'Ningxia', 'Type II Large City');
INSERT INTO `city_info` VALUES (62, 'Yangzhou', 'Jiangsu', 'Type II Large City');
INSERT INTO `city_info` VALUES (63, 'Zunyi', 'Guizhou', 'Type II Large City');
INSERT INTO `city_info` VALUES (64, 'Xiangyang', 'Hubei', 'Type II Large City');
INSERT INTO `city_info` VALUES (65, 'Anshan', 'Liaoning', 'Type II Large City');
INSERT INTO `city_info` VALUES (66, 'Kunshan', 'Jiangsu', 'Type II Large City');
INSERT INTO `city_info` VALUES (67, 'Putian', 'Fujian', 'Type II Large City');
INSERT INTO `city_info` VALUES (68, 'Mianyang', 'Sichuan', 'Type II Large City');
INSERT INTO `city_info` VALUES (69, 'Yancheng', 'Jiangsu', 'Type II Large City');
INSERT INTO `city_info` VALUES (70, 'Quanzhou', 'Fujian', 'Type II Large City');
INSERT INTO `city_info` VALUES (71, 'Xianyang', 'Shaanxi', 'Type II Large City');
INSERT INTO `city_info` VALUES (72, 'Taizhou', 'Zhejiang', 'Type II Large City');
INSERT INTO `city_info` VALUES (73, 'Wuhu', 'Anhui', 'Type II Large City');
INSERT INTO `city_info` VALUES (74, 'Zhuzhou', 'Hunan', 'Type II Large City');
INSERT INTO `city_info` VALUES (75, 'Huaian', 'Jiangsu', 'Type II Large City');
INSERT INTO `city_info` VALUES (76, 'Jining', 'Shandong', 'Type II Large City');
INSERT INTO `city_info` VALUES (77, 'Jilin', 'Jilin', 'Type II Large City');
INSERT INTO `city_info` VALUES (78, 'Daqing', 'Heilongjiang', 'Type II Large City');
INSERT INTO `city_info` VALUES (79, 'Guilin', 'Guangxi', 'Type II Large City');
INSERT INTO `city_info` VALUES (80, 'Qinhuangdao', 'Hebei', 'Type II Large City');
INSERT INTO `city_info` VALUES (81, 'Zhanjiang', 'Guangdong', 'Type II Large City');
INSERT INTO `city_info` VALUES (82, 'Yichang', 'Hubei', 'Type II Large City');
INSERT INTO `city_info` VALUES (83, 'Qiqihar', 'Heilongjiang', 'Type II Large City');
INSERT INTO `city_info` VALUES (84, 'Fushun', 'Liaoning', 'Type II Large City');
INSERT INTO `city_info` VALUES (85, 'Shangrao', 'Jiangxi', 'Type II Large City');
INSERT INTO `city_info` VALUES (86, 'Nanchong', 'Sichuan', 'Type II Large City');
INSERT INTO `city_info` VALUES (87, 'Yiwu', 'Zhejiang', 'Type II Large City');
INSERT INTO `city_info` VALUES (88, 'Xingtai', 'Hebei', 'Type II Large City');
INSERT INTO `city_info` VALUES (89, 'Tai\'an', 'Shandong', 'Type II Large City');
INSERT INTO `city_info` VALUES (90, 'Kaifeng', 'Henan', 'Type II Large City');
INSERT INTO `city_info` VALUES (91, 'Zhangjiakou', 'Hebei', 'Type II Large City');
INSERT INTO `city_info` VALUES (92, 'Xinxiang', 'Henan', 'Type II Large City');
INSERT INTO `city_info` VALUES (93, 'Liaocheng', 'Shandong', 'Type II Large City');
INSERT INTO `city_info` VALUES (94, 'Huainan', 'Anhui', 'Type II Large City');
INSERT INTO `city_info` VALUES (95, 'Shiyan', 'Hubei', 'Type II Large City');
INSERT INTO `city_info` VALUES (96, 'Yibin', 'Sichuan', 'Type II Large City');
INSERT INTO `city_info` VALUES (97, 'Zaozhuang', 'Shandong', 'Type II Large City');
INSERT INTO `city_info` VALUES (98, 'Yueyang', 'Hunan', 'Type II Large City');
INSERT INTO `city_info` VALUES (99, 'Cixi', 'Zhejiang', 'Type II Large City');
INSERT INTO `city_info` VALUES (100, 'Hengyang', 'Hunan', 'Type II Large City');
INSERT INTO `city_info` VALUES (101, 'Changzhi', 'Shanxi', 'Type II Large City');
INSERT INTO `city_info` VALUES (102, 'Lianyungang', 'Jiangsu', 'Type II Large City');
INSERT INTO `city_info` VALUES (103, 'Jinzhou', 'Liaoning', 'Type II Large City');
INSERT INTO `city_info` VALUES (104, 'Chifeng', 'Inner Mongolia', 'Type II Large City');
INSERT INTO `city_info` VALUES (105, 'Jinjiang', 'Fujian', 'Type II Large City');
INSERT INTO `city_info` VALUES (106, 'Luzhou', 'Sichuan', 'Type II Large City');

SET FOREIGN_KEY_CHECKS = 1;
