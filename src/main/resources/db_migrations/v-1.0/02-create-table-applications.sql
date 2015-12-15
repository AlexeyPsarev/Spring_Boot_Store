--liquibase formatted sql

--changeset alex:02
CREATE TABLE IF NOT EXISTS applications (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  app_name VARCHAR(255) NOT NULL,
  pkg_name VARCHAR(255) UNIQUE NOT NULL,
  picture_128 VARCHAR(255),
  picture_512 VARCHAR(255),
  category VARCHAR(20),
  downloads INTEGER NOT NULL,
  time_uploaded TIMESTAMP,
  description TEXT,
  author INTEGER REFERENCES users ON UPDATE CASCADE ON DELETE CASCADE
);

--rollback drop table applications;
