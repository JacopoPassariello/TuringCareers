drop database if exists turing_careers;
create database turing_careers;
use turing_careers;


#Developer (developerId, firstName, lastName, bio, mail, passwordAccount)
CREATE TABLE Developer
(
    developerId INT NOT NULL AUTO_INCREMENT
    ,firstName VARCHAR(255) NOT NULL
    ,lastName VARCHAR(255) NOT NULL
    ,bio TEXT NOT NULL
    ,mail VARCHAR(255) NOT NULL
    ,passwordAccount VARCHAR(255) NOT NULL
    ,locationName VARCHAR(255) NOT NULL
    ,PRIMARY KEY (developerId)
    ,UNIQUE KEY (mail)
    ,FULLTEXT(bio)
);


#Employer (employerId, firstName, lastName, mail, passwordAccount)
CREATE TABLE Employer
(
    employerId INT NOT NULL AUTO_INCREMENT
    ,firstName VARCHAR(255) NOT NULL
    ,lastName VARCHAR(255) NOT NULL
    ,mail VARCHAR(255) NOT NULL
    ,passwordAccount VARCHAR(255) NOT NULL
    ,companyName VARCHAR(255) NOT NULL
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
    ,employerId INT NOT NULL
    ,locationName VARCHAR(255)
    ,PRIMARY KEY (offerId)
    ,FOREIGN KEY (employerId) REFERENCES Employer(employerId)
    ,FULLTEXT(title, offerDescription)
);

#Skill (skillId, name, type)
CREATE TABLE Skill
(
    skillId INT NOT NULL AUTO_INCREMENT
    ,skill_name VARCHAR(255) NOT NULL
    ,skill_type VARCHAR(255) NOT NULL
    ,PRIMARY KEY (skillId)
);


#Language (languageId, lat, lon)
CREATE TABLE Language
(
    languageId INT NOT NULL AUTO_INCREMENT
    ,languageCode VARCHAR(5) NOT NULL
    ,PRIMARY KEY (languageId)
    ,UNIQUE KEY (languageCode)
);


#DeveloperSkill (developerId, skillId)
CREATE TABLE DeveloperSkill
(
	developerId INT NOT NULL
    ,skillId INT NOT NULL
    ,PRIMARY KEY (developerId, skillId)
    ,FOREIGN KEY (developerId) REFERENCES Developer(developerId)
    ,FOREIGN KEY (skillId) REFERENCES Skill(skillId)
);

#DeveloperOffer (developerId, offerId)
CREATE TABLE DeveloperOffer 
(
	developerId INT NOT NULL
    ,offerId INT NOT NULL
    ,PRIMARY KEY (developerId, offerId)
    ,FOREIGN KEY (developerId) REFERENCES Developer(developerId)
    ,FOREIGN KEY (offerId) REFERENCES Offer(offerId)
);


#DeveloperLanguage (developerId, languageId)
CREATE TABLE DeveloperLanguage
(
	developerId INT NOT NULL
    ,languageId INT NOT NULL
    ,PRIMARY KEY (developerId, languageId)
    ,FOREIGN KEY (developerId) REFERENCES Developer(developerId)
    ,FOREIGN KEY (languageId) REFERENCES Language(languageId)
);


#OfferSkill (offerId, skillId)
CREATE TABLE OfferSkill
(
	offerId INT NOT NULL
    ,skillId INT NOT NULL
    ,PRIMARY KEY (offerId, skillId)
    ,FOREIGN KEY (offerId) REFERENCES Offer(offerId)
    ,FOREIGN KEY (skillId) REFERENCES Skill(skillId)
);


#OfferLanguage (offerId, languageId)
CREATE TABLE OfferLanguage
(
	offerId INT NOT NULL
    ,languageId INT NOT NULL
    ,PRIMARY KEY (offerId, languageId)
    ,FOREIGN KEY (offerId) REFERENCES Offer(offerId)
    ,FOREIGN KEY (languageId) REFERENCES Language(languageId)
);

#EmployerDeveloper (employerId, developerId)
CREATE TABLE EmployerDeveloper
(
	employerId INT NOT NULL
    ,developerId INT NOT NULL
    ,PRIMARY KEY (employerId, developerId)
    ,FOREIGN KEY (employerId) REFERENCES Employer(employerId)
    ,FOREIGN KEY (developerId) REFERENCES Developer(developerId)
);