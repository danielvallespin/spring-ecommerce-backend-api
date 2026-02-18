-- Todos estos datos son ficticios y han sido generados con IA generativa

-- ======================================
-- Productos iniciales (las rutas de imagenes no son reales)
-- ======================================
INSERT INTO products (name, description, price, stock, image_url)
VALUES
('Laptop Lenovo', 'Laptop Lenovo i7 16GB RAM 512GB', 899.99, 15, '/images/products/lenovo-i7.jpg'),
('iPhone 15', 'Smartphone Apple iPhone 15 128GB', 999.00, 20, '/images/products/iphone15.jpg'),
('Monitor LG 27"', 'Monitor LG 27 pulgadas Full HD', 299.90, 16, '/images/products/monitor-lg-27.jpg'),
('Teclado Mecanico', 'Teclado mecanico RGB switches blue', 79.50, 30, '/images/products/teclado-mecanico-rgb.jpg'),
('Mouse Logitech', 'Mouse inalambrico Logitech MX Master', 59.99, 50, '/images/products/mouse-logitech-mx.jpg'),
('Auriculares Bluetooth', 'Auriculares inalámbricos con cancelación de ruido', 120.00, 28, '/images/products/auriculares-bt.jpg'),
('Reloj Inteligente', 'Smartwatch con monitor de actividad', 199.99, 12, '/images/products/smartwatch.jpg');

-- ======================================
-- Detalle de los productos
-- ======================================
INSERT INTO product_details (product_id, long_description, brand, categories)
VALUES
(1, 'Laptop Lenovo equipada con procesador Intel i7 de última generación, 16GB de RAM DDR4 y SSD de 512GB. Ideal para trabajo profesional, programación y gaming ligero. Pantalla Full HD de 15.6 pulgadas y batería de larga duración.', 'Lenovo', 'Electrónica,Ordenadores,Laptops'),
(2, 'El nuevo iPhone 15 con chip A16 Bionic, pantalla Super Retina XDR de 6.1 pulgadas y cámara dual de alta resolución. Compatible con 5G y Face ID. Diseño elegante y resistente al agua.', 'Apple', 'Electrónica,Smartphones'),
(3, 'Monitor LG de 27 pulgadas con resolución Full HD (1920x1080), panel IPS y tasa de refresco de 75Hz. Ideal para oficina, diseño básico y entretenimiento multimedia.', 'LG', 'Electrónica,Monitores'),
(4, 'Teclado mecánico con iluminación RGB personalizable y switches blue de alta precisión. Construcción robusta en aluminio y tecnología anti-ghosting para gaming competitivo.', 'Generic', 'Electrónica,Periféricos,Teclados'),
(5, 'Mouse inalámbrico Logitech MX Master con sensor de alta precisión, múltiples botones configurables y batería recargable de larga duración. Perfecto para productividad avanzada.', 'Logitech', 'Electrónica,Periféricos,Mouse'),
(6, 'Auriculares Bluetooth con cancelación activa de ruido (ANC), sonido envolvente y hasta 30 horas de autonomía. Incluye micrófono integrado para llamadas.', 'Sony', 'Electrónica,Audio'),
(7, 'Reloj inteligente con monitorización de ritmo cardíaco, seguimiento de actividad física y notificaciones inteligentes. Compatible con Android e iOS.', 'Xiaomi', 'Electrónica,Wearables');

-- ======================================
-- Usuarios iniciales
-- ======================================
INSERT INTO users (username, password, email, enabled, created_at)
VALUES 
('dani', '$2a$10$KPjBgkVObnC9iXOrp/.HM.jF1l3bUaqCZkJRrASaWqOH5IaUADD/K', 'dani@email.com', 1, CURDATE()), -- Password: 1234
('iris', '$2a$10$KPjBgkVObnC9iXOrp/.HM.jF1l3bUaqCZkJRrASaWqOH5IaUADD/K', 'iris@email.com', 1, CURDATE()), -- Password: 1234
('user', '$2a$10$KPjBgkVObnC9iXOrp/.HM.jF1l3bUaqCZkJRrASaWqOH5IaUADD/K', 'user@email.com', 1, CURDATE()), -- Password: 1234
('admin', '$2a$10$GpA8gpzcemMvu.KasOxvIO1KICEt70Dn4MZ4/QSPSzGBAIYmITmOS', 'admin@email.com', 1, CURDATE()); -- Password: admin

-- ======================================
-- Roles
-- ======================================
INSERT INTO roles (name)
VALUES ('ROLE_USER'), 
('ROLE_ADMIN');

-- ======================================
-- Relacion users y roles
-- ======================================
INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(4, 2);

-- ======================================
-- Carts
-- ======================================
INSERT INTO carts (user_id) VALUES
(1),
(2),
(3),
(4);

-- ======================================
-- Cart Items
-- ======================================
INSERT INTO cart_items (cart_id, product_id, quantity) VALUES
(1, 1, 2),
(1, 4, 1),
(2, 2, 1), 
(3, 5, 1),
(3, 3, 2);

-- ======================================
-- Wishlists
-- ======================================
INSERT INTO wishlist (user_id) VALUES
(1),
(2),
(3),
(4);

-- ======================================
-- Wishlist Items
-- ======================================
INSERT INTO wishlist_items (wishlist_id, product_id) VALUES
(1, 2),
(1, 5),
(2, 1),
(3, 4),
(3, 2);

-- ======================================
-- Orders
-- ======================================
INSERT INTO orders (user_id, amount, status) VALUES
(1, 151.98, 'paid'),
(2, 79.50, 'pending'),
(3, 289.99, 'shipped');

-- ======================================
-- Orders Items
-- ======================================
INSERT INTO orders_items (order_id, product_id, quantity, price) VALUES
(1, 1, 2, 15.99),
(1, 4, 1, 120.00),
(2, 2, 1, 79.50),
(3, 5, 1, 199.99),
(3, 3, 2, 45.00);

-- ======================================
-- Card Payment Methods
-- ======================================
INSERT INTO card_payment_methods (user_id, type, last_4, expiry_month, expiry_year, is_default) VALUES
(1, 'visa', '1234', 12, 2028, 1),
(1, 'visa', '9876', 8, 2030, 0),
(2, 'mastercard', '5678', 5, 2026, 1),
(3, 'visa', '4321', 11, 2027, 1);

-- ======================================
-- Order Payments
-- ======================================
INSERT INTO order_payments (order_id, card_id, amount) VALUES   
(1, 1, 140),
(1, 2, 11.98),
(2, 2, 79.50),
(3, 3, 289.99);

-- ======================================
-- Reviews
-- ======================================
INSERT INTO reviews (user_id, product_id, rating, title, comment) VALUES
(1, 1, 5, 'Muy buen laptop', 'La calidad es excelente y el rendimiento perfecto.'),
(1, 4, 4, 'Teclado bueno', 'Es cómodo algo pequeño.'),
(2, 2, 2, 'Telefono normales', 'El diseño me gusta pero no es lo que esperaba.'),
(3, 5, 5, 'Mouse top', 'Funcionalidades muy completas y diseño elegante.'),
(3, 3, 4, 'Monitor que cumple', 'Buena prestaciones y calidad, pero algo caro.');