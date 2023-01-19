DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
	`user_id`	INT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
	`user_email`	VARCHAR(255)	NOT NULL,
	`user_password`	VARCHAR(255)	NOT NULL,
	`user_birth`	DATETIME	NOT NULL,
	`user_gender`	VARCHAR(255)	NOT NULL	COMMENT 'male / female',
	`user_phone`	VARCHAR(255)	NOT NULL,
	`user_validation`	VARCHAR(1)	NOT NULL	DEFAULT 'Y' COMMENT '회원 탈퇴 시 N',
	`user_regist_time`	DATETIME	NOT NULL	DEFAULT NOW(),
	`user_withdrawal_time`	DATETIME	NULL,
	`user_nation`	VARCHAR(255)	NOT NULL,
	`user_nation_code`	VARCHAR(255)	NOT NULL,
	`user_name`	VARCHAR(255)	NOT NULL
);

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
	`review_id`	int	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`review_content`	VARCHAR(255)	NOT NULL,
	`review_time`	DATETIME	NOT NULL	DEFAULT NOW(),
	`user_id`	int	NOT NULL,
	`ps_id`	int	NOT NULL,
	`review_isAccused`	VARCHAR(1)	NOT NULL	DEFAULT 'N'	COMMENT '신고된 리뷰일 경우 Y
신고안될 리뷰일 경우 N(기본값)'
);

DROP TABLE IF EXISTS `ps`;

CREATE TABLE `ps` (
	`ps_id`	INT	NULL	AUTO_INCREMENT PRIMARY KEY,
	`ps_email`	VARCHAR(255)	NOT NULL	COMMENT '병원 계정 아이디(이메일)',
	`ps_intro`	VARCHAR(255)	NULL,
	`ps_name`	VARCHAR(255)	NOT NULL,
	`ps_address`	VARCHAR(255)	NOT NULL,
	`ps_zipcode`	VARCHAR(255)	NOT NULL,
	`ps_registration`	VARCHAR(255)	NOT NULL,
	`ps_registration_img`	VARCHAR(255)	NOT NULL	COMMENT '서버에 저장한 이미지 경로',
	`ps_profile_img`	VARCHAR(255)	NULL	COMMENT '서버에 저장할 병원 프로필 사진 경로',
	`ps_number`	VARCHAR(255)	NOT NULL,
	`ps_director`	VARCHAR(255)	NOT NULL,
	`ps_validation`	VARCHAR(1)	NOT NULL	DEFAULT 'Y'	COMMENT '계정 탈퇴 여부에 따른 Y / N',
	`ps_homepage`	VARCHAR(255)	NULL,
	`main_kategory_id`	INT	NOT NULL	COMMENT 'auto increment',
	`sub_category_id`	INT	NOT NULL	COMMENT 'auto increment',
	`ps_regist_time`	DATETIME	NOT NULL	DEFAULT now(),
	`ps_withdrawal_time`	DATETIME	NULL,
	`ps_confirm`	VARCHAR(1)	NOT NULL	DEFAULT 'N'	COMMENT '가입 승인 여부에 따른 Y / N'
);

DROP TABLE IF EXISTS `favorites`;

CREATE TABLE `favorites` (
	`favorite_id`	int	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`user_id`	int	NOT NULL,
	`ps_id`	int	NOT NULL
);

DROP TABLE IF EXISTS `main_category`;

CREATE TABLE `main_category` (
	`main_category_id`	INT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
	`main_category_name`	VARCHAR(255)	NOT NULL	COMMENT '눈, 코, 입술, 안면윤곽, 보톡스/필러, 모발이식, 피부, 리프팅'
);

DROP TABLE IF EXISTS `sub_category`;

CREATE TABLE `sub_category` (
	`sub_category_id`	int	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
	`sub_category_name`	VARCHAR(255)	NOT NULL	COMMENT '- 쌍커풀, 눈매교정, 트임, 눈밑성형, 재수술
- 콧대, 코끝 성형, 콧불 축소, 재수술
- 입꼬리, 입술축소술, 입술확대수술
- 사각턱, 광대, 턱 끝, 이마, 양약, 재수술
- 절개, 비절개
- 여드름 치료, 화상치료, 흉터 치료',
	`main_category_id`	INT	NOT NULL	COMMENT 'AUTO INCREMENT'
);

