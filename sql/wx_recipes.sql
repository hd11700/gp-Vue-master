/*
 Navicat Premium Dump SQL

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80403 (8.4.3)
 Source Host           : localhost:3306
 Source Schema         : ry-vue

 Target Server Type    : MySQL
 Target Server Version : 80403 (8.4.3)
 File Encoding         : 65001

 Date: 03/03/2025 14:07:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wx_recipes
-- ----------------------------
DROP TABLE IF EXISTS `wx_recipes`;
CREATE TABLE `wx_recipes`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `calories` int NULL DEFAULT NULL,
  `ingredients` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `category_id` int NULL DEFAULT NULL,
  `effect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `suitpeople` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `make` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `category_id`(`category_id` ASC) USING BTREE,
  CONSTRAINT `wx_recipes_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `wx_categories` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 201 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wx_recipes
-- ----------------------------
INSERT INTO `wx_recipes` VALUES (1, '宫保鸡丁', 'http://localhost:8080/鸡汤.jpeg', 500, '鸡肉、花生、辣椒', 1, '补充蛋白质、增强免疫力和促进消化', '增脂', '将鸡胸肉切丁腌制，花生炒熟备用。炒鸡丁至变色后备用，再炒香干辣椒和姜蒜，加入鸡丁、青椒和调料翻炒，最后加入花生，翻炒均匀即可出锅。');
INSERT INTO `wx_recipes` VALUES (2, '红烧肉', 'http://localhost:8080/红烧肉.jpeg', 600, '五花肉、酱油、糖', 2, '补充能量、滋补身体', '增脂', '将五花肉切块，焯水后捞出。锅中放油，加入糖炒至焦糖色，加入肉块翻炒，加入酱油和水，炖煮至肉软烂。');
INSERT INTO `wx_recipes` VALUES (3, '麻婆豆腐', 'http://localhost:8080/麻婆豆腐.jpeg', 350, '豆腐、牛肉末、辣椒', 3, '开胃、促进消化', '增脂', '豆腐切块焯水备用。锅中放油，炒香牛肉末，加入辣椒和豆瓣酱炒香，加入豆腐翻炒，最后加水淀粉勾芡。');
INSERT INTO `wx_recipes` VALUES (4, '鱼香肉丝', 'http://localhost:8080/鱼香肉丝.jpeg', 400, '猪肉、胡萝卜、青椒', 4, '开胃、促进食欲', '增脂', '猪肉切丝腌制，胡萝卜和青椒切丝。锅中放油，炒香肉丝，加入胡萝卜和青椒翻炒，加入调料炒匀。');
INSERT INTO `wx_recipes` VALUES (5, '糖醋排骨', 'http://localhost:8080/糖醋排骨.jpeg', 450, '排骨、糖、醋', 5, '开胃、补钙', '增脂', '排骨焯水后捞出。锅中放油，加入糖炒至焦糖色，加入排骨翻炒，加入醋和水，炖煮至排骨软烂。');
INSERT INTO `wx_recipes` VALUES (6, '清蒸鲈鱼', 'http://localhost:8080/清蒸鲈鱼.jpeg', 300, '鲈鱼、姜、葱', 6, '补充蛋白质、益智', '一般人群', '鲈鱼洗净，放入姜葱，蒸锅中蒸熟，淋上热油和酱油即可。');
INSERT INTO `wx_recipes` VALUES (7, '酸辣土豆丝', 'http://localhost:8080/酸辣土豆丝.jpeg', 200, '土豆、辣椒、醋', 7, '开胃、促进消化', '一般人群', '土豆切丝焯水，锅中放油，炒香辣椒，加入土豆丝翻炒，加入醋和调料炒匀。');
INSERT INTO `wx_recipes` VALUES (8, '西红柿炒鸡蛋', 'http://localhost:8080/西红柿炒鸡蛋.jpeg', 250, '鸡蛋、西红柿', 8, '补充维生素、提高免疫力', '一般人群', '鸡蛋打散炒熟备用。锅中放油，炒香西红柿，加入鸡蛋翻炒，加入调料炒匀。');
INSERT INTO `wx_recipes` VALUES (9, '宫保虾球', 'http://localhost:8080/宫保虾球.jpeg', 400, '虾仁、花生、辣椒', 1, '补充蛋白质、增强免疫力', '一般人群', '虾仁腌制，花生炒熟备用。炒虾仁至变色后备用，再炒香干辣椒和姜蒜，加入虾仁、青椒和调料翻炒，最后加入花生，翻炒均匀即可出锅。');
INSERT INTO `wx_recipes` VALUES (10, '辣子鸡', 'http://localhost:8080/辣子鸡.jpeg', 500, '鸡肉、辣椒、花椒', 2, '开胃、促进食欲', '一般人群', '鸡肉切块腌制，锅中放油，炒香辣椒和花椒，加入鸡肉翻炒，加入调料炒匀。');
INSERT INTO `wx_recipes` VALUES (11, '水煮鱼', 'http://localhost:8080/水煮鱼.jpeg', 550, '鱼片、辣椒、豆芽', 3, '开胃、补充蛋白质', '一般人群', '鱼片腌制，锅中放油，炒香辣椒，加入水煮开，加入鱼片和豆芽煮熟。');
INSERT INTO `wx_recipes` VALUES (12, '干锅花菜', 'http://localhost:8080/干锅花菜.jpeg', 300, '花菜、五花肉、辣椒', 4, '开胃、促进消化', '一般人群', '花菜焯水，五花肉切片。锅中放油，炒香五花肉，加入辣椒和花菜翻炒，加入调料炒匀。');
INSERT INTO `wx_recipes` VALUES (13, '红烧茄子', 'http://localhost:8080/红烧茄子.jpeg', 350, '茄子、酱油、糖', 5, '开胃、促进消化', '一般人群', '茄子切块，锅中放油，炒香茄子，加入酱油和糖翻炒，加入水炖煮至茄子软烂。');
INSERT INTO `wx_recipes` VALUES (14, '蒜蓉西兰花', 'http://localhost:8080/蒜蓉西兰花.jpeg', 200, '西兰花、蒜', 6, '补充维生素、提高免疫力', '一般人群', '西兰花焯水，锅中放油，炒香蒜蓉，加入西兰花翻炒，加入调料炒匀。');
INSERT INTO `wx_recipes` VALUES (15, '香菇炖鸡', 'http://localhost:8080/香菇炖鸡.jpeg', 400, '鸡肉、香菇、姜', 7, '补充蛋白质、滋补身体', '一般人群', '鸡肉切块，香菇泡发。锅中放油，炒香姜片，加入鸡肉和香菇翻炒，加入水炖煮至鸡肉熟烂。');
INSERT INTO `wx_recipes` VALUES (16, '豆豉蒸排骨', 'http://localhost:8080/豆豉蒸排骨.jpeg', 450, '排骨、豆豉、辣椒', 8, '开胃、补钙', '一般人群', '排骨腌制，加入豆豉和辣椒，蒸锅中蒸熟。');
INSERT INTO `wx_recipes` VALUES (17, '葱爆羊肉', 'http://localhost:8080/葱爆羊肉.jpeg', 500, '羊肉、葱', 1, '补充蛋白质、滋补身体', '一般人群', '羊肉切片腌制，锅中放油，炒香葱段，加入羊肉翻炒，加入调料炒匀。');
INSERT INTO `wx_recipes` VALUES (18, '鱼香茄子', 'http://localhost:8080/鱼香茄子.jpeg', 350, '茄子、辣椒、酱油', 2, '开胃、促进食欲', '一般人群', '茄子切块，锅中放油，炒香辣椒，加入茄子翻炒，加入酱油和调料炒匀。');
INSERT INTO `wx_recipes` VALUES (19, '宫保豆腐', 'http://localhost:8080/宫保豆腐.jpeg', 300, '豆腐、花生、辣椒', 3, '补充蛋白质、增强免疫力', '一般人群', '豆腐切块，花生炒熟备用。炒豆腐至金黄后备用，再炒香干辣椒和姜蒜，加入豆腐、青椒和调料翻炒，最后加入花生，翻炒均匀即可出锅。');
INSERT INTO `wx_recipes` VALUES (20, '香辣虾', 'http://localhost:8080/香辣虾.jpeg', 400, '虾、辣椒、花椒', 4, '开胃、补充蛋白质', '一般人群', '虾去壳腌制，锅中放油，炒香辣椒和花椒，加入虾翻炒，加入调料炒匀。');
INSERT INTO `wx_recipes` VALUES (21, '红烧狮子头', 'http://localhost:8080/红烧狮子头.jpeg', 500, '猪肉、酱油、糖', 5, '补充能量、滋补身体', '一般人群', '猪肉剁成肉馅，加入调料搅拌均匀，捏成肉丸。锅中放油，煎至金黄，加入酱油和糖炖煮。');
INSERT INTO `wx_recipes` VALUES (22, '蒜蓉粉丝蒸扇贝', 'http://localhost:8080/蒜蓉粉丝蒸扇贝.jpeg', 300, '扇贝、粉丝、蒜', 6, '补充蛋白质、提高免疫力', '一般人群', '扇贝洗净，粉丝泡软。蒜蓉炒香，加入扇贝和粉丝，蒸锅中蒸熟。');
INSERT INTO `wx_recipes` VALUES (23, '酸菜鱼', 'http://localhost:8080/酸菜鱼.jpeg', 550, '鱼片、酸菜、辣椒', 7, '开胃、补充蛋白质', '一般人群', '鱼片腌制，锅中放油，炒香酸菜和辣椒，加入水煮开，加入鱼片煮熟。');
INSERT INTO `wx_recipes` VALUES (24, '宫保鸡翅', 'http://localhost:8080/宫保鸡翅.jpeg', 500, '鸡翅、花生、辣椒', 8, '补充蛋白质、增强免疫力', '一般人群', '鸡翅腌制，花生炒熟备用。炒鸡翅至变色后备用，再炒香干辣椒和姜蒜，加入鸡翅、青椒和调料翻炒，最后加入花生，翻炒均匀即可出锅。');
INSERT INTO `wx_recipes` VALUES (25, '香煎豆腐', 'http://localhost:8080/香煎豆腐.jpeg', 250, '豆腐、酱油、葱', 1, '补充蛋白质、提高免疫力', '一般人群', '豆腐切块，锅中放油，煎至金黄，加入酱油和葱花翻炒。');
INSERT INTO `wx_recipes` VALUES (26, '红烧鸡翅', 'http://localhost:8080/红烧鸡翅.jpeg', 450, '鸡翅、酱油、糖', 2, '补充蛋白质、滋补身体', '一般人群', '鸡翅焯水后捞出。锅中放油，加入糖炒至焦糖色，加入鸡翅翻炒，加入酱油和水，炖煮至鸡翅软烂。');
INSERT INTO `wx_recipes` VALUES (27, '蒜蓉蒸茄子', 'http://localhost:8080/蒜蓉蒸茄子.jpeg', 200, '茄子、蒜', 3, '补充维生素、提高免疫力', '一般人群', '茄子切块，蒜蓉炒香，加入茄子，蒸锅中蒸熟。');
INSERT INTO `wx_recipes` VALUES (28, '宫保牛肉', 'http://localhost:8080/宫保牛肉.jpeg', 500, '牛肉、花生、辣椒', 4, '补充蛋白质、增强免疫力', '一般人群', '牛肉切片腌制，花生炒熟备用。炒牛肉至变色后备用，再炒香干辣椒和姜蒜，加入牛肉、青椒和调料翻炒，最后加入花生，翻炒均匀即可出锅。');
INSERT INTO `wx_recipes` VALUES (29, '香辣鸡翅', 'http://localhost:8080/香辣鸡翅.jpeg', 500, '鸡翅、辣椒、花椒', 5, '开胃、补充蛋白质', '一般人群', '鸡翅腌制，锅中放油，炒香辣椒和花椒，加入鸡翅翻炒，加入调料炒匀。');
INSERT INTO `wx_recipes` VALUES (30, '红烧豆腐', 'http://localhost:8080/红烧豆腐.jpeg', 300, '豆腐、酱油、糖', 6, '补充蛋白质、促进消化', '一般人群', '豆腐切块，锅中放油，炒香豆腐，加入酱油和糖翻炒，加入水炖煮至豆腐软烂。');

SET FOREIGN_KEY_CHECKS = 1;
