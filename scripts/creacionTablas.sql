-- Crear la base de datos
CREATE DATABASE minitienda;

-- Crear la tabla de usuarios
CREATE TABLE Usuario (
    ID_usuario INT PRIMARY KEY,
    nombre VARCHAR(50),
    correo VARCHAR(50),
    contraseña VARCHAR(50),
    tipoTarjeta VARCHAR(50),
    numeroTarjeta VARCHAR(50)
);

-- Crear la tabla de cds
CREATE TABLE CD (
    ID_cd INT PRIMARY KEY,
    titulo VARCHAR(100),
    artista VARCHAR(100),
    genero VARCHAR(100),
    pais VARCHAR(100),
    precio DECIMAL(10, 2),
    stock INT   
);

-- Crear la tabla de pedidos
CREATE TABLE Pedido (
    ID_pedido INT PRIMARY KEY,
    id_usuario INT,
    id_cd INT,
    importe DECIMAL(10, 2),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(ID_usuario),
    FOREIGN KEY (id_cd) REFERENCES CD(ID_cd)
);
