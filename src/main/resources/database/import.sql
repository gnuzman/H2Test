DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `Id` INT IDENTITY PRIMARY KEY  ,
  `countryname` varchar(80) DEFAULT '',
  `countrycode` varchar(80) DEFAULT '',
);