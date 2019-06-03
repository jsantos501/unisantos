CREATE SCHEMA `unisantos` ;

CREATE TABLE `unisantos`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(20) NULL,
  `senha` VARCHAR(10) NULL,
  `perfil` INT(1) NULL,
  `nome` VARCHAR(20) NULL,
  `cpf` BIGINT(11) NULL,
  `email` VARCHAR(20) NULL,
  `cep` INT(8) NULL,
  `celular` BIGINT(11) NULL,
  PRIMARY KEY (`id`));

  
  CREATE TABLE `unisantos`.`produto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pego` INT(1) NULL,
  `nome` VARCHAR(20) NULL,
  `marca` VARCHAR(20) NULL,
  `quantidade` INT(2) NULL,
  `descricao` VARCHAR(20) NULL,
  `valorProduto` DOUBLE NULL,
  `dataProduto` DATE NULL,
  `mercado` VARCHAR(20) NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `unisantos`.`compras` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(20) NULL,
  `data` DATE NULL,
  `idUser` INT NULL,
  PRIMARY KEY (`id`));

  
  
  CREATE TABLE `unisantos`.`mercado` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(20) NULL,
  `endereco` VARCHAR(20) NULL,
  `descricao` VARCHAR(20) NULL,
  `cidade` VARCHAR(20) NULL,
  `cep` INT(8) NULL,
  PRIMARY KEY (`id`));
  
  
  CREATE TABLE `unisantos`.`compras_produtos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idCompra` INT NULL,
  `idProduto` INT NULL,
  PRIMARY KEY (`id`));

  
  ALTER TABLE `unisantos`.`compras` 
ADD CONSTRAINT `idUser`
  FOREIGN KEY (`idUser`)
  REFERENCES `unisantos`.`usuario` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
  
  ALTER TABLE `unisantos`.`compras_produtos` 
ADD INDEX `id_idx` (`idCompra` ASC) VISIBLE,
ADD INDEX `idProduto_idx` (`idProduto` ASC) VISIBLE;
;
ALTER TABLE `unisantos`.`compras_produtos` 
ADD CONSTRAINT `idCompra`
  FOREIGN KEY (`idCompra`)
  REFERENCES `unisantos`.`compras` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `idProduto`
  FOREIGN KEY (`idProduto`)
  REFERENCES `unisantos`.`produto` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
