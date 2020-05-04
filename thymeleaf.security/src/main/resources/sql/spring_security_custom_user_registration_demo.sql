DROP DATABASE  IF EXISTS `spring_security_custom_user_thymeleaf`;

CREATE DATABASE  IF NOT EXISTS `spring_security_custom_user_thymeleaf`;
USE `spring_security_custom_user_thymeleaf`;

--
-- Table structure for table `engine`
--

DROP TABLE IF EXISTS `engine`;

CREATE TABLE `engine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `layout` varchar(50) NOT NULL,
  `image_url` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `engine` (layout, image_url)
VALUES 
('inline-6','https://s3-prod.autonews.com/styles/width_792/s3/BLOG06_171029886_AR_-1_EXDBQYTSUSYN.jpg'),
('V-12','https://silodrome.com/wp-content/uploads/2019/02/Ferrari-F50-V12-Engine-740x625.jpg'),
('V-8','https://i.wheelsage.org/pictures/engines/autowp.ru_gm_ls1_v8_5.7_1.jpg'),
('Boxer-6','https://www.torquenews.com/sites/default/files/images/flat_6_porsche_engine.jpg');

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) UNIQUE NOT NULL,
  `password` char(80) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) UNIQUE NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--  passwords are encrypted using BCrypt
--
-- generation tool is available at: https://www.bcryptcalculator.com/
--
-- Default passwords is fun123 for every user
--

INSERT INTO `user` (username,password,first_name,last_name,email)
VALUES 
('peter','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','peter','bur','peter@kek.com'),
('kek','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','cojo','guerra','cojo@kek.com'),
('coisa','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','lily','caneças','lily@kek.com');


--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `role` (name)
VALUES 
('ROLE_EMPLOYEE'),('ROLE_MANAGER'),('ROLE_ADMIN');

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(3, 3)