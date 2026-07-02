-- hotelDB
-- antes de iniciar el proyecto crear la database 

-- Crear base de datos si no existe
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'hotelDb')
BEGIN
    CREATE DATABASE hotelDb;
END
GO

USE hotelDb;
GO

-- Una vez creadas las tablas ejecutar el siguiente script

IF NOT EXISTS (SELECT 1 FROM estados)
BEGIN
    INSERT INTO estados (nombre) VALUES ('DISPONIBLE');
    INSERT INTO estados (nombre) VALUES ('MANTENIMIENTO');
    INSERT INTO estados (nombre) VALUES ('RESERVADO');
    INSERT INTO estados (nombre) VALUES ('SIN_PAGO');
    INSERT INTO estados (nombre) VALUES ('PROCESANDO_PAGO');
    INSERT INTO estados (nombre) VALUES ('CONFIRMADA');
    INSERT INTO estados (nombre) VALUES ('CANCELADA');
END
GO

IF NOT EXISTS (SELECT 1 FROM tipos_habitacion)
BEGIN
    INSERT INTO tipos_habitacion (nombre, descripcion) VALUES ('SENCILLA', 'Habitación individual');
    INSERT INTO tipos_habitacion (nombre, descripcion) VALUES ('DOBLE', 'Habitación doble');
    INSERT INTO tipos_habitacion (nombre, descripcion) VALUES ('SUITE', 'Suite de lujo');
END
GO

IF NOT EXISTS (SELECT 1 FROM clientes)
BEGIN
    INSERT INTO clientes (nombre, correo, telefono) VALUES ('Bryan', 'bryan@gmail.com', '0999999999');
END
GO

IF NOT EXISTS (SELECT 1 FROM habitaciones)
BEGIN
    INSERT INTO habitaciones (numero, descripcion, capacidad, costo, tipo_id, estado_id)
    VALUES ('001', 'Suite', 2, 1500, 3, 1);
END
GO