DROP TABLE IF EXISTS `consulting`;

CREATE TABLE `consulting` (
	`consulting_id`	INT	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`ps_id`	INT	NOT NULL,
	`user_id`	INT	NOT NULL,
	`review_id`	INT	NOT NULL,
	`sub_category_id`	INT	NOT NULL	COMMENT 'auto increment',
	`consulting_confirm`	VARCHAR(1)	NOT NULL	DEFAULT 'N',
	`consulting_notification`	VARCHAR(1)	NULL	DEFAULT 'N'	COMMENT '확인 여부에 따라 Y/N'
);

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
	`rating_id`	int	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
	`rating_point`	NUMERIC	NULL	COMMENT '최초 평가 후 평점 적용
평가마다 평점 업데이트',
	`total_rating_id`	int	NOT NULL	COMMENT 'AUTO INCREMENT',
	`user_id`	int	NOT NULL,
	`consulting_id`	int	NOT NULL
);

DROP TABLE IF EXISTS `report`;

CREATE TABLE `report` (
	`report_id`	int	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
	`report_before_img`	VARCHAR(255)	NOT NULL	COMMENT '서버에 저장된 비포 사진 경로',
	`report_after_img`	VARCHAR(255)	NOT NULL	COMMENT '서버에 저장된 애프터 사진 경로',
	`report_content`	VARCHAR(255)	NOT NULL	COMMENT '소견 작성 내용',
	`report_estimate`	VARCHAR(255)	NOT NULL	COMMENT '예상 견적 비용',
	`report_regist_time`	DATETIME	NOT NULL	DEFAULT NOW(),
	`report_consulting_id`	int	NOT NULL
);

DROP TABLE IF EXISTS `doctor`;

CREATE TABLE `doctor` (
	`doctor_id`	INT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
	`doctor_name`	VARCHAR(255)	NOT NULL,
	`ps_id`	INT	NOT NULL,
	`doctor_img`	VARCHAR(255)	NULL	COMMENT '전문의 사진 경로',
	`sub_category_id`	int	NOT NULL	COMMENT 'auto increment',
	`main_category_id`	INT	NOT NULL	COMMENT 'AUTO INCREMENT'
);

DROP TABLE IF EXISTS `total_rating`;

CREATE TABLE `total_rating` (
	`total_rating_id`	int	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
	`total_rating_update_time`	DATETIME	NOT NULL	DEFAULT NOW(),
	`total_rating_result`	NUMERIC	NOT NULL,
	`total_rating_count`	int	NOT NULL	DEFAULT 0,
	`total_rating_sum`	NUMERIC	NOT NULL	DEFAULT 0,
	`ps_id`	int	NOT NULL
);

DROP TABLE IF EXISTS `operation`;

CREATE TABLE `operation` (
	`operation_id`	int	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`ps_id`	INT	NULL	COMMENT 'AUTO INCREMENT',
	`calender_id`	INT	NOT NULL	COMMENT 'AUTO INCREMENT'
);

DROP TABLE IF EXISTS `calender`;

CREATE TABLE `calender` (
	`calender_seq`	INT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
	`calender_year`	INT	NULL,
	`calender_week`	INT	NULL,
	`calender_hour`	INT	NULL,
	`calender_day_of_week`	INT	NULL	COMMENT '1 : 월
2 : 화
3 : 수
4 : 목
5 : 금
6 : 토
7 : 일'
);

DROP TABLE IF EXISTS `reservation`;

CREATE TABLE `reservation` (
	`reservation_id`	INT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
	`consulting_id`	INT	NOT NULL,
	`calender_id`	INT	NOT NULL	COMMENT 'AUTO INCREMENT',
	`user_id`	INT	NOT NULL	COMMENT 'int unsigned auto_increment',
	`ps_id`	INT	NULL	COMMENT 'AUTO INCREMENT'
);



