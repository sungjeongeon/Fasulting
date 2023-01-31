-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.6.11-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- hotsix 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `hotsix` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci */;
USE `hotsix`;

-- 테이블 hotsix.consulting 구조 내보내기
CREATE TABLE IF NOT EXISTS `consulting` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `ps_seq` bigint(20) DEFAULT NULL,
  `reservation_seq` bigint(20) DEFAULT NULL,
  `user_seq` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`seq`),
  KEY `FKsffd0qt16rx6j1ljd7hiu3kia` (`ps_seq`),
  KEY `FK2rrve9ocolp5g5jicqprlxy8` (`reservation_seq`),
  KEY `FKtbf0rnymyobxxr2u9k7uf2d1l` (`user_seq`),
  CONSTRAINT `FK2rrve9ocolp5g5jicqprlxy8` FOREIGN KEY (`reservation_seq`) REFERENCES `reservation` (`seq`),
  CONSTRAINT `FKsffd0qt16rx6j1ljd7hiu3kia` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`),
  CONSTRAINT `FKtbf0rnymyobxxr2u9k7uf2d1l` FOREIGN KEY (`user_seq`) REFERENCES `user` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.default_cal 구조 내보내기
CREATE TABLE IF NOT EXISTS `default_cal` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `day_of_week` int(11) DEFAULT NULL,
  `off_yn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.doctor 구조 내보내기
CREATE TABLE IF NOT EXISTS `doctor` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `ps_seq` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`seq`),
  KEY `FKa8qmiu5inooc8kwkx82rjeoc5` (`ps_seq`),
  CONSTRAINT `FKa8qmiu5inooc8kwkx82rjeoc5` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.doctor_main 구조 내보내기
CREATE TABLE IF NOT EXISTS `doctor_main` (
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `main_seq` bigint(20) NOT NULL,
  `doctor_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`doctor_seq`,`main_seq`),
  KEY `FKfpngg0jxi0jt641sw6uy76twf` (`main_seq`),
  CONSTRAINT `FK2hoxinh1hlba6eyb69h8aeu3y` FOREIGN KEY (`doctor_seq`) REFERENCES `doctor` (`seq`),
  CONSTRAINT `FKfpngg0jxi0jt641sw6uy76twf` FOREIGN KEY (`main_seq`) REFERENCES `main_category` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.favorite 구조 내보내기
CREATE TABLE IF NOT EXISTS `favorite` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `ps_seq` bigint(20) DEFAULT NULL,
  `user_seq` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`seq`),
  KEY `FKn5p677c04a9qg8m7pqhh5pn01` (`ps_seq`),
  KEY `FKo1rtd34i3gw9qnkpwr3or6q8m` (`user_seq`),
  CONSTRAINT `FKn5p677c04a9qg8m7pqhh5pn01` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`),
  CONSTRAINT `FKo1rtd34i3gw9qnkpwr3or6q8m` FOREIGN KEY (`user_seq`) REFERENCES `user` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.main_category 구조 내보내기
CREATE TABLE IF NOT EXISTS `main_category` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.operating_cal 구조 내보내기
CREATE TABLE IF NOT EXISTS `operating_cal` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `day_of_week` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.ps 구조 내보내기
CREATE TABLE IF NOT EXISTS `ps` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `confirm_by` varchar(255) DEFAULT NULL,
  `confirm_date` datetime(6) DEFAULT NULL,
  `confirm_yn` varchar(255) DEFAULT NULL,
  `del_by` varchar(255) DEFAULT NULL,
  `del_date` datetime(6) DEFAULT NULL,
  `del_yn` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `homepage` varchar(255) DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile_img` varchar(255) DEFAULT NULL,
  `registration` varchar(255) DEFAULT NULL,
  `registration_img` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.ps_default 구조 내보내기
CREATE TABLE IF NOT EXISTS `ps_default` (
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `time_seq` bigint(20) NOT NULL,
  `cal_seq` bigint(20) NOT NULL,
  `ps_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`cal_seq`,`ps_seq`,`time_seq`),
  KEY `FKk9xwy3bsynoywulnimnt6idhn` (`time_seq`),
  KEY `FKinnr1wu4h9pt1bp1rsm3sfp0c` (`ps_seq`),
  CONSTRAINT `FKinnr1wu4h9pt1bp1rsm3sfp0c` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`),
  CONSTRAINT `FKjgd5i9ihl1tcv80sv06527ys1` FOREIGN KEY (`cal_seq`) REFERENCES `default_cal` (`seq`),
  CONSTRAINT `FKk9xwy3bsynoywulnimnt6idhn` FOREIGN KEY (`time_seq`) REFERENCES `time` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.ps_main 구조 내보내기
CREATE TABLE IF NOT EXISTS `ps_main` (
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `main_seq` bigint(20) NOT NULL,
  `ps_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`main_seq`,`ps_seq`),
  KEY `FKm6u15rhgyww0u27c7buvhx34k` (`ps_seq`),
  CONSTRAINT `FKjet9bmb0sojlo08f6blv28j0y` FOREIGN KEY (`main_seq`) REFERENCES `main_category` (`seq`),
  CONSTRAINT `FKm6u15rhgyww0u27c7buvhx34k` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.ps_main_sub 구조 내보내기
