-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: my_ebookshop
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `author_book`
--

DROP TABLE IF EXISTS `author_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author_book` (
  `book_id` binary(16) NOT NULL,
  `author_id` binary(16) NOT NULL,
  KEY `FK7cqs8nb7l859jcwwqoawcokqf` (`author_id`),
  KEY `FKmeehr164a2cpxegeiawuv40a3` (`book_id`),
  CONSTRAINT `FK7cqs8nb7l859jcwwqoawcokqf` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`),
  CONSTRAINT `FKmeehr164a2cpxegeiawuv40a3` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author_book`
--

LOCK TABLES `author_book` WRITE;
/*!40000 ALTER TABLE `author_book` DISABLE KEYS */;
INSERT INTO `author_book` VALUES (_binary 'ØÕ∞ $;B\rà≤\"óbïI',_binary '\"2¸™†Mp™&ëgå*hà'),(_binary 'P\÷G\\\’D\Ëó\„É\‡B]ä',_binary '\Ó˚Ú°OÉ∑I˙ä/ˆ\È\”'),(_binary 'Éä¬áIMLö\"w[Ä\ny',_binary '+ ûê¿NTß7e6¿\0W');
/*!40000 ALTER TABLE `author_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
  `id` binary(16) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (_binary '\Ó˚Ú°OÉ∑I˙ä/ˆ\È\”','1837-03-01','Ion','Creanga','ro'),(_binary '\"2¸™†Mp™&ëgå*hà','1828-02-08','Jules','Verne','fr'),(_binary '+ ûê¿NTß7e6¿\0W','0544-08-23','Sun','Tzu','chn');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_cart`
--

DROP TABLE IF EXISTS `book_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_cart` (
  `cart_id` binary(16) NOT NULL,
  `book_id` binary(16) NOT NULL,
  KEY `FK13d0pmxfe82vwbuckfqry2792` (`book_id`),
  KEY `FKhsaedbd79d2wf7akv09svuuf3` (`cart_id`),
  CONSTRAINT `FK13d0pmxfe82vwbuckfqry2792` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `FKhsaedbd79d2wf7akv09svuuf3` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_cart`
--

LOCK TABLES `book_cart` WRITE;
/*!40000 ALTER TABLE `book_cart` DISABLE KEYS */;
INSERT INTO `book_cart` VALUES (_binary 'XVU\Ï0∏I¥\'ò§_\„]',_binary 'P\÷G\\\’D\Ëó\„É\‡B]ä'),(_binary '§8\÷≈ãH˜Ü\Á¿:™Õ§',_binary 'P\÷G\\\’D\Ëó\„É\‡B]ä'),(_binary '§8\÷≈ãH˜Ü\Á¿:™Õ§',_binary 'Éä¬áIMLö\"w[Ä\ny');
/*!40000 ALTER TABLE `book_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_genre`
--

DROP TABLE IF EXISTS `book_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_genre` (
  `book_id` binary(16) NOT NULL,
  `genre_id` binary(16) NOT NULL,
  KEY `FKnkh6m50njl8771p0ll3lylqp2` (`genre_id`),
  KEY `FKq0f88ptislu8lv230mvgonssl` (`book_id`),
  CONSTRAINT `FKnkh6m50njl8771p0ll3lylqp2` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`id`),
  CONSTRAINT `FKq0f88ptislu8lv230mvgonssl` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_genre`
--

LOCK TABLES `book_genre` WRITE;
/*!40000 ALTER TABLE `book_genre` DISABLE KEYS */;
INSERT INTO `book_genre` VALUES (_binary 'P\÷G\\\’D\Ëó\„É\‡B]ä',_binary 'd™PpcFﬂØ3.˚XT'),(_binary 'P\÷G\\\’D\Ëó\„É\‡B]ä',_binary 'ÄaÉÉ∞;J≤ﬁ©ûéî\0\‚'),(_binary 'Éä¬áIMLö\"w[Ä\ny',_binary '\œ#r∫\ÂMà©\ZyR¿Åî%'),(_binary 'ØÕ∞ $;B\rà≤\"óbïI',_binary 't{Xl\ŒAR•s≤AV©\‰\·');
/*!40000 ALTER TABLE `book_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` binary(16) NOT NULL,
  `price` float DEFAULT NULL,
  `publishing_year` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `publishing_house_id` binary(16) DEFAULT NULL,
  `pages_file_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpefada6ov6umcrf8j8jog7wv0` (`publishing_house_id`),
  CONSTRAINT `FKpefada6ov6umcrf8j8jog7wv0` FOREIGN KEY (`publishing_house_id`) REFERENCES `publishing_houses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (_binary 'P\÷G\\\’D\Ëó\„É\‡B]ä',10.1,1978,'Harap Alb',_binary 'lA,ÖA\Ïü\≈Hy≠','pages.pdf'),(_binary 'Éä¬áIMLö\"w[Ä\ny',5.39,2002,'Art of War',_binary 'lA,ÖA\Ïü\≈Hy≠','pages.pdf'),(_binary 'ØÕ∞ $;B\rà≤\"óbïI',13.33,2010,'20.000 de leghe sub mari',_binary 'lA,ÖA\Ïü\≈Hy≠','pages.pdf');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carts`
--

DROP TABLE IF EXISTS `carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carts` (
  `id` binary(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carts`
--

LOCK TABLES `carts` WRITE;
/*!40000 ALTER TABLE `carts` DISABLE KEYS */;
INSERT INTO `carts` VALUES (_binary '°\·ß¯BÒ∫ú8˙.l)#'),(_binary 'XVU\Ï0∏I¥\'ò§_\„]'),(_binary '§8\÷≈ãH˜Ü\Á¿:™Õ§');
/*!40000 ALTER TABLE `carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` binary(16) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (_binary '\»⁄î¡¢D‹∫féG\—¸','Support'),(_binary '¢ko&ûqGÕür*jgÇ','Contact'),(_binary '\«\‹ˆ˛\…JŸáh¡ππ4§','FAQ');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genres`
--

DROP TABLE IF EXISTS `genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genres` (
  `id` binary(16) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genres`
--

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES (_binary '\œ#r∫\ÂMà©\ZyR¿Åî%','strategy'),(_binary 'd™PpcFﬂØ3.˚XT','fantasy'),(_binary 't{Xl\ŒAR•s≤AV©\‰\·','adventure'),(_binary 'ÄaÉÉ∞;J≤ﬁ©ûéî\0\‚','fairy-tale');
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profiles`
--

DROP TABLE IF EXISTS `profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profiles` (
  `date_of_birth` date DEFAULT NULL,
  `id` binary(16) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profiles`
--

LOCK TABLES `profiles` WRITE;
/*!40000 ALTER TABLE `profiles` DISABLE KEYS */;
INSERT INTO `profiles` VALUES (NULL,_binary '\…Ò∞¶®Ñ@>ø\Í\’\—\œ','Admin Address','Admin Contact','Admin','Admin'),(NULL,_binary '\—lw\Àw˛Ck¥nr·ïè¡|','Advisor Address','Advisor Contact','Advisor','Advisor'),(NULL,_binary '\Îo\Ê\ÎµM◊±aúßy∂','Buyer Address','Buyer Contact','Buyer','Buyer');
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publishing_houses`
--

DROP TABLE IF EXISTS `publishing_houses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publishing_houses` (
  `id` binary(16) NOT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `year_of_establishment` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publishing_houses`
--

LOCK TABLES `publishing_houses` WRITE;
/*!40000 ALTER TABLE `publishing_houses` DISABLE KEYS */;
INSERT INTO `publishing_houses` VALUES (_binary 'lA,ÖA\Ïü\≈Hy≠','New York','New York Times',1970);
/*!40000 ALTER TABLE `publishing_houses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `cart_id` binary(16) DEFAULT NULL,
  `id` binary(16) NOT NULL,
  `profile_id` binary(16) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role` enum('ROLE_ADMIN','ROLE_BUYER','ROLE_ADVISOR') DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pnp1baae4enifkkuq2cd01r9l` (`cart_id`),
  UNIQUE KEY `UK_7s5nlreekaxdbfml4ofky7utw` (`profile_id`),
  CONSTRAINT `FKdv26y3bb4vdmsr89c9ppnx85w` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`),
  CONSTRAINT `FKq2e6rj0p6p1gec2cslmaxugw1` FOREIGN KEY (`profile_id`) REFERENCES `profiles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (_binary '§8\÷≈ãH˜Ü\Á¿:™Õ§',_binary '\ZØ≥kGB©∑\ÃW\Ô/ı<',_binary '\…Ò∞¶®Ñ@>ø\Í\’\—\œ','admin@admin.ro','$2a$10$/SclyRRLp0VXUCxAuYyQVubkRn.e26r2NlSLakxiK.SPC4BYlufP6',NULL,'ROLE_ADMIN','Basic YWRtaW5AYWRtaW4ucm86cGFzc3dvcmQ='),(_binary '°\·ß¯BÒ∫ú8˙.l)#',_binary '• Öd\ÿFÕìÉJ\Í˙\√',_binary '\—lw\Àw˛Ck¥nr·ïè¡|','advisor@advisor.ro','$2a$10$NhGM9KQA44pnMUZells86uDOB/X.oZXJdDfR3h0HlNfNKfXzn4g5S',NULL,'ROLE_ADVISOR','Basic YWR2aXNvckBhZHZpc29yLnJvOnBhc3N3b3Jk'),(_binary 'XVU\Ï0∏I¥\'ò§_\„]',_binary '.\ﬂ`mkIˇëY\÷3\‚\'j\‚',_binary '\Îo\Ê\ÎµM◊±aúßy∂','buyer@buyer.ro','$2a$10$hQOhGchTXsn5sKNUz6o47.0ASo9YMtVpjDfoqvMTPusYgVuhm3dfe',NULL,'ROLE_BUYER','Basic YnV5ZXJAYnV5ZXIucm86cGFzc3dvcmQ=');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-21 17:14:10
