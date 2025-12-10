CREATE DATABASE IF NOT EXISTS bank_db;
USE bank_db;

CREATE TABLE IF NOT EXISTS customer (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(150) UNIQUE NOT NULL
);

INSERT INTO customer (name,email)
VALUES ('Alice Example','alice@example.com'),('Bob Example','bob@example.com');
