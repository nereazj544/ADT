
CREATE TABLE departamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    ubicacion VARCHAR(100)
);


CREATE TABLE empleados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    salario DECIMAL(10, 2),
    departamento_id INT,
    FOREIGN KEY (departamento_id) REFERENCES departamentos(id)
);

INSERT INTO departamentos (nombre, ubicacion) VALUES
('Ventas', 'Oficina 101'),
('Marketing', 'Oficina 102'),
('Recursos Humanos', 'Oficina 103');


INSERT INTO empleados (nombre, apellido, salario, departamento_id) VALUES
('Juan', 'Pérez', 3000.00, 1),
('María', 'González', 3500.00, 2),
('Carlos', 'López', 3200.00, 1),
('Ana', 'Martínez', 3800.00, 3),
('Pedro', 'García', 3100.00, 1);
