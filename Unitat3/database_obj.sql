CREATE TYPE Direccion AS (
    Direccion VARCHAR(100),
    Ciudad VARCHAR(50),
    Pais VARCHAR(50),
    Codigo_Postal VARCHAR(10)
);

CREATE TYPE Contacto AS (
    Telefono VARCHAR(15),
    Email VARCHAR(100)
);

CREATE TABLE Persona (
    ID INT PRIMARY KEY,
    Nombre VARCHAR(100),
    Apellido VARCHAR(100),
    Direccion Direccion,
    Contacto Contacto
);

CREATE TABLE Cliente (
    ID INT PRIMARY KEY,
    Fecha_Registro DATE
) INHERITS (Persona);

CREATE TABLE Proveedor (
    ID INT PRIMARY KEY,
    Empresa VARCHAR(100)
) INHERITS (Persona);

CREATE TABLE Empleado (
    ID INT PRIMARY KEY,
    Fecha_Contratacion DATE,
    Salario DECIMAL(8,2)
) INHERITS (Persona);

CREATE TABLE Producto (
    ID INT PRIMARY KEY,
    Nombre VARCHAR(100),
    Precio DECIMAL(5,2),
    Descripcion TEXT,
    Stock INT
);

CREATE TABLE Pedido (
    ID INT PRIMARY KEY,
    ID_Producto INT,
    Fecha DATE,
    ID_Cliente INT,
    Cantidad INT,
    FOREIGN KEY (ID_Producto) REFERENCES Producto(ID),
    FOREIGN KEY (ID_Cliente) REFERENCES Cliente(ID)
);


CREATE TABLE Detalle_Pedido (
    ID_Pedido INT,
    ID_Producto INT,
    Cantidad INT,
    FOREIGN KEY (ID_Pedido) REFERENCES Pedido(ID),
    FOREIGN KEY (ID_Producto) REFERENCES Producto(ID)
);

CREATE TABLE Inventario (
    ID INT PRIMARY KEY,
    ID_Producto INT,
    Cantidad INT,
    Fecha_Entrada DATE,
    FOREIGN KEY (ID_Producto) REFERENCES Producto(ID)
);

CREATE TABLE Factura (
    ID INT PRIMARY KEY,
    Fecha DATE,
    ID_Cliente INT,
    Total DECIMAL(8,2),
    FOREIGN KEY (ID_Cliente) REFERENCES Cliente(ID)
);

CREATE TABLE Detalle_Factura (
    ID_Factura INT,
    ID_Producto INT,
    Cantidad INT,
    Precio_Unitario DECIMAL(5,2),
    FOREIGN KEY (ID_Factura) REFERENCES Factura(ID),
    FOREIGN KEY (ID_Producto) REFERENCES Producto(ID)
);

CREATE TABLE Departamento (
    ID INT PRIMARY KEY,
    Nombre VARCHAR(100),
    ID_Empleado INT,
    Presupuesto DECIMAL(10,2),
    FOREIGN KEY (ID_Empleado) REFERENCES Empleado(ID)
);
