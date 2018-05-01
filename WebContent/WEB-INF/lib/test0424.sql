-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.20-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 sun 的数据库结构
CREATE DATABASE IF NOT EXISTS `meizhuang` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `meizhuang`;

-- 导出  表 sun.z_j_comment 结构
CREATE TABLE IF NOT EXISTS `z_j_comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `infoId` int(11) NOT NULL DEFAULT '0' COMMENT '对应详情id',
  `userId` int(11) NOT NULL DEFAULT '0' COMMENT '评论人id',
  `comment` text COMMENT '评论内容',
  `creatTime` datetime DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='评论表';

-- 正在导出表  sun.z_j_comment 的数据：~11 rows (大约)
/*!40000 ALTER TABLE `z_j_comment` DISABLE KEYS */;
INSERT INTO `z_j_comment` (`id`, `infoId`, `userId`, `comment`, `creatTime`) VALUES
	(13, 38, 8, '请问购买途径是什么呢', '2018-03-19 21:44:14'),
	(14, 36, 8, '牌子是什么名字啊', '2018-03-19 21:45:00'),
	(15, 35, 8, '请问这个眼霜用了会长粟丘疹么', '2018-03-19 21:45:51'),
	(16, 38, 9, '减脂时期可以吃么', '2018-03-19 21:47:17'),
	(17, 38, 9, '可以作为代餐么', '2018-03-19 21:47:25'),
	(18, 38, 9, '好吃么', '2018-03-19 21:47:31'),
	(19, 38, 9, '服用频率大概多久一次啊', '2018-03-19 21:47:50'),
	(20, 37, 9, '楼主没有具体说明方法啊', '2018-03-19 21:48:17'),
	(21, 35, 9, '干皮收藏了', '2018-03-19 21:48:39'),
	(22, 32, 9, '亲用过这个品牌的粉底么 ？K妹说很好用 请问使用感如何', '2018-03-19 21:49:50'),
	(23, 31, 9, '啊啊啊 想要cpb', '2018-03-19 21:50:31'),
	(24, 34, 10, '感觉好好吃的样子啊', '2018-03-19 21:52:17'),
	(25, 38, 8, '0.0', '2018-03-23 19:38:15');
/*!40000 ALTER TABLE `z_j_comment` ENABLE KEYS */;

-- 导出  表 sun.z_j_dict 结构
CREATE TABLE IF NOT EXISTS `z_j_dict` (
  `type1` varchar(50) DEFAULT NULL,
  `type2` varchar(50) DEFAULT NULL,
  `value1` varchar(50) DEFAULT NULL,
  `value2` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- 正在导出表  sun.z_j_dict 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `z_j_dict` DISABLE KEYS */;
/*!40000 ALTER TABLE `z_j_dict` ENABLE KEYS */;

-- 导出  表 sun.z_j_favorite 结构
CREATE TABLE IF NOT EXISTS `z_j_favorite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `infoId` int(11) NOT NULL DEFAULT '0' COMMENT '对应详情id',
  `userId` int(11) NOT NULL DEFAULT '0' COMMENT '收藏人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='详情收藏表';

-- 正在导出表  sun.z_j_favorite 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `z_j_favorite` DISABLE KEYS */;
INSERT INTO `z_j_favorite` (`id`, `infoId`, `userId`) VALUES
	(6, 38, 8),
	(7, 37, 8),
	(8, 35, 8),
	(9, 38, 9),
	(10, 32, 9),
	(11, 34, 10),
	(12, 36, 10),
	(13, 32, 8);
/*!40000 ALTER TABLE `z_j_favorite` ENABLE KEYS */;

-- 导出  表 sun.z_j_friend 结构
CREATE TABLE IF NOT EXISTS `z_j_friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `friendId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='好友关注表';

-- 正在导出表  sun.z_j_friend 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `z_j_friend` DISABLE KEYS */;
INSERT INTO `z_j_friend` (`id`, `userId`, `friendId`) VALUES
	(4, 10, 9),
	(5, 10, 8),
	(6, 8, 10);
/*!40000 ALTER TABLE `z_j_friend` ENABLE KEYS */;

