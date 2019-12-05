-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema facturacion
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema facturacion
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `facturacion` DEFAULT CHARACTER SET utf8 ;
USE `facturacion` ;

-- -----------------------------------------------------
-- Table `facturacion`.`Rol`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`Rol` (
  `idRol` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `Detalle` VARCHAR(100) NULL,
  PRIMARY KEY (`idRol`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facturacion`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`Usuario` (
  `Cedula` VARCHAR(10) NOT NULL,
  `PrimerNombre` VARCHAR(45) NOT NULL,
  `SegundoNombre` VARCHAR(45) NOT NULL,
  `PrimerApellido` VARCHAR(45) NOT NULL,
  `SegundoApellido` VARCHAR(45) NOT NULL,
  `FechaNacimiento` DATE NOT NULL,
  `Telefono` VARCHAR(10) NOT NULL,
  `Estado` VARCHAR(2) NOT NULL,
  `password` BLOB(500) NOT NULL,
  `idRol` INT NOT NULL,
  PRIMARY KEY (`Cedula`),
  INDEX `fk_Usuario_Rol_idx` (`idRol` ASC) ,
  CONSTRAINT `fk_Usuario_Rol`
    FOREIGN KEY (`idRol`)
    REFERENCES `facturacion`.`Rol` (`idRol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facturacion`.`Bodega`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`Bodega` (
  `idBodega` INT NOT NULL,
  `Detalle` VARCHAR(45) NULL,
  `Ubicacion` VARCHAR(45) NULL,
  PRIMARY KEY (`idBodega`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facturacion`.`Tienda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`Tienda` (
  `idTienda` INT NOT NULL,
  `Nombre` VARCHAR(45) NULL,
  `RUC` VARCHAR(45) NULL,
  `Direccion` VARCHAR(45) NULL,
  `Telefono` VARCHAR(45) NULL,
  PRIMARY KEY (`idTienda`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facturacion`.`DocumentoPago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`DocumentoPago` (
  `idDocumento` INT NOT NULL AUTO_INCREMENT,
  `Fecha` DATE NULL,
  `Subtotal` DECIMAL(6,2) NULL,
  `Total` DECIMAL(6,2) NULL,
  `iva` DECIMAL(6,2) NULL,
  `Estado` INT NULL,
  `UsuarioUsuario` VARCHAR(45) NOT NULL,
  `idTienda` INT NOT NULL,
  `idCliente` INT NOT NULL,
  PRIMARY KEY (`idDocumento`),
  INDEX `fk_Factura_Usuario1_idx` (`UsuarioUsuario` ASC) ,
  INDEX `fk_DocumentoPago_Tienda1_idx` (`idTienda` ASC) ,
  CONSTRAINT `fk_Factura_Usuario1`
    FOREIGN KEY (`UsuarioUsuario`)
    REFERENCES `facturacion`.`Usuario` (`Cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DocumentoPago_Tienda1`
    FOREIGN KEY (`idTienda`)
    REFERENCES `facturacion`.`Tienda` (`idTienda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facturacion`.`Provedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`Provedor` (
  `idProvedor` INT NOT NULL,
  `Nombre` VARCHAR(45) NULL,
  `Ubicacion` VARCHAR(45) NULL,
  `Telefono` VARCHAR(45) NULL,
  PRIMARY KEY (`idProvedor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facturacion`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`Categoria` (
  `idCategoria` INT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Detalle` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facturacion`.`Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`Producto` (
  `idProducto` INT NOT NULL AUTO_INCREMENT,
  `Cod_Barras` VARCHAR(45) NOT NULL,
  `Cod_Producto` VARCHAR(45) NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Detalle` VARCHAR(150) NOT NULL,
  `Stock` INT NOT NULL,
  `Estado` VARCHAR(45) NOT NULL,
  `Precio_Compra` DOUBLE NOT NULL,
  `Precio_Venta` DOUBLE NOT NULL,
  `idBodega` INT NOT NULL,
  `idProvedor` INT NOT NULL,
  `idCategoria` INT NOT NULL,
  PRIMARY KEY (`idProducto`),
  INDEX `fk_Producto_Bodega1_idx` (`idBodega` ASC) ,
  INDEX `fk_Producto_Provedor1_idx` (`idProvedor` ASC) ,
  INDEX `fk_Producto_Categoria1_idx` (`idCategoria` ASC) ,
  UNIQUE INDEX `Cod_Producto_UNIQUE` (`Cod_Producto` ASC) ,
  CONSTRAINT `fk_Producto_Bodega1`
    FOREIGN KEY (`idBodega`)
    REFERENCES `facturacion`.`Bodega` (`idBodega`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Producto_Provedor1`
    FOREIGN KEY (`idProvedor`)
    REFERENCES `facturacion`.`Provedor` (`idProvedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Producto_Categoria1`
    FOREIGN KEY (`idCategoria`)
    REFERENCES `facturacion`.`Categoria` (`idCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facturacion`.`DetalleFactura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`DetalleFactura` (
  `idFactura` INT NOT NULL,
  `idProducto` INT NOT NULL,
  `PrecioUnit` DOUBLE NULL,
  `Cantidad` INT NULL,
  `TotalProducto` DOUBLE NULL,
  PRIMARY KEY (`idFactura`, `idProducto`),
  INDEX `fk_Factura_has_Producto_Producto1_idx` (`idProducto` ASC) ,
  INDEX `fk_Factura_has_Producto_Factura1_idx` (`idFactura` ASC) ,
  CONSTRAINT `fk_Factura_has_Producto_Factura1`
    FOREIGN KEY (`idFactura`)
    REFERENCES `facturacion`.`DocumentoPago` (`idDocumento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Factura_has_Producto_Producto1`
    FOREIGN KEY (`idProducto`)
    REFERENCES `facturacion`.`Producto` (`idProducto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facturacion`.`Devolucion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`Devolucion` (
  `idDevolucion` INT NOT NULL,
  `Motivo` VARCHAR(150) NULL,
  `FechaDevolucion` DATE NULL,
  `Cantidad` INT NULL,
  `idFactura` INT NOT NULL,
  PRIMARY KEY (`idDevolucion`),
  INDEX `fk_Devolucion_Factura1_idx` (`idFactura` ASC) ,
  CONSTRAINT `fk_Devolucion_Factura1`
    FOREIGN KEY (`idFactura`)
    REFERENCES `facturacion`.`DocumentoPago` (`idDocumento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facturacion`.`ConsumidorFinal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`ConsumidorFinal` (
  `idDocumento` INT NOT NULL,
  `IdConsumidorFinal` INT NOT NULL AUTO_INCREMENT,
  INDEX `fk_ConsumidorFinal_DocumentoPago1_idx` (`idDocumento` ASC) ,
  PRIMARY KEY (`IdConsumidorFinal`),
  CONSTRAINT `fk_ConsumidorFinal_DocumentoPago1`
    FOREIGN KEY (`idDocumento`)
    REFERENCES `facturacion`.`DocumentoPago` (`idDocumento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facturacion`.`Factura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`Factura` (
  `idFactura` INT NOT NULL AUTO_INCREMENT,
  `idDocumento` INT NOT NULL,
  PRIMARY KEY (`idFactura`),
  INDEX `fk_Factura_DocumentoPago1_idx` (`idDocumento` ASC) ,
  CONSTRAINT `fk_Factura_DocumentoPago1`
    FOREIGN KEY (`idDocumento`)
    REFERENCES `facturacion`.`DocumentoPago` (`idDocumento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facturacion`.`TipoRuc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`TipoRuc` (
  `idTipoRuc` INT NOT NULL,
  `Nombre` VARCHAR(45) NULL,
  `Descripcion` VARCHAR(120) NULL,
  PRIMARY KEY (`idTipoRuc`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facturacion`.`PCliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`PCliente` (
  `idPCliente` INT NOT NULL AUTO_INCREMENT,
  `PrimerNombre` VARCHAR(45) NULL,
  `SegundoNombre` VARCHAR(45) NULL,
  `PrimerApellido` VARCHAR(45) NULL,
  `SegundoApellido` VARCHAR(45) NULL,
  `Telefono` VARCHAR(15) NULL,
  `Direccion` VARCHAR(100) NULL,
  `Estado` VARCHAR(2) NULL,
  `FechaIngreso` DATE NULL,
  `Email` VARCHAR(100) NULL,
  PRIMARY KEY (`idPCliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facturacion`.`Ruc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`Ruc` (
  `Ruc` VARCHAR(13) NOT NULL,
  `idTipoRuc` INT NOT NULL,
  `idPCliente` INT NOT NULL,
  PRIMARY KEY (`Ruc`),
  INDEX `fk_Ruc_TipoRuc1_idx` (`idTipoRuc` ASC) ,
  INDEX `fk_Ruc_PCliente1_idx` (`idPCliente` ASC) ,
  CONSTRAINT `fk_Ruc_TipoRuc1`
    FOREIGN KEY (`idTipoRuc`)
    REFERENCES `facturacion`.`TipoRuc` (`idTipoRuc`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Ruc_PCliente1`
    FOREIGN KEY (`idPCliente`)
    REFERENCES `facturacion`.`PCliente` (`idPCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `facturacion`.`Cedula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `facturacion`.`Cedula` (
  `Cedula` VARCHAR(10) NOT NULL,
  `idPCliente` INT NOT NULL,
  PRIMARY KEY (`Cedula`),
  INDEX `fk_Cedula_PCliente1_idx` (`idPCliente` ASC) ,
  CONSTRAINT `fk_Cedula_PCliente1`
    FOREIGN KEY (`idPCliente`)
    REFERENCES `facturacion`.`PCliente` (`idPCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
