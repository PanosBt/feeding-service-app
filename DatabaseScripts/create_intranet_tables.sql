CREATE TABLE IF NOT EXISTS user (
	username VARCHAR(50) NOT NULL,
	password VARCHAR(100) NOT NULL,
	enabled TINYINT(1) NOT NULL,
	PRIMARY KEY (username)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS authorities (
	id INT(10) NOT NULL AUTO_INCREMENT,
	username VARCHAR(50) NOT NULL,
	authority VARCHAR(50) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE KEY ix_auth_username (username, authority),
	CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES user (username)
) AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS student (
	id INT(10) NOT NULL AUTO_INCREMENT,
	username VARCHAR(50) NOT NULL UNIQUE,
	firstName VARCHAR(100) DEFAULT NULL,
	lastName VARCHAR(100) DEFAULT NULL,
	dateOfBirth DATE DEFAULT NULL,
	identityCardNO VARCHAR(8) DEFAULT NULL,
	email VARCHAR(50) DEFAULT NULL,
	phone VARCHAR(20) DEFAULT NULL,
	academicID INT(12) UNSIGNED DEFAULT NULL,
	dept VARCHAR(10) DEFAULT NULL,
	PRIMARY KEY (id)
) AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS clerk (
	id INT(10) NOT NULL AUTO_INCREMENT,
	username VARCHAR(50) NOT NULL UNIQUE,
	firstName VARCHAR(100) DEFAULT NULL,
	lastName VARCHAR(100) DEFAULT NULL,
	dateOfBirth DATE DEFAULT NULL,
	identityCardNO VARCHAR(8) DEFAULT NULL,
	email VARCHAR(50) DEFAULT NULL,
	phone VARCHAR(20) DEFAULT NULL,
	supervising_dept VARCHAR(10) DEFAULT NULL,
	PRIMARY KEY (id)
) AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS admin (
	id INT(10) NOT NULL AUTO_INCREMENT,
	username VARCHAR(50) NOT NULL UNIQUE,
	firstName VARCHAR(100) DEFAULT NULL,
	lastName VARCHAR(100) DEFAULT NULL,
	dateOfBirth DATE DEFAULT NULL,
	identityCardNO VARCHAR(8) DEFAULT NULL,
	email VARCHAR(50) DEFAULT NULL,
	phone VARCHAR(20) DEFAULT NULL,
	PRIMARY KEY (id)
) AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS application (
	appl_id INT(10) NOT NULL AUTO_INCREMENT,
	student_id INT(10) NOT NULL,
	clerk_checked_id INT(10) DEFAULT NULL,
	familyIncome INT(10) UNSIGNED NOT NULL,
	num_siblings INT(2) UNSIGNED NOT NULL,
	origin_city VARCHAR(100) NOT NULL,
	mother_employeed BOOLEAN NOT NULL,
	father_employeed BOOLEAN NOT NULL,
	approved BOOLEAN DEFAULT NULL,
	score INT(3) UNSIGNED DEFAULT NULL,
	subm_date DATE NOT NULL,
	PRIMARY KEY (appl_id),
	FOREIGN KEY (student_id) REFERENCES student(id),
	FOREIGN KEY (clerk_checked_id) REFERENCES clerk(id)
) AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS accompanying_document (
	doc_id INT(10) NOT NULL AUTO_INCREMENT,
	appl_id INT(10) NOT NULL,
	doc_type VARCHAR(50) NOT NULL,
	file_path VARCHAR(100) NOT NULL,
	PRIMARY KEY (doc_id),
	FOREIGN KEY (appl_id) REFERENCES application(appl_id)
) AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;