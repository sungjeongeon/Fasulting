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
    `seq` bigint(20) AUTO_INCREMENT NOT NULL,
    `reservation_seq` bigint(20) NOT NULL,
    `ps_seq` bigint(20) NOT NULL,
    `user_seq` bigint(20) NOT NULL,
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`seq`),
    KEY `FK_reservation_TO_consulting_1` (`reservation_seq`),
    KEY `FK_ps_TO_consulting_1` (`ps_seq`),
    KEY `FK_user_TO_consulting_1` (`user_seq`),
    CONSTRAINT `FK_ps_TO_consulting_1` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`),
    CONSTRAINT `FK_reservation_TO_consulting_1` FOREIGN KEY (`reservation_seq`) REFERENCES `reservation` (`seq`),
    CONSTRAINT `FK_user_TO_consulting_1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.default_operating 구조 내보내기
CREATE TABLE IF NOT EXISTS `default_operating` (
    `seq` bigint(20) AUTO_INCREMENT NOT NULL,
    `ps_seq` bigint(20) NOT NULL,
    `day_of_week` int(11) NOT NULL COMMENT '1 : 월\r\n2 : 화\r\n3 : 수\r\n4 : 목\r\n5 : 금\r\n6 : 토\r\n7 : 일',
    `am_start` int(11) NOT NULL COMMENT '1 ~ 48 로 표현(0시부터 24시까지 30분 단위)',
    `am_end` int(11) NOT NULL COMMENT '1 ~ 48 로 표현(0시부터 24시까지 30분 단위)',
    `pm_start` int(11) NOT NULL COMMENT '1 ~ 48 로 표현(0시부터 24시까지 30분 단위)',
    `pm_end` int(11) NOT NULL COMMENT '1 ~ 48 로 표현(0시부터 24시까지 30분 단위)',
    `off_yn` varchar(1) NOT NULL DEFAULT 'N',
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`seq`),
    KEY `FK_ps_TO_default_operating_1` (`ps_seq`),
    CONSTRAINT `FK_ps_TO_default_operating_1` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.doctor 구조 내보내기
CREATE TABLE IF NOT EXISTS `doctor` (
    `seq` bigint(20) AUTO_INCREMENT NOT NULL,
    `ps_seq` bigint(20) NOT NULL,
    `name` varchar(255) NOT NULL,
    `img` varchar(255) DEFAULT NULL COMMENT '전문의 사진 경로',
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`seq`),
    KEY `FK_ps_TO_doctor_1` (`ps_seq`),
    CONSTRAINT `FK_ps_TO_doctor_1` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.doctor_main 구조 내보내기
