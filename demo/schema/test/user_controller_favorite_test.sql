INSERT INTO `user` (`name`) VALUES
	('test user1');

INSERT INTO ps (`name`) VALUES
	('test ps1');

INSERT INTO ps_main (`ps_seq`, `main_seq`) VALUES
	(1, 1);
	
INSERT INTO ps_main_sub (`ps_seq`, `main_seq`, `sub_seq`) VALUES
	(1, 1, 1),
	(1, 1, 2);
	
INSERT INTO `favorite` (`user_seq`, `ps_seq`) VALUES
	(1, 1);	
	
INSERT INTO ps (`name`) VALUES
	('test ps2');

INSERT INTO ps_main (`ps_seq`, `main_seq`) VALUES
	(2, 2);
	
INSERT INTO ps_main_sub (`ps_seq`, `main_seq`, `sub_seq`) VALUES
	(2, 2, 8),
	(2, 2, 9);
	
INSERT INTO `favorite` (`user_seq`, `ps_seq`) VALUES
	(1, 2);
	
INSERT INTO `total_rating` (`count`, `result`, `sum`, `ps_seq`) VALUES
	(1, 1, 1, 1),
	(2, 2, 2, 2);
	
INSERT INTO `review` (`ps_seq`) VALUES
	(1),
	(1),
	(2),
	(2),
	(2);
	
INSERT INTO `user` (`name`) VALUES
	('test user2');

