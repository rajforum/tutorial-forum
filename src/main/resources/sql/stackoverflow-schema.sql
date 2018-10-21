DROP SCHEMA IF EXISTS stackoverflow;
CREATE SCHEMA stackoverflow;
USE stackoverflow;



--
--  1. Table structure for table `user_credential`
--

DROP TABLE IF EXISTS `userCredential`;

CREATE TABLE userCredential(
    `userId` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `contact_no` VARCHAR(13) NOT NULL UNIQUE,
    `email` VARCHAR(60) NOT NULL UNIQUE,
    `password` VARCHAR(20) NOT NULL,
    `role` ENUM('admin', 'user'), 
    
    PRIMARY KEY(`userID`)

)AUTO_INCREMENT = 1000 ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
--  1.1 Table structure for table `userProfile`
--


DROP TABLE IF EXISTS `userProfile`;

CREATE TABLE userProfile(
    `stackId` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `image`  VARCHAR(200) DEFAULT'-',
    `country` VARCHAR(50) NOT NULL,
    `sex` ENUM('male', 'female'),
    `dob` DATE,
    `userId` INT UNSIGNED NOT NULL,

    PRIMARY KEY(`stackID`),
    CONSTRAINT `fk_up_userid` FOREIGN KEY (`userId`) REFERENCES `UserCredential` (`userId`) ON UPDATE CASCADE ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
--  1.2 User authentication mapping
--


DROP TABLE IF EXISTS `userAccessMapping`;

CREATE TABLE userAccessMapping(
    `userAccessMapId` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	`profile_stackId` INT UNSIGNED NOT NULL,
    `authToken` VARCHAR(255) UNIQUE DEFAULT '-',
    `authToken_creation` TIMESTAMP,

    PRIMARY KEY(`userAccessMapId`),
    CONSTRAINT `fk_uam_profilestackid` FOREIGN KEY (`profile_stackId`) REFERENCES `userProfile` (`stackId`) ON UPDATE CASCADE ON DELETE CASCADE
    
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--
--  2. Table structure for table `queryTopic`
--

DROP TABLE IF EXISTS `queryTopic`;

CREATE TABLE `queryTopic`(
    `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `tagName` VARCHAR(100) NOT NULL,
    
    PRIMARY KEY(`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
--  2. Table structure for table `querySubTopic`
--

DROP TABLE IF EXISTS `querySubTopic`;

CREATE TABLE `querySubTopic`(
    `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `tagName` VARCHAR(100) NOT NULL,
    `super_tagId` INT UNSIGNED,
    
    PRIMARY KEY(`Id`),
    CONSTRAINT `fk_qst_supertagid` FOREIGN KEY (`super_tagId`) REFERENCES `queryTopic` (`Id`) ON UPDATE CASCADE ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--
--  4. Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;


CREATE TABLE `question` (
  `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `creationTime` TIMESTAMP,
  `title` VARCHAR(300) NOT NULL,
  `bodyText` MEDIUMTEXT NOT NULL, 
  `authorId` INT UNSIGNED NOT NULL,
  `vote` INT NOT NULL DEFAULT 0,

  PRIMARY KEY (`Id`),
  CONSTRAINT `fk_q_author` FOREIGN KEY (`authorId`) REFERENCES `userProfile` (`stackId`) ON DELETE RESTRICT ON UPDATE CASCADE
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--
--  2.1 Question tag mapping
--

DROP TABLE IF EXISTS `questionTagMapping`;

CREATE TABLE `questionTagMapping`(
    `questionId`INT UNSIGNED NOT NULL,
    `tagId`INT UNSIGNED NOT NULL,
    
    
    CONSTRAINT `fk_qtm_questionid` FOREIGN KEY (`questionId`) REFERENCES `question` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_qtm_tagID` FOREIGN KEY (`tagId`) REFERENCES `querySubTopic`(`Id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8;



--
--  3. Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;

CREATE TABLE `answer`(
    `answerId` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `queryId` INT  UNSIGNED NOT NULL,
    `userId` INT  UNSIGNED NOT NULL,
    `bodyText` MEDIUMTEXT NOT NULL,
    `vote` INT NOT NULL DEFAULT 0,
    
    PRIMARY KEY(`answerId`),
    CONSTRAINT `fk_qr_queryid` FOREIGN KEY (`queryId`) REFERENCES `question` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_qr_userid` FOREIGN KEY (`userId`) REFERENCES `userProfile` (`stackId`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;








--
--  5. Table structure for table `user_activity`
--

DROP TABLE IF EXISTS `userBookmarkMapping`;

CREATE TABLE `userBookmarkMapping`(
    `userId` INT  UNSIGNED NOT NULL,
    `questionId` INT UNSIGNED NOT NULL,
    
    CONSTRAINT `fk_ubm_userid` FOREIGN KEY (`userId`) REFERENCES `userProfile`(`stackId`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_ubm_questionid` FOREIGN KEY (`questionId`) REFERENCES `question`(`Id`) ON DELETE CASCADE  ON UPDATE CASCADE

    
)ENGINE=InnoDB DEFAULT CHARSET=utf8;