CREATE TABLE IF NOT EXISTS `doctor_main` (
    `doctor_seq` bigint(20) AUTO_INCREMENT NOT NULL,
    `main_seq` bigint(20) NOT NULL,
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`doctor_seq`,`main_seq`),
    KEY `FK_main_category_TO_doctor_main_1` (`main_seq`),
    CONSTRAINT `FK_doctor_TO_doctor_main_1` FOREIGN KEY (`doctor_seq`) REFERENCES `doctor` (`seq`),
    CONSTRAINT `FK_main_category_TO_doctor_main_1` FOREIGN KEY (`main_seq`) REFERENCES `main_category` (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.favorite 구조 내보내기
CREATE TABLE IF NOT EXISTS `favorite` (
    `seq` bigint(20) AUTO_INCREMENT NOT NULL,
    `ps_seq` bigint(20) NOT NULL,
    `user_seq` bigint(20) NOT NULL,
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`seq`),
    KEY `FK_ps_TO_favorite_1` (`ps_seq`),
    KEY `FK_user_TO_favorite_1` (`user_seq`),
    CONSTRAINT `FK_ps_TO_favorite_1` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`),
    CONSTRAINT `FK_user_TO_favorite_1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.main_category 구조 내보내기
CREATE TABLE IF NOT EXISTS `main_category` (
    `seq` bigint(20) AUTO_INCREMENT NOT NULL,
    `name` varchar(255) NOT NULL COMMENT '눈, 코, 입술, 안면윤곽, 보톡스/필러, 모발이식, 피부, 리프팅',
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.operating 구조 내보내기
CREATE TABLE IF NOT EXISTS `operating` (
    `seq` bigint(20) AUTO_INCREMENT NOT NULL,
    `ps_seq` bigint(20) NOT NULL,
    `year` int(11) NOT NULL,
    `month` int(11) NOT NULL,
    `day` int(11) NOT NULL,
    `day_of_week` int(11) NOT NULL COMMENT '1 : 월\r\n2 : 화\r\n3 : 수\r\n4 : 목\r\n5 : 금\r\n6 : 토\r\n7 : 일',
    `hour` int(11) NOT NULL COMMENT '0 ~ 24 시간, 30분 단위 1 ~ 48',
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`seq`),
    KEY `FK_ps_TO_operating_1` (`ps_seq`),
    CONSTRAINT `FK_ps_TO_operating_1` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.ps 구조 내보내기
CREATE TABLE IF NOT EXISTS `ps` (
    `seq` bigint(20) AUTO_INCREMENT NOT NULL,
    `email` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    `name` varchar(255) NOT NULL,
    `address` varchar(255) NOT NULL,
    `zipcode` varchar(255) NOT NULL,
    `registration` varchar(255) NOT NULL,
    `registration_img` varchar(255) NOT NULL COMMENT '서버에 저장한 이미지 경로',
    `number` varchar(255) NOT NULL,
    `director` varchar(255) NOT NULL,
    `homepage` varchar(255) DEFAULT NULL,
    `profile_img` varchar(255) DEFAULT NULL COMMENT '서버에 저장할 병원 프로필 사진 경로',
    `intro` varchar(255) DEFAULT NULL,
    `confirm_yn` varchar(1) NOT NULL DEFAULT 'N' COMMENT '가입 승인 여부에 따른 Y / N',
    `confirm_date` datetime DEFAULT NULL,
    `confirm_by` varchar(255) DEFAULT NULL,
    `del_date` datetime DEFAULT NULL,
    `del_by` varchar(255) DEFAULT NULL,
    `del_yn` varchar(1) NOT NULL DEFAULT 'N' COMMENT '병원 계정의 삭제는 탈퇴 기능',
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.ps_main 구조 내보내기
CREATE TABLE IF NOT EXISTS `ps_main` (
    `ps_seq` bigint(20) NOT NULL,
    `main_seq` bigint(20) NOT NULL,
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`ps_seq`,`main_seq`),
    KEY `FK_main_category_TO_ps_main_1` (`main_seq`),
    CONSTRAINT `FK_main_category_TO_ps_main_1` FOREIGN KEY (`main_seq`) REFERENCES `main_category` (`seq`),
    CONSTRAINT `FK_ps_TO_ps_main_1` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.ps_main_sub 구조 내보내기
CREATE TABLE IF NOT EXISTS `ps_main_sub` (
    `ps_seq` bigint(20) NOT NULL,
    `main_seq` bigint(20) NOT NULL,
    `sub_seq` bigint(20) NOT NULL,
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`ps_seq`,`main_seq`,`sub_seq`),
    KEY `FK_ps_main_TO_ps_main_sub_2` (`main_seq`),
    KEY `FK_sub_category_TO_ps_main_sub_1` (`sub_seq`),
    CONSTRAINT `FK_ps_main_TO_ps_main_sub_1` FOREIGN KEY (`ps_seq`) REFERENCES `ps_main` (`ps_seq`),
    CONSTRAINT `FK_ps_main_TO_ps_main_sub_2` FOREIGN KEY (`main_seq`) REFERENCES `ps_main` (`main_seq`),
    CONSTRAINT `FK_sub_category_TO_ps_main_sub_1` FOREIGN KEY (`sub_seq`) REFERENCES `sub_category` (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.ps_token 구조 내보내기
CREATE TABLE IF NOT EXISTS `ps_token` (
    `ps_seq` bigint(20) NOT NULL,
    `token_seq` bigint(20) NOT NULL,
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`ps_seq`,`token_seq`),
    KEY `FK_token_TO_ps_token_1` (`token_seq`),
    CONSTRAINT `FK_ps_TO_ps_token_1` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`),
    CONSTRAINT `FK_token_TO_ps_token_1` FOREIGN KEY (`token_seq`) REFERENCES `token` (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.rating_hist 구조 내보내기
CREATE TABLE IF NOT EXISTS `rating_hist` (
    `seq` bigint(20) AUTO_INCREMENT NOT NULL,
    `consulting_seq` bigint(20) NOT NULL,
    `ps_seq` bigint(20) NOT NULL,
    `user_seq` bigint(20) NOT NULL,
    `point` decimal(10,0) NOT NULL COMMENT '최초 평가 후 평점 적용\r\n평가마다 평점 업데이트',
    `del_date` datetime DEFAULT NULL,
    `del_by` varchar(255) DEFAULT NULL,
    `del_yn` varchar(1) NOT NULL DEFAULT 'N',
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`seq`),
    KEY `FK_consulting_TO_rating_hist_1` (`consulting_seq`),
    KEY `FK_ps_TO_rating_hist_1` (`ps_seq`),
    KEY `FK_user_TO_rating_hist_1` (`user_seq`),
    CONSTRAINT `FK_consulting_TO_rating_hist_1` FOREIGN KEY (`consulting_seq`) REFERENCES `consulting` (`seq`),
    CONSTRAINT `FK_ps_TO_rating_hist_1` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`),
    CONSTRAINT `FK_user_TO_rating_hist_1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.report 구조 내보내기
CREATE TABLE IF NOT EXISTS `report` (
    `seq` bigint(20) AUTO_INCREMENT NOT NULL,
    `consulting_seq` bigint(20) NOT NULL,
    `before_img` varchar(255) NOT NULL COMMENT '서버에 저장된 비포 사진 경로',
    `after_img` varchar(255) NOT NULL COMMENT '서버에 저장된 애프터 사진 경로',
    `content` varchar(255) NOT NULL COMMENT '소견 작성 내용',
    `estimate` varchar(255) NOT NULL COMMENT '예상 견적 비용',
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`seq`),
    KEY `FK_consulting_TO_report_1` (`consulting_seq`),
    CONSTRAINT `FK_consulting_TO_report_1` FOREIGN KEY (`consulting_seq`) REFERENCES `consulting` (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.reservation 구조 내보내기
CREATE TABLE IF NOT EXISTS `reservation` (
    `seq` bigint(20) AUTO_INCREMENT NOT NULL,
    `reservation_seq` bigint(20) NOT NULL,
    `ps_seq` bigint(20) NOT NULL,
    `user_seq` bigint(20) NOT NULL,
    `del_date` datetime DEFAULT NULL,
    `del_by` varchar(255) DEFAULT NULL,
    `del_yn` varchar(1) NOT NULL DEFAULT 'N',
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`seq`),
    KEY `FK_reservation_cal_TO_reservation_1` (`reservation_seq`),
    KEY `FK_ps_TO_reservation_1` (`ps_seq`),
    KEY `FK_user_TO_reservation_1` (`user_seq`),
    CONSTRAINT `FK_ps_TO_reservation_1` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`),
    CONSTRAINT `FK_reservation_cal_TO_reservation_1` FOREIGN KEY (`reservation_seq`) REFERENCES `reservation_cal` (`seq`),
    CONSTRAINT `FK_user_TO_reservation_1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.reservation_cal 구조 내보내기
CREATE TABLE IF NOT EXISTS `reservation_cal` (
    `seq` bigint(20) AUTO_INCREMENT NOT NULL,
    `year` int(11) NOT NULL,
    `month` int(11) NOT NULL,
    `day` int(11) NOT NULL,
    `day_of_week` int(11) NOT NULL COMMENT '1 : 월\r\n2 : 화\r\n3 : 수\r\n4 : 목\r\n5 : 금\r\n6 : 토\r\n7 : 일',
    `hour` int(11) NOT NULL,
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.reservation_sub 구조 내보내기
CREATE TABLE IF NOT EXISTS `reservation_sub` (
    `reservation_seq` bigint(20) NOT NULL,
    `sub_seq` bigint(20) NOT NULL,
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`reservation_seq`,`sub_seq`),
    KEY `FK_sub_category_TO_reservation_sub_1` (`sub_seq`),
    CONSTRAINT `FK_reservation_TO_reservation_sub_1` FOREIGN KEY (`reservation_seq`) REFERENCES `reservation` (`seq`),
    CONSTRAINT `FK_sub_category_TO_reservation_sub_1` FOREIGN KEY (`sub_seq`) REFERENCES `sub_category` (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.review 구조 내보내기
CREATE TABLE IF NOT EXISTS `review` (
    `seq` bigint(20) AUTO_INCREMENT NOT NULL,
    `consulting_seq` bigint(20) NOT NULL,
    `rating_hist_seq` bigint(20) NOT NULL,
    `ps_seq` bigint(20) NOT NULL,
    `user_seq` bigint(20) NOT NULL,
    `content` varchar(255) NOT NULL,
    `dec_date` datetime DEFAULT NULL,
    `dec_by` varchar(255) DEFAULT NULL,
    `dec_yn` varchar(1) NOT NULL DEFAULT 'N',
    `del_date` datetime DEFAULT NULL,
    `del_by` varchar(255) DEFAULT NULL,
    `del_yn` varchar(1) NOT NULL DEFAULT 'N',
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`seq`),
    KEY `FK_consulting_TO_review_1` (`consulting_seq`),
    KEY `FK_rating_hist_TO_review_1` (`rating_hist_seq`),
    KEY `FK_ps_TO_review_1` (`ps_seq`),
    KEY `FK_user_TO_review_1` (`user_seq`),
    CONSTRAINT `FK_consulting_TO_review_1` FOREIGN KEY (`consulting_seq`) REFERENCES `consulting` (`seq`),
    CONSTRAINT `FK_ps_TO_review_1` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`),
    CONSTRAINT `FK_rating_hist_TO_review_1` FOREIGN KEY (`rating_hist_seq`) REFERENCES `rating_hist` (`seq`),
    CONSTRAINT `FK_user_TO_review_1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.review_sub 구조 내보내기
CREATE TABLE IF NOT EXISTS `review_sub` (
    `review_seq` bigint(20) NOT NULL,
    `sub_seq` bigint(20) NOT NULL,
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`review_seq`,`sub_seq`),
    KEY `FK_sub_category_TO_review_sub_1` (`sub_seq`),
    CONSTRAINT `FK_review_TO_review_sub_1` FOREIGN KEY (`review_seq`) REFERENCES `review` (`seq`),
    CONSTRAINT `FK_sub_category_TO_review_sub_1` FOREIGN KEY (`sub_seq`) REFERENCES `sub_category` (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.role 구조 내보내기
CREATE TABLE IF NOT EXISTS `role` (
    `user_seq` bigint(20) NOT NULL,
    `authority` varchar(255) NOT NULL COMMENT 'admin or user',
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`user_seq`),
    CONSTRAINT `FK_user_TO_role_1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.sub_category 구조 내보내기
CREATE TABLE IF NOT EXISTS `sub_category` (
    `seq` bigint(20) AUTO_INCREMENT NOT NULL,
    `main_seq` bigint(20) NOT NULL,
    `name` varchar(255) NOT NULL COMMENT '- 쌍커풀, 눈매교정, 트임, 눈밑성형, 재수술\r\n- 콧대, 코끝 성형, 콧불 축소, 재수술\r\n- 입꼬리, 입술축소술, 입술확대수술\r\n- 사각턱, 광대, 턱 끝, 이마, 양약, 재수술\r\n- 절개, 비절개\r\n- 여드름 치료, 화상치료, 흉터 치료',
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`seq`),
    KEY `FK_main_category_TO_sub_category_1` (`main_seq`),
    CONSTRAINT `FK_main_category_TO_sub_category_1` FOREIGN KEY (`main_seq`) REFERENCES `main_category` (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.token 구조 내보내기
CREATE TABLE IF NOT EXISTS `token` (
    `seq` bigint(20) AUTO_INCREMENT NOT NULL,
    `refresh_token` varchar(255) NOT NULL,
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.total_rating 구조 내보내기
CREATE TABLE IF NOT EXISTS `total_rating` (
    `seq` bigint(20) AUTO_INCREMENT NOT NULL,
    `ps_seq` bigint(20) NOT NULL,
    `result` decimal(10,0) DEFAULT NULL,
    `sum` decimal(10,0) NOT NULL DEFAULT 0,
    `count` int(11) NOT NULL DEFAULT 0,
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`seq`),
    KEY `FK_ps_TO_total_rating_1` (`ps_seq`),
    CONSTRAINT `FK_ps_TO_total_rating_1` FOREIGN KEY (`ps_seq`) REFERENCES `ps` (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
    `seq` bigint(20) AUTO_INCREMENT NOT NULL,
    `email` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    `birth` VARCHAR(255) NOT NULL,
    `gender` varchar(1) NOT NULL COMMENT 'M or F',
    `number` varchar(255) NOT NULL,
    `nation` varchar(255) NOT NULL,
    `nation_code` varchar(255) NOT NULL,
    `name` varchar(255) NOT NULL,
    `del_date` datetime DEFAULT NULL,
    `del_by` varchar(255) DEFAULT NULL,
    `del_yn` varchar(1) NOT NULL DEFAULT 'N',
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 hotsix.user_token 구조 내보내기
CREATE TABLE IF NOT EXISTS `user_token` (
    `user_seq` bigint(20) NOT NULL,
    `token_seq` bigint(20) NOT NULL,
    `mod_date` datetime(6) DEFAULT NULL,
    `mod_by` varchar(255) DEFAULT NULL,
    `reg_by` varchar(255) DEFAULT NULL,
    `reg_date` datetime(6) DEFAULT NULL,
    PRIMARY KEY (`user_seq`,`token_seq`),
    KEY `FK_token_TO_user_token_1` (`token_seq`),
    CONSTRAINT `FK_token_TO_user_token_1` FOREIGN KEY (`token_seq`) REFERENCES `token` (`seq`),
    CONSTRAINT `FK_user_TO_user_token_1` FOREIGN KEY (`user_seq`) REFERENCES `user` (`seq`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