-- 导出  表 sun.z_j_great 结构
CREATE TABLE IF NOT EXISTS `z_j_great` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `infoId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='点赞表';

-- 正在导出表  sun.z_j_great 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `z_j_great` DISABLE KEYS */;
INSERT INTO `z_j_great` (`id`, `userId`, `infoId`) VALUES
	(15, 8, 38),
	(16, 8, 36),
	(17, 9, 38),
	(18, 9, 37),
	(19, 9, 32),
	(20, 9, 31),
	(21, 10, 34),
	(22, 10, 36),
	(23, 8, 32);
/*!40000 ALTER TABLE `z_j_great` ENABLE KEYS */;

-- 导出  表 sun.z_j_images 结构
CREATE TABLE IF NOT EXISTS `z_j_images` (
  `imageId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `infoId` int(10) unsigned DEFAULT NULL COMMENT '与info表关联',
  `url` varchar(200) DEFAULT NULL COMMENT '图片路径',
  PRIMARY KEY (`imageId`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COMMENT='图片链接表';

-- 正在导出表  sun.z_j_images 的数据：~19 rows (大约)
/*!40000 ALTER TABLE `z_j_images` DISABLE KEYS */;
INSERT INTO `z_j_images` (`imageId`, `infoId`, `url`) VALUES
	(31, 31, '/693a0df4-23e0-49f0-babb-e7935c91098d@info.jpg'),
	(32, 32, '/aea8dc0f-6a83-4cd3-847a-60d575aecfef@info.jpg'),
	(33, 32, '/a984c799-060e-4061-a5aa-f5d919d96ded@info.jpg'),
	(34, 33, '/3135729c-324b-4674-a1bb-8760001c8a15@info.jpg'),
	(35, 33, '/2e479cce-970e-4954-bd4a-740876f31e04@info.jpg'),
	(36, 34, '/8142e05d-2eb6-47e4-90e5-a01a45af02c9@info.jpg'),
	(37, 34, '/a1784ae2-3ec2-42b6-be63-2cba7fe389d3@info.jpg'),
	(38, 34, '/67780de9-ca4a-491c-8310-74a250be3af6@info.jpg'),
	(39, 35, '/fa5769ac-e1e8-4f11-8ecb-b4634ee61f3b@info.jpg'),
	(40, 35, '/48931dc0-5be5-4223-a8e1-5662b3b04bb4@info.jpg'),
	(41, 36, '/07382490-9be0-4aee-97f5-7647455c2099@info.jpg'),
	(42, 36, '/69543b38-2d4f-4e1d-a7f5-8b216fc66581@info.jpg'),
	(43, 37, '/9c8c197b-354d-4200-963d-44c92beb3543@info.jpg'),
	(44, 37, '/33bf9800-b0ff-49a3-8346-f4ec504361e1@info.jpg'),
	(45, 38, '/f54e6ca9-35bf-406e-8ed1-28b3d4f300a6@info.jpg'),
	(46, 43, '/3adbe356-deaf-4b4e-bbf0-4cc7d884f529@info.jpg'),
	(47, 43, '/c2bd0cde-fc88-48d2-999d-a55387ffc4d1@info.jpg'),
	(48, 44, '/dd5158e2-d928-43b0-a156-bffa8db581d1@info.jpg'),
	(49, 44, '/95756206-abe6-4249-b7c2-68ca4a40c776@info.jpg'),
	(50, 45, '/fe05b5d9-601d-4027-99e6-dd562637f4af@info.jpg'),
	(51, 45, '/a589d216-3555-4b74-a5b8-79c74c393fb6@info.jpg');
/*!40000 ALTER TABLE `z_j_images` ENABLE KEYS */;

-- 导出  表 sun.z_j_info 结构
CREATE TABLE IF NOT EXISTS `z_j_info` (
  `infoId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `infoTitle` varchar(50) DEFAULT NULL COMMENT '信息标题',
  `infoDetail` mediumtext COMMENT '信息详情',
  `createTime` datetime DEFAULT NULL COMMENT '信息创建时间',
  `type1` int(3) unsigned DEFAULT NULL COMMENT '标签1',
  `type2` int(3) unsigned DEFAULT NULL COMMENT '标签2',
  `isCheck` int(1) unsigned DEFAULT '0' COMMENT '审核通过1/没通过0',
  `userId` int(10) unsigned DEFAULT NULL COMMENT '与用户表关联',
  `comment` int(11) DEFAULT '0' COMMENT '这条info的评论数',
  `great` int(11) DEFAULT '0' COMMENT '这条info的点赞数',
  `firstUrl` varchar(100) DEFAULT NULL COMMENT '封面图片',
  `top6` int(1) NOT NULL DEFAULT '0' COMMENT '1加入top',
  PRIMARY KEY (`infoId`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='详情表';

-- 正在导出表  sun.z_j_info 的数据：~14 rows (大约)
/*!40000 ALTER TABLE `z_j_info` DISABLE KEYS */;
INSERT INTO `z_j_info` (`infoId`, `infoTitle`, `infoDetail`, `createTime`, `type1`, `type2`, `isCheck`, `userId`, `comment`, `great`, `firstUrl`, `top6`) VALUES
	(31, 'cpb三件套使用心得', '超级难买的套装，滋润化妆水170m日乳125ml夜乳125ml。补水保湿非常好，没有紧绷感，日用乳，又叫做日间防护乳。自带SPF25/PA+++的防晒系数，短时间出门连防晒都省了。兼具有化妆底霜的作用，有效琐住水分，在日间全面呵护肌肤。CPB夜乳，专注于夜间修复的一款乳液，让白天受损的皮肤恢复弹性水润。质地清爽不油腻，用过后皮肤变得干净柔软，像喝饱水一样，十分有弹性。', '2018-03-19 20:51:43', 1, NULL, 1, 11, 1, 1, '/693a0df4-23e0-49f0-babb-e7935c91098d@info.jpg', 2),
	(32, 'No7冰淇淋面膜', '今年圣诞节一过，这款冰淇淋新款面膜就在英国Boots上市了。\n\n我一直是No7冰淇淋面膜的忠实拥趸，我身边在英国留学和工作的朋友也几乎人手一瓶。因为我冬天如果在北京生活，脸上是干到脱皮的，晚上使用一次冰淇淋，第二天一早便能好转。\n\n然而，唯一有个问题，旧款冰淇淋面膜偏厚重、推开时油感比较重，每次敷完要清洁的时候即使使用化妆棉擦拭，仍有些挥之不去的黏（争做名副其实的冰淇淋是吗哈哈哈）。\n\n当时我在店里试用这款新冰淇淋之后，当即囤下6罐。不过，讲真我是最反感橘色的人，论颜值，还是旧款更清新更“冰淇淋”呐！！\n\n不过，好在新款胜在改善了质地，更加轻薄，好推开、好吸收，我试用之后没有清洁，任由它停留在手背，完全没有黏腻的不适感。直到晚上回家还能感觉手背的水润。\n\n我一般是轻薄一层涂在脸上，10分钟左右，化妆棉擦拭后清水冲洗。', '2018-03-19 20:53:21', 1, NULL, 1, 11, 1, 2, '/aea8dc0f-6a83-4cd3-847a-60d575aecfef@info.jpg', 0),
	(33, '适合女生喝的酒', '虽然是男的但我的喝酒口味……\n\n很娘哈哈哈哈哈哈哈哈哈\n\n不太能受的了烈酒，太辣烧灼感太强\n\n喜欢那种好入口又可以达到微醺效果的\n\n同时！又不喜欢太过甜腻的感觉像在喝饮料是的\n\n（矫情本人）\n\nBUZZ（蜂狂）就是我的天菜类型！\n\n刚入口很柔很顺，细细品味会有一点正好的花香\n\n但不要害怕～不是化妆品或者香水的那种花香\n\n是很自然很香甜的那种\n\n（我个人及其害很工业的那种花香，这款不是）\n\n再来会感受到一点点蜂蜜的甜\n\n这种甜更多是喝下去的那种回甘，而不是入口马\n\n上能感受到的那种强烈的甜度\n\n酒量正常一点的，基本3、4瓶下肚就有feel了\n\n五星推荐！！！', '2018-03-19 20:56:16', 6, NULL, 1, 8, 0, 0, '/3135729c-324b-4674-a1bb-8760001c8a15@info.jpg', 3),
	(34, '木棉花的做法', '第一次在小红书分享美食 心情有点鸡冻木棉花又叫攀枝花，（貌似木棉花高大上点），这种含苞待放的才好吃，脆脆的，开放的吃起来口感太黏太绵第一步:剥出花蕊，红红的花瓣不能吃哦，把上面的小颗粒去掉，放在一个盆里泡着，15分钟换水，继续泡第二步:准备好配料，大蒜，这种本地独个独个的大蒜才给力，手工老酱，青椒，（有韭菜放点就更完美了）老干妈豆豉适量第三步:热锅烧油，油温温热下蒜末，老酱，豆豉爆香，木棉花，翻炒三分钟左右，中火即可！最后下青椒，一分钟后放盐，关火！', '2018-03-19 21:02:02', 5, NULL, 1, 8, 1, 1, '/8142e05d-2eb6-47e4-90e5-a01a45af02c9@info.jpg', 5),
	(35, 'loreal 好物分享', '作为人生第一瓶眼霜，欧家这瓶眼霜真的没有让我失望\n\n非常适合初期抗老，20-25岁的仙女们都可以用这瓶哦，质地非常清爽不油腻，已经使用了三个月左右才来写心得，没有长脂肪粒，去小细纹的功效还是很不错的，不过对黑眼圈好像没什么用。\n\n想到有别的再继续更哦', '2018-03-19 21:04:31', 4, NULL, 1, 9, 2, 0, '/fa5769ac-e1e8-4f11-8ecb-b4634ee61f3b@info.jpg', 0),
	(36, '我喜欢的瑞典小众品牌', '去年过生日的时候好友送了他们家的身体线和香水给我\n\n无意间认识这个瑞典的小众品牌\n\n当然现在知道他的朋友也很多啦\n\n图一是他们家三款手霜 我刚买回来 刚刚每一个都试用了一下\n\nBlanche是我最喜欢的味道 沐浴乳 香水用的都是这个味道 前调是玫瑰 后调是我最爱的麝香。之前用了三年的white suede是Tom Ford里的一款白麝香\n\nGypsy water 是我第二喜欢的 有一股檀香味\n\n重磅推荐的沐浴乳 他们家的blanche 我用了四个空瓶了！！非常喜欢 泡沫不是很丰富 但是写完一点不会觉得身体很干 最重要的是那股麝香比香水留的时间都要久 晚上睡觉的时候能闻见自己忽闪忽闪的味道～\n\n最后他们家的香水 我就不介绍了 依旧是我最爱白色浪漫味道', '2018-03-19 21:07:21', 1, NULL, 1, 9, 1, 2, '/07382490-9be0-4aee-97f5-7647455c2099@info.jpg', 0),
	(37, '一个月瘦20斤教程', '从130到106、也就是一个月时间主要从瘦脸、瘦腿、减脂三部分开始减肥之路的、首先饮食瘦腿操分享、有需要的DD直接发给你们吧因为我成功的瘦下来了 而且自己确实总结了很多方法 所以才想要跟大家分享 先放对比图吧 希望不要让你们辣眼睛\n没有错这是我以前 胖到没朋友这句话用在我身上实在太合适不过了 唯一的朋友因为我的胖也走了更不要说男朋友了好嘛 男的看见了都怕hahaha我想应该是这样吧\n瘦下来之后会尝试各种风格 可爱 高冷 气质\n', '2018-03-19 21:13:20', 7, NULL, 1, 10, 1, 1, '/9c8c197b-354d-4200-963d-44c92beb3543@info.jpg', 0),
	(38, '健身助力！安全酵素', '橘色分解酵母范冰冰也在吃的：主要是针对分解糖分！适合饭量大 爱吃主食甜品等的朋友。想吃还怕发胖的朋友。减肥部门2连冠\n大家对酵素不要有太高的期望。恨不得吃一盒能瘦十几斤？其实任何一个安全减肥产品都没有神速瘦！而且也不是每一个人吃了都可以瘦下来。\n或许有的人可以瘦几斤不等 也许有的人一点效果没有。都很正常\n我个人吃酵素的目的就是控制现有的体重不要持续发胖 减少大吃大喝以后的罪恶感。它们都是通过减少你摄入糖分 脂肪 卡路里的原理 让你慢慢的瘦下来。\n其实两个可以一起吃。比如早上吃黑姜的 晚上吃分解酵母。都可以的\n', '2018-03-19 21:17:46', 7, NULL, 1, 10, 6, 2, '/f54e6ca9-35bf-406e-8ed1-28b3d4f300a6@info.jpg', 0),
	(39, 'tf20', 'tf20', '2018-03-23 20:09:07', 3, NULL, 0, 8, 0, 0, NULL, 0),
	(40, 'tf20', 'tf20', '2018-03-23 20:09:07', 1, NULL, 0, 8, 0, 0, NULL, 0),
	(41, 'tf20', 'tf20', '2018-03-23 20:09:07', 1, NULL, 0, 8, 0, 0, NULL, 0),
	(42, 'tf20', 'tf20', '2018-03-23 20:09:07', 1, NULL, 0, 8, 0, 0, NULL, 0),
	(43, 'tf', 'love tfffff', '2018-03-23 20:10:34', 1, NULL, 99, 9, 0, 0, '/3adbe356-deaf-4b4e-bbf0-4cc7d884f529@info.jpg', 0),
	(44, 'tf20', 'tf disco dust', '2018-03-23 20:12:51', 1, NULL, 1, 9, 0, 0, '/dd5158e2-d928-43b0-a156-bffa8db581d1@info.jpg', 0),
	(45, '测试top', '我是测试top6', '2018-04-22 16:53:56', 2, NULL, 1, 8, 0, 0, '/fe05b5d9-601d-4027-99e6-dd562637f4af@info.jpg', 0);
/*!40000 ALTER TABLE `z_j_info` ENABLE KEYS */;

-- 导出  表 sun.z_j_liketype 结构
CREATE TABLE IF NOT EXISTS `z_j_liketype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL DEFAULT '0' COMMENT '标签',
  `delFlag` int(2) NOT NULL DEFAULT '0' COMMENT '逻辑删除1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='喜欢标签';

-- 正在导出表  sun.z_j_liketype 的数据：~7 rows (大约)
/*!40000 ALTER TABLE `z_j_liketype` DISABLE KEYS */;
INSERT INTO `z_j_liketype` (`id`, `type`, `delFlag`) VALUES
	(1, '护肤', 0),
	(2, '彩妆', 0),
	(3, '眼影', 0),
	(4, '面霜', 0),
	(5, '美食', 0),
	(6, '饮品', 0),
	(7, '减肥', 0);
/*!40000 ALTER TABLE `z_j_liketype` ENABLE KEYS */;

-- 导出  表 sun.z_j_users 结构
CREATE TABLE IF NOT EXISTS `z_j_users` (
  `userId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `loginName` varchar(50) DEFAULT NULL COMMENT '登录帐号',
  `loginKey` varchar(50) DEFAULT NULL COMMENT '登录密码',
  `isType` int(1) unsigned DEFAULT '0' COMMENT '管理员1/普通用户0',
  `email` varchar(200) DEFAULT NULL COMMENT '用户邮箱',
  `nickName` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `age` int(3) unsigned DEFAULT '0' COMMENT '用户年龄',
  `headIcon` varchar(200) DEFAULT NULL COMMENT '头像路径',
  `likeType` varchar(50) DEFAULT NULL COMMENT '用户喜好(用‘，’分隔)',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 正在导出表  sun.z_j_users 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `z_j_users` DISABLE KEYS */;
INSERT INTO `z_j_users` (`userId`, `loginName`, `loginKey`, `isType`, `email`, `nickName`, `age`, `headIcon`, `likeType`) VALUES
	(2, '1', '1', 1, '1@qq.com', '超级管理员', 18, '/469ad234-3d0f-4a5e-9e6b-ff8e55260a2d@headIcon.jpg', NULL),
	(8, 'test1', 'test1', 0, '1@qq.com', '大风车-.-！', 2, '/ef442881-2bd9-4d25-84f1-595f2cdd202f@headIcon.jpg', '1,2,6,3'),
	(9, 'test2', 'test2', 0, 'q@qq.com', '小太阳', 1, '/f1abe50c-0378-42c7-aa3b-3ba932a24a31@headIcon.jpg', NULL),
	(10, 'test3', 'test3', 0, '1@qq.com', '我是谁?我在哪？', 1, '/9c17bf2d-9427-4f11-bc40-6f513f3d69b2@headIcon.jpg', NULL),
	(11, 'test4', 'test4', 0, '1@q.com', 'yolo', 1, '/6e9b0c80-152f-470b-bed3-7b1351070b21@headIcon.jpg', NULL);
/*!40000 ALTER TABLE `z_j_users` ENABLE KEYS */;

-- 导出  触发器 sun.comment_add 结构
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `comment_add` AFTER INSERT ON `z_j_comment` FOR EACH ROW begin
update z_j_info set comment = comment + 1 where infoId = new.infoId;
end//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 导出  触发器 sun.comment_del 结构
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `comment_del` AFTER DELETE ON `z_j_comment` FOR EACH ROW begin
update z_j_info set comment = comment - 1 where infoId = old.infoId;
end//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 导出  触发器 sun.great_add 结构
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `great_add` AFTER INSERT ON `z_j_great` FOR EACH ROW begin
update z_j_info set great = great + 1 where infoId = new.infoId;
end//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 导出  触发器 sun.great_del 结构
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `great_del` AFTER DELETE ON `z_j_great` FOR EACH ROW begin
update z_j_info set great = great - 1 where infoId = old.infoId;
end//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
