-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: db_example
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `cid` bigint(20) NOT NULL AUTO_INCREMENT,
  `cname` varchar(63) DEFAULT NULL,
  `cnumber` varchar(255) NOT NULL,
  `credits` smallint(6) DEFAULT NULL,
  `description` varchar(63) DEFAULT NULL,
  `semester` varchar(127) DEFAULT NULL,
  `did` bigint(20) DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `UK_ma1m8wnd7wag8skcqag2ctiwg` (`cnumber`),
  KEY `FKp3gbxen0p05dl9qvcyfsrs7g1` (`did`),
  KEY `FK9y427ivle00co8pempbvbrkwx` (`pid`),
  CONSTRAINT `FK9y427ivle00co8pempbvbrkwx` FOREIGN KEY (`pid`) REFERENCES `professor` (`pid`),
  CONSTRAINT `FKp3gbxen0p05dl9qvcyfsrs7g1` FOREIGN KEY (`did`) REFERENCES `department` (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Database Design','6370',4,'Introduction to Relational Algebra, SQL, 3NF, and CNF','Fall 2017',1,1),(2,'Linear Systems','6210',3,'Introduction to Laplace and Fourier analysis','Fall 2017',3,2),(3,'Intro to Robotics','6530',4,'Introduction to probabilist theory and application','Spring 2018',1,2),(4,'Computers','6710',4,'All about computers','Fall 2017',2,1);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `did` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `dname` varchar(60) NOT NULL,
  PRIMARY KEY (`did`),
  UNIQUE KEY `UK_ph88huqhqo9v6vy293ua1oh09` (`dname`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Department of Computer Science, Franklin College of Arts and Sciences','CSCI'),(2,'Department of Mathematics, Franklin College of Arts and Sciences','MATH'),(3,'Department of Electrical Engineering, College of Engineering','ELEE'),(4,'Department of Mechanical Engineering, College of Engineering','MECH'),(5,'Department of Physics, Franklin College of ARts and Sciences','PHYS');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professor` (
  `pid` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fname` varchar(40) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `lname` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `did` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `UK_qjm28ojevoom770jyieljec3e` (`email`),
  KEY `FKctvrdi3euuaxq9cj0sy3gkfk0` (`did`),
  CONSTRAINT `FKctvrdi3euuaxq9cj0sy3gkfk0` FOREIGN KEY (`did`) REFERENCES `department` (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES (1,'Athens','1965-09-18','jam@cs.uga.edu','John','M','Miller','GA','111 Database Way','30605',1),(2,'Athens','1980-01-28','alweshah@uga.edu','Khaled','M','Alweshah','GA','876 Electricity Ave','30605',3),(3,'Athens','1970-10-30','arabnia@cs.uga.edu','Hamid','M','Arabnia','GA','555 Computer Way','30605',1);
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(63) NOT NULL,
  `dob` date NOT NULL,
  `email` varchar(127) NOT NULL,
  `fname` varchar(63) NOT NULL,
  `gender` char(1) DEFAULT NULL,
  `lname` varchar(63) NOT NULL,
  `residency` varchar(63) NOT NULL,
  `state` varchar(2) NOT NULL,
  `street` varchar(63) DEFAULT NULL,
  `zip` int(11) NOT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `UK_fe0i52si7ybu0wjedj6motiim` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Athens','1996-07-23','melanie@gmail.com','Melanie','F','Lee','Citizen','GA','817 Peachtree Rd',45678),(2,'Athens','1995-09-03','adam@gmail.com','Adam','M','King','Citizen','GA','314 Pike Street',89067),(3,'Thompson','1990-04-27','susan@gmail.com','Susan','F','George','Visa','GA','192 Aptitude Way',19281),(4,'Lawrenceville','1997-06-17','nirav@gmail.com','Nirav','M','Ilango','National','GA','2773 Database Lane',31341);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_course_grade`
--

DROP TABLE IF EXISTS `student_course_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_course_grade` (
  `grade` varchar(255) DEFAULT NULL,
  `cid` bigint(20) NOT NULL,
  `sid` bigint(20) NOT NULL,
  PRIMARY KEY (`cid`,`sid`),
  KEY `FKkfu8xpt1f61820e47n4s9iykn` (`sid`),
  CONSTRAINT `FKertcouqv4unekwyk2qafslwka` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`),
  CONSTRAINT `FKkfu8xpt1f61820e47n4s9iykn` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_course_grade`
--

LOCK TABLES `student_course_grade` WRITE;
/*!40000 ALTER TABLE `student_course_grade` DISABLE KEYS */;
INSERT INTO `student_course_grade` VALUES ('81',1,1),('89',1,2),('76',1,3),('90',2,1),('87',2,2),('96',2,3),('79',2,4),('99',3,1),('67',3,2),('85',3,3);
/*!40000 ALTER TABLE `student_course_grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_department_major`
--

DROP TABLE IF EXISTS `student_department_major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_department_major` (
  `major` varchar(255) DEFAULT NULL,
  `did` bigint(20) NOT NULL,
  `sid` bigint(20) NOT NULL,
  PRIMARY KEY (`did`,`sid`),
  KEY `FKb2tjrmjeohrnppts6ime3y6w4` (`sid`),
  CONSTRAINT `FK1eevs4f46ge7nh0bmygfi7kko` FOREIGN KEY (`did`) REFERENCES `department` (`did`),
  CONSTRAINT `FKb2tjrmjeohrnppts6ime3y6w4` FOREIGN KEY (`sid`) REFERENCES `student` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_department_major`
--

LOCK TABLES `student_department_major` WRITE;
/*!40000 ALTER TABLE `student_department_major` DISABLE KEYS */;
INSERT INTO `student_department_major` VALUES ('Agricultural Engineering',1,4),('Applied Mathematics',2,3),('Electrical Engineering',3,1),('Computer Systems Engineering',3,2),('Computer Science',4,1);
/*!40000 ALTER TABLE `student_department_major` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-27  9:56:26
