--liquibase formatted sql

--changeset alex:01
CREATE TABLE IF NOT EXISTS users (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(40) UNIQUE NOT NULL,
  password CHAR(80) NOT NULL,
  full_name VARCHAR(60) NOT NULL
);

--rollback drop table users;
