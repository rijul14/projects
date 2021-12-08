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

INSERT INTO user (nickname, username, password, location) 
	VALUES ('Rijul', 'jraghu@usc.edu', 'rijul123', USC),
			('James', 'james@usc.edu', 'james123', USC),
            ('Jack', 'jack@usc.edu', 'jack123', USC),
			('Andrew', 'sndrew@usc.edu', 'andrew123', USC),
            ('Sam', 'sam@usc.edu', 'sam123', USC),
			('Jill', 'jill@usc.edu', 'jill123', USC),
            ('Tina', 'tina@usc.edu', 'tina123', USC),
			('Samantha', 'samantha@usc.edu', 'samantha123', USC),
            ('Bill', 'bill@usc.edu', 'bill123', USC),
			('Kate', 'kate@usc.edu', 'kate123', USC);
            
INSERT INTO item (user_id, title, image_url, price, location, description, category) 
VALUES (1, 'Selling Macbook Pro', 
'https://www.macworld.com/wp-content/uploads/2021/03/macbook-2017-hero-100726395-orig-12.jpg?quality=50&strip=all', 
'$300', 'USC', 'Almost New', 'electronics');
INSERT INTO item (user_id, title, image_url, price, location, description, category) 
VALUES (2, 'Selling CS201 Textbook', 
'https://images-na.ssl-images-amazon.com/images/I/51FkY6Xwb8L._SX218_BO1,204,203,200_QL40_FMwebp_.jpg', 
'$50', 'USC', 'Textbook required for CS201 class!', 'book');
INSERT INTO item (user_id, title, image_url, price, location, description, category) 
VALUES (1, 'Mattress', 
'https://www.choosemattress.com/wp-content/uploads/2016/06/used-mattress-2-1536x1152.jpg.webp', '$100', 
'USC', 'Almost New', 'furniture');
INSERT INTO item (user_id, title, image_url, price,location,description,category) 
	VALUES (3, 'Selling Beats', 
'https://i.ebayimg.com/images/g/imsAAOSwIyphpCPg/s-l225.webp', '$200', 
'USC', 'Unused', 'electronics'),
(4, 'Basketball Shoes', 
'https://i.ebayimg.com/images/g/DbgAAOSwWqthqFuh/s-l225.webp', '$80', 
'USC', '2 weeks old', 'clothing'),
(5, 'Gym Equipment', 
'https://i.ebayimg.com/images/g/V5UAAOSwcV5eF~vJ/s-l225.webp', '$1000', 
'USC', 'Unused', 'other'),
(6, 'Roku TV', 
'https://i.ebayimg.com/images/g/gyEAAOSwzPpgB0ym/s-l225.webp', '$300', 
'USC', 'Brand new', 'electronics'),
(7, 'Hoverboard', 
'https://i.ebayimg.com/images/g/GT4AAOSwZophr4i3/s-l225.webp', '$70', 
'USC', 'Bought 1 year ago', 'vehicle'),
(8, 'Golf club', 
'https://i.ebayimg.com/images/g/jAEAAOSwre9gKwKB/s-l225.webp', '$200', 
'USC', 'Unused', 'other'),
(9, 'Monitor', 
'https://i.ebayimg.com/thumbs/images/g/L1gAAOSwDsNhcJeS/s-l225.webp', '$255', 
'USC', 'Bought 6 months ago', 'electronics');








