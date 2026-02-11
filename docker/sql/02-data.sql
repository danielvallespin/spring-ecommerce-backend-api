-- Productos iniciales
INSERT INTO products (name, description, price)
VALUES
('Laptop Lenovo', 'Laptop Lenovo i7 16GB RAM 512GB SSD', 899.99),
('iPhone 15', 'Smartphone Apple iPhone 15 128GB', 999.00),
('Monitor LG 27"', 'Monitor LG 27 pulgadas Full HD', 199.90),
('Teclado Mecanico', 'Teclado mecanico RGB switches blue', 79.50),
('Mouse Logitech', 'Mouse inalambrico Logitech MX Master', 59.99);

-- Usuarios iniciales
INSERT INTO users (username, password, email, enabled, created_at)
VALUES 
('dani', '1234', 'dani@email.com', 1, CURDATE()),
('admin', '1234', 'admin@email.com', 1, CURDATE());

-- Roles iniciales
INSERT INTO roles (name)
VALUES ('ROLE_USER'), 
('ROLE_ADMIN');

-- Asignacion de roles a usuarios
INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(2, 1),
(2, 2);