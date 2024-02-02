drop database if exists unisa;
create database unisa;
use unisa;

#Developer (developerId, firstName, lastName, bio, mail, passwordAccount)
CREATE TABLE Developer
(
    developerId INT NOT NULL AUTO_INCREMENT
    ,firstName VARCHAR(255) NOT NULL
    ,lastName VARCHAR(255) NOT NULL
    ,bio TEXT NOT NULL
    ,mail VARCHAR(255) NOT NULL
    ,passwordAccount VARCHAR(20) NOT NULL
    ,PRIMARY KEY (developerId)
    ,UNIQUE KEY (mail)
);


#Employer (employerId, firstName, lastName, mail, passwordAccount)
CREATE TABLE Employer
(
    employerId INT NOT NULL AUTO_INCREMENT
    ,firstName VARCHAR(255) NOT NULL
    ,lastName VARCHAR(255) NOT NULL
    ,mail VARCHAR(255) NOT NULL
    ,passwordAccount VARCHAR(20) NOT NULL
    ,PRIMARY KEY (employerId)
    ,UNIQUE KEY (mail)
);


#Offer (offerId, nome, mail, password)
CREATE TABLE Offer
(
    offerId INT NOT NULL AUTO_INCREMENT
    ,title VARCHAR(255) NOT NULL
    ,state VARCHAR(255) NOT NULL
    ,offerDescription TEXT NOT NULL
    ,locationType VARCHAR(10) NOT NULL
    ,passwordAccount VARCHAR(20) NOT NULL
    ,PRIMARY KEY (offerId),
    FULLTEXT(title, offerDescription, RequiredSkills)
);

#Skill (skillId, name, type)
CREATE TABLE Skill
(
    skillId INT NOT NULL AUTO_INCREMENT
    ,name VARCHAR(255) NOT NULL
    ,type VARCHAR(10) NOT NULL
    ,PRIMARY KEY (skillId)
);

#Location (skillId, name, type)
CREATE TABLE Location
(
    locationId INT NOT NULL AUTO_INCREMENT
    ,lat FLOAT NOT NULL
    ,lon FLOAT NOT NULL
    ,PRIMARY KEY (locationId)
);

#Language (languageId, lat, lon)
CREATE TABLE Language
(
    languageId INT NOT NULL AUTO_INCREMENT
    ,languageCode VARCHAR(5) NOT NULL
    ,PRIMARY KEY (languageId)
    ,UNIQUE KEY (languageCode)
);


