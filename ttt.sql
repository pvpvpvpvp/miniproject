-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema reservation
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema reservation
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `reservation` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`member` (
  `mno` INT NOT NULL AUTO_INCREMENT,
  `mgrade` VARCHAR(45) NOT NULL COMMENT '회원들의 상태를 나타내는 값\n\n1 : 일반회원\n2 : 판매회원\n3 : 일반+판매회원\n4 : 탈퇴대기\n5 : 휴먼회원 #1년 이상 변동사항없음\n',
  `mid` VARCHAR(45) NOT NULL,
  `mpw` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`mno`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`inn`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`inn` (
  `ino` INT NOT NULL AUTO_INCREMENT,
  `iname` VARCHAR(45) NULL COMMENT '숙박업소명',
  `ilocaltion` VARCHAR(45) NULL COMMENT '숙박업소 소재지',
  `ihp` VARCHAR(45) NULL COMMENT '숙박업소연락처',
  `inumber_of_room` INT NULL COMMENT '객실수',
  `member_mno` INT NOT NULL,
  PRIMARY KEY (`ino`),
  UNIQUE INDEX `idinn_UNIQUE` (`ino` ASC) VISIBLE,
  INDEX `fk_inn_member1_idx` (`member_mno` ASC) VISIBLE,
  CONSTRAINT `fk_inn_member1`
    FOREIGN KEY (`member_mno`)
    REFERENCES `mydb`.`member` (`mno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`orders` (
  `ono` INT NOT NULL AUTO_INCREMENT,
  `ostate` INT NULL COMMENT '주문의 처리상태!\n\n1 : 결제 대기\n2 : 예약 요청(결제 후)\n3 : 예약 완료\n4 : 환불 요청\n5 : 환불 완료\n6 : ',
  `odate` DATE NOT NULL,
  `o_reservation_date` DATE NOT NULL,
  `inn_ino` INT NOT NULL,
  `member_mno` INT NOT NULL,
  `onumber` VARCHAR(45) NULL,
  PRIMARY KEY (`ono`),
  INDEX `fk_orders_inn_idx` (`inn_ino` ASC) VISIBLE,
  INDEX `fk_orders_member1_idx` (`member_mno` ASC) VISIBLE,
  CONSTRAINT `fk_orders_inn`
    FOREIGN KEY (`inn_ino`)
    REFERENCES `mydb`.`inn` (`ino`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_member1`
    FOREIGN KEY (`member_mno`)
    REFERENCES `mydb`.`member` (`mno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

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
