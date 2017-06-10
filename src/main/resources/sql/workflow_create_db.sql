drop table IF EXISTS user_files_access CASCADE;
drop table IF EXISTS user_files CASCADE;
drop table IF EXISTS users CASCADE;
drop table IF EXISTS user_roles CASCADE;
drop table IF EXISTS user_deeds CASCADE;


--documentation trading tables
create table user_roles(
role_id serial primary key,
role_name varchar(255) not null
);

create table users(
user_id serial primary key,
email varchar(255) unique not null,
first_name varchar(255) not null,
last_name varchar(255) not null,
password varchar(255) not null,
registration_date timestamp default current_timestamp,
rights integer not null,
photo bytea default null
);
alter table users
add foreign key(rights) references user_roles;

create table user_files(
file_id serial primary key,
author_id integer not null,
file_name varchar(255) not null,
file_blob bytea not null,
file_added_date timestamp default current_timestamp
);
alter table user_files 
add foreign key(author_id) references users;


create table user_files_access(
file_id integer,
user_id integer,
primary key(file_id , user_id)
);

create table user_deeds(
deeed_id serial primary key,
user_id integer,
deed_info text,
foreign key(user_id) references users
);

--children tables
drop table IF EXISTS children_files CASCADE;
drop table IF EXISTS parents CASCADE;
drop table IF EXISTS parent_types CASCADE;
drop table IF EXISTS children_clarified_info CASCADE;
drop table IF EXISTS children_basic_info CASCADE;
drop table IF EXISTS districts CASCADE;
drop table IF EXISTS detentions CASCADE;

create table districts(
district_id serial primary key,
district_name varchar(255) not null
);

create table detentions(
detention_id serial primary key,
detention_by varchar(255),
detention_when date,
detention_why varchar(255),
detention_where varchar(255)
);


create table children_basic_info(
child_id serial primary key,
first_name varchar(255) not null,
last_name varchar(255) not null,
middle_name varchar(255) not null,
birth_date date not null,
personal_record_code varchar(255) unique not null, -- or integer or decimal
entrance_date date not null,
retire_date date,
photo bytea
);

create table children_clarified_info (
clarified_child_id serial,
child_id integer,
first_name varchar(255),
last_name varchar(255),
middle_name varchar(255),
birth_date date,
address text,
birth_place varchar(255),
occupation varchar(255),
comes_from_city varchar(255),
comes_from_date date,
detention_info_id integer,
duty_officer varchar(255),
district_id integer,
judged_or_detained_info text,
child_notes text,
foreign key(district_id) references districts,
foreign key(detention_info_id) references detentions,
foreign key(child_id) references children_basic_info,
primary key(child_id, clarified_child_id)
);


create table parent_types (
parent_type_id serial primary key,
parent_type_name varchar(255) not null
);

create table parents (
parent_id serial primary key,
child_id integer,
parent_type_id integer not null, 
parent_name varchar(255) not null,
parent_birth_date date,
parent_info text,
foreign key(parent_type_id) references parent_types,
foreign key(child_id) references children_basic_info
);


create table children_files(
file_id serial,
child_id integer,
file_name varchar(255),
file_blob bytea
);
alter table children_files 
add foreign key(child_id) references children_basic_info;

















