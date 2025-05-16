CREATE SCHEMA LAB5;

USE LAB5;

CREATE TABLE rol (
    idrol INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL UNIQUE
);

CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    apellido VARCHAR(45) NOT NULL,
    dni CHAR(8) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    edad INT NOT NULL,
    pwd VARCHAR(100) NOT NULL,
    activo TINYINT NOT NULL DEFAULT 1,
    idrol INT NOT NULL,
    FOREIGN KEY (idrol) REFERENCES rol(idrol)
);

-- Roles
INSERT INTO rol(idrol, nombre) VALUES (1, 'ADMIN'), (2, 'USER');

-- Usuario con contraseña encriptada  encriptacion bcrypt, clave=silkroad)
INSERT INTO usuario (nombre, apellido, dni, email, edad, pwd, activo, idrol)
VALUES ('Jaimito', 'Sky', '12345678', 'lab5@gmail.com', 30, '$2a$12$dph5tAef7Fp9jw14axukY.5YWxJ3khz8bCzGoXqHUlGUUDGxIR1em', 1, 1);
