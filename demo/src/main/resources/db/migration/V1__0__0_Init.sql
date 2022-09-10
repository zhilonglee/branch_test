CREATE TABLE IF NOt EXISTS `actor` (
                         `actor_id` smallint unsigned NOT NULL AUTO_INCREMENT,
                         `first_name` varchar(45) NOT NULL,
                         `last_name` varchar(45) NOT NULL,
                         `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         PRIMARY KEY (`actor_id`),
                         KEY `idx_actor_last_name` (`last_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;