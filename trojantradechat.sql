CREATE TABLE `trojantrade`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(300) NOT NULL,
  `location` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);

 CREATE TABLE `trojantrade`.`readMessages` (
  `from_id` INT NOT NULL,
  `to_id` INT NOT NULL,
  `text` VARCHAR(500) NOT NULL,
  `time` DATETIME);
  
CREATE TABLE `trojantrade`.`unreadMessages` (
  `from_id` INT NOT NULL,
  `to_id` INT NOT NULL,
  `text` VARCHAR(500) NOT NULL,
  `time` DATETIME);

INSERT INTO user (nickname, username, password, location) VALUES ('Trojan','trojan@usc.edu', 'trojan123', 'USC');
INSERT INTO user (nickname, username, password, location) VALUES ('Hyeri', 'hyeriwoo@usc.edu', 'hyeri123', 'USC');
INSERT INTO user (nickname, username, password, location) VALUES ('Alice','alice@usc.edu', 'alice123', 'USC');
INSERT INTO user (nickname, username, password, location) VALUES ('Bob','bob@usc.edu', 'bob123', 'USC');

INSERT INTO readMessages VALUES (1,2,'Interested in 201 textbook.','2021-12-07 08:30:45');
INSERT INTO unreadMessages VALUES(2,1,'When can you pick up?','2021-12-07 09:15:12');
INSERT INTO unreadMessages VALUES(4,2,'I am Alice.','2021-12-07 10:30:00');
INSERT INTO unreadMessages VALUES(4,2,'Sorry wrong person.','2021-12-07 10:30:15');

INSERT INTO readMessages VALUES(4,3,'I am Alice.','2021-12-07 10:30:22');
INSERT INTO readMessages VALUES(3,4,'No you are not.','2021-12-07 10:30:23');
INSERT INTO unreadMessages(4,3,'Yes I am.','2021-12-07 10:30:33');