-- Todos estos datos son ficticios y han sido generados con IA generativa

-- Forzar UTF8MB4
SET NAMES utf8mb4;

-- ======================================
-- Productos iniciales (las rutas de imagenes no son reales)
-- ======================================
INSERT INTO products (name, description, price, stock, image_url, visible)
VALUES
('Laptop Lenovo', 'Laptop Lenovo i7 16GB RAM 512GB', 899.99, 15, '/images/products/lenovo-i7.jpg', 1),
('iPhone 15', 'Smartphone Apple iPhone 15 128GB', 999.00, 20, '/images/products/iphone15.jpg', 1),
('Monitor LG 27"', 'Monitor LG 27 pulgadas Full HD', 299.90, 16, '/images/products/monitor-lg-27.jpg', 1),
('Teclado Mecanico', 'Teclado mecanico RGB switches blue', 79.50, 30, '/images/products/teclado-mecanico-rgb.jpg', 1),
('Mouse Logitech', 'Mouse inalambrico Logitech MX Master', 59.99, 50, '/images/products/mouse-logitech-mx.jpg', 1),
('Auriculares Bluetooth', 'Auriculares inalámbricos con cancelación de ruido', 120.00, 28, '/images/products/auriculares-bt.jpg', 1),
('Reloj Inteligente', 'Smartwatch con monitor de actividad', 199.99, 12, '/images/products/smartwatch.jpg', 1),
('Tablet Samsung S9', 'Tablet Samsung Galaxy Tab S9 128GB', 649.99, 18, '/images/products/tablet-samsung-s9.jpg', 1),
('MacBook Air M2', 'Laptop Apple MacBook Air chip M2 256GB SSD', 1299.00, 10, '/images/products/macbook-air-m2.jpg', 0),
('SSD Samsung 1TB', 'Disco SSD Samsung 1TB NVMe', 129.99, 40, '/images/products/ssd-samsung-1tb.jpg', 1),
('Disco Duro Seagate 2TB', 'HDD Seagate Barracuda 2TB', 79.99, 35, '/images/products/hdd-seagate-2tb.jpg', 1),
('Memoria RAM 16GB', 'Memoria RAM DDR4 16GB Kingston', 69.90, 45, '/images/products/ram-16gb.jpg', 1),
('Webcam Logitech C920', 'Webcam Full HD Logitech C920', 89.99, 22, '/images/products/webcam-logitech-c920.jpg', 1),
('Silla Gaming', 'Silla gaming ergonómica con soporte lumbar', 199.90, 14, '/images/products/silla-gaming.jpg', 1),
('Mesa Gaming', 'Mesa gaming amplia con gestión de cables', 249.99, 9, '/images/products/mesa-gaming.jpg', 1),
('Altavoces Logitech', 'Altavoces estéreo Logitech para PC', 49.99, 38, '/images/products/altavoces-logitech.jpg', 1),
('Router TP-Link AX3000', 'Router WiFi 6 TP-Link AX3000', 129.00, 17, '/images/products/router-tplink.jpg', 1),
('Smart TV Samsung 55"', 'Smart TV Samsung 55 pulgadas 4K', 699.00, 8, '/images/products/tv-samsung-55.jpg', 0),
('Smart TV LG 65"', 'Televisor LG 65 pulgadas OLED', 1599.00, 6, '/images/products/tv-lg-65.jpg', 1),
('PS5', 'Consola Sony PlayStation 5', 549.99, 12, '/images/products/ps5.jpg', 1),
('Xbox Series X', 'Consola Microsoft Xbox Series X', 549.99, 11, '/images/products/xbox-series-x.jpg', 1),
('Nintendo Switch', 'Consola Nintendo Switch OLED', 349.99, 20, '/images/products/nintendo-switch.jpg', 1),
('Mando PS5', 'Mando inalámbrico DualSense', 69.99, 25, '/images/products/mando-ps5.jpg', 1),
('Mando Xbox', 'Mando inalámbrico Xbox', 64.99, 25, '/images/products/mando-xbox.jpg', 1),
('Cámara Canon EOS', 'Cámara réflex Canon EOS 250D', 599.00, 7, '/images/products/canon-eos.jpg', 1),
('Cámara Sony Alpha', 'Cámara mirrorless Sony Alpha A6400', 999.99, 5, '/images/products/sony-alpha.jpg', 1),
('Trípode Fotográfico', 'Trípode profesional aluminio', 39.99, 30, '/images/products/tripode.jpg', 1),
('Micrófono USB', 'Micrófono USB para streaming', 79.99, 28, '/images/products/microfono-usb.jpg', 1),
('Luces LED Streaming', 'Kit luces LED para streaming', 59.99, 21, '/images/products/luces-streaming.jpg', 1),
('Power Bank 20000mAh', 'Batería externa 20000mAh', 39.90, 50, '/images/products/powerbank.jpg', 0),
('Cargador USB-C', 'Cargador rápido USB-C 65W', 29.99, 60, '/images/products/cargador-usbc.jpg', 1),
('Cable USB-C', 'Cable USB-C trenzado 1m', 9.99, 80, '/images/products/cable-usbc.jpg', 1),
('Soporte Laptop', 'Soporte aluminio para laptop', 24.99, 40, '/images/products/soporte-laptop.jpg', 1),
('Base Refrigeración', 'Base refrigeradora para laptop', 29.99, 32, '/images/products/base-refrigeracion.jpg', 1),
('Disco SSD 2TB', 'SSD NVMe 2TB alta velocidad', 219.99, 16, '/images/products/ssd-2tb.jpg', 1),
('Tarjeta Grafica RTX 4060', 'GPU Nvidia RTX 4060 8GB', 399.00, 13, '/images/products/rtx4060.jpg', 1),
('Fuente Alimentación 750W', 'Fuente alimentación modular 750W', 109.99, 20, '/images/products/fuente-750w.jpg', 1),
('Placa Base ASUS B550', 'Placa base ASUS B550 para Ryzen', 149.99, 14, '/images/products/asus-b550.jpg', 1),
('Procesador Ryzen 7', 'AMD Ryzen 7 5800X', 329.99, 11, '/images/products/ryzen7.jpg', 1),
('Procesador Intel i5', 'Intel Core i5 13400F', 249.99, 13, '/images/products/intel-i5.jpg', 1),
('Disipador CPU', 'Cooler CPU torre silencioso', 39.99, 27, '/images/products/disipador-cpu.jpg', 1),
('Caja PC Gaming', 'Caja PC gaming con RGB', 99.99, 19, '/images/products/caja-pc.jpg', 1),
('Ventiladores RGB', 'Pack ventiladores RGB 3 unidades', 34.99, 24, '/images/products/ventiladores-rgb.jpg', 1),
('Dock USB-C', 'Dock USB-C con HDMI y USB', 79.99, 18, '/images/products/dock-usbc.jpg', 0),
('Hub USB', 'Hub USB 3.0 4 puertos', 19.99, 45, '/images/products/hub-usb.jpg', 1),
('Impresora HP', 'Impresora multifunción HP', 119.99, 15, '/images/products/impresora-hp.jpg', 1),
('Escáner Epson', 'Escáner Epson alta resolución', 139.99, 10, '/images/products/escaner-epson.jpg', 1),
('Proyector Xiaomi', 'Proyector portátil Xiaomi', 399.99, 9, '/images/products/proyector-xiaomi.jpg', 1),
('Pantalla Proyección', 'Pantalla para proyector 100"', 79.99, 14, '/images/products/pantalla-proyector.jpg', 1),
('Drone DJI Mini', 'Drone DJI Mini con cámara 4K', 499.99, 7, '/images/products/dji-mini.jpg', 0);

