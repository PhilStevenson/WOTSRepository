-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: localhost    Database: wots
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` varchar(8) NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `surName` varchar(30) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telephone` varchar(30) NOT NULL,
  `addressFirstLine` varchar(50) NOT NULL,
  `addressSecondLine` varchar(50) NOT NULL,
  `addressTownCity` varchar(50) NOT NULL,
  `addressCounty` varchar(50) NOT NULL,
  `addressCountry` varchar(50) NOT NULL,
  `addressPostcode` varchar(45) NOT NULL,
  `availableCredit` double(8,2) NOT NULL,
  `cardNumber` varchar(16) NOT NULL,
  `cardName` varchar(50) NOT NULL,
  `cardExpiry` varchar(4) NOT NULL,
  `cardSecNum` varchar(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('CUS00001','Philip','Stevenson','philstevenson@live.co.uk','07754319562','Beech House','Brucefield Road','Blairgowrie','Perthshire','Scotland','PH106LA',10000.00,'1234123412341234','MR PHILIP A STEVENSON','0915','123'),('CUS00002','Eddard','Stark','stark@winterfell.com','999','The Keep','Winterfell','Winterfell','The North','Westeros','N98 W23',5999.00,'086512376537','LORD EDDARD STARK','2145','123');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `custorder`
--

DROP TABLE IF EXISTS `custorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `custorder` (
  `id` varchar(8) NOT NULL,
  `custID` varchar(8) NOT NULL,
  `dateTime` varchar(45) NOT NULL,
  `zone` varchar(45) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custorder`
--

LOCK TABLES `custorder` WRITE;
/*!40000 ALTER TABLE `custorder` DISABLE KEYS */;
INSERT INTO `custorder` VALUES ('COR00001','CUS00001','Thu Oct 22 13:09:46 BST 2015','PH10','Incomplete'),('COR00002','CUS00001','Thu Oct 22 15:40:20 BST 2015','PH10','Incomplete'),('COR00003','CUS00001','Thu Oct 22 16:54:36 BST 2015','PH10','Incomplete'),('COR00004','CUS00001','Thu Oct 22 16:54:59 BST 2015','PH10','Incomplete'),('COR00005','CUS00001','Thu Oct 22 16:56:36 BST 2015','PH10','Incomplete'),('COR00006','CUS00001','Thu Oct 22 16:58:48 BST 2015','PH10','Incomplete'),('COR00007','CUS00002','Thu Oct 22 17:15:22 BST 2015','N98 ','Incomplete');
/*!40000 ALTER TABLE `custorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderline`
--

DROP TABLE IF EXISTS `orderline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderline` (
  `orderID` varchar(8) NOT NULL,
  `productID` varchar(8) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`orderID`,`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderline`
--

LOCK TABLES `orderline` WRITE;
/*!40000 ALTER TABLE `orderline` DISABLE KEYS */;
INSERT INTO `orderline` VALUES ('COR00001','PRO00001',50),('COR00001','PRO00005',1),('COR00002','PRO00002',6),('COR00002','PRO00004',8),('COR00002','PRO00006',2),('COR00003','PRO00001',12),('COR00003','PRO00003',22),('COR00004','PRO00002',21);
/*!40000 ALTER TABLE `orderline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` varchar(8) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `price` double(5,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('PRO00001','Halfpint Ben Gnome','A Gnome designed from the famous character Halfpint Ben.',0.56),('PRO00002','Physics Joe Gnome','A Gnome of Joe who studied physics',69.00),('PRO00003','The Rampant Mabbett Gnome','oh Mabbett',22.50),('PRO00004','Mediterranean Dan Gnome','Disclaimer: NOT Mediterranean',45.21),('PRO00005','The Colm it Down Gnome','Colm the gnome is a fictional charachter who works the long lost trade of NETbuilder',500.50),('PRO00006','Bill and Ben Gnomes','Inspired by the ledgend of bilal and Ben from Coding Tiger Compiling Dragon',79.99);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-29 17:22:25
