-- Insert Sample Categories
INSERT INTO food_category (category_id, category_name) VALUES (1, 'Pizza');
INSERT INTO food_category (category_id, category_name) VALUES (2, 'Pasta');
INSERT INTO food_category (category_id, category_name) VALUES (3, 'Burgers');
INSERT INTO food_category (category_id, category_name) VALUES (4, 'Drinks');
INSERT INTO food_category (category_id, category_name) VALUES (5, 'Desserts');

-- Insert Sample Foods
INSERT INTO food (food_id, food_name, price, is_available, category_id) VALUES (1, 'Margherita Pizza', 12.99, true, 1);
INSERT INTO food (food_id, food_name, price, is_available, category_id) VALUES (2, 'Pepperoni Pizza', 14.99, true, 1);
INSERT INTO food (food_id, food_name, price, is_available, category_id) VALUES (3, 'Veggie Supreme Pizza', 13.99, true, 1);
INSERT INTO food (food_id, food_name, price, is_available, category_id) VALUES (4, 'BBQ Chicken Pizza', 15.99, true, 1);

INSERT INTO food (food_id, food_name, price, is_available, category_id) VALUES (5, 'Carbonara Pasta', 11.99, true, 2);
INSERT INTO food (food_id, food_name, price, is_available, category_id) VALUES (6, 'Alfredo Pasta', 10.99, true, 2);
INSERT INTO food (food_id, food_name, price, is_available, category_id) VALUES (7, 'Bolognese Pasta', 12.99, true, 2);

INSERT INTO food (food_id, food_name, price, is_available, category_id) VALUES (8, 'Classic Burger', 9.99, true, 3);
INSERT INTO food (food_id, food_name, price, is_available, category_id) VALUES (9, 'Cheese Burger', 10.99, true, 3);
INSERT INTO food (food_id, food_name, price, is_available, category_id) VALUES (10, 'Bacon Burger', 12.99, true, 3);

INSERT INTO food (food_id, food_name, price, is_available, category_id) VALUES (11, 'Coca Cola', 2.99, true, 4);
INSERT INTO food (food_id, food_name, price, is_available, category_id) VALUES (12, 'Orange Juice', 3.99, true, 4);
INSERT INTO food (food_id, food_name, price, is_available, category_id) VALUES (13, 'Lemonade', 3.49, true, 4);

INSERT INTO food (food_id, food_name, price, is_available, category_id) VALUES (14, 'Chocolate Cake', 5.99, true, 5);
INSERT INTO food (food_id, food_name, price, is_available, category_id) VALUES (15, 'Ice Cream Sundae', 4.99, true, 5);
INSERT INTO food (food_id, food_name, price, is_available, category_id) VALUES (16, 'Tiramisu', 6.99, true, 5);
