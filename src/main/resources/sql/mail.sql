/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.17-log : Database - mail
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `oa_email` */

CREATE DATABASE /*!32312 IF NOT EXISTS*/`mail` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mail`;

DROP TABLE IF EXISTS `oa_email`;

CREATE TABLE `oa_email` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `receive_email` varchar(500) NOT NULL COMMENT '接收人邮箱(多个逗号分开)',
  `subject` varchar(100) NOT NULL COMMENT '主题',
  `content` text NOT NULL COMMENT '发送内容',
  `template` varchar(100) DEFAULT NULL COMMENT '模板',
  `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `oa_email` */

insert  into `oa_email`(`id`,`receive_email`,`subject`,`content`,`template`,`send_time`) values (4,'345849402@qq.com','吃鸡','大吉大利今晚吃鸡','welcome.flt','2018-10-12 00:00:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
