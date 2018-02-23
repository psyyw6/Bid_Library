DROP TABLE IF EXISTS Section;

CREATE TABLE Section(
	Section_Name VARCHAR(50) NOT NULL,
	Title VARCHAR(50) NOT NULL,
	Section_Detail TEXT NOT NULL,
	Section_Version INT NOT NULL,
	CONSTRAINT se_pk
	PRIMARY KEY (Section_Name, Title, Section_Version),
	CONSTRAINT se_fk
	FOREIGN KEY (Title, Section_Version)
	REFERENCES Content(Title, Version)
	ON UPDATE CASCADE
	ON DELETE CASCADE
)ENGINE=InnoDB;