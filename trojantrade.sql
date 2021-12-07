CREATE TABLE `trojantrade`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(300) NOT NULL,
  `location` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);
  
CREATE TABLE `trojantrade`.`item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `image_url` VARCHAR(500) NOT NULL,
  `price` VARCHAR(45) NOT NULL,
  `location` VARCHAR(100) NOT NULL,
  `description` VARCHAR(400) NOT NULL,
  `category` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
  
CREATE TABLE `trouserjantrade`.`postlist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
  
CREATE TABLE `trojantrade`.`wishlist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
  
INSERT INTO user (nickname, username, password, location) VALUES ('Trojan', 'trojan@usc.edu', 'trojan123', 'USC');
INSERT INTO user (nickname, username, password, location) VALUES ('Hyeri', 'hyeriwoo@usc.edu', 'hyeri123', 'USC');

INSERT INTO item (user_id, title, image_url, price, location, description, category) VALUES (1, 'Selling Macbook Pro', 'https://www.macworld.com/wp-content/uploads/2021/03/macbook-2017-hero-100726395-orig-12.jpg?quality=50&strip=all', '$300', 'USC', 'Almost New', 'electronics');
INSERT INTO item (user_id, title, image_url, price, location, description, category) VALUES (2, 'Selling CS201 Textbook', 'https://images-na.ssl-images-amazon.com/images/I/51FkY6Xwb8L._SX218_BO1,204,203,200_QL40_FMwebp_.jpg', '$50', 'USC', 'Textbook required for CS201 class!', 'book');
INSERT INTO item (user_id, title, image_url, price, location, description, category) VALUES (1, 'Mattress', 'https://www.choosemattress.com/wp-content/uploads/2016/06/used-mattress-2-1536x1152.jpg.webp', '$100', 'USC', 'Almost New', 'furniture');






