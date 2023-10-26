-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS RedSocial;

-- Usar la base de datos recién creada
USE RedSocial;

-- Crear la tabla de Usuarios
CREATE TABLE Usuarios (
    ID INT PRIMARY KEY,
    Nombre VARCHAR(50),
    Apellido VARCHAR(50),
    Edad INT,
    Ciudad VARCHAR(50)
);

-- Insertar algunos usuarios de ejemplo
INSERT INTO Usuarios (ID, Nombre, Apellido, Edad, Ciudad) VALUES
(1, 'Juan', 'Pérez', 25, 'Ciudad A'),
(2, 'María', 'Gómez', 30, 'Ciudad B'),
(3, 'Luis', 'Martínez', 22, 'Ciudad C'),
(4, 'Ana', 'Rodríguez', 28, 'Ciudad A');

-- Crear la tabla de Publicaciones
CREATE TABLE Publicaciones (
    ID INT PRIMARY KEY,
    UsuarioID INT,
    Contenido TEXT,
    FechaPublicacion DATE
);

-- Insertar algunas publicaciones de ejemplo
INSERT INTO Publicaciones (ID, UsuarioID, Contenido, FechaPublicacion) VALUES
(1, 1, '¡Hola a todos! Soy nuevo aquí.', '2023-10-26'),
(2, 2, 'Haciendo una caminata en el parque.', '2023-10-25'),
(3, 3, 'Feliz de compartir mis fotos de viaje.', '2023-10-24'),
(4, 1, '¿Alguien quiere unirse a mi equipo de deportes?', '2023-10-24');
