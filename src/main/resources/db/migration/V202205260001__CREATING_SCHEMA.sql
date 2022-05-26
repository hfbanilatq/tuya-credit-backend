
USE `tuya_db` ;

-- -----------------------------------------------------
-- Table `credit_card`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `credit_card` ;

CREATE TABLE IF NOT EXISTS `credit_card` (
  `id` SMALLINT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `fee_value` DECIMAL NOT NULL,
  `max_fee` INT NOT NULL,
  `monthly_interest` DOUBLE NOT NULL,
  `effective_annual_interest` DOUBLE NOT NULL,
  `image_url` VARCHAR(255) NULL,

  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `document_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `document_type` ;

CREATE TABLE IF NOT EXISTS `document_type` (
  `id` SMALLINT NOT NULL AUTO_INCREMENT,
  `document_type` VARCHAR(120) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `users` ;

CREATE TABLE IF NOT EXISTS `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `document_number` VARCHAR(45) NOT NULL,
  `email` VARCHAR(120) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `document_type_id` SMALLINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `documentNumber_UNIQUE` (`document_number` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_user_document_type1_idx` (`document_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_document_type1`
    FOREIGN KEY (`document_type_id`)
    REFERENCES `document_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `simulation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `simulation` ;

CREATE TABLE IF NOT EXISTS `simulation` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `number_of_installments` INT NOT NULL,
  `created_at` DATE NOT NULL,
  `updated_at` DATE NOT NULL,
  `credit_card_id` SMALLINT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_simulation_credit_card1_idx` (`credit_card_id` ASC) VISIBLE,
  INDEX `fk_simulation_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_simulation_credit_card1`
    FOREIGN KEY (`credit_card_id`)
    REFERENCES `credit_card` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_simulation_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_role` ;

CREATE TABLE IF NOT EXISTS `user_role` (
  `id` SMALLINT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product` ;

CREATE TABLE IF NOT EXISTS `product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `reference` VARCHAR(45) NOT NULL,
  `image_url` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL,
  `price` DECIMAL NOT NULL,
  `warehouse` VARCHAR(45) NOT NULL,
  `discount` DOUBLE NULL,
  `discount_with_credit_card` DOUBLE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_user_role` ;

CREATE TABLE IF NOT EXISTS `user_has_user_role` (
  `user_id` INT NOT NULL,
  `user_role_id` SMALLINT NOT NULL,
  INDEX `fk_user_has_user_role_user_role1_idx` (`user_role_id` ASC) VISIBLE,
  INDEX `fk_user_has_user_role_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_user_role_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_role_user_role1`
    FOREIGN KEY (`user_role_id`)
    REFERENCES `user_role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `product_has_simulation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `product_has_simulation` ;

CREATE TABLE IF NOT EXISTS `product_has_simulation` (
  `product_id` BIGINT NOT NULL,
  `simulation_id` BIGINT NOT NULL,
  INDEX `fk_product_has_simulation_simulation1_idx` (`simulation_id` ASC) VISIBLE,
  INDEX `fk_product_has_simulation_product1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_has_simulation_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_has_simulation_simulation1`
    FOREIGN KEY (`simulation_id`)
    REFERENCES `simulation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;