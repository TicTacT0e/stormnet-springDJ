CREATE TABLE `Activities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `Assignments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employeeId` bigint(20) NOT NULL,
  `projectId` bigint(20) NOT NULL,
  `activityId` bigint(20) NOT NULL,
  `workLoad` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `EmployeeIdProjectIdActivityId_UNIQUE` (`employeeId`,`projectId`,`activityId`),
  KEY `AssignmentProject_idx` (`projectId`),
  KEY `AssignmentEmployee_idx` (`employeeId`),
  KEY `AssignmentActivity_idx` (`activityId`),
  CONSTRAINT `AssignmentActivity` FOREIGN KEY (`activityId`) REFERENCES `Activities` (`id`),
  CONSTRAINT `AssignmentEmployee` FOREIGN KEY (`employeeId`) REFERENCES `Employees` (`id`),
  CONSTRAINT `AssignmentProject` FOREIGN KEY (`projectId`) REFERENCES `Projects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `Companies` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `logo` varchar(45) NOT NULL,
  `ownerId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Name_UNIQUE` (`name`),
  UNIQUE KEY `Logo_UNIQUE` (`logo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `Employees` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `companyId` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `roleId` varchar(10) NOT NULL,
  `workLoad` int(11) DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UserIdCompanyId_UNIQUE` (`userId`,`companyId`),
  KEY `EmployeeRoleId_idx` (`roleId`),
  KEY `EmployeeCompanyId_idx` (`companyId`),
  KEY `EmployeeUserId_idx` (`userId`),
  CONSTRAINT `EmployeeCompanyId` FOREIGN KEY (`companyId`) REFERENCES `Companies` (`id`),
  CONSTRAINT `EmployeeRoleId` FOREIGN KEY (`roleId`) REFERENCES `Roles` (`code`),
  CONSTRAINT `EmployeeUserId` FOREIGN KEY (`userId`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `Integrations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `companyId` bigint(20) NOT NULL,
  `type` varchar(45) NOT NULL,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `TypeCompanyId_UNIQUE` (`companyId`,`type`),
  KEY `IntegrationCompany_idx` (`companyId`),
  CONSTRAINT `IntegrationCompany` FOREIGN KEY (`companyId`) REFERENCES `Companies` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `Invitations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employeeId` bigint(20) NOT NULL,
  `code` varchar(40) NOT NULL,
  `dateEnd` datetime NOT NULL,
  `status` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  UNIQUE KEY `PartnerId_UNIQUE` (`employeeId`),
  KEY `InvationPartner_idx` (`employeeId`),
  CONSTRAINT `InvationPartnerId` FOREIGN KEY (`employeeId`) REFERENCES `Employees` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `Logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `assignmentId` bigint(20) NOT NULL,
  `date` date NOT NULL,
  `order` int(11) NOT NULL,
  `time` double NOT NULL,
  `comment` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `OrderDataAssignmentId_UNIQUE` (`assignmentId`,`order`,`date`),
  KEY `LogsAssignment_idx` (`assignmentId`),
  CONSTRAINT `LogsAssignment` FOREIGN KEY (`assignmentId`) REFERENCES `Assignments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `Notifications` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employeeId` bigint(20) NOT NULL,
  `createdAt` datetime NOT NULL,
  `status` varchar(100) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` text,
  `link` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `NotificationEmployeeId_idx` (`employeeId`),
  UNIQUE KEY `employeeId_UNIQUE` (`employeeId`),
  CONSTRAINT `NotificationPartnerId` FOREIGN KEY (`employeeId`) REFERENCES `Employees` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `Projects` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
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
  UNIQUE KEY `CompanyIdName_UNIQUE` (`companyId`,`name`),
  KEY `companyId_idx` (`companyId`),
  CONSTRAINT `ProjectCompanyId` FOREIGN KEY (`companyId`) REFERENCES `Companies` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `Roles` (
  `code` varchar(10) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `Settings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `companyId` bigint(20) NOT NULL,
  `type` varchar(45) NOT NULL,
  `value` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `CompanyIdType_UNIQUE` (`companyId`,`type`),
  KEY `SettingCompany_idx` (`companyId`),
  CONSTRAINT `SettingCompanyId` FOREIGN KEY (`companyId`) REFERENCES `Companies` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `Timesheets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `assignmentId` bigint(20) NOT NULL,
  `status` varchar(100) NOT NULL,
  `TimesheetJson` varchar(250) DEFAULT NULL,
  `fromDate` date NOT NULL,
  `toDate` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AssignmentIdFromDateToDate_UNIQUE` (`assignmentId`,`fromDate`,`toDate`),
  KEY `TimesheetAssignment_idx` (`assignmentId`),
  CONSTRAINT `TimesheetAssignment` FOREIGN KEY (`assignmentId`) REFERENCES `Assignments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `Users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `phone` varchar(40) DEFAULT NULL,
  `email` varchar(150) NOT NULL,
  `photoUrl` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Email_UNIQUE` (`email`),
  UNIQUE KEY `PhotoUrlPhoneName_UNIQUE` (`photoUrl`,`phone`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8