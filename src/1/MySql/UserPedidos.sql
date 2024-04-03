-- Crear la tabla de usuarios
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    email VARCHAR(100),
    telefono VARCHAR(20)
);

-- Crear la tabla de pedidos
CREATE TABLE pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    producto VARCHAR(100),
    cantidad INT,
    precio DECIMAL(10, 2),
    fecha TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Insertar datos en la tabla usuarios
INSERT INTO usuarios (nombre, email, telefono) VALUES
('Juan Perez', 'juan@example.com', '123456789'),
('María García', 'maria@example.com', '987654321'),
('Pedro López', 'pedro@example.com', '456789123');

-- Insertar datos en la tabla pedidos
INSERT INTO pedidos (usuario_id, producto, cantidad, precio, fecha) VALUES
(1, 'Camisa', 2, 25.50, NOW()),
(1, 'Pantalón', 1, 35.75, NOW()),
(2, 'Zapatos', 1, 50.00, NOW()),
(3, 'Camiseta', 3, 15.00, NOW());
