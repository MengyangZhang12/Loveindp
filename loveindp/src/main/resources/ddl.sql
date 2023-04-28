CREATE TABLE `loveindp`.`user`  (
    `id` int NOT NULL AUTO_INCREMENT,
    `created_at` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_at` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `telphone` varchar(40) NOT NULL DEFAULT '',
    `password` varchar(200) NOT NULL DEFAULT '',
    `nick_name` varchar(40) NOT NULL DEFAULT '',
    `gender` int NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `telphone_unique_index`(`telphone`) USING BTREE
);

CREATE TABLE `loveindp`.`seller`  (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(80) NOT NULL DEFAULT '',
    `created_at` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_at` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `remark_score` decimal(2, 1) NOT NULL,
    `disabled_flag` int NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`)
);

CREATE TABLE `loveindp`.`category`  (
    `id` int NOT NULL AUTO_INCREMENT,
    `created_at` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_at` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `name` varchar(20) NOT NULL DEFAULT '',
    `icon_url` varchar(255) NOT NULL DEFAULT '',
    `sort` int NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_unique_index`(`name`) USING BTREE
);

CREATE TABLE `loveindp`.`shop`  (
    `id` int NOT NULL AUTO_INCREMENT,
    `created_at` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `updated_at` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `name` varchar(80) NOT NULL DEFAULT '',
    `remark_score` decimal(2, 1) NOT NULL DEFAULT 0,
    `price_per_man` int NOT NULL DEFAULT 0,
    `latitude` decimal(10, 6) NOT NULL DEFAULT '',
    `longitude` decimal(10, 6) NOT NULL,
    `category_id` int NOT NULL DEFAULT 0,
    `tags` varchar(2000) NOT NULL DEFAULT '',
    `start_time` varchar(200) NOT NULL DEFAULT '',
    `end_time` varchar(200) NOT NULL DEFAULT '',
    `address` varchar(200) NOT NULL,
    `seller_id` int NOT NULL DEFAULT 0,
    `icon_url` varchar(100) NOT NULL DEFAULT '',
    PRIMARY KEY (`id`)
);