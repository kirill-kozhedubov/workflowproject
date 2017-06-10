--initial roles
INSERT INTO user_roles (role_name) VALUES ('ADMIN'); --1
INSERT INTO user_roles (role_name) VALUES ('REGULAR_USER'); --2

--initial parent types

INSERT INTO parent_types (parent_type_name) VALUES ('Мати'); --1
INSERT INTO parent_types (parent_type_name) VALUES ('Батько'); --2
INSERT INTO parent_types (parent_type_name) VALUES ('Опікун'); --3
INSERT INTO parent_types (parent_type_name) VALUES ('Піклувальник'); --4
INSERT INTO parent_types (parent_type_name) VALUES ('Прийомні батьки'); --5
INSERT INTO parent_types (parent_type_name) VALUES ('Усиновлювач'); --6
INSERT INTO parent_types (parent_type_name) VALUES ('Інші'); --7

--initial districts

INSERT INTO districts (district_name) VALUES ('Малиновський'); --1
INSERT INTO districts (district_name) VALUES ('Примроський'); --2
INSERT INTO districts (district_name) VALUES ('Коївський'); --3
INSERT INTO districts (district_name) VALUES ('Суворовський'); --4
INSERT INTO districts (district_name) VALUES ('Одеська область'); --5
INSERT INTO districts (district_name) VALUES ('Україна'); --6
INSERT INTO districts (district_name) VALUES ('Інша країна'); --7
INSERT INTO districts (district_name) VALUES ('Молдова'); --8


--initial users

INSERT INTO users (email, first_name, last_name, password, registration_date, rights)
VALUES ('user', 'Пользователь', 'Программы', 'user', '2007,09,11', 2);
INSERT INTO users (email, first_name, last_name, password, registration_date, rights)
VALUES ('admin', 'admin', 'admin', 'admin', '2000,09,11', 1);