CREATE TABLE IF NOT EXISTS `ps_main_sub` (
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `main_seq` bigint(20) NOT NULL,
  `sub_seq` bigint(20) NOT NULL,
  `ps_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`main_seq`,`ps_seq`,`sub_seq`),
  KEY `FK323j99dgr6cborvbd1mm1eif7` (`sub_seq`),
  KEY `FKmjampohmjwuf0i2479pxe871w` (`ps_seq`),
  CONSTRAINT `FK323j99dgr6cborvbd1mm1eif7` FOREIGN KEY (`sub_seq`) REFERENCES `sub_category` (`seq`),
  CONSTRAINT `FKdc8anf8h6wwq6mpeu050umhgn` FOREIGN KEY (`main_seq`) REFERENCES `main_category` (`seq`),
  CONSTRAINT `FKmjampohmjwuf0i2479pxe871w` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.ps_operating 구조 내보내기
CREATE TABLE IF NOT EXISTS `ps_operating` (
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `time_seq` bigint(20) NOT NULL,
  `cal_seq` bigint(20) NOT NULL,
  `ps_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`cal_seq`,`ps_seq`,`time_seq`),
  KEY `FKnykdlmxm9lem3oqj2yiayhu7x` (`time_seq`),
  KEY `FKr0cshmxwwsehh6vp4sv1yvo5j` (`ps_seq`),
  CONSTRAINT `FK4u9q7t29yqpe7c9q2uco0wg2w` FOREIGN KEY (`cal_seq`) REFERENCES `operating_cal` (`seq`),
  CONSTRAINT `FKnykdlmxm9lem3oqj2yiayhu7x` FOREIGN KEY (`time_seq`) REFERENCES `time` (`seq`),
  CONSTRAINT `FKr0cshmxwwsehh6vp4sv1yvo5j` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.ps_token 구조 내보내기
CREATE TABLE IF NOT EXISTS `ps_token` (
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `token_seq` bigint(20) NOT NULL,
  `ps_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`token_seq`,`ps_seq`),
  UNIQUE KEY `UK_375qteky1wxc60n556v2g6jjq` (`token_seq`),
  UNIQUE KEY `UK_cnp194x4nwqh88xfrhmtyyk65` (`ps_seq`),
  CONSTRAINT `FKbkk7psnnifhgayjgipka92npb` FOREIGN KEY (`token_seq`) REFERENCES `token` (`seq`),
  CONSTRAINT `FKg80igh4rfx38qmg91b0rku7k7` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.rating_hist 구조 내보내기
CREATE TABLE IF NOT EXISTS `rating_hist` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `del_by` varchar(255) DEFAULT NULL,
  `del_date` datetime(6) DEFAULT NULL,
  `del_yn` varchar(255) DEFAULT NULL,
  `point` decimal(19,2) DEFAULT NULL,
  `consulting_seq` bigint(20) DEFAULT NULL,
  `ps_seq` bigint(20) DEFAULT NULL,
  `user_seq` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`seq`),
  KEY `FK7sopgi0u92dv4hvwy8ffkx4t6` (`consulting_seq`),
  KEY `FKa4wogj4ooiqcys2jpdj858e9h` (`ps_seq`),
  KEY `FKdg2w5xeala6nbwhmnjod139qj` (`user_seq`),
  CONSTRAINT `FK7sopgi0u92dv4hvwy8ffkx4t6` FOREIGN KEY (`consulting_seq`) REFERENCES `consulting` (`seq`),
  CONSTRAINT `FKa4wogj4ooiqcys2jpdj858e9h` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`),
  CONSTRAINT `FKdg2w5xeala6nbwhmnjod139qj` FOREIGN KEY (`user_seq`) REFERENCES `user` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.report 구조 내보내기
