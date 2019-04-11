CREATE TABLE `activity` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `assignment` (
  `id` bigint(20) NOT NULL,
  `employeeId` bigint(20) NOT NULL,
  `projectId` bigint(20) NOT NULL,
  `activityId` bigint(20) NOT NULL,
  `workLoad` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `EmployeeIdProjectIdEmployeeId_UNIQUE` (`employeeId`,`projectId`,`activityId`),
  KEY `AssignmentProject` (`projectId`),
  KEY `AssigmentActivity` (`activityId`),
  CONSTRAINT `AssigmentActivity` FOREIGN KEY (`activityId`) REFERENCES `activity` (`id`),
  CONSTRAINT `AssignmentEmployee` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`),
  CONSTRAINT `AssignmentProject` FOREIGN KEY (`projectId`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `company` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `logo` varchar(45) NOT NULL,
  `ownerId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `NameLogo_UNIQUE` (`name`,`logo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL,
  `name` varchar(40) NOT NULL,
  `phone` varchar(40) DEFAULT NULL,
  `email` varchar(150) NOT NULL,
  `photoUrl` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PhotoUrlEmailPhoneName_UNIQUE` (`photoUrl`,`email`,`phone`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `integration` (
  `id` bigint(20) NOT NULL,
  `companyId` bigint(20) NOT NULL,
  `type` varchar(45) NOT NULL,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `TypeCompanyId_UNIQUE` (`companyId`,`type`),
  KEY `IntegrationCompany_idx` (`companyId`),
  CONSTRAINT `IntegrationCompany` FOREIGN KEY (`companyId`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `invitations` (
  `partnerId` bigint(20) NOT NULL,
  `code` varchar(40) NOT NULL,
  `dateEnd` datetime NOT NULL,
  `status` varchar(25) NOT NULL,
  PRIMARY KEY (`partnerId`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  CONSTRAINT `InvaitionPartner` FOREIGN KEY (`partnerId`) REFERENCES `partner` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `assignmentId` bigint(20) NOT NULL,
  `date` date NOT NULL,
  `order` int(11) NOT NULL,
  `time` double NOT NULL,
  `comment` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `OrderDataAssignmentId_UNIQUE` (`assignmentId`,`order`,`date`),
  KEY `LogsAssignment_idx` (`assignmentId`),
  CONSTRAINT `LogsAssignment` FOREIGN KEY (`assignmentId`) REFERENCES `assignment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8

CREATE TABLE `notification` (
  `id` bigint(20) NOT NULL,
  `partnerId` bigint(20) NOT NULL,
  `createdAt` datetime NOT NULL,
  `status` varchar(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` text,
  `link` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `NotificationPartner_idx` (`partnerId`),
  CONSTRAINT `NotificationPartner` FOREIGN KEY (`partnerId`) REFERENCES `partner` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `partner` (
  `id` bigint(20) NOT NULL,
  `companyId` bigint(20) NOT NULL,
  `employeeId` bigint(20) NOT NULL,
  `roleId` varchar(10) NOT NULL,
  `workLoad` int(11) DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `CompanyIdEmployeeId_UNIQUE` (`companyId`,`employeeId`),
  KEY `PartnerEmployeeId_idx` (`employeeId`),
  KEY `PartnerRoleId_idx` (`roleId`),
  CONSTRAINT `PartnerCompanyId` FOREIGN KEY (`companyId`) REFERENCES `company` (`id`),
  CONSTRAINT `PartnerEmployeeId` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`),
  CONSTRAINT `PartnerRoleId` FOREIGN KEY (`roleId`) REFERENCES `role` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `period` (
  `from` date NOT NULL,
  `to` date NOT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `project` (
  `id` bigint(20) NOT NULL,
  `companyId` bigint(20) NOT NULL,
  `name` varchar(45) NOT NULL,
  `logoUrl` varchar(100) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date DEFAULT NULL,
  `manHours` int(11) DEFAULT NULL,
  `code` varchar(45) NOT NULL,
  `color` varchar(20) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`,`logoUrl`),
  UNIQUE KEY `NameCompanyId_UNIQUE` (`companyId`,`name`),
  KEY `companyId_idx` (`companyId`),
  CONSTRAINT `companyId` FOREIGN KEY (`companyId`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `role` (
  `code` varchar(10) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `settings` (
  `id` bigint(20) NOT NULL,
  `companyId` bigint(20) NOT NULL,
  `type` varchar(45) NOT NULL,
  `value` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `TypeCompanyId_UNIQUE` (`companyId`,`type`),
  KEY `SettingCompany_idx` (`companyId`),
  CONSTRAINT `SettingCompany` FOREIGN KEY (`companyId`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `timesheet` (
  `id` bigint(20) NOT NULL,
  `assignmentId` bigint(20) NOT NULL,
  `periodId` bigint(20) NOT NULL,
  `timesheetJson` json NOT NULL,
  `status` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AssigmentPeriodId_UNIQUE` (`periodId`,`assignmentId`),
  KEY `TimesheetAssignment_idx` (`assignmentId`),
  CONSTRAINT `TimesheetAssignment` FOREIGN KEY (`assignmentId`) REFERENCES `assignment` (`id`),
  CONSTRAINT `TimesheetPeriod` FOREIGN KEY (`periodId`) REFERENCES `period` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8