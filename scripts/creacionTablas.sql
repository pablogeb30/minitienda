-- Crear la base de datos
CREATE DATABASE minitienda;

-- Crear la tabla de usuarios
CREATE TABLE Usuario (
    nombre VARCHAR(50),
    correo VARCHAR(50) NOT NULL,
    contrasenha VARCHAR(100) NOT NULL,
    tipoTarjeta VARCHAR(25) NOT NULL,
    numeroTarjeta VARCHAR(16) NOT NULL
    PRIMARY KEY (nombre)
);

-- Crear la tabla de pedidos
CREATE TABLE Pedido (
    id SERIAL,
    usuario VARCHAR(50) NOT NULL,
    importeTotal DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (usuario) REFERENCES Usuario(nombre) ON DELETE CASCADE ON UPDATE CASCADE
);