-- ======================================
-- Detalle de los productos
-- ======================================
INSERT INTO product_details (product_id, long_description, brand, categories)
VALUES
(1, 'Laptop Lenovo equipada con procesador Intel i7 de última generación, 16GB de RAM DDR4 y SSD de 512GB. Ideal para trabajo profesional, programación y gaming ligero. Pantalla Full HD de 15.6 pulgadas y batería de larga duración.', 'Lenovo', 'electronica,ordenadores,laptops'),
(2, 'El nuevo iPhone 15 con chip A16 Bionic, pantalla Super Retina XDR de 6.1 pulgadas y cámara dual de alta resolución. Compatible con 5G y Face ID. Diseño elegante y resistente al agua.', 'Apple', 'electronica,smartphones'),
(3, 'Monitor LG de 27 pulgadas con resolución Full HD (1920x1080), panel IPS y tasa de refresco de 75Hz. Ideal para oficina, diseño básico y entretenimiento multimedia.', 'LG', 'electronica,monitores'),
(4, 'Teclado mecánico con iluminación RGB personalizable y switches blue de alta precisión. Construcción robusta en aluminio y tecnología anti-ghosting para gaming competitivo.', 'Generic', 'electronica,perifericos,teclados'),
(5, 'Mouse inalámbrico Logitech MX Master con sensor de alta precisión, múltiples botones configurables y batería recargable de larga duración. Perfecto para productividad avanzada.', 'Logitech', 'electronica,perifericos,mouse'),
(6, 'Auriculares Bluetooth con cancelación activa de ruido (ANC), sonido envolvente y hasta 30 horas de autonomía. Incluye micrófono integrado para llamadas.', 'Sony', 'electronica,Audio'),
(7, 'Reloj inteligente con monitorización de ritmo cardíaco, seguimiento de actividad física y notificaciones inteligentes. Compatible con Android e iOS.', 'Xiaomi', 'electronica,wearables'),
(8,'Tablet Samsung Galaxy Tab S9 con pantalla AMOLED y gran rendimiento para multimedia y trabajo.','Samsung','electronica,tablets'),
(9,'MacBook Air ultraligero con chip M2 y batería de larga duración.','Apple','electronica,ordenadores,laptops'),
(10,'SSD NVMe Samsung de 1TB con velocidades de lectura ultra rápidas.','Samsung','electronica,almacenamiento'),
(11,'Disco duro mecánico Seagate Barracuda ideal para almacenamiento masivo.','Seagate','electronica,almacenamiento'),
(12,'Memoria RAM Kingston DDR4 16GB para mejorar el rendimiento del PC.','Kingston','electronica,componentes'),
(13,'Webcam Logitech C920 con grabación Full HD para videollamadas y streaming.','Logitech','electronica,perifericos'),
(14,'Silla gaming ergonómica con soporte lumbar y reposabrazos ajustables.','Generic','gaming,muebles'),
(15,'Mesa gaming con superficie amplia y soporte para accesorios.','Generic','gaming,muebles'),
(16,'Altavoces estéreo Logitech con sonido claro para ordenador.','Logitech','electronica,audio'),
(17,'Router TP-Link AX3000 con tecnología WiFi 6 de alta velocidad.','TP-Link','electronica,redes'),
(18,'Smart TV Samsung 55 pulgadas con resolución 4K y Smart Hub.','Samsung','electronica,televisores'),
(19,'Televisor LG OLED 65 pulgadas con imagen de alta calidad.','LG','electronica,televisores'),
(20,'Consola Sony PlayStation 5 con gráficos de nueva generación.','Sony','gaming,consolas'),
(21,'Consola Xbox Series X con potencia y rendimiento avanzados.','Microsoft','gaming,consolas'),
(22,'Nintendo Switch OLED híbrida para jugar en casa o portátil.','Nintendo','gaming,consolas'),
(23,'Mando DualSense con vibración háptica y gatillos adaptativos.','Sony','gaming,accesorios'),
(24,'Mando Xbox inalámbrico ergonómico.','Microsoft','gaming,accesorios'),
(25,'Cámara réflex Canon EOS con sensor de alta resolución.','Canon','electronica,fotografia'),
(26,'Cámara Sony Alpha mirrorless con grabación 4K.','Sony','electronica,fotografia'),
(27,'Trípode fotográfico estable y ligero para cámaras.','Generic','fotografia,accesorios'),
(28,'Micrófono USB para streaming y podcast.','Generic','audio,streaming'),
(29,'Kit de iluminación LED para creadores de contenido.','Generic','streaming,iluminacion'),
(30,'Power bank de gran capacidad para cargar dispositivos.','Xiaomi','electronica,baterias'),
(31,'Cargador rápido USB-C compatible con múltiples dispositivos.','Anker','electronica,cargadores'),
(32,'Cable USB-C resistente con carga rápida.','Generic','electronica,cables'),
(33,'Soporte de aluminio para elevar y refrigerar laptops.','Generic','accesorios,ordenadores'),
(34,'Base de refrigeración con ventiladores para laptops.','Generic','ordenadores,accesorios'),
(35,'SSD NVMe 2TB ideal para almacenamiento rápido.','Samsung','electronica,almacenamiento'),
(36,'Tarjeta gráfica RTX 4060 para gaming y creación de contenido.','Nvidia','componentes,gpu'),
(37,'Fuente de alimentación modular 750W eficiente.','Corsair','componentes,pc'),
(38,'Placa base ASUS B550 compatible con procesadores Ryzen.','ASUS','componentes,pc'),
(39,'Procesador AMD Ryzen 7 de alto rendimiento.','AMD','componentes,cpu'),
(40,'Procesador Intel Core i5 de última generación.','Intel','componentes,cpu'),
(41,'Disipador para CPU silencioso con gran capacidad térmica.','CoolerMaster','componentes,refrigeracion'),
(42,'Caja gaming con panel de cristal templado y RGB.','Generic','componentes,pc'),
(43,'Ventiladores RGB para mejorar refrigeración y estética.','Generic','componentes,refrigeracion'),
(44,'Dock USB-C con múltiples puertos para laptops.','Generic','accesorios,ordenadores'),
(45,'Hub USB 3.0 compacto para ampliar puertos.','Generic','accesorios,ordenadores'),
(46,'Impresora HP multifunción para hogar u oficina.','HP','electronica,impresoras'),
(47,'Escáner Epson con alta resolución para documentos.','Epson','electronica,oficina'),
(48,'Proyector portátil Xiaomi ideal para cine en casa.','Xiaomi','electronica,video'),
(49,'Pantalla de proyección de 100 pulgadas.','Generic','video,accesorios'),
(50,'Drone DJI Mini con cámara 4K estabilizada.','DJI','electronica,drones');

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
INSERT INTO wishlist (user_id, name) VALUES
(1, 'Lista deseos'),
(1, 'Gaming'),
(2, 'Lista deseos'),
(3, 'Lista deseos'),
(4, 'Lista deseos');

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
INSERT INTO orders (user_id, amount, status, full_shipping_address, shipping_country, shipping_city, shipping_postal_code) VALUES
(1, 151.98, 'paid', 'Calle Mayor, 15, 3 - 3', 'Spain', 'Barcelona', '28013'),
(2, 79.50, 'delivered', 'Carrer de Balmes, 120, 2 - 1', 'Spain', 'Barcelona', '08008'),
(3, 289.99, 'shipped', 'Calle Gran Vía, 48, 4 - C', 'Spain', 'Madrid', '28004');

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
INSERT INTO reviews (user_id, product_id, rating, title, comment, purchased) VALUES
(1, 1, 5, 'Muy buen laptop', 'La calidad es excelente y el rendimiento perfecto.', 1),
(1, 4, 4, 'Teclado bueno', 'Es cómodo algo pequeño.', 1),
(2, 2, 2, 'Telefono normales', 'El diseño me gusta pero no es lo que esperaba.', 1),
(3, 5, 5, 'Mouse top', 'Funcionalidades muy completas y diseño elegante.', 1),
(3, 3, 4, 'Monitor que cumple', 'Buena prestaciones y calidad, pero algo caro.', 1),
(2, 5, 1, 'Tuve este mouse y no me gustó', 'Mala calidad y rendimiento.', 0),
(3, 4, 3, 'Teclado normal', 'Esta bien, sin mas.', 0);


-- ======================================
-- Addresses
-- ======================================
INSERT INTO addresses (user_id, name, street, number, floor, door, country, city, postal_code, is_default) VALUES
(1, 'Casa', 'Calle Mayor', '15', '3', '3', 'Spain', 'Barcelona', '28013', 1),
(1, 'Trabajo', 'Avenida de América', '24', '5', 'A', 'Spain', 'Madrid', '28002', 0),
(2, 'Casa', 'Carrer de Balmes', '120', '2', '1', 'Spain', 'Barcelona', '08008', 1),
(3, 'Casa', 'Calle Gran Vía', '48', '4', 'C', 'Spain', 'Madrid', '28004', 1),
(4, 'Casa', 'Carrer de la Marina', '210', '1', '2', 'Spain', 'Barcelona', '08013', 1);