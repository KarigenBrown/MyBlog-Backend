/*
SQLyog v10.2 
MySQL - 5.5.40 : Database - myblog
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE = ''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS */`myblog` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `myblog`;

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `name`        varchar(128) DEFAULT NULL COMMENT '标签名',
    `create_by`   bigint(20)   DEFAULT NULL,
    `create_time` datetime     DEFAULT NULL,
    `update_by`   bigint(20)   DEFAULT NULL,
    `update_time` datetime     DEFAULT NULL,
    `remark`      varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8mb4 COMMENT ='标签';

/*Data for the table `tag` */

insert into `tag`(`id`, `name`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`)
values (1, 'Mybatis', NULL, NULL, NULL, '2022-01-11 09:20:50', 'weqwe'),
       (2, 'asdas', NULL, '2022-01-11 09:20:55', NULL, '2022-01-11 09:20:55', 'weqw'),
       (3, 'weqw', NULL, '2022-01-11 09:21:07', NULL, '2022-01-11 09:21:07', 'qweqwe'),
       (4, 'Java', NULL, '2022-01-13 15:22:43', NULL, '2022-01-13 15:22:43', 'sdad'),
       (5, 'WAD', NULL, '2022-01-13 15:22:47', NULL, '2022-01-13 15:22:47', 'ASDAD');

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;
