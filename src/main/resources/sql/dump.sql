-- MySQL dump 10.13  Distrib 8.3.0, for Win64 (x86_64)
--
-- Host: localhost    Database: buildLedgerSchema
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `buildLedgerSchema`
--

/*!40000 DROP DATABASE IF EXISTS `buildLedgerSchema`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `buildLedgerSchema` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `buildLedgerSchema`;

--
-- Table structure for table `apartment`
--

DROP TABLE IF EXISTS `apartment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `price_eur` double NOT NULL,
  `sold` bit(1) NOT NULL,
  `adjoining_terrace` double NOT NULL,
  `adjoining_yard_roof` double NOT NULL,
  `area` double NOT NULL,
  `bathroom_count` int NOT NULL,
  `bedroom_count` int NOT NULL,
  `clean_area` double NOT NULL,
  `common_parts` double NOT NULL,
  `common_parts_percentage` double NOT NULL,
  `compensation` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price_per_square_meter` double NOT NULL,
  `price_yard` double NOT NULL,
  `building_id` bigint DEFAULT NULL,
  `floor_id` bigint DEFAULT NULL,
  `sell_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc6tleiew3udqxomadmwq7b0c4` (`building_id`),
  KEY `FKktgce96euiwuat21u9f54ccvw` (`floor_id`),
  KEY `FK18l4w8wn4cymcic7885oivox` (`sell_id`),
  CONSTRAINT `FK18l4w8wn4cymcic7885oivox` FOREIGN KEY (`sell_id`) REFERENCES `sell` (`id`),
  CONSTRAINT `FKc6tleiew3udqxomadmwq7b0c4` FOREIGN KEY (`building_id`) REFERENCES `buildings` (`id`),
  CONSTRAINT `FKktgce96euiwuat21u9f54ccvw` FOREIGN KEY (`floor_id`) REFERENCES `floor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment`
--

LOCK TABLES `apartment` WRITE;
/*!40000 ALTER TABLE `apartment` DISABLE KEYS */;
INSERT INTO `apartment` VALUES (1,'Apartment 1',90000,_binary '',20,20,75,1,1,60,15,20,NULL,NULL,1200,15000,1,1,1),(2,'Apartment 2',150000,_binary '\0',0,0,120,2,2,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(3,'Apartment 3',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(4,'Apartment 4',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(5,'Apartment 5',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(6,'Apartment 6',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(7,'Apartment 7',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(8,'Apartment 8',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(9,'Apartment 9',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(10,'Apartment 10',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(11,'Apartment 11',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(12,'Apartment 12',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(13,'Apartment 13',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(14,'Apartment 14',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(15,'Apartment 15',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(16,'Apartment 16',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(17,'Apartment 17',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(18,'Apartment 18',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(19,'Apartment 19',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(20,'Apartment 20',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(21,'Apartment 21',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(22,'Apartment 22',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(23,'Apartment 23',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(24,'Apartment 24',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL),(25,'Apartment 25',0,_binary '\0',0,0,0,0,0,0,0,0,NULL,NULL,0,0,1,NULL,NULL);
/*!40000 ALTER TABLE `apartment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `broker`
--

DROP TABLE IF EXISTS `broker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `broker` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `broker`
--

LOCK TABLES `broker` WRITE;
/*!40000 ALTER TABLE `broker` DISABLE KEYS */;
INSERT INTO `broker` VALUES (1,'petargeorgiev@gmail.com','Petar','Georgiev','broker'),(3,'4848','48484','84848','broker'),(4,'petargeorgiev@gmail.com','hmgm','Georgiev','broker'),(5,'','','','broker'),(6,'user@mail.bg','first','workflow','broker');
/*!40000 ALTER TABLE `broker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buildings`
--

DROP TABLE IF EXISTS `buildings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buildings` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `entrance_count` int NOT NULL,
  `rsp` double NOT NULL,
  `title` varchar(255) NOT NULL,
  `parcel_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlmugk57t81p3rmlc5ruavrug7` (`parcel_id`),
  CONSTRAINT `FKlmugk57t81p3rmlc5ruavrug7` FOREIGN KEY (`parcel_id`) REFERENCES `parcels` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buildings`
--

LOCK TABLES `buildings` WRITE;
/*!40000 ALTER TABLE `buildings` DISABLE KEYS */;
INSERT INTO `buildings` VALUES (1,'some block simple information',2,5500,'Cooperation 1',1),(2,NULL,1,0,'Cooperation 2',1);
/*!40000 ALTER TABLE `buildings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cooperations`
--

DROP TABLE IF EXISTS `cooperations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cooperations` (
  `type` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKe7nenovvnfmg9ookq2vc5e5q6` FOREIGN KEY (`id`) REFERENCES `buildings` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cooperations`
--

LOCK TABLES `cooperations` WRITE;
/*!40000 ALTER TABLE `cooperations` DISABLE KEYS */;
INSERT INTO `cooperations` VALUES ('Cooperation',1),('Cooperation',2);
/*!40000 ALTER TABLE `cooperations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expense`
--

DROP TABLE IF EXISTS `expense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expense` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount_euro` double NOT NULL,
  `date` date DEFAULT NULL,
  `pay_status` enum('BANK','CASH','NO') DEFAULT NULL,
  `payed_amount_euro` double NOT NULL,
  `remaining_amount_euro` double NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `expense_status` tinyint DEFAULT NULL,
  `facture_number` varchar(255) DEFAULT NULL,
  `building_id` bigint DEFAULT NULL,
  `project_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo6knggo932u2u6eg4qewkixyh` (`building_id`),
  KEY `FK7nox80wjucucw8m8fpantmlry` (`project_id`),
  CONSTRAINT `FK7nox80wjucucw8m8fpantmlry` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FKo6knggo932u2u6eg4qewkixyh` FOREIGN KEY (`building_id`) REFERENCES `buildings` (`id`),
  CONSTRAINT `expense_chk_1` CHECK ((`expense_status` between 0 and 3))
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense`
--

LOCK TABLES `expense` WRITE;
/*!40000 ALTER TABLE `expense` DISABLE KEYS */;
INSERT INTO `expense` VALUES (1,0,'2025-01-21',NULL,0,0,'Broker-Commission',0,NULL,NULL,NULL),(2,0,'2025-01-21',NULL,0,0,'Broker-Commission',0,NULL,NULL,NULL),(3,0,'2025-01-21',NULL,0,0,'Broker-Commission',0,NULL,NULL,NULL),(4,0,'2025-01-21',NULL,0,0,'Broker-Commission',0,NULL,NULL,NULL),(5,0,'2025-01-21',NULL,0,0,'Broker-Commission',0,NULL,NULL,NULL),(6,0,'2025-01-21',NULL,0,0,'Broker-Commission',0,NULL,NULL,NULL),(7,2900,'2025-01-21',NULL,0,2900,'Broker-Commission',0,NULL,NULL,NULL),(8,0,'2025-01-21',NULL,0,0,'Broker-Commission',0,NULL,NULL,NULL),(9,2900,'2025-01-21',NULL,0,2900,'Broker-Commission',0,NULL,NULL,NULL),(10,2900,'2025-01-21',NULL,0,2900,'Broker-Commission',0,NULL,NULL,NULL),(11,2900,'2025-01-21',NULL,0,2900,'Broker-Commission',0,NULL,NULL,NULL),(12,10000,'2025-01-24','CASH',5000,5000,'зидария',0,'4454',1,1);
/*!40000 ALTER TABLE `expense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `floor`
--

DROP TABLE IF EXISTS `floor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `floor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `common_area` double DEFAULT NULL,
  `number` varchar(255) NOT NULL,
  `building_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtxout07tmf510gw0o0n0f05u` (`building_id`),
  CONSTRAINT `FKtxout07tmf510gw0o0n0f05u` FOREIGN KEY (`building_id`) REFERENCES `buildings` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `floor`
--

LOCK TABLES `floor` WRITE;
/*!40000 ALTER TABLE `floor` DISABLE KEYS */;
INSERT INTO `floor` VALUES (1,NULL,'1',1),(2,NULL,'2',1),(3,NULL,'3',1),(4,NULL,'4',1),(5,NULL,'5',1),(6,NULL,'-1',1);
/*!40000 ALTER TABLE `floor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `garages`
--

DROP TABLE IF EXISTS `garages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `garages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `price_eur` double NOT NULL,
  `sold` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `apartment_id` bigint DEFAULT NULL,
  `building_id` bigint DEFAULT NULL,
  `floor_id` bigint DEFAULT NULL,
  `sell_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc4ae7fgd1l2mv30whvk27f1p0` (`apartment_id`),
  KEY `FKcjx0lj7jg1touna4as94o36ie` (`building_id`),
  KEY `FKndm6fbpacqtkdiae712qsj7y3` (`floor_id`),
  KEY `FKlrr2h4h5o00dpr3a33c7xpyu7` (`sell_id`),
  CONSTRAINT `FKc4ae7fgd1l2mv30whvk27f1p0` FOREIGN KEY (`apartment_id`) REFERENCES `apartment` (`id`),
  CONSTRAINT `FKcjx0lj7jg1touna4as94o36ie` FOREIGN KEY (`building_id`) REFERENCES `buildings` (`id`),
  CONSTRAINT `FKlrr2h4h5o00dpr3a33c7xpyu7` FOREIGN KEY (`sell_id`) REFERENCES `sell` (`id`),
  CONSTRAINT `FKndm6fbpacqtkdiae712qsj7y3` FOREIGN KEY (`floor_id`) REFERENCES `floor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `garages`
--

LOCK TABLES `garages` WRITE;
/*!40000 ALTER TABLE `garages` DISABLE KEYS */;
INSERT INTO `garages` VALUES (1,'Garage 1',25000,_binary '',NULL,NULL,1,6,1),(2,'Garage 2',0,_binary '\0',NULL,NULL,1,NULL,NULL),(3,'Garage 3',0,_binary '\0',NULL,NULL,1,NULL,NULL),(4,'Garage 4',0,_binary '\0',NULL,NULL,1,NULL,NULL),(5,'Garage 5',0,_binary '\0',NULL,NULL,1,NULL,NULL),(6,'Garage 6',0,_binary '\0',NULL,NULL,1,NULL,NULL),(7,'Garage 7',0,_binary '\0',NULL,NULL,1,NULL,NULL),(8,'Garage 8',0,_binary '\0',NULL,NULL,1,NULL,NULL),(9,'Garage 9',0,_binary '\0',NULL,NULL,1,NULL,NULL),(10,'Garage 10',0,_binary '\0',NULL,NULL,1,NULL,NULL);
/*!40000 ALTER TABLE `garages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `house`
--

DROP TABLE IF EXISTS `house`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `house` (
  `type` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK5qsio7pwkhu9iedajgm04331f` FOREIGN KEY (`id`) REFERENCES `buildings` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house`
--

LOCK TABLES `house` WRITE;
/*!40000 ALTER TABLE `house` DISABLE KEYS */;
/*!40000 ALTER TABLE `house` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `income`
--

DROP TABLE IF EXISTS `income`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `income` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount_euro` double NOT NULL,
  `date` date DEFAULT NULL,
  `pay_status` enum('BANK','CASH','NO') DEFAULT NULL,
  `payed_amount_euro` double NOT NULL,
  `remaining_amount_euro` double NOT NULL,
  `facture_number` varchar(255) DEFAULT NULL,
  `income_log` varchar(255) DEFAULT NULL,
  `income_status` varchar(255) DEFAULT NULL,
  `building_id` bigint DEFAULT NULL,
  `installment_id` bigint DEFAULT NULL,
  `project_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKp9noggt47bkxifg7wm88taln8` (`installment_id`),
  KEY `FK4q00mx6422f0ouo5q46ajgx8h` (`building_id`),
  KEY `FKtmlqugf68mu7hds61edqhe1oj` (`project_id`),
  CONSTRAINT `FK4q00mx6422f0ouo5q46ajgx8h` FOREIGN KEY (`building_id`) REFERENCES `buildings` (`id`),
  CONSTRAINT `FKt21haregpx2tg7eky6i9dvtbt` FOREIGN KEY (`installment_id`) REFERENCES `installment` (`id`),
  CONSTRAINT `FKtmlqugf68mu7hds61edqhe1oj` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `income`
--

LOCK TABLES `income` WRITE;
/*!40000 ALTER TABLE `income` DISABLE KEYS */;
INSERT INTO `income` VALUES (22,55000,'2025-01-21','CASH',55000,0,NULL,'2/2 Apartment 1 Garage 1 ',NULL,1,23,1),(23,55000,'2025-01-21','CASH',55000,0,NULL,'1/2 Apartment 1 Garage 1 ',NULL,1,22,1);
/*!40000 ALTER TABLE `income` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `installment`
--

DROP TABLE IF EXISTS `installment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `installment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `installment_amount` double NOT NULL,
  `installment_date` date DEFAULT NULL,
  `installment_log` varchar(255) DEFAULT NULL,
  `is_pay_status` bit(1) DEFAULT NULL,
  `payment_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9oqo6ff1bgmo2pfpb49li9vd9` (`payment_id`),
  CONSTRAINT `FK9oqo6ff1bgmo2pfpb49li9vd9` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `installment`
--

LOCK TABLES `installment` WRITE;
/*!40000 ALTER TABLE `installment` DISABLE KEYS */;
INSERT INTO `installment` VALUES (22,55000,'2025-01-21','1/2',_binary '',1),(23,55000,'2025-01-21','2/2',_binary '',1);
/*!40000 ALTER TABLE `installment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parcels`
--

DROP TABLE IF EXISTS `parcels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parcels` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `area` double NOT NULL,
  `eik` varchar(255) DEFAULT NULL,
  `is_compensated` bit(1) NOT NULL,
  `is_regular` bit(1) NOT NULL,
  `percentage_of_compensation` double NOT NULL,
  `price_eur` double NOT NULL,
  `with_electricity_and_water` bit(1) NOT NULL,
  `project_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKciy6n9mj8crt8yv1l8jpknkvy` (`project_id`),
  CONSTRAINT `FKrfjw77lbd9c49ujodwv1h05b5` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parcels`
--

LOCK TABLES `parcels` WRITE;
/*!40000 ALTER TABLE `parcels` DISABLE KEYS */;
INSERT INTO `parcels` VALUES (1,'OVCHA KUPEL 1',2000,'231.222.333.11.1',_binary '\0',_binary '\0',22,200050,_binary '\0',1);
/*!40000 ALTER TABLE `parcels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking_places`
--

DROP TABLE IF EXISTS `parking_places`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parking_places` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `price_eur` double NOT NULL,
  `sold` bit(1) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `building_id` bigint DEFAULT NULL,
  `sell_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKagya61d5khosdtfrhcevuqlc1` (`building_id`),
  KEY `FK6p54fayk4mqh205v0u4g3d1kk` (`sell_id`),
  CONSTRAINT `FK6p54fayk4mqh205v0u4g3d1kk` FOREIGN KEY (`sell_id`) REFERENCES `sell` (`id`),
  CONSTRAINT `FKagya61d5khosdtfrhcevuqlc1` FOREIGN KEY (`building_id`) REFERENCES `buildings` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_places`
--

LOCK TABLES `parking_places` WRITE;
/*!40000 ALTER TABLE `parking_places` DISABLE KEYS */;
INSERT INTO `parking_places` VALUES (1,'Parking-Place 1',15000,_binary '\0',NULL,1,NULL),(2,'Parking-Place 2',0,_binary '\0',NULL,1,NULL),(3,'Parking-Place 3',0,_binary '\0',NULL,1,NULL),(4,'Parking-Place 4',0,_binary '\0',NULL,1,NULL),(5,'Parking-Place 5',0,_binary '\0',NULL,1,NULL),(6,'Parking-Place 6',0,_binary '\0',NULL,1,NULL),(7,'Parking-Place 7',0,_binary '\0',NULL,1,NULL),(8,'Parking-Place 8',0,_binary '\0',NULL,1,NULL),(9,'Parking-Place 9',0,_binary '\0',NULL,1,NULL),(10,'Parking-Place 10',0,_binary '\0',NULL,1,NULL),(11,'Parking-Place 11',0,_binary '\0',NULL,1,NULL),(12,'Parking-Place 12',0,_binary '\0',NULL,1,NULL),(13,'Parking-Place 13',0,_binary '\0',NULL,1,NULL),(14,'Parking-Place 14',0,_binary '\0',NULL,1,NULL),(15,'Parking-Place 15',0,_binary '\0',NULL,1,NULL);
/*!40000 ALTER TABLE `parking_places` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount_received` double NOT NULL,
  `amount_remaining` double NOT NULL,
  `installment_count` int NOT NULL,
  `payment_status` enum('IN_PROGRESS','PAID','UNPAID') DEFAULT NULL,
  `sell_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6lw8eu5lvxeqeijvgai0jgeji` (`sell_id`),
  CONSTRAINT `FKa765teyhxiu71l5ee8cwqjpbq` FOREIGN KEY (`sell_id`) REFERENCES `sell` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,110000,0,2,'PAID',1);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_schema`
--

DROP TABLE IF EXISTS `payment_schema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_schema` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `installment_count` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_schema`
--

LOCK TABLES `payment_schema` WRITE;
/*!40000 ALTER TABLE `payment_schema` DISABLE KEYS */;
INSERT INTO `payment_schema` VALUES (1,4,'30-30-30-10');
/*!40000 ALTER TABLE `payment_schema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_schema_installments`
--

DROP TABLE IF EXISTS `payment_schema_installments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_schema_installments` (
  `payment_schema_id` bigint NOT NULL,
  `percent` int DEFAULT NULL,
  KEY `FKq417fj1l37u5a4wslucsn4bqc` (`payment_schema_id`),
  CONSTRAINT `FKq417fj1l37u5a4wslucsn4bqc` FOREIGN KEY (`payment_schema_id`) REFERENCES `payment_schema` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_schema_installments`
--

LOCK TABLES `payment_schema_installments` WRITE;
/*!40000 ALTER TABLE `payment_schema_installments` DISABLE KEYS */;
INSERT INTO `payment_schema_installments` VALUES (1,30),(1,30),(1,30),(1,10);
/*!40000 ALTER TABLE `payment_schema_installments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `building_status` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `eik` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `parcel_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKth25iene2tgo8mm4o9fauwohb` (`parcel_id`),
  CONSTRAINT `FKr2keww9ds10ne6nu76srfy412` FOREIGN KEY (`parcel_id`) REFERENCES `parcels` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'Cooperation','Somе company','231.222.333.11.1','2026-01-21','2025-01-21','Obelq',1);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchaser`
--

DROP TABLE IF EXISTS `purchaser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchaser` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchaser`
--

LOCK TABLES `purchaser` WRITE;
/*!40000 ALTER TABLE `purchaser` DISABLE KEYS */;
INSERT INTO `purchaser` VALUES (1,'ivanpetrov@mail','Ivan','Petrov','purchaser'),(2,'48484','484848','84848','purchaser'),(3,'ivanpetrov@mail','Ivan','Petrov','purchaser'),(4,'буибуиб','ихоувсбджиуб','бнуйибуийб','purchaser'),(5,'opkjopko@mail','first','workflow','purchaser'),(6,'opkjopko@mail','first','workflow','purchaser'),(7,'opkjopko@mail','first','workflow','purchaser'),(8,'opkjopko@mail','first','workflow','purchaser'),(9,'opkjopko@mail','first','workflow','purchaser'),(10,'opkjopko@mail','first','workflow','purchaser'),(11,'opkjopko@mail','first','workflow','purchaser');
/*!40000 ALTER TABLE `purchaser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sell`
--

DROP TABLE IF EXISTS `sell`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sell` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `broker_profit_in_euro` double NOT NULL,
  `broker_profit_in_percentage` double NOT NULL,
  `contract_date` date DEFAULT NULL,
  `contract_price_in_euro` double NOT NULL,
  `cooperation_id` bigint DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `discount_in_euro` double NOT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `total_price_in_euro` double NOT NULL,
  `broker_id` bigint DEFAULT NULL,
  `building_id` bigint DEFAULT NULL,
  `payment_id` bigint DEFAULT NULL,
  `purchaser_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKd532sfx5id51uhayya03poidy` (`payment_id`),
  KEY `FKh7fdm3ejbcwbvqtipjdf3pvmt` (`broker_id`),
  KEY `FK5qmu9221fk4xt0y3al8g4515h` (`building_id`),
  KEY `FKeav18v4tvb1apvtwyku8pi3nl` (`purchaser_id`),
  CONSTRAINT `FK5qmu9221fk4xt0y3al8g4515h` FOREIGN KEY (`building_id`) REFERENCES `buildings` (`id`),
  CONSTRAINT `FKeav18v4tvb1apvtwyku8pi3nl` FOREIGN KEY (`purchaser_id`) REFERENCES `purchaser` (`id`),
  CONSTRAINT `FKh7fdm3ejbcwbvqtipjdf3pvmt` FOREIGN KEY (`broker_id`) REFERENCES `broker` (`id`),
  CONSTRAINT `FKonm89d2vsqejxfylhrnd1vvvf` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sell`
--

LOCK TABLES `sell` WRITE;
/*!40000 ALTER TABLE `sell` DISABLE KEYS */;
INSERT INTO `sell` VALUES (1,0,2,'2025-01-21',80000,1,'Регистрираме продажбата',5000,'C:\\father project\\two repos\\BuildLedger-Backend\\src\\main\\resources\\contracts\\78cc83dd-3269-401a-9892-2da1159c8112_0-02-05-0b5fefd0a695acc97264a052aad1492a76a43198e539167962e3624a75ce57c7_6e231bb3685304cb.jpg',110000,1,NULL,1,1);
/*!40000 ALTER TABLE `sell` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount_euro` double NOT NULL,
  `date` date DEFAULT NULL,
  `pay_status` enum('BANK','CASH','NO') DEFAULT NULL,
  `payed_amount_euro` double NOT NULL,
  `remaining_amount_euro` double NOT NULL,
  `transaction_log` varchar(255) DEFAULT NULL,
  `transaction_status` tinyint DEFAULT NULL,
  `building_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7gloplyxfx9lxf7rocc8ownlw` (`building_id`),
  CONSTRAINT `FK7gloplyxfx9lxf7rocc8ownlw` FOREIGN KEY (`building_id`) REFERENCES `buildings` (`id`),
  CONSTRAINT `transaction_chk_1` CHECK ((`transaction_status` between 0 and 1))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,55000,'2025-01-21','BANK',5000,50000,'Income transaction by installment: 2/2 Apartment 1 Garage 1  Sell: Apartment 1, Garage 1, ',0,1),(2,55000,'2025-01-21','CASH',50000,0,'Income transaction by installment: 2/2 Apartment 1 Garage 1  Sell: Apartment 1, Garage 1, ',0,1),(3,55000,'2025-01-21','BANK',25000,30000,'Income transaction by installment: 1/2 Apartment 1 Garage 1  Sell: Apartment 1, Garage 1, ',0,1),(4,55000,'2025-01-21','CASH',30000,0,'Income transaction by installment: 1/2 Apartment 1 Garage 1  Sell: Apartment 1, Garage 1, ',0,1),(5,10000,'2025-01-21','CASH',5000,5000,'Expense: зидария - WORK - 4454',1,1);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-21 12:05:48