ALTER TABLE `review` ADD CONSTRAINT `FK_user_TO_review_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `review` ADD CONSTRAINT `FK_ps_TO_review_1` FOREIGN KEY (
	`ps_id`
)
REFERENCES `ps` (
	`ps_id`
);

ALTER TABLE `ps` ADD CONSTRAINT `FK_main_category_TO_ps_1` FOREIGN KEY (
	`main_kategory_id`
)
REFERENCES `main_category` (
	`main_category_id`
);

ALTER TABLE `ps` ADD CONSTRAINT `FK_sub_category_TO_ps_1` FOREIGN KEY (
	`sub_category_id`
)
REFERENCES `sub_category` (
	`sub_category_id`
);

ALTER TABLE `favorites` ADD CONSTRAINT `FK_user_TO_favorites_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `favorites` ADD CONSTRAINT `FK_ps_TO_favorites_1` FOREIGN KEY (
	`ps_id`
)
REFERENCES `ps` (
	`ps_id`
);

ALTER TABLE `sub_category` ADD CONSTRAINT `FK_main_category_TO_sub_category_1` FOREIGN KEY (
	`main_category_id`
)
REFERENCES `main_category` (
	`main_category_id`
);

ALTER TABLE `consulting` ADD CONSTRAINT `FK_ps_TO_consulting_1` FOREIGN KEY (
	`ps_id`
)
REFERENCES `ps` (
	`ps_id`
);

ALTER TABLE `consulting` ADD CONSTRAINT `FK_user_TO_consulting_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `consulting` ADD CONSTRAINT `FK_review_TO_consulting_1` FOREIGN KEY (
	`review_id`
)
REFERENCES `review` (
	`review_id`
);

ALTER TABLE `consulting` ADD CONSTRAINT `FK_sub_category_TO_consulting_1` FOREIGN KEY (
	`sub_category_id`
)
REFERENCES `sub_category` (
	`sub_category_id`
);

ALTER TABLE `rating` ADD CONSTRAINT `FK_total_rating_TO_rating_1` FOREIGN KEY (
	`total_rating_id`
)
REFERENCES `total_rating` (
	`total_rating_id`
);

ALTER TABLE `rating` ADD CONSTRAINT `FK_user_TO_rating_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `rating` ADD CONSTRAINT `FK_consulting_TO_rating_1` FOREIGN KEY (
	`consulting_id`
)
REFERENCES `consulting` (
	`consulting_id`
);

ALTER TABLE `report` ADD CONSTRAINT `FK_consulting_TO_report_1` FOREIGN KEY (
	`report_consulting_id`
)
REFERENCES `consulting` (
	`consulting_id`
);

ALTER TABLE `doctor` ADD CONSTRAINT `FK_ps_TO_doctor_1` FOREIGN KEY (
	`ps_id`
)
REFERENCES `ps` (
	`ps_id`
);

ALTER TABLE `doctor` ADD CONSTRAINT `FK_sub_category_TO_doctor_1` FOREIGN KEY (
	`sub_category_id`
)
REFERENCES `sub_category` (
	`sub_category_id`
);

ALTER TABLE `doctor` ADD CONSTRAINT `FK_main_category_TO_doctor_1` FOREIGN KEY (
	`main_category_id`
)
REFERENCES `main_category` (
	`main_category_id`
);

ALTER TABLE `total_rating` ADD CONSTRAINT `FK_ps_TO_total_rating_1` FOREIGN KEY (
	`ps_id`
)
REFERENCES `ps` (
	`ps_id`
);

ALTER TABLE `operation` ADD CONSTRAINT `FK_ps_TO_operation_1` FOREIGN KEY (
	`ps_id`
)
REFERENCES `ps` (
	`ps_id`
);

ALTER TABLE `operation` ADD CONSTRAINT `FK_calender_TO_operation_1` FOREIGN KEY (
	`calender_id`
)
REFERENCES `calender` (
	`calender_seq`
);

ALTER TABLE `reservation` ADD CONSTRAINT `FK_consulting_TO_reservation_1` FOREIGN KEY (
	`consulting_id`
)
REFERENCES `consulting` (
	`consulting_id`
);

ALTER TABLE `reservation` ADD CONSTRAINT `FK_calender_TO_reservation_1` FOREIGN KEY (
	`calender_id`
)
REFERENCES `calender` (
	`calender_seq`
);

ALTER TABLE `reservation` ADD CONSTRAINT `FK_user_TO_reservation_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `reservation` ADD CONSTRAINT `FK_ps_TO_reservation_1` FOREIGN KEY (
	`ps_id`
)
REFERENCES `ps` (
	`ps_id`
);

