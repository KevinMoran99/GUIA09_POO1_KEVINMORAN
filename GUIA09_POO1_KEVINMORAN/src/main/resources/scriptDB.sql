DROP DATABASE IF EXISTS Examen2POO1;
CREATE DATABASE Examen2POO1;

USE Examen2POO1;

CREATE TABLE Piezas(
	codi_piez BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	nomb_piez varchar(50) NOT NULL,
	tipo_piez varchar(50) NOT NULL,
	marc_piez varchar(50) NOT NULL
);

CREATE TABLE Proveedores(
	codi_prov BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	nomb_prov varchar(50) NOT NULL,
	dire_prov varchar(255) NOT NULL,
	tele_prov char(8) NOT NULL
);

CREATE TABLE Bodega(
	codi_bode BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
	codi_piez BIGINT NOT NULL,
	codi_prov BIGINT NOT NULL,
	cant BIGINT NOT NULL,
	fecha_comp DATE NOT NULL,
	FOREIGN KEY (codi_piez) REFERENCES Piezas(codi_piez),
	FOREIGN KEY (codi_prov) REFERENCES Proveedores(codi_prov)
);

INSERT INTO Piezas VALUES (NULL, "Tornillo", "Metal", "Metalon");
INSERT INTO Piezas VALUES (NULL, "Tabla", "Madera", "Maderon");
INSERT INTO Piezas VALUES (NULL, "Alambre", "Metal", "Metalon");

INSERT INTO Proveedores VALUES (NULL, "Maestros del metal", "San Salvador", "22224444");
INSERT INTO Proveedores VALUES (NULL, "Maderalandia", "Santa Ana", "23324334");