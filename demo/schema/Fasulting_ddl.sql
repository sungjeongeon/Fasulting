CREATE TABLE `user` (
                        `seq`	BIGINT	NOT NULL,
                        `email`	VARCHAR(255)	NOT NULL,
                        `password`	VARCHAR(255)	NOT NULL,
                        `birth`	DATETIME	NOT NULL,
                        `number`	VARCHAR(255)	NOT NULL,
                        `nation`	VARCHAR(255)	NOT NULL,
                        `nation_code`	VARCHAR(255)	NOT NULL,
                        `name`	VARCHAR(255)	NOT NULL,
                        `del_date`	DATETIME	NULL,
                        `del_by`	VARCHAR(255)	NULL,
                        `del_yn`	VARCHAR(1)	NOT NULL	DEFAULT 'N'
);

CREATE TABLE `review` (
                          `seq`	BIGINT	NOT NULL,
                          `consulting_seq`	BIGINT	NOT NULL,
                          `rating_hist_seq`	BIGINT	NOT NULL,
                          `ps_seq`	BIGINT	NOT NULL,
                          `user_seq`	BIGINT	NOT NULL,
                          `content`	VARCHAR(255)	NOT NULL,
                          `dec_date`	DATETIME	NULL,
                          `dec_by`	VARCHAR(255)	NULL,
                          `dec_yn`	VARCHAR(1)	NOT NULL	DEFAULT 'N',
                          `del_date`	DATETIME	NULL,
                          `del_by`	VARCHAR(255)	NULL,
                          `del_yn`	VARCHAR(1)	NOT NULL	DEFAULT 'N'
);

CREATE TABLE `ps` (
                      `seq`	BIGINT	NOT NULL,
                      `email`	VARCHAR(255)	NOT NULL,
                      `password`	VARCHAR(255)	NOT NULL,
                      `name`	VARCHAR(255)	NOT NULL,
                      `address`	VARCHAR(255)	NOT NULL,
                      `zipcode`	VARCHAR(255)	NOT NULL,
                      `registration`	VARCHAR(255)	NOT NULL,
                      `registration_img`	VARCHAR(255)	NOT NULL	COMMENT '서버에 저장한 이미지 경로',
                      `number`	VARCHAR(255)	NOT NULL,
                      `director`	VARCHAR(255)	NOT NULL,
                      `homepage`	VARCHAR(255)	NULL,
                      `profile_img`	VARCHAR(255)	NULL	COMMENT '서버에 저장할 병원 프로필 사진 경로',
                      `intro`	VARCHAR(255)	NULL,
                      `confirm_yn`	VARCHAR(1)	NOT NULL	DEFAULT 'N'	COMMENT '가입 승인 여부에 따른 Y / N',
                      `confirm_date`	DATETIME	NULL,
                      `confirm_by`	VARCHAR(255)	NULL,
                      `del_date`	DATETIME	NULL,
                      `del_by`	VARCHAR(255)	NULL,
                      `del_yn`	VARCHAR(1)	NOT NULL	DEFAULT 'N'	COMMENT '병원 계정의 삭제는 탈퇴 기능'
);

CREATE TABLE `favorite` (
                            `seq`	BIGINT	NOT NULL,
                            `ps_seq`	BIGINT	NOT NULL,
                            `user_seq`	BIGINT	NOT NULL
);

CREATE TABLE `main_category` (
                                 `seq`	BIGINT	NOT NULL,
                                 `name`	VARCHAR(255)	NOT NULL	COMMENT '눈, 코, 입술, 안면윤곽, 보톡스/필러, 모발이식, 피부, 리프팅'
);

CREATE TABLE `sub_category` (
                                `seq`	BIGINT	NOT NULL,
                                `main_seq`	BIGINT	NOT NULL,
                                `name`	VARCHAR(255)	NOT NULL	COMMENT '- 쌍커풀, 눈매교정, 트임, 눈밑성형, 재수술
- 콧대, 코끝 성형, 콧불 축소, 재수술
- 입꼬리, 입술축소술, 입술확대수술
- 사각턱, 광대, 턱 끝, 이마, 양약, 재수술
- 절개, 비절개
- 여드름 치료, 화상치료, 흉터 치료'
);

CREATE TABLE `consulting` (
                              `seq`	BIGINT	NOT NULL,
                              `reservation_seq`	BIGINT	NOT NULL,
                              `ps_seq`	BIGINT	NOT NULL,
                              `user_seq`	BIGINT	NOT NULL
);

