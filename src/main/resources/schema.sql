CREATE TABLE customer (id SERIAL PRIMARY KEY, name TEXT, age INTEGER)
CREATE TABLE product (id SERIAL PRIMARY KEY, name TEXT, price INTEGER)
CREATE TABLE orders (id SERIAL PRIMARY KEY, foreign_customer INTEGER,
FOREIGN KEY (foreign_customer) REFERENCES customer (id),
foreign_product INTEGER, FOREIGN KEY (foreign_product) REFERENCES product (id))