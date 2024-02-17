CREATE DATABASE IF NOT EXISTS school;
USE school;

CREATE TABLE Estudiantes (
    ID INT PRIMARY KEY,
    Nombre VARCHAR(100),
    Apellido VARCHAR(100),
    Email VARCHAR(100)
);

CREATE TABLE Cursos (
    ID INT PRIMARY KEY,
    Nombre VARCHAR(100),
    Descripcion VARCHAR(255),
    EstudianteID INT,
    ProfesorID INT,
    FOREIGN KEY (EstudianteID) REFERENCES Estudiantes(ID),
    FOREIGN KEY (ProfesorID) REFERENCES Profesores(ID)
);

CREATE TABLE Profesores (
    ID INT PRIMARY KEY,
    Nombre VARCHAR(100),
    Apellido VARCHAR(100),
    Especialidad VARCHAR(100)
);

CREATE TABLE Asignaturas (
    ID INT PRIMARY KEY,
    Nombre VARCHAR(100),
    Descripcion VARCHAR(255),
    CursoID INT,
    FOREIGN KEY (CursoID) REFERENCES Cursos(ID)
);

CREATE TABLE Aulas (
    ID INT PRIMARY KEY,
    Nombre VARCHAR(100),
    Capacidad INT,
    AsignaturaID INT,
    FOREIGN KEY (AsignaturaID) REFERENCES Asignaturas(ID)
);

CREATE TABLE Horarios (
    ID INT PRIMARY KEY,
    Dia VARCHAR(10),
    HoraInicio TIME,
    HoraFin TIME,
    AulaID INT,
    FOREIGN KEY (AulaID) REFERENCES Aulas(ID)
);