CREATE TABLE IF NOT EXISTS `report` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `after_img` varchar(255) DEFAULT NULL,
  `before_img` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `estimate` varchar(255) DEFAULT NULL,
  `consulting_seq` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`seq`),
  KEY `FKc0snr0i9ql52jy3w9llb7in20` (`consulting_seq`),
  CONSTRAINT `FKc0snr0i9ql52jy3w9llb7in20` FOREIGN KEY (`consulting_seq`) REFERENCES `consulting` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.reservation 구조 내보내기
CREATE TABLE IF NOT EXISTS `reservation` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `del_by` varchar(255) DEFAULT NULL,
  `del_date` datetime(6) DEFAULT NULL,
  `del_yn` varchar(255) DEFAULT NULL,
  `ps_seq` bigint(20) DEFAULT NULL,
  `cal_seq` bigint(20) DEFAULT NULL,
  `time_seq` bigint(20) DEFAULT NULL,
  `user_seq` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`seq`),
  KEY `FK8cvrhshmwh6e6k9hk60s0bifl` (`ps_seq`),
  KEY `FK4hdvn08v2wacmix1f83ckoddj` (`cal_seq`),
  KEY `FK2f3mfbvfiqfq7re27ivoxjawc` (`time_seq`),
  KEY `FKfm417muc4fl0oqcsr8tdcnh1y` (`user_seq`),
  CONSTRAINT `FK2f3mfbvfiqfq7re27ivoxjawc` FOREIGN KEY (`time_seq`) REFERENCES `time` (`seq`),
  CONSTRAINT `FK4hdvn08v2wacmix1f83ckoddj` FOREIGN KEY (`cal_seq`) REFERENCES `reservation_cal` (`seq`),
  CONSTRAINT `FK8cvrhshmwh6e6k9hk60s0bifl` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`),
  CONSTRAINT `FKfm417muc4fl0oqcsr8tdcnh1y` FOREIGN KEY (`user_seq`) REFERENCES `user` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.reservation_cal 구조 내보내기
CREATE TABLE IF NOT EXISTS `reservation_cal` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `day_of_week` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.reservation_sub 구조 내보내기
CREATE TABLE IF NOT EXISTS `reservation_sub` (
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `resrvation_seq` bigint(20) NOT NULL,
  `sub_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`sub_seq`,`resrvation_seq`),
  KEY `FKcjh7uv7pwbkkdmddetd8oaxbx` (`resrvation_seq`),
  CONSTRAINT `FKcjh7uv7pwbkkdmddetd8oaxbx` FOREIGN KEY (`resrvation_seq`) REFERENCES `reservation` (`seq`),
  CONSTRAINT `FKjg7gt5l3eqg2yl5x95mwvmkid` FOREIGN KEY (`sub_seq`) REFERENCES `sub_category` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.review 구조 내보내기
