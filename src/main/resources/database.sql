DROP DATABASE IF EXISTS seat_allocation;
SET GLOBAL time_zone = '+2:00';

CREATE DATABASE IF NOT EXISTS seat_allocation;

USE seat_allocation;
CREATE TABLE IF NOT EXISTS seats (
id int not null auto_increment,
username varchar(100) ,
type varchar(100) ,
seat_number int not null,
primary key(id));
INSERT INTO seats (seat_number)
   VALUES
   (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),
   (12),(13),(14),(15),(16),(17),(18),(19),(20),(21),(22),(23),(24);

