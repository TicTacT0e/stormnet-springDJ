CREATE TABLE `Project` (
  `id` int(11) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `logoUrl` VARCHAR(200) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `manHours` int(11) NOT NULL,
  `code` VARCHAR(6) NOT NULL,
  `colour` VARCHAR(6) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `Employee` (
  `id` BIGINT NOT NULL,
  `firstName` VARCHAR(40) NULL,
  `lastName` VARCHAR(40) NULL,
  `email` VARCHAR(150) NOT NULL,
  `positionId` BIGINT NULL,
  `photoUrl` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  `Position` (
  `id` BIGINT NOT NULL,
  `position` VARCHAR(100) NOT NULL,
  `companyId` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `companyId_UNIQUE, position_UNIQUE` (`companyId, position` ASC) VISIBLE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Assignment (
  projectId BIGINT NOT NULL,
  employeeId BIGINT NOT NULL,
  workLoadInMinutes int,
  CONSTRAINT PK_Assigment PRIMARY KEY 
  (projectId, employeeId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Invitations (
  employeeId BIGINT NOT NULL PRIMARY KEY,
  companyId BIGINT NOT NULL,
  email VARCHAR(150) NOT NULL,
  invitationsCode VARCHAR(40) NOT NULL,
  dateEnd DATETIME NOT NULL,
  status varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table Timesheet (
  id BIGINT NOT NULL primary key,
  periodId BIGINT NOT NULL,
  timesheetJson json NOT NULL,
  status varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `projectId` bigint(20) NOT NULL,
  `employeeId` bigint(20) NOT NULL,
  `time` double NOT NULL,
  `comment` varchar(100) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


create table Notification (
id bigint NOT NULL PRIMARY KEY,
createdAt DATETIME NOT NULL,
employeeId bigint NOT NULL,
status varchar(100) NOT NULL,
title varchar(100) NOT NULL,
description text(200),
link varchar(100)
);

CREATE TABLE `company` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `logo` VARCHAR(45) NOT NULL,
  `ownerId` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `settings` (
  `id` BIGINT NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `value` VARCHAR(45) NOT NULL,
  `companyId` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE `integration` (
  `id` BIGINT NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `url` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `pass` VARCHAR(45) NOT NULL,
  `companyId` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;