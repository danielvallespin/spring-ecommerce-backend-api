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
('dani', '$2a$10$KPjBgkVObnC9iXOrp/.HM.jF1l3bUaqCZkJRrASaWqOH5IaUADD/K', 'dani@email.com', 1, CURDATE()), -- Password: 1234
('admin', '$2a$10$GpA8gpzcemMvu.KasOxvIO1KICEt70Dn4MZ4/QSPSzGBAIYmITmOS', 'admin@email.com', 1, CURDATE()); -- Password: admin

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