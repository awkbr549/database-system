SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `db_example` DEFAULT CHARACTER SET utf8;
USE `db_example` ;


CREATE TABLE IF NOT EXISTS `db_example`.`student` (
  `sid` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(255) NOT NULL,
  `dob` DATE NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `fname` VARCHAR(255) NOT NULL,
  `gender` CHAR NOT NULL,
  `lname` VARCHAR(255) NOT NULL,
  `residency` VARCHAR(255) NOT NULL,
  `state` VARCHAR(2) NOT NULL,
  `street` VARCHAR(255) NOT NULL,
  `zip` INT NOT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
  
CREATE TABLE IF NOT EXISTS `db_example`.`department` (
  `did` INT NOT NULL AUTO_INCREMENT,
  `dname` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`did`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `student_department_major` (
    `sid` INT(11) NOT NULL,
    `did` INT(11) NOT NULL,
    `major` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`sid` , `did`),
    KEY `fk_sdm_department_idx` (`did`),
    CONSTRAINT `fk_sdm_student` FOREIGN KEY (`sid`)
        REFERENCES `student` (`sid`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_sdm_department` FOREIGN KEY (`did`)
        REFERENCES `department` (`did`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `course` (
    `cid` INT(11) NOT NULL AUTO_INCREMENT,
    `cnumber` SMALLINT(6) NOT NULL,
    `cname` VARCHAR(255) NOT NULL,
    `credits` TINYINT(4) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `semester` VARCHAR(255) NOT NULL,
    `did` INT(11) NOT NULL,
    `pid` INT(11) NOT NULL,
    PRIMARY KEY (`cid`),
    KEY `fk_course_department_idx` (`did`),
    KEY `fk_course_professor_idx` (`pid`),
    CONSTRAINT `fk_course_department` FOREIGN KEY (`did`)
        REFERENCES `department` (`did`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_course_professor` FOREIGN KEY (`pid`)
        REFERENCES `professor` (`pid`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE `professor` (
    `pid` INT(11) NOT NULL AUTO_INCREMENT,
    `fname` VARCHAR(255) NOT NULL,
    `lname` VARCHAR(255) NOT NULL,
    `dob` DATE NOT NULL,
    `gender` CHAR(1) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `street` VARCHAR(255) NOT NULL,
    `city` VARCHAR(255) NOT NULL,
    `state` VARCHAR(2) NOT NULL,
    `zip` INT(11) NOT NULL,
    `did` INT(11) NOT NULL,
    PRIMARY KEY (`pid`),
    UNIQUE KEY `email_UNIQUE` (`email`),
    KEY `fk_professor_department_idx` (`did`),
    CONSTRAINT `fk_professor_department` FOREIGN KEY (`did`)
        REFERENCES `department` (`did`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;


CREATE TABLE `student_course_grade` (
    `sid` INT(11) NOT NULL,
    `cid` INT(11) NOT NULL,
    `grade` FLOAT NOT NULL,
    PRIMARY KEY (`sid` , `cid`),
    KEY `fk_scm_department_idx` (`cid`),
    CONSTRAINT `fk_scm_student` FOREIGN KEY (`sid`)
        REFERENCES `student` (`sid`)
        ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT `fk_scm_course` FOREIGN KEY (`cid`)
        REFERENCES `course` (`cid`)
        ON DELETE CASCADE ON UPDATE NO ACTION
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