CREATE TABLE IF NOT EXISTS `review` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `dec_by` varchar(255) DEFAULT NULL,
  `dec_date` datetime(6) DEFAULT NULL,
  `dec_yn` varchar(255) DEFAULT NULL,
  `del_by` varchar(255) DEFAULT NULL,
  `del_date` datetime(6) DEFAULT NULL,
  `del_yn` varchar(255) DEFAULT NULL,
  `consulting_seq` bigint(20) DEFAULT NULL,
  `ps_seq` bigint(20) DEFAULT NULL,
  `rating_hist_seq` bigint(20) DEFAULT NULL,
  `user_seq` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`seq`),
  KEY `FKbbdrmxovrqah2uemhg4ao3jba` (`consulting_seq`),
  KEY `FKg66ro2800w9iky7wigkvihcpp` (`ps_seq`),
  KEY `FKgohii7svjkdf5a6rct76ioidw` (`rating_hist_seq`),
  KEY `FKq7vee7vmlqrhvnflflsw6p1q7` (`user_seq`),
  CONSTRAINT `FKbbdrmxovrqah2uemhg4ao3jba` FOREIGN KEY (`consulting_seq`) REFERENCES `consulting` (`seq`),
  CONSTRAINT `FKg66ro2800w9iky7wigkvihcpp` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`),
  CONSTRAINT `FKgohii7svjkdf5a6rct76ioidw` FOREIGN KEY (`rating_hist_seq`) REFERENCES `rating_hist` (`seq`),
  CONSTRAINT `FKq7vee7vmlqrhvnflflsw6p1q7` FOREIGN KEY (`user_seq`) REFERENCES `user` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.review_sub 구조 내보내기
CREATE TABLE IF NOT EXISTS `review_sub` (
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `review_seq` bigint(20) NOT NULL,
  `sub_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`sub_seq`,`review_seq`),
  KEY `FK4ibkrjqk3hf0jpuwkqliyauc0` (`review_seq`),
  CONSTRAINT `FK4ibkrjqk3hf0jpuwkqliyauc0` FOREIGN KEY (`review_seq`) REFERENCES `review` (`seq`),
  CONSTRAINT `FKcv3jturel5yaa804l2dx3uhqb` FOREIGN KEY (`sub_seq`) REFERENCES `sub_category` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.role 구조 내보내기
CREATE TABLE IF NOT EXISTS `role` (
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `user_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`user_seq`),
  CONSTRAINT `FKexnaj3rkopn0xwrp40t8np3j2` FOREIGN KEY (`user_seq`) REFERENCES `user` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.sub_category 구조 내보내기
CREATE TABLE IF NOT EXISTS `sub_category` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `main_seq` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`seq`),
  KEY `FKqus487tlgic3ksp6jfgnr27ry` (`main_seq`),
  CONSTRAINT `FKqus487tlgic3ksp6jfgnr27ry` FOREIGN KEY (`main_seq`) REFERENCES `main_category` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.time 구조 내보내기
CREATE TABLE IF NOT EXISTS `time` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `end_hour` int(11) DEFAULT NULL,
  `end_min` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `start_hour` int(11) DEFAULT NULL,
  `start_min` int(11) DEFAULT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.token 구조 내보내기
CREATE TABLE IF NOT EXISTS `token` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.total_rating 구조 내보내기
CREATE TABLE IF NOT EXISTS `total_rating` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `result` decimal(19,2) DEFAULT NULL,
  `sum` decimal(19,2) DEFAULT NULL,
  `ps_seq` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`seq`),
  KEY `FKqdff2df2cxmbqjw21qnedj5e7` (`ps_seq`),
  CONSTRAINT `FKqdff2df2cxmbqjw21qnedj5e7` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `birth` varchar(255) DEFAULT NULL,
  `del_by` varchar(255) DEFAULT NULL,
  `del_date` datetime(6) DEFAULT NULL,
  `del_yn` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL,
  `nation_code` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.user_token 구조 내보내기
CREATE TABLE IF NOT EXISTS `user_token` (
  `mod_date` datetime(6) DEFAULT NULL,
  `mod_by` varchar(255) DEFAULT NULL,
  `reg_by` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `token_seq` bigint(20) NOT NULL,
  `user_seq` bigint(20) NOT NULL,
  PRIMARY KEY (`user_seq`,`token_seq`),
  UNIQUE KEY `UK_7db7awfed02he0y3qxv8qopxh` (`token_seq`),
  UNIQUE KEY `UK_gvtp2tc3wglm7s2k8jucrqqx5` (`user_seq`),
  CONSTRAINT `FK85c8tok2bx1oeuv3qvgkkoshi` FOREIGN KEY (`user_seq`) REFERENCES `user` (`seq`),
  CONSTRAINT `FK9w5kwhkfwnxny9o7tl3b09rjs` FOREIGN KEY (`token_seq`) REFERENCES `token` (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
