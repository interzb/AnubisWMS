# MySQL-Front 5.1  (Build 4.2)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;


# Host: localhost    Database: inventory
# ------------------------------------------------------
# Server version 5.1.53-community

DROP DATABASE IF EXISTS `inventory`;
CREATE DATABASE `inventory` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `inventory`;

#
# Source for table branch
#

DROP TABLE IF EXISTS `branch`;
CREATE TABLE `branch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

#
# Source for table family
#

DROP TABLE IF EXISTS `family`;
CREATE TABLE `family` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

#
# Source for table product
#

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sku` bigint(20) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `barcode` varchar(255) DEFAULT NULL,
  `delivery_type` int(11) DEFAULT NULL,
  `unit_of_measurement` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `division` varchar(255) DEFAULT NULL,
  `groupname` varchar(255) DEFAULT NULL,
  `subcategory` varchar(255) DEFAULT NULL,
  `package_policy` varchar(255) DEFAULT NULL,
  `provider_id` bigint(20) DEFAULT NULL,
  `client_price` decimal(19,2) DEFAULT NULL,
  `client_tax` decimal(19,2) DEFAULT NULL,
  `client_cost` decimal(19,2) DEFAULT NULL,
  `modified_flag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

#
# Source for table sales
#

DROP TABLE IF EXISTS `sales`;
CREATE TABLE `sales` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `branchId` bigint(20) DEFAULT NULL,
  `createtime` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Source for table salesentry
#

DROP TABLE IF EXISTS `salesentry`;
CREATE TABLE `salesentry` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `productId` bigint(20) DEFAULT NULL,
  `quantity` bigint(11) DEFAULT NULL,
  `saleId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Source for table stock
#

DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branchId` bigint(11) DEFAULT NULL,
  `productId` bigint(11) DEFAULT NULL,
  `quantity` bigint(11) DEFAULT NULL,
  `expiration` varchar(40) DEFAULT NULL,
  `ts` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

#
# Source for table transfer
#

DROP TABLE IF EXISTS `transfer`;
CREATE TABLE `transfer` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `fromBranchId` bigint(20) DEFAULT NULL,
  `destinationBranchId` bigint(20) DEFAULT NULL,
  `fromStockid` bigint(20) DEFAULT NULL,
  `quantity` bigint(20) DEFAULT NULL,
  `status` bigint(11) DEFAULT NULL,
  `creattime` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
