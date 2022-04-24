-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema reservation
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema reservation
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `reservation` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `reservation` ;

-- -----------------------------------------------------
-- Table `reservation`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservation`.`admin` (
  `admin_idx` INT NOT NULL AUTO_INCREMENT,
  `id` VARCHAR(200) NULL DEFAULT NULL,
  `pw` VARCHAR(200) NULL DEFAULT NULL,
  `grade` INT NULL DEFAULT NULL,
  `nickname` VARCHAR(400) NULL DEFAULT NULL,
  PRIMARY KEY (`admin_idx`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `reservation`.`corp`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservation`.`corp` (
  `corp_idx` INT NOT NULL AUTO_INCREMENT,
  `id` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `pw` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `nickname` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `name` VARCHAR(50) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `phone_number` VARCHAR(200) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL,
  `join_date` DATE NULL DEFAULT NULL,
  `leave_date` DATE NULL DEFAULT NULL,
  `corp_state` INT NULL DEFAULT NULL,
  PRIMARY KEY (`corp_idx`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `reservation`.`house`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservation`.`house` (
  `house_idx` INT NOT NULL AUTO_INCREMENT,
  `corp_idx` INT NULL DEFAULT NULL,
  `house_name` VARCHAR(200) NULL DEFAULT NULL,
  `location_sido` VARCHAR(200) NULL DEFAULT NULL,
  `location_sigungu` VARCHAR(200) NULL DEFAULT NULL,
  `location_dong` VARCHAR(200) NULL DEFAULT NULL,
  `image_main` VARCHAR(200) NULL DEFAULT NULL,
  `image_sub` VARCHAR(200) NULL DEFAULT NULL,
  `desc` VARCHAR(4000) NULL DEFAULT NULL,
  `room_count` INT NULL DEFAULT NULL,
  `house_state` INT NULL DEFAULT NULL,
  `check_out` DATE NULL DEFAULT NULL,
  `check_in` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`house_idx`),
  CONSTRAINT `corp_idx`
    FOREIGN KEY ()
    REFERENCES `reservation`.`corp` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `reservation`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservation`.`member` (
  `member_idx` INT NOT NULL AUTO_INCREMENT COMMENT '회원 식별 키',
  `id` VARCHAR(200) NULL DEFAULT NULL,
  `pw` VARCHAR(200) NULL DEFAULT NULL,
  `nickname` VARCHAR(50) NULL DEFAULT NULL,
  `name` VARCHAR(50) NULL DEFAULT NULL,
  `phone_number` VARCHAR(200) NULL DEFAULT NULL,
  `join_date` DATE NULL DEFAULT NULL,
  `leave_date` DATE NULL DEFAULT NULL,
  `menber_state` INT NULL DEFAULT NULL,
  PRIMARY KEY (`member_idx`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `reservation`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservation`.`order` (
  `order_idx` INT NOT NULL AUTO_INCREMENT,
  `order_date` DATE NULL DEFAULT NULL,
  `cancle_date` DATE NULL DEFAULT NULL,
  `order_state` INT NULL DEFAULT NULL,
  `order_price` INT NULL DEFAULT NULL,
  PRIMARY KEY (`order_idx`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `reservation`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `reservation`.`room` (
  `room_idx` INT NOT NULL AUTO_INCREMENT,
  `room_state` INT NULL DEFAULT NULL,
  `image_main` VARCHAR(50) NULL DEFAULT NULL,
  `image_sub` VARCHAR(50) NULL DEFAULT NULL,
  `desc` VARCHAR(4000) NULL DEFAULT NULL,
  `max_count` INT NULL DEFAULT NULL,
  `min_count` INT NULL DEFAULT NULL,
  `price` INT NULL DEFAULT NULL,
  PRIMARY KEY (`room_idx`),
  CONSTRAINT `house_idx`
    FOREIGN KEY ()
    REFERENCES `reservation`.`house` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
