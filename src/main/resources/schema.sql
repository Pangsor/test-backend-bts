CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE armscii8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE armscii8_bin NOT NULL,
  `password` varchar(255) COLLATE armscii8_bin NOT NULL,
  `username` varchar(255) COLLATE armscii8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

CREATE TABLE IF NOT EXISTS `users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

CREATE TABLE IF NOT EXISTS `checklists` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE armscii8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKgdomd9ggwftfvrx69qo95blt1` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

CREATE TABLE IF NOT EXISTS `checklistitem` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) COLLATE armscii8_bin DEFAULT NULL,
  `checklist_id` bigint NOT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc5obqf6p4aga6hf39x7iyk62a` (`checklist_id`),
  CONSTRAINT `FKc5obqf6p4aga6hf39x7iyk62a` FOREIGN KEY (`checklist_id`) REFERENCES `checklists` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;