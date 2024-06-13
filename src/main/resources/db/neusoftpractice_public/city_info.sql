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

 Date: 08/06/2024 10:51:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for city_info
-- ----------------------------
DROP TABLE IF EXISTS `city_info`;
CREATE TABLE `city_info`  (
  `id` int NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
INSERT INTO `city_info` VALUES (8, 'Wuhan', 'Hubei', 'megaCity');
INSERT INTO `city_info` VALUES (9, 'Dongguan', 'Guangdong', 'megaCity');
INSERT INTO `city_info` VALUES (10, 'Xi\'an', 'Shaanxi', 'majorCity');
INSERT INTO `city_info` VALUES (11, 'Hangzhou', 'Zhejiang', 'megaCity');
INSERT INTO `city_info` VALUES (12, 'Foshan', 'Guangdong', 'bigCity');
INSERT INTO `city_info` VALUES (13, 'Nanjing', 'Jiangsu', 'majorCity');
INSERT INTO `city_info` VALUES (14, 'Shenyang', 'Liaoning', 'majorCity');
INSERT INTO `city_info` VALUES (15, 'Qingdao', 'Shandong', 'majorCity');
INSERT INTO `city_info` VALUES (16, 'Jinan', 'Shandong', 'majorCity');
INSERT INTO `city_info` VALUES (17, 'Changsha', 'Hunan', 'majorCity');
INSERT INTO `city_info` VALUES (18, 'Harbin', 'Heilongjiang', 'bigCity');
INSERT INTO `city_info` VALUES (19, 'Zhengzhou', 'Henan', 'majorCity');
INSERT INTO `city_info` VALUES (20, 'Kunming', 'Yunnan', 'bigCity');
INSERT INTO `city_info` VALUES (21, 'Dalian', 'Liaoning', 'bigCity');
INSERT INTO `city_info` VALUES (22, 'Nanning', 'Guangxi', 'bigCity');
INSERT INTO `city_info` VALUES (23, 'Shijiazhuang', 'Hebei', 'bigCity');
INSERT INTO `city_info` VALUES (24, 'Xiamen', 'Fujian', 'bigCity');
INSERT INTO `city_info` VALUES (25, 'Taiyuan', 'Shanxi', 'bigCity');
INSERT INTO `city_info` VALUES (26, 'Suzhou', 'Jiangsu', 'majorCity');
INSERT INTO `city_info` VALUES (27, 'Guiyang', 'Guizhou', 'bigCity');
INSERT INTO `city_info` VALUES (28, 'Hefei', 'Anhui', 'majorCity');
INSERT INTO `city_info` VALUES (29, 'Urumqi', 'Xinjiang', 'bigCity');
INSERT INTO `city_info` VALUES (30, 'Ningbo', 'Zhejiang', 'bigCity');
INSERT INTO `city_info` VALUES (31, 'Wuxi', 'Jiangsu', 'bigCity');
INSERT INTO `city_info` VALUES (32, 'Fuzhou', 'Fujian', 'bigCity');
INSERT INTO `city_info` VALUES (33, 'Changchun', 'Jilin', 'bigCity');
INSERT INTO `city_info` VALUES (34, 'Nanchang', 'Jiangxi', 'bigCity');
INSERT INTO `city_info` VALUES (35, 'Changzhou', 'Jiangsu', 'bigCity');
INSERT INTO `city_info` VALUES (36, 'Lanzhou', 'Gansu', 'bigCity');
INSERT INTO `city_info` VALUES (37, 'Zhongshan', 'Guangdong', 'mediunCity');
INSERT INTO `city_info` VALUES (38, 'Huizhou', 'Guangdong', 'bigCity');
INSERT INTO `city_info` VALUES (39, 'Shantou', 'Guangdong', 'bigCity');
INSERT INTO `city_info` VALUES (40, 'Linyi', 'Shandong', 'bigCity');
INSERT INTO `city_info` VALUES (41, 'Zibo', 'Shandong', 'bigCity');
INSERT INTO `city_info` VALUES (42, 'Wenzhou', 'Zhejiang', 'bigCity');
INSERT INTO `city_info` VALUES (43, 'Hohhot', 'Inner Mongolia', 'bigCity');
INSERT INTO `city_info` VALUES (44, 'Shaoxing', 'Zhejiang', 'bigCity');
INSERT INTO `city_info` VALUES (45, 'Tangshan', 'Hebei', 'bigCity');
INSERT INTO `city_info` VALUES (46, 'Haikou', 'Hainan', 'bigCity');
INSERT INTO `city_info` VALUES (47, 'Liuzhou', 'Guangxi', 'bigCity');
INSERT INTO `city_info` VALUES (48, 'Xuzhou', 'Jiangsu', 'bigCity');
INSERT INTO `city_info` VALUES (49, 'Yantai', 'Shandong', 'bigCity');
INSERT INTO `city_info` VALUES (50, 'Luoyang', 'Henan', 'bigCity');
INSERT INTO `city_info` VALUES (51, 'Handan', 'Hebei', 'bigCity');
INSERT INTO `city_info` VALUES (52, 'Zhuhai', 'Guangdong', 'bigCity');
INSERT INTO `city_info` VALUES (53, 'Baotou', 'Inner Mongolia', 'bigCity');
INSERT INTO `city_info` VALUES (54, 'Baoding', 'Hebei', 'bigCity');
INSERT INTO `city_info` VALUES (55, 'Weifang', 'Shandong', 'bigCity');
INSERT INTO `city_info` VALUES (56, 'Datong', 'Shanxi', 'bigCity');
INSERT INTO `city_info` VALUES (57, 'Jiangmen', 'Guangdong', 'bigCity');
INSERT INTO `city_info` VALUES (58, 'Ganzhou', 'Jiangxi', 'bigCity');
INSERT INTO `city_info` VALUES (59, 'Xining', 'Qinghai', 'bigCity');
INSERT INTO `city_info` VALUES (60, 'Nantong', 'Jiangsu', 'bigCity');
INSERT INTO `city_info` VALUES (61, 'Yinchuan', 'Ningxia', 'bigCity');
INSERT INTO `city_info` VALUES (62, 'Yangzhou', 'Jiangsu  ', 'bigCity');
INSERT INTO `city_info` VALUES (63, 'Zunyi', 'Guizhou  ', 'bigCity');
INSERT INTO `city_info` VALUES (64, 'Xiangyang', 'Hubei  ', 'bigCity');
INSERT INTO `city_info` VALUES (65, 'Anshan', 'Liaoning  ', 'bigCity');
INSERT INTO `city_info` VALUES (66, 'Kunshan', 'Jiangsu  ', 'mediunCity');
INSERT INTO `city_info` VALUES (67, 'Putian', 'Fujian', 'mediunCity');
INSERT INTO `city_info` VALUES (68, 'Mianyang', 'Sichuan', 'bigCity');
INSERT INTO `city_info` VALUES (69, 'Yancheng', 'Jiangsu', 'bigCity');
INSERT INTO `city_info` VALUES (70, 'Quanzhou', 'Fujian', 'bigCity');
INSERT INTO `city_info` VALUES (71, 'Xianyang', 'Shaanxi', 'bigCity');
INSERT INTO `city_info` VALUES (72, 'Taizhou', 'Zhejiang', 'bigCity');
INSERT INTO `city_info` VALUES (73, 'Wuhu', 'Anhui', 'bigCity');
INSERT INTO `city_info` VALUES (74, 'Zhuzhou', 'Hunan', 'bigCity');
INSERT INTO `city_info` VALUES (75, 'Huaian', 'Jiangsu', 'bigCity');
INSERT INTO `city_info` VALUES (76, 'Jining', 'Shandong', 'bigCity');
INSERT INTO `city_info` VALUES (77, 'Jilin', 'Jilin', 'bigCity');
INSERT INTO `city_info` VALUES (78, 'Daqing', 'Heilongjiang', 'bigCity');
INSERT INTO `city_info` VALUES (79, 'Guilin', 'Guangxi', 'bigCity');
INSERT INTO `city_info` VALUES (80, 'Qinhuangdao', 'Hebei', 'bigCity');
INSERT INTO `city_info` VALUES (81, 'Zhanjiang', 'Guangdong', 'mediunCity');
INSERT INTO `city_info` VALUES (82, 'Yichang', 'Hubei', 'bigCity');
INSERT INTO `city_info` VALUES (83, 'Qiqihar', 'Heilongjiang', 'bigCity');
INSERT INTO `city_info` VALUES (84, 'Fushun', 'Liaoning', 'bigCity');
INSERT INTO `city_info` VALUES (85, 'Shangrao', 'Jiangxi', 'mediunCity');
INSERT INTO `city_info` VALUES (86, 'Nanchong', 'Sichuan', 'bigCity');
INSERT INTO `city_info` VALUES (87, 'Yiwu', 'Zhejiang', 'bigCity');
INSERT INTO `city_info` VALUES (88, 'Xingtai', 'Hebei', 'mediunCity');
INSERT INTO `city_info` VALUES (89, 'Taian', 'Shandong', 'bigCity');
INSERT INTO `city_info` VALUES (90, 'Kaifeng', 'Henan', 'bigCity');
INSERT INTO `city_info` VALUES (91, 'Zhangjiakou', 'Hebei', 'bigCity');
INSERT INTO `city_info` VALUES (92, 'Xinxiang', 'Henan', 'mediunCity');
INSERT INTO `city_info` VALUES (93, 'Liaocheng', 'Shandong', 'bigCity');
INSERT INTO `city_info` VALUES (94, 'Huainan', 'Anhui', 'bigCity');
INSERT INTO `city_info` VALUES (95, 'Shiyan', 'Hubei', 'mediunCity');
INSERT INTO `city_info` VALUES (96, 'Yibin', 'Sichuan', 'bigCity');
INSERT INTO `city_info` VALUES (97, 'Zaozhuang', 'Shandong', 'bigCity');
INSERT INTO `city_info` VALUES (98, 'Yueyang', 'Hunan', 'mediunCity');
INSERT INTO `city_info` VALUES (99, 'Cixi', 'Zhejiang', 'mediunCity');
INSERT INTO `city_info` VALUES (100, 'Hengyang', 'Hunan', 'bigCity');
INSERT INTO `city_info` VALUES (101, 'Changzhi', 'Shanxi', 'mediunCity');
INSERT INTO `city_info` VALUES (102, 'Lianyungang', 'Jiangsu', 'bigCity');
INSERT INTO `city_info` VALUES (103, 'Jinzhou', 'Liaoning', 'bigCity');
INSERT INTO `city_info` VALUES (104, 'Chifeng', 'Inner Mongolia', 'mediunCity');
INSERT INTO `city_info` VALUES (105, 'Luzhou', 'Sichuan', 'bigCity');
INSERT INTO `city_info` VALUES (106, 'Anyang', 'Henan', 'mediunCity');
INSERT INTO `city_info` VALUES (107, 'Pingdingshan', 'Henan', 'mediunCity');
INSERT INTO `city_info` VALUES (108, 'Jiaxing', 'Zhejiang', 'mediunCity');
INSERT INTO `city_info` VALUES (109, 'Ruian', 'Zhejiang', 'bigCity');
INSERT INTO `city_info` VALUES (110, 'Taizhou', 'Jiangsu', 'mediunCity');
INSERT INTO `city_info` VALUES (111, 'Qujing', 'Yunnan', 'mediunCity');
INSERT INTO `city_info` VALUES (112, 'Dazhou', 'Sichuan', 'bigCity');
INSERT INTO `city_info` VALUES (113, 'Yingkou', 'Liaoning', 'mediunCity');
INSERT INTO `city_info` VALUES (114, 'Jieyang', 'Guangdong', 'mediunCity');
INSERT INTO `city_info` VALUES (115, 'Panjin', 'Liaoning', 'mediunCity');
INSERT INTO `city_info` VALUES (116, 'Chaozhou', 'Guangdong', 'mediunCity');
INSERT INTO `city_info` VALUES (117, 'Dongying', 'Shandong', 'mediunCity');
INSERT INTO `city_info` VALUES (118, 'Maoming', 'Guangdong', 'mediunCity');
INSERT INTO `city_info` VALUES (119, 'Changde', 'Hunan', 'mediunCity');
INSERT INTO `city_info` VALUES (120, 'Jiujiang', 'Jiangxi', 'mediunCity');
INSERT INTO `city_info` VALUES (121, 'Qingyuan', 'Guangdong', 'mediunCity');
INSERT INTO `city_info` VALUES (122, 'Xinyang', 'Henan', 'mediunCity');
INSERT INTO `city_info` VALUES (123, 'Yueqing', 'Zhejiang', 'mediunCity');
INSERT INTO `city_info` VALUES (124, 'Ma\'anshan', 'Anhui', 'mediunCity');
INSERT INTO `city_info` VALUES (125, 'Fuyang', 'Anhui', 'bigCity');
INSERT INTO `city_info` VALUES (126, 'Rizhao', 'Shandong', 'mediunCity');
INSERT INTO `city_info` VALUES (127, 'Baoji', 'Shaanxi', 'bigCity');
INSERT INTO `city_info` VALUES (128, 'Nanyang', 'Henan', 'bigCity');
INSERT INTO `city_info` VALUES (129, 'Hengshui', 'Hebei', 'mediunCity');
INSERT INTO `city_info` VALUES (130, 'Zhaoqing', 'Guangdong', 'mediunCity');
INSERT INTO `city_info` VALUES (131, 'Changshu', 'Jiangsu', 'mediunCity');
INSERT INTO `city_info` VALUES (132, 'Suqian', 'Jiangsu', 'bigCity');
INSERT INTO `city_info` VALUES (133, 'Yulin', 'Shaanxi', 'mediunCity');
INSERT INTO `city_info` VALUES (134, 'Jingzhou', 'Hubei', 'mediunCity');
INSERT INTO `city_info` VALUES (135, 'Xuchang', 'Henan', 'mediunCity');
INSERT INTO `city_info` VALUES (136, 'Lu\'an', 'Anhui', 'mediunCity');
INSERT INTO `city_info` VALUES (137, 'Heze', 'Shandong', 'bigCity');
INSERT INTO `city_info` VALUES (138, 'Huaibei', 'Anhui', 'mediunCity');
INSERT INTO `city_info` VALUES (139, 'Cangzhou', 'Hebei', 'mediunCity');
INSERT INTO `city_info` VALUES (140, 'Jinhua', 'Zhejiang', 'bigCity');
INSERT INTO `city_info` VALUES (141, 'Zhenjiang', 'Jiangsu', 'mediunCity');
INSERT INTO `city_info` VALUES (142, 'Jinzhong', 'Shanxi', 'mediunCity');
INSERT INTO `city_info` VALUES (143, 'Fuzhou', 'Jiangxi', 'mediunCity');
INSERT INTO `city_info` VALUES (144, 'Jiaozuo', 'Henan', 'mediunCity');
INSERT INTO `city_info` VALUES (145, 'Chenzhou', 'Hunan', 'mediunCity');
INSERT INTO `city_info` VALUES (146, 'Zigong', 'Sichuan', 'bigCity');
INSERT INTO `city_info` VALUES (147, 'Benxi', 'Liaoning', 'mediunCity');
INSERT INTO `city_info` VALUES (148, 'Zhangzhou', 'Fujian', 'mediunCity');
INSERT INTO `city_info` VALUES (149, 'Shangqiu', 'Henan', 'bigCity');
INSERT INTO `city_info` VALUES (150, 'Zhoukou', 'Henan', 'mediunCity');
INSERT INTO `city_info` VALUES (151, 'Suining', 'Sichuan', 'mediunCity');
INSERT INTO `city_info` VALUES (152, 'Huzhou', 'Zhejiang', 'mediunCity');
INSERT INTO `city_info` VALUES (153, 'Yangjiang', 'Guangdong', 'mediunCity');
INSERT INTO `city_info` VALUES (154, 'Binzhou', 'Shandong', 'mediunCity');
INSERT INTO `city_info` VALUES (155, 'Liaoyang', 'Liaoning', 'mediunCity');

SET FOREIGN_KEY_CHECKS = 1;
