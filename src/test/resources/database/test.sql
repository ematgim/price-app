CREATE TABLE IF NOT EXISTS `rate`
(
    `id`               bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `start_date` DATETIME,
    `end_date` DATETIME,
    `price_list_id` integer(45),
    `product_id` integer(45),
    `priority` integer(45),
    `price` double(45,4),
    `currency` varchar(45)         NOT NULL DEFAULT '',

    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8;