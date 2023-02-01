INSERT INTO review (`user_seq`, `ps_seq`, `point`, `content`, `reg_date`) VALUES
	(1, 1, 3, 'test review', NOW());
	
INSERT INTO review_sub (`review_seq`, `sub_seq`) VALUES
	(1, 1);