INSERT INTO doctor (`name`, `ps_seq` , `img`) VALUES
	('doc1', 1, 'img1'),
	('doc2', 2, 'img2');
	
INSERT INTO doctor_main (`main_seq`, `doctor_seq`) VALUES
	(1, 1),
	(2, 2);
	
UPDATE `user` SET email = 'test.com' WHERE `seq` = 1;