CREATE TABLE customer (id SERIAL PRIMARY KEY, name TEXT, age INTEGER)
CREATE TABLE product (id SERIAL PRIMARY KEY, name TEXT, price DEC)
CREATE TABLE orders (id SERIAL PRIMARY KEY, customer_id INTEGER,
FOREIGN KEY (customer_id) REFERENCES customer (id),
product_id INTEGER, FOREIGN KEY (product_id) REFERENCES product (id))