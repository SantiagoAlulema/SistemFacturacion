-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema  facturacion
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema  facturacion
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS ` facturacion` DEFAULT CHARACTER SET utf8 ;
USE ` facturacion` ;

-- -----------------------------------------------------
-- Table ` facturacion`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ` facturacion`.`Cliente` (
  `Cedula` VARCHAR(10) NOT NULL,
  `Primer Nombre` VARCHAR(45) NULL,
  `SegudoNombre` VARCHAR(45) NULL,
  `PrimerApellido` VARCHAR(45) NULL,
  `SegudoApellido` VARCHAR(45) NULL,
  `Telefono` VARCHAR(45) NULL,
  `Email` VARCHAR(45) NULL,
  `Estado` VARCHAR(2) NULL,
  `Clientecol` VARCHAR(45) NULL,
  PRIMARY KEY (`Cedula`),
  UNIQUE INDEX `Cedula_UNIQUE` (`Cedula` ASC)   )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ` facturacion`.`Rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ` facturacion`.`Rol` (
  `idRol` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `Detalle` VARCHAR(100) NULL,
  PRIMARY KEY (`idRol`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ` facturacion`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ` facturacion`.`Usuario` (
  `Cedula` VARCHAR(10) NOT NULL,
  `PrimerNombre` VARCHAR(45) NULL,
  `SegundoNombre` VARCHAR(45) NULL,
  `PrimerApellido` VARCHAR(45) NULL,
  `SegundoApellido` VARCHAR(45) NULL,
  `FechaNacimiento` DATE NULL,
  `Telefono` VARCHAR(45) NULL,
  `Estado` VARCHAR(2) NULL,
  `idRol` INT NOT NULL,
  PRIMARY KEY (`Cedula`),
  INDEX `fk_Usuario_Rol_idx` (`idRol` ASC)   ,
  CONSTRAINT `fk_Usuario_Rol`
    FOREIGN KEY (`idRol`)
    REFERENCES ` facturacion`.`Rol` (`idRol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ` facturacion`.`Bodega`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ` facturacion`.`Bodega` (
  `idBodega` INT NOT NULL,
  `Detalle` VARCHAR(45) NULL,
  `Ubicacion` VARCHAR(45) NULL,
  PRIMARY KEY (`idBodega`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ` facturacion`.`DocumentoPago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ` facturacion`.`DocumentoPago` (
  `idDocumento` INT NOT NULL AUTO_INCREMENT,
  `Fecha` DATE NULL,
  `Subtotal` DECIMAL(6,2) NULL,
  `Total` DECIMAL(6,2) NULL,
  `iva` DECIMAL(6,2) NULL,
  `Estado` INT NULL,
  `Cedula` VARCHAR(45) NOT NULL,
  `UsuarioCedula` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idDocumento`),
  INDEX `fk_Factura_Cliente1_idx` (`Cedula` ASC)   ,
  INDEX `fk_Factura_Usuario1_idx` (`UsuarioCedula` ASC)   ,
  CONSTRAINT `fk_Factura_Cliente1`
    FOREIGN KEY (`Cedula`)
    REFERENCES ` facturacion`.`Cliente` (`Cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Factura_Usuario1`
    FOREIGN KEY (`UsuarioCedula`)
    REFERENCES ` facturacion`.`Usuario` (`Cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ` facturacion`.`Provedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ` facturacion`.`Provedor` (
  `idProvedor` INT NOT NULL,
  `Nombre` VARCHAR(45) NULL,
  `Ubicacion` VARCHAR(45) NULL,
  `Telefono` VARCHAR(45) NULL,
  PRIMARY KEY (`idProvedor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ` facturacion`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ` facturacion`.`Categoria` (
  `idCategoria` INT NOT NULL,
  `Nombre` VARCHAR(45) NULL,
  `Detalle` VARCHAR(100) NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ` facturacion`.`Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ` facturacion`.`Producto` (
  `idProducto` INT NOT NULL AUTO_INCREMENT,
  `Cod_Barras` VARCHAR(45) NULL,
  `Cod_Producto` VARCHAR(45) NOT NULL,
  `Nombre` VARCHAR(45) NULL,
  `Detalle` VARCHAR(150) NULL,
  `Stock` INT NULL,
  `Estado` VARCHAR(45) NULL,
  `Precio_Compra` DOUBLE NULL,
  `Precio_Venta` DOUBLE NULL,
  `idBodega` INT NOT NULL,
  `idProvedor` INT NOT NULL,
  `idCategoria` INT NOT NULL,
  PRIMARY KEY (`idProducto`),
  INDEX `fk_Producto_Bodega1_idx` (`idBodega` ASC)   ,
  INDEX `fk_Producto_Provedor1_idx` (`idProvedor` ASC)   ,
  INDEX `fk_Producto_Categoria1_idx` (`idCategoria` ASC)   ,
  UNIQUE INDEX `Cod_Producto_UNIQUE` (`Cod_Producto` ASC)   ,
  CONSTRAINT `fk_Producto_Bodega1`
    FOREIGN KEY (`idBodega`)
    REFERENCES ` facturacion`.`Bodega` (`idBodega`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Producto_Provedor1`
    FOREIGN KEY (`idProvedor`)
    REFERENCES ` facturacion`.`Provedor` (`idProvedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Producto_Categoria1`
    FOREIGN KEY (`idCategoria`)
    REFERENCES ` facturacion`.`Categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ` facturacion`.`DetalleFactura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ` facturacion`.`DetalleFactura` (
  `idFactura` INT NOT NULL,
  `idProducto` INT NOT NULL,
  `PrecioUnit` DOUBLE NULL,
  `Cantidad` INT NULL,
  `TotalProducto` DOUBLE NULL,
  PRIMARY KEY (`idFactura`, `idProducto`),
  INDEX `fk_Factura_has_Producto_Producto1_idx` (`idProducto` ASC)   ,
  INDEX `fk_Factura_has_Producto_Factura1_idx` (`idFactura` ASC)   ,
  CONSTRAINT `fk_Factura_has_Producto_Factura1`
    FOREIGN KEY (`idFactura`)
    REFERENCES ` facturacion`.`DocumentoPago` (`idDocumento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Factura_has_Producto_Producto1`
    FOREIGN KEY (`idProducto`)
    REFERENCES ` facturacion`.`Producto` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ` facturacion`.`Devolucion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ` facturacion`.`Devolucion` (
  `idDevolucion` INT NOT NULL,
  `Motivo` VARCHAR(150) NULL,
  `FechaDevolucion` DATE NULL,
  `Cantidad` INT NULL,
  `idFactura` INT NOT NULL,
  PRIMARY KEY (`idDevolucion`),
  INDEX `fk_Devolucion_Factura1_idx` (`idFactura` ASC)   ,
  CONSTRAINT `fk_Devolucion_Factura1`
    FOREIGN KEY (`idFactura`)
    REFERENCES ` facturacion`.`DocumentoPago` (`idDocumento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ` facturacion`.`ConsumidorFinal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ` facturacion`.`ConsumidorFinal` (
  `idDocumento` INT NOT NULL,
  `IdConsumidorFinal` INT NOT NULL AUTO_INCREMENT,
  INDEX `fk_ConsumidorFinal_DocumentoPago1_idx` (`idDocumento` ASC)   ,
  PRIMARY KEY (`IdConsumidorFinal`),
  CONSTRAINT `fk_ConsumidorFinal_DocumentoPago1`
    FOREIGN KEY (`idDocumento`)
    REFERENCES ` facturacion`.`DocumentoPago` (`idDocumento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ` facturacion`.`Factura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ` facturacion`.`Factura` (
  `idFactura` INT NOT NULL AUTO_INCREMENT,
  `idDocumento` INT NOT NULL,
  PRIMARY KEY (`idFactura`),
  INDEX `fk_Factura_DocumentoPago1_idx` (`idDocumento` ASC)   ,
  CONSTRAINT `fk_Factura_DocumentoPago1`
    FOREIGN KEY (`idDocumento`)
    REFERENCES ` facturacion`.`DocumentoPago` (`idDocumento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