CREATE TABLE `rating_hist` (
                               `seq`	BIGINT	NOT NULL,
                               `consulting_seq`	BIGINT	NOT NULL,
                               `ps_seq`	BIGINT	NOT NULL,
                               `user_seq`	BIGINT	NOT NULL,
                               `point`	NUMERIC	NOT NULL	COMMENT '최초 평가 후 평점 적용
평가마다 평점 업데이트',
                               `del_date`	DATETIME	NULL,
                               `del_by`	VARCHAR(255)	NULL,
                               `del_yn`	VARCHAR(1)	NOT NULL	DEFAULT 'N'
);

CREATE TABLE `report` (
                          `seq`	BIGINT	NOT NULL,
                          `consulting_seq`	BIGINT	NOT NULL,
                          `before_img`	VARCHAR(255)	NOT NULL	COMMENT '서버에 저장된 비포 사진 경로',
                          `after_img`	VARCHAR(255)	NOT NULL	COMMENT '서버에 저장된 애프터 사진 경로',
                          `content`	VARCHAR(255)	NOT NULL	COMMENT '소견 작성 내용',
                          `estimate`	VARCHAR(255)	NOT NULL	COMMENT '예상 견적 비용'
);

CREATE TABLE `doctor` (
                          `seq`	BIGINT	NOT NULL,
                          `ps_seq`	BIGINT	NOT NULL,
                          `name`	VARCHAR(255)	NOT NULL,
                          `img`	VARCHAR(255)	NULL	COMMENT '전문의 사진 경로'
);

CREATE TABLE `total_rating` (
                                `seq`	BIGINT	NOT NULL,
                                `ps_seq`	BIGINT	NOT NULL,
                                `result`	NUMERIC	NULL,
                                `sum`	NUMERIC	NOT NULL	DEFAULT 0,
                                `count`	INT	NOT NULL	DEFAULT 0
);

CREATE TABLE `reservation` (
                               `seq`	BIGINT	NOT NULL,
                               `reservation_seq`	BIGINT	NOT NULL,
                               `ps_seq`	BIGINT	NOT NULL,
                               `user_seq`	BIGINT	NOT NULL,
                               `del_date`	DATETIME	NULL,
                               `del_by`	VARCHAR(255)	NULL,
                               `del_yn`	VARCHAR(1)	NOT NULL	DEFAULT 'N'
);

CREATE TABLE `reservation_cal` (
                                   `seq`	BIGINT	NOT NULL,
                                   `year`	INT	NOT NULL,
                                   `month`	INT	NOT NULL,
                                   `day`	INT	NOT NULL,
                                   `day_of_week`	INT	NOT NULL	COMMENT '1 : 월
2 : 화
3 : 수
4 : 목
5 : 금
6 : 토
7 : 일',
                                   `hour`	INT	NOT NULL
);

CREATE TABLE `ps_main` (
                           `ps_seq`	BIGINT	NOT NULL,
                           `main_seq`	BIGINT	NOT NULL
);

CREATE TABLE `ps_main_sub` (
                               `ps_seq`	BIGINT	NOT NULL,
                               `main_seq`	BIGINT	NOT NULL,
                               `sub_seq`	BIGINT	NOT NULL
);

CREATE TABLE `operating` (
                             `seq`	BIGINT	NOT NULL,
                             `ps_seq`	BIGINT	NOT NULL,
                             `year`	INT	NOT NULL,
                             `month`	INT	NOT NULL,
                             `day`	INT	NOT NULL,
                             `day_of_week`	INT	NOT NULL	COMMENT '1 : 월
2 : 화
3 : 수
4 : 목
5 : 금
6 : 토
7 : 일',
                             `hour`	INT	NOT NULL	COMMENT '0 ~ 24 시간, 30분 단위 1 ~ 48'
);

CREATE TABLE `default_operating` (
                                     `seq`	BIGINT	NOT NULL,
                                     `ps_seq`	BIGINT	NOT NULL,
                                     `day_of_week`	INT	NOT NULL	COMMENT '1 : 월
2 : 화
3 : 수
4 : 목
5 : 금
6 : 토
7 : 일',
                                     `am_start`	INT	NOT NULL	COMMENT '1 ~ 48 로 표현(0시부터 24시까지 30분 단위)',
                                     `am_end`	INT	NOT NULL	COMMENT '1 ~ 48 로 표현(0시부터 24시까지 30분 단위)',
                                     `pm_start`	INT	NOT NULL	COMMENT '1 ~ 48 로 표현(0시부터 24시까지 30분 단위)',
                                     `pm_end`	INT	NOT NULL	COMMENT '1 ~ 48 로 표현(0시부터 24시까지 30분 단위)',
                                     `off_yn`	VARCHAR(1)	NOT NULL	DEFAULT 'N'
);

CREATE TABLE `doctor_main` (
                               `doctor_seq`	BIGINT	NOT NULL,
                               `main_seq`	BIGINT	NOT NULL
);

CREATE TABLE `token` (
                         `seq`	BIGINT	NOT NULL,
                         `refresh_token`	VARCHAR(255)	NOT NULL
);

CREATE TABLE `role` (
                        `user_seq`	BIGINT	NOT NULL,
                        `authority`	VARCHAR(255)	NOT NULL	COMMENT 'admin or user'
);

CREATE TABLE `ps_token` (
                            `ps_seq`	BIGINT	NOT NULL,
                            `token_seq`	BIGINT	NOT NULL
);

CREATE TABLE `user_token` (
                              `user_seq`	BIGINT	NOT NULL,
                              `token_seq`	BIGINT	NOT NULL
);

CREATE TABLE `reservation_sub` (
                                   `reservation_seq`	BIGINT	NOT NULL,
                                   `sub_seq`	BIGINT	NOT NULL
);

CREATE TABLE `review_sub` (
                              `review_seq`	BIGINT	NOT NULL,
                              `sub_seq`	BIGINT	NOT NULL
);

ALTER TABLE `user` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
                                                         `seq`
    );

ALTER TABLE `review` ADD CONSTRAINT `PK_REVIEW` PRIMARY KEY (
                                                             `seq`
    );

ALTER TABLE `ps` ADD CONSTRAINT `PK_PS` PRIMARY KEY (
                                                     `seq`
    );

ALTER TABLE `favorite` ADD CONSTRAINT `PK_FAVORITE` PRIMARY KEY (
                                                                 `seq`
    );

ALTER TABLE `main_category` ADD CONSTRAINT `PK_MAIN_CATEGORY` PRIMARY KEY (
                                                                           `seq`
    );

ALTER TABLE `sub_category` ADD CONSTRAINT `PK_SUB_CATEGORY` PRIMARY KEY (
                                                                         `seq`
    );

ALTER TABLE `consulting` ADD CONSTRAINT `PK_CONSULTING` PRIMARY KEY (
                                                                     `seq`
    );

ALTER TABLE `rating_hist` ADD CONSTRAINT `PK_RATING_HIST` PRIMARY KEY (
                                                                       `seq`
    );

ALTER TABLE `report` ADD CONSTRAINT `PK_REPORT` PRIMARY KEY (
                                                             `seq`
    );

ALTER TABLE `doctor` ADD CONSTRAINT `PK_DOCTOR` PRIMARY KEY (
                                                             `seq`
    );

ALTER TABLE `total_rating` ADD CONSTRAINT `PK_TOTAL_RATING` PRIMARY KEY (
                                                                         `seq`
    );

ALTER TABLE `reservation` ADD CONSTRAINT `PK_RESERVATION` PRIMARY KEY (
                                                                       `seq`
    );

ALTER TABLE `reservation_cal` ADD CONSTRAINT `PK_RESERVATION_CAL` PRIMARY KEY (
                                                                               `seq`
    );

ALTER TABLE `ps_main` ADD CONSTRAINT `PK_PS_MAIN` PRIMARY KEY (
                                                               `ps_seq`,
                                                               `main_seq`
    );

ALTER TABLE `ps_main_sub` ADD CONSTRAINT `PK_PS_MAIN_SUB` PRIMARY KEY (
                                                                       `ps_seq`,
                                                                       `main_seq`,
                                                                       `sub_seq`
    );

ALTER TABLE `operating` ADD CONSTRAINT `PK_OPERATING` PRIMARY KEY (
                                                                   `seq`
    );

ALTER TABLE `default_operating` ADD CONSTRAINT `PK_DEFAULT_OPERATING` PRIMARY KEY (
                                                                                   `seq`
    );

ALTER TABLE `doctor_main` ADD CONSTRAINT `PK_DOCTOR_MAIN` PRIMARY KEY (
                                                                       `doctor_seq`,
                                                                       `main_seq`
    );

ALTER TABLE `token` ADD CONSTRAINT `PK_TOKEN` PRIMARY KEY (
                                                           `seq`
    );

ALTER TABLE `role` ADD CONSTRAINT `PK_ROLE` PRIMARY KEY (
                                                         `user_seq`
    );

ALTER TABLE `ps_token` ADD CONSTRAINT `PK_PS_TOKEN` PRIMARY KEY (
                                                                 `ps_seq`,
                                                                 `token_seq`
    );

ALTER TABLE `user_token` ADD CONSTRAINT `PK_USER_TOKEN` PRIMARY KEY (
                                                                     `user_seq`,
                                                                     `token_seq`
    );

ALTER TABLE `reservation_sub` ADD CONSTRAINT `PK_RESERVATION_SUB` PRIMARY KEY (
                                                                               `reservation_seq`,
                                                                               `sub_seq`
    );

ALTER TABLE `review_sub` ADD CONSTRAINT `PK_REVIEW_SUB` PRIMARY KEY (
                                                                     `review_seq`,
                                                                     `sub_seq`
    );

ALTER TABLE `review` ADD CONSTRAINT `FK_consulting_TO_review_1` FOREIGN KEY (
                                                                             `consulting_seq`
    )
    REFERENCES `consulting` (
                             `seq`
        );

ALTER TABLE `review` ADD CONSTRAINT `FK_rating_hist_TO_review_1` FOREIGN KEY (
                                                                              `rating_hist_seq`
    )
    REFERENCES `rating_hist` (
                              `seq`
        );

ALTER TABLE `review` ADD CONSTRAINT `FK_ps_TO_review_1` FOREIGN KEY (
                                                                     `ps_seq`
    )
    REFERENCES `ps` (
                     `seq`
        );

ALTER TABLE `review` ADD CONSTRAINT `FK_user_TO_review_1` FOREIGN KEY (
                                                                       `user_seq`
    )
    REFERENCES `user` (
                       `seq`
        );

ALTER TABLE `favorite` ADD CONSTRAINT `FK_ps_TO_favorite_1` FOREIGN KEY (
                                                                         `ps_seq`
    )
    REFERENCES `ps` (
                     `seq`
        );

ALTER TABLE `favorite` ADD CONSTRAINT `FK_user_TO_favorite_1` FOREIGN KEY (
                                                                           `user_seq`
    )
    REFERENCES `user` (
                       `seq`
        );

ALTER TABLE `sub_category` ADD CONSTRAINT `FK_main_category_TO_sub_category_1` FOREIGN KEY (
                                                                                            `main_seq`
    )
    REFERENCES `main_category` (
                                `seq`
        );

ALTER TABLE `consulting` ADD CONSTRAINT `FK_reservation_TO_consulting_1` FOREIGN KEY (
                                                                                      `reservation_seq`
    )
    REFERENCES `reservation` (
                              `seq`
        );

ALTER TABLE `consulting` ADD CONSTRAINT `FK_ps_TO_consulting_1` FOREIGN KEY (
                                                                             `ps_seq`
    )
    REFERENCES `ps` (
                     `seq`
        );

ALTER TABLE `consulting` ADD CONSTRAINT `FK_user_TO_consulting_1` FOREIGN KEY (
                                                                               `user_seq`
    )
    REFERENCES `user` (
                       `seq`
        );

ALTER TABLE `rating_hist` ADD CONSTRAINT `FK_consulting_TO_rating_hist_1` FOREIGN KEY (
                                                                                       `consulting_seq`
    )
    REFERENCES `consulting` (
                             `seq`
        );

ALTER TABLE `rating_hist` ADD CONSTRAINT `FK_ps_TO_rating_hist_1` FOREIGN KEY (
                                                                               `ps_seq`
    )
    REFERENCES `ps` (
                     `seq`
        );

ALTER TABLE `rating_hist` ADD CONSTRAINT `FK_user_TO_rating_hist_1` FOREIGN KEY (
                                                                                 `user_seq`
    )
    REFERENCES `user` (
                       `seq`
        );

ALTER TABLE `report` ADD CONSTRAINT `FK_consulting_TO_report_1` FOREIGN KEY (
                                                                             `consulting_seq`
    )
    REFERENCES `consulting` (
                             `seq`
        );

ALTER TABLE `doctor` ADD CONSTRAINT `FK_ps_TO_doctor_1` FOREIGN KEY (
                                                                     `ps_seq`
    )
    REFERENCES `ps` (
                     `seq`
        );

ALTER TABLE `total_rating` ADD CONSTRAINT `FK_ps_TO_total_rating_1` FOREIGN KEY (
                                                                                 `ps_seq`
    )
    REFERENCES `ps` (
                     `seq`
        );

ALTER TABLE `reservation` ADD CONSTRAINT `FK_reservation_cal_TO_reservation_1` FOREIGN KEY (
                                                                                            `reservation_seq`
    )
    REFERENCES `reservation_cal` (
                                  `seq`
        );

ALTER TABLE `reservation` ADD CONSTRAINT `FK_ps_TO_reservation_1` FOREIGN KEY (
                                                                               `ps_seq`
    )
    REFERENCES `ps` (
                     `seq`
        );

ALTER TABLE `reservation` ADD CONSTRAINT `FK_user_TO_reservation_1` FOREIGN KEY (
                                                                                 `user_seq`
    )
    REFERENCES `user` (
                       `seq`
        );

ALTER TABLE `ps_main` ADD CONSTRAINT `FK_ps_TO_ps_main_1` FOREIGN KEY (
                                                                       `ps_seq`
    )
    REFERENCES `ps` (
                     `seq`
        );

ALTER TABLE `ps_main` ADD CONSTRAINT `FK_main_category_TO_ps_main_1` FOREIGN KEY (
                                                                                  `main_seq`
    )
    REFERENCES `main_category` (
                                `seq`
        );

ALTER TABLE `ps_main_sub` ADD CONSTRAINT `FK_ps_main_TO_ps_main_sub_1` FOREIGN KEY (
                                                                                    `ps_seq`
    )
    REFERENCES `ps_main` (
                          `ps_seq`
        );

ALTER TABLE `ps_main_sub` ADD CONSTRAINT `FK_ps_main_TO_ps_main_sub_2` FOREIGN KEY (
                                                                                    `main_seq`
    )
    REFERENCES `ps_main` (
                          `main_seq`
        );

ALTER TABLE `ps_main_sub` ADD CONSTRAINT `FK_sub_category_TO_ps_main_sub_1` FOREIGN KEY (
                                                                                         `sub_seq`
    )
    REFERENCES `sub_category` (
                               `seq`
        );

ALTER TABLE `operating` ADD CONSTRAINT `FK_ps_TO_operating_1` FOREIGN KEY (
                                                                           `ps_seq`
    )
    REFERENCES `ps` (
                     `seq`
        );

ALTER TABLE `default_operating` ADD CONSTRAINT `FK_ps_TO_default_operating_1` FOREIGN KEY (
                                                                                           `ps_seq`
    )
    REFERENCES `ps` (
                     `seq`
        );

ALTER TABLE `doctor_main` ADD CONSTRAINT `FK_doctor_TO_doctor_main_1` FOREIGN KEY (
                                                                                   `doctor_seq`
    )
    REFERENCES `doctor` (
                         `seq`
        );

ALTER TABLE `doctor_main` ADD CONSTRAINT `FK_main_category_TO_doctor_main_1` FOREIGN KEY (
                                                                                          `main_seq`
    )
    REFERENCES `main_category` (
                                `seq`
        );

ALTER TABLE `role` ADD CONSTRAINT `FK_user_TO_role_1` FOREIGN KEY (
                                                                   `user_seq`
    )
    REFERENCES `user` (
                       `seq`
        );

ALTER TABLE `ps_token` ADD CONSTRAINT `FK_ps_TO_ps_token_1` FOREIGN KEY (
                                                                         `ps_seq`
    )
    REFERENCES `ps` (
                     `seq`
        );

ALTER TABLE `ps_token` ADD CONSTRAINT `FK_token_TO_ps_token_1` FOREIGN KEY (
                                                                            `token_seq`
    )
    REFERENCES `token` (
                        `seq`
        );

ALTER TABLE `user_token` ADD CONSTRAINT `FK_user_TO_user_token_1` FOREIGN KEY (
                                                                               `user_seq`
    )
    REFERENCES `user` (
                       `seq`
        );

ALTER TABLE `user_token` ADD CONSTRAINT `FK_token_TO_user_token_1` FOREIGN KEY (
                                                                                `token_seq`
    )
    REFERENCES `token` (
                        `seq`
        );

ALTER TABLE `reservation_sub` ADD CONSTRAINT `FK_reservation_TO_reservation_sub_1` FOREIGN KEY (
                                                                                                `reservation_seq`
    )
    REFERENCES `reservation` (
                              `seq`
        );

ALTER TABLE `reservation_sub` ADD CONSTRAINT `FK_sub_category_TO_reservation_sub_1` FOREIGN KEY (
                                                                                                 `sub_seq`
    )
    REFERENCES `sub_category` (
                               `seq`
        );

ALTER TABLE `review_sub` ADD CONSTRAINT `FK_review_TO_review_sub_1` FOREIGN KEY (
                                                                                 `review_seq`
    )
    REFERENCES `review` (
                         `seq`
        );

ALTER TABLE `review_sub` ADD CONSTRAINT `FK_sub_category_TO_review_sub_1` FOREIGN KEY (
                                                                                       `sub_seq`
    )
    REFERENCES `sub_category` (
                               `seq`
        );

