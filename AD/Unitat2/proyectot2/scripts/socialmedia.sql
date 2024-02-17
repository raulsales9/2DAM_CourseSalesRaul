-- Crea la base de datos si no existe
CREATE DATABASE IF NOT EXISTS SocialMedia;

-- Selecciona la base de datos
USE SocialMedia;

-- Crea la tabla Usuarios si no existe
CREATE TABLE IF NOT EXISTS Usuarios (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(50),
    Apellido VARCHAR(50),
    Edad INT,
    Ciudad VARCHAR(50)
);

-- INSERT INTO Usuarios con una sintaxis válida para múltiples filas
INSERT INTO Usuarios (Nombre, Apellido, Edad, Ciudad) VALUES
('Juan', 'Pérez', 25, 'Ciudad A'),
('María', 'Gómez', 30, 'Ciudad B'),
('Luis', 'Martínez', 22, 'Ciudad C'),
('Ana', 'Rodríguez', 28, 'Ciudad A');

-- Crea la tabla Publicaciones si no existe
CREATE TABLE IF NOT EXISTS Publicaciones (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    UsuarioID INT,
    Contenido TEXT,
    FechaPublicacion DATE
);

-- INSERT INTO Publicaciones con una sintaxis válida para múltiples filas
INSERT INTO Publicaciones (UsuarioID, Contenido, FechaPublicacion) VALUES
(1, '¡Hola a todos! Soy nuevo aquí.', '2023-10-26'),
(2, 'Haciendo una caminata en el parque.', '2023-10-25'),
(3, 'Feliz de compartir mis fotos de viaje.', '2023-10-24'),
(1, '¿Alguien quiere unirse a mi equipo de deportes?', '2023-10-24');
