DROP TABLE IF EXISTS Section;

CREATE TABLE Section(
	Section_Name VARCHAR(50) NOT NULL,
	Title VARCHAR(50) NOT NULL,
	IsExternal VARCHAR(50) NOT NULL,
	Section_Detail TEXT NOT NULL,
	Section_Version INT NOT NULL,
	Modify_Time VARCHAR(255) NOT NULL,
	InUse BOOL NOT NULL,
	CONSTRAINT se_pk
	PRIMARY KEY (Section_Name, Title, Section_Version,IsExternal),
	CONSTRAINT se_fk
	FOREIGN KEY (Title,IsExternal)
	REFERENCES Content(Title)
	ON UPDATE CASCADE
	ON DELETE CASCADE
)ENGINE=InnoDB;