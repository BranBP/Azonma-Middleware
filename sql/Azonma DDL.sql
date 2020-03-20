-- MySQL Script generated by MySQL Workbench
-- Sat Mar  7 20:40:47 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema azonma
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema azonma
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `azonma` DEFAULT CHARACTER SET utf8 ;
USE `azonma` ;

-- -----------------------------------------------------
-- Table `azonma`.`SEXO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azonma`.`SEXO` (
  `ID_SEXO` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`ID_SEXO`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azonma`.`IDIOMA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azonma`.`IDIOMA` (
  `ID_IDIOMA` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`ID_IDIOMA`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azonma`.`ESTADO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azonma`.`ESTADO` (
  `ID_ESTADO` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_ESTADO`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azonma`.`USUARIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azonma`.`USUARIO` (
  `ID_USUARIO` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `EMAIL` VARCHAR(256) NOT NULL,
  `CONTRASENA` VARCHAR(256) NOT NULL,
  `FECHA_NACIMIENTO` DATE NOT NULL,
  `NOMBRE` VARCHAR(20) NOT NULL,
  `APELLIDO1` VARCHAR(20) NOT NULL,
  `APELLIDO2` VARCHAR(25) NULL,
  `ID_SEXO` VARCHAR(1) NOT NULL,
  `ID_IDIOMA` INT UNSIGNED NOT NULL,
  `ID_ESTADO` INT NOT NULL,
  PRIMARY KEY (`ID_USUARIO`),
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE,
  INDEX `fk_USUARIO_SEXO1_idx` (`ID_SEXO` ASC) VISIBLE,
  INDEX `fk_USUARIO_IDIOMA1_idx` (`ID_IDIOMA` ASC) VISIBLE,
  INDEX `fk_USUARIO_ESTADO1_idx` (`ID_ESTADO` ASC) VISIBLE,
  CONSTRAINT `fk_USUARIO_SEXO1`
    FOREIGN KEY (`ID_SEXO`)
    REFERENCES `azonma`.`SEXO` (`ID_SEXO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USUARIO_IDIOMA1`
    FOREIGN KEY (`ID_IDIOMA`)
    REFERENCES `azonma`.`IDIOMA` (`ID_IDIOMA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USUARIO_ESTADO1`
    FOREIGN KEY (`ID_ESTADO`)
    REFERENCES `azonma`.`ESTADO` (`ID_ESTADO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azonma`.`PEDIDO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azonma`.`PEDIDO` (
  `ID_PEDIDO` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `FECHA` DATE NOT NULL,
  `PRECIO_TOTAL` DECIMAL(12) UNSIGNED NOT NULL,
  `CARRITO` TINYINT(1) NOT NULL,
  `ID_USUARIO` INT UNSIGNED NOT NULL,
  `ID_ESTADO` INT NOT NULL,
  INDEX `fk_table2_USUARIO_idx` (`ID_USUARIO` ASC) VISIBLE,
  PRIMARY KEY (`ID_PEDIDO`),
  INDEX `fk_PEDIDO_ESTADO1_idx` (`ID_ESTADO` ASC) VISIBLE,
  CONSTRAINT `fk_table2_USUARIO`
    FOREIGN KEY (`ID_USUARIO`)
    REFERENCES `azonma`.`USUARIO` (`ID_USUARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PEDIDO_ESTADO1`
    FOREIGN KEY (`ID_ESTADO`)
    REFERENCES `azonma`.`ESTADO` (`ID_ESTADO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azonma`.`CATEGORIA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azonma`.`CATEGORIA` (
  `ID_CATEGORIA` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ID_CATEGORIA_PADRE` INT UNSIGNED NULL,
  PRIMARY KEY (`ID_CATEGORIA`),
  INDEX `fk_CATEGORIA_CATEGORIA1_idx` (`ID_CATEGORIA_PADRE` ASC) VISIBLE,
  CONSTRAINT `fk_CATEGORIA_CATEGORIA1`
    FOREIGN KEY (`ID_CATEGORIA_PADRE`)
    REFERENCES `azonma`.`CATEGORIA` (`ID_CATEGORIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azonma`.`PRODUCTO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azonma`.`PRODUCTO` (
  `ID_PRODUCTO` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `PRECIO` DECIMAL(12) UNSIGNED NOT NULL,
  `ID_CATEGORIA` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`ID_PRODUCTO`),
  INDEX `fk_PRODUCTO_CATEGORIA1_idx` (`ID_CATEGORIA` ASC) VISIBLE,
  CONSTRAINT `fk_PRODUCTO_CATEGORIA1`
    FOREIGN KEY (`ID_CATEGORIA`)
    REFERENCES `azonma`.`CATEGORIA` (`ID_CATEGORIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azonma`.`LINEA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azonma`.`LINEA` (
  `ID_LINEA` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `PRECIO_UNITARIO` DECIMAL(12) UNSIGNED NOT NULL,
  `PRECIO_TOTAL` DECIMAL(12) UNSIGNED NOT NULL,
  `UNIDADES` INT UNSIGNED NOT NULL,
  `VALORACION` INT NULL,
  `ID_PEDIDO` INT UNSIGNED NOT NULL,
  `ID_PRODUCTO` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`ID_LINEA`),
  INDEX `fk_LINEA_PEDIDO1_idx` (`ID_PEDIDO` ASC) VISIBLE,
  INDEX `fk_LINEA_PRODUCTO1_idx` (`ID_PRODUCTO` ASC) VISIBLE,
  CONSTRAINT `fk_LINEA_PEDIDO1`
    FOREIGN KEY (`ID_PEDIDO`)
    REFERENCES `azonma`.`PEDIDO` (`ID_PEDIDO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LINEA_PRODUCTO1`
    FOREIGN KEY (`ID_PRODUCTO`)
    REFERENCES `azonma`.`PRODUCTO` (`ID_PRODUCTO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azonma`.`PAIS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azonma`.`PAIS` (
  `ID_PAIS` INT UNSIGNED NOT NULL,
  `ISO` VARCHAR(2) NOT NULL,
  `NOMBRE` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`ID_PAIS`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azonma`.`PROVINCIA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azonma`.`PROVINCIA` (
  `ID_PROVINCIA` INT UNSIGNED NOT NULL,
  `NOMBRE` VARCHAR(80) NOT NULL,
  `ID_PAIS` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`ID_PROVINCIA`),
  INDEX `fk_PROVINCIA_PAIS1_idx` (`ID_PAIS` ASC) VISIBLE,
  CONSTRAINT `fk_PROVINCIA_PAIS1`
    FOREIGN KEY (`ID_PAIS`)
    REFERENCES `azonma`.`PAIS` (`ID_PAIS`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azonma`.`LOCALIDAD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azonma`.`LOCALIDAD` (
  `ID_LOCALIDAD` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `CIUDAD` VARCHAR(80) NOT NULL,
  `ID_PROVINCIA` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`ID_LOCALIDAD`),
  INDEX `fk_LOCALIZACION_PROVINCIA1_idx` (`ID_PROVINCIA` ASC) VISIBLE,
  CONSTRAINT `fk_LOCALIZACION_PROVINCIA1`
    FOREIGN KEY (`ID_PROVINCIA`)
    REFERENCES `azonma`.`PROVINCIA` (`ID_PROVINCIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azonma`.`DIRECCION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azonma`.`DIRECCION` (
  `ID_DIRECCION` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(80) NOT NULL,
  `CALLE` VARCHAR(120) NOT NULL,
  `ID_LOCALIDAD` INT UNSIGNED NOT NULL,
  `ID_USUARIO` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`ID_DIRECCION`),
  INDEX `fk_DIRECCION_LOCALIZACION1_idx` (`ID_LOCALIDAD` ASC) VISIBLE,
  INDEX `fk_DIRECCION_USUARIO1_idx` (`ID_USUARIO` ASC) VISIBLE,
  CONSTRAINT `fk_DIRECCION_LOCALIZACION1`
    FOREIGN KEY (`ID_LOCALIDAD`)
    REFERENCES `azonma`.`LOCALIDAD` (`ID_LOCALIDAD`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DIRECCION_USUARIO1`
    FOREIGN KEY (`ID_USUARIO`)
    REFERENCES `azonma`.`USUARIO` (`ID_USUARIO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azonma`.`PRODUCTO_IDIOMA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azonma`.`PRODUCTO_IDIOMA` (
  `ID_PRODUCTO` INT UNSIGNED NOT NULL,
  `ID_IDIOMA` INT UNSIGNED NOT NULL,
  `NOMBRE` VARCHAR(80) NOT NULL,
  `DESCRIPCION` VARCHAR(256) NOT NULL,
  INDEX `fk_PRODUCTO_has_IDIOMA_IDIOMA1_idx` (`ID_IDIOMA` ASC) VISIBLE,
  INDEX `fk_PRODUCTO_has_IDIOMA_PRODUCTO1_idx` (`ID_PRODUCTO` ASC) VISIBLE,
  PRIMARY KEY (`ID_PRODUCTO`, `ID_IDIOMA`),
  CONSTRAINT `fk_PRODUCTO_has_IDIOMA_PRODUCTO1`
    FOREIGN KEY (`ID_PRODUCTO`)
    REFERENCES `azonma`.`PRODUCTO` (`ID_PRODUCTO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PRODUCTO_has_IDIOMA_IDIOMA1`
    FOREIGN KEY (`ID_IDIOMA`)
    REFERENCES `azonma`.`IDIOMA` (`ID_IDIOMA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `azonma`.`CATEGORIA_IDIOMA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `azonma`.`CATEGORIA_IDIOMA` (
  `ID_CATEGORIA` INT UNSIGNED NOT NULL,
  `ID_IDIOMA` INT UNSIGNED NOT NULL,
  `NOMBRE` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`ID_CATEGORIA`, `ID_IDIOMA`),
  INDEX `fk_CATEGORIA_has_IDIOMA_IDIOMA1_idx` (`ID_IDIOMA` ASC) VISIBLE,
  INDEX `fk_CATEGORIA_has_IDIOMA_CATEGORIA1_idx` (`ID_CATEGORIA` ASC) VISIBLE,
  CONSTRAINT `fk_CATEGORIA_has_IDIOMA_CATEGORIA1`
    FOREIGN KEY (`ID_CATEGORIA`)
    REFERENCES `azonma`.`CATEGORIA` (`ID_CATEGORIA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CATEGORIA_has_IDIOMA_IDIOMA1`
    FOREIGN KEY (`ID_IDIOMA`)
    REFERENCES `azonma`.`IDIOMA` (`ID_IDIOMA`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;