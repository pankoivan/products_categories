INSERT INTO category (name, description) VALUES
('Игрушки', 'Категория товаров "Игрушки"'),
('Косметика', 'Категория товаров "Косметика"'),
('Бытовая техника', 'Категория товаров "Бытовая техника"');

INSERT INTO product (category_id, name, description, price, status, image_name, creation_date) VALUES
(1, 'Дед Мороз', 'Игрушка "Дед Мороз"', 129.99, TRUE, 'filename.png', CURRENT_TIMESTAMP),
(2, 'Губная помада', 'Косметика "Губная помада"', 86.99, TRUE, 'filename.png', CURRENT_TIMESTAMP),
(2, 'Тушь для ресниц', 'Косметика "Тушь для ресниц"', 94.99, TRUE, 'filename.png', CURRENT_TIMESTAMP),
(3, 'Стиральная машина', 'Бытовая техника "Стиральная машина"', 89999.00, TRUE, 'filename.png', CURRENT_TIMESTAMP),
(3, 'Холодильник', 'Бытовая техника "Холодильник"', 101000.00, TRUE, 'filename.png', CURRENT_TIMESTAMP);
