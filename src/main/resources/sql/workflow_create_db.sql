DROP TABLE IF EXISTS user_files_access CASCADE;
DROP TABLE IF EXISTS user_files CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS user_roles CASCADE;
DROP TABLE IF EXISTS user_deeds CASCADE;

--documentation trading tables
CREATE TABLE user_roles (
  role_id   SERIAL PRIMARY KEY,
  role_name VARCHAR(255) NOT NULL
);

CREATE TABLE users (
  user_id           SERIAL PRIMARY KEY,
  email             VARCHAR(255) UNIQUE NOT NULL,
  first_name        VARCHAR(255)        NOT NULL,
  last_name         VARCHAR(255)        NOT NULL,
  password          VARCHAR(255)        NOT NULL,
  registration_date TIMESTAMP DEFAULT current_timestamp,
  rights            INTEGER             NOT NULL,
  photo             BYTEA     DEFAULT NULL
);
ALTER TABLE users
  ADD FOREIGN KEY (rights) REFERENCES user_roles;

CREATE TABLE user_files (
  file_id         SERIAL PRIMARY KEY,
  author_id       INTEGER      NOT NULL,
  file_name       VARCHAR(255) NOT NULL,
  file_blob       BYTEA        NOT NULL,
  file_added_date TIMESTAMP DEFAULT current_timestamp
);
ALTER TABLE user_files
  ADD FOREIGN KEY (author_id) REFERENCES users;


CREATE TABLE user_files_access (
  file_id INTEGER,
  user_id INTEGER,
  PRIMARY KEY (file_id, user_id)
);

CREATE TABLE user_deeds (
  deeed_id  SERIAL PRIMARY KEY,
  user_id   INTEGER,
  deed_info TEXT,
  FOREIGN KEY (user_id) REFERENCES users
);

--children tables
DROP TABLE IF EXISTS children_files CASCADE;
DROP TABLE IF EXISTS parents CASCADE;
DROP TABLE IF EXISTS parent_types CASCADE;
DROP TABLE IF EXISTS children_clarified_info CASCADE;
DROP TABLE IF EXISTS children_basic_info CASCADE;
DROP TABLE IF EXISTS districts CASCADE;
DROP TABLE IF EXISTS detentions CASCADE;

CREATE TABLE districts (
  district_id   SERIAL PRIMARY KEY,
  district_name VARCHAR(255) NOT NULL
);

CREATE TABLE detentions (
  detention_id    SERIAL PRIMARY KEY,
  detention_by    VARCHAR(255),
  detention_when  DATE,
  detention_why   VARCHAR(255),
  detention_where VARCHAR(255)
);


CREATE TABLE children_basic_info (
  child_id             SERIAL PRIMARY KEY,
  first_name           VARCHAR(255)        NOT NULL,
  last_name            VARCHAR(255)        NOT NULL,
  middle_name          VARCHAR(255)        NOT NULL,
  birth_date           DATE                NOT NULL,
  personal_record_code VARCHAR(255) UNIQUE NOT NULL,
  entrance_date        DATE                NOT NULL,
  retire_date          DATE,
  photo                BYTEA
);

CREATE TABLE children_clarified_info (
  clarified_child_id      SERIAL,
  child_id                INTEGER,
  first_name              VARCHAR(255),
  last_name               VARCHAR(255),
  middle_name             VARCHAR(255),
  birth_date              DATE,
  address                 TEXT,
  birth_place             VARCHAR(255),
  occupation              VARCHAR(255),
  comes_from_city         VARCHAR(255),
  comes_from_date         DATE,
  detention_info_id       INTEGER,
  duty_officer            VARCHAR(255),
  district_id             INTEGER,
  judged_or_detained_info TEXT,
  child_notes             TEXT,
  FOREIGN KEY (district_id) REFERENCES districts,
  FOREIGN KEY (detention_info_id) REFERENCES detentions,
  FOREIGN KEY (child_id) REFERENCES children_basic_info,
  PRIMARY KEY (child_id, clarified_child_id)
);


CREATE TABLE parent_types (
  parent_type_id   SERIAL PRIMARY KEY,
  parent_type_name VARCHAR(255) NOT NULL
);

CREATE TABLE parents (
  parent_id         SERIAL PRIMARY KEY,
  child_id          INTEGER,
  parent_type_id    INTEGER      NOT NULL,
  parent_name       VARCHAR(255) NOT NULL,
  parent_birth_date DATE,
  parent_info       TEXT,
  FOREIGN KEY (parent_type_id) REFERENCES parent_types,
  FOREIGN KEY (child_id) REFERENCES children_basic_info
);


CREATE TABLE children_files (
  file_id   SERIAL,
  child_id  INTEGER,
  file_name VARCHAR(255),
  file_blob BYTEA
);
ALTER TABLE children_files
  ADD FOREIGN KEY (child_id) REFERENCES children_basic_info;

















