CREATE TABLE `activity` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `assignment` (
  `id` bigint(20) NOT NULL,
  `projectId` bigint(20) NOT NULL,
  `employeeId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  `workLoadInMinutes` int(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `projectId_UNIQUE` (`projectId`,`employeeId`),
  KEY `employee_idx` (`employeeId`),
  KEY `project_idx` (`projectId`),
  KEY `AssignmentRole_idx` (`roleId`),
  CONSTRAINT `AssignmentRole` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`),
  CONSTRAINT `employee` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`),
  CONSTRAINT `project` FOREIGN KEY (`projectId`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `company` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) NOT NULL,
  `logo` varchar(45) NOT NULL,
  `ownerId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `logo_UNIQUE` (`logo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  `name` varchar(40) NOT NULL,
  `phone` varchar(40) NOT NULL,
  `email` varchar(150) NOT NULL,
  `photoUrl` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `photoUrl_UNIQUE` (`photoUrl`,`name`,`phone`),
  KEY `EmployeeRoleId_idx` (`roleId`),
  CONSTRAINT `EmployeeRoleId` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `integration` (
  `id` bigint(20) NOT NULL,
  `companyId` bigint(20) NOT NULL,
  `type` varchar(45) NOT NULL,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IntegrationCompany_idx` (`companyId`),
  CONSTRAINT `IntegrationCompany` FOREIGN KEY (`companyId`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `invitations` (
  `partnerId` bigint(20) NOT NULL,
  `email` varchar(150) NOT NULL,
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
  `comment` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `LogsAssignment_idx` (`assignmentId`),
  CONSTRAINT `LogsAssignment` FOREIGN KEY (`assignmentId`) REFERENCES `assignment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8

CREATE TABLE `logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `assignmentId` bigint(20) NOT NULL,
  `date` date NOT NULL,
  `order` int(11) NOT NULL,
  `time` double NOT NULL,
  `comment` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `LogsAssignment_idx` (`assignmentId`),
  CONSTRAINT `LogsAssignment` FOREIGN KEY (`assignmentId`) REFERENCES `assignment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8

CREATE TABLE `notification` (
  `id` bigint(20) NOT NULL,
  `partnerId` bigint(20) NOT NULL,
  `createdAt` datetime NOT NULL,
  `employeeId` bigint(20) NOT NULL,
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
  `workLoad` int(11) DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `PartnerCompany_idx` (`companyId`),
  KEY `PartnerEmployee_idx` (`employeeId`),
  CONSTRAINT `PartnerCompany` FOREIGN KEY (`companyId`) REFERENCES `company` (`id`),
  CONSTRAINT `PartnerEmployee` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `period` (
  `from` date NOT NULL,
  `to` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `project` (
  `id` bigint(20) NOT NULL,
  `companyId` bigint(20) NOT NULL,
  `name` varchar(45) NOT NULL,
  `logoUrl` varchar(100) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `manHours` int(11) NOT NULL,
  `code` varchar(45) NOT NULL,
  `colour` varchar(20) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`,`logoUrl`),
  KEY `companyId_idx` (`companyId`),
  CONSTRAINT `companyId` FOREIGN KEY (`companyId`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `owner` varchar(45) NOT NULL,
  `admin` varchar(45) NOT NULL,
  `manager` varchar(45) NOT NULL,
  `hr` varchar(45) NOT NULL,
  `employee` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `settings` (
  `id` bigint(20) NOT NULL,
  `companyId` bigint(20) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `SettingCompany_idx` (`companyId`),
  CONSTRAINT `SettingCompany` FOREIGN KEY (`companyId`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `timesheet` (
  `id` bigint(20) NOT NULL,
  `period` int(11) NOT NULL,
  `timesheetJson` json NOT NULL,
  `status` varchar(100) NOT NULL,
  `assignmentId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `TimeSheetAssigment_idx` (`assignmentId`),
  CONSTRAINT `TimeSheetAssigment` FOREIGN KEY (`assignmentId`) REFERENCES `assignment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8