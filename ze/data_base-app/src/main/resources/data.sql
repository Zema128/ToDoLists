DROP TABLE if EXISTS sub_task CASCADE;
DROP TABLE if EXISTS to_do CASCADE;
DROP TABLE if EXISTS user CASCADE;

create table sub_task (
id bigint NOT NULL AUTO_INCREMENT,
text varchar(5000),
time_created timestamp,
time_notification timestamp,
todo_id bigint,
primary key (id));

create table to_do (
id bigint NOT NULL AUTO_INCREMENT,
categories varchar(255),
done boolean not null,
text varchar(255),
time_created timestamp,
time_notification timestamp,
user_id bigint,
primary key (id));

create table user (
id bigint NOT NULL AUTO_INCREMENT,
email varchar(255) not null,
password varchar(255) not null,
time_created timestamp,
username varchar(255) not null,
primary key (id));

INSERT INTO to_do (categories, done, text, time_created, user_id)
values ('PERSONAL', false, 'first', '2021-09-11', 1);
INSERT INTO to_do (categories, done, text, time_created, user_id)
values ('WORK', false, 'firstfirst','2021-09-11', 1);
INSERT INTO to_do (categories, done, text, time_created, user_id)
values ('PERSONAL', false, 'two','2021-09-11', 2);
INSERT INTO to_do (categories, done, text, time_created, user_id)
values ('USERS', false, 'twotwo','2021-09-11', 2);

INSERT INTO user (email, password, time_created, username)
values ('wfaf@mail.ru', '$2a$10$NRanpMSgtNgaNiuKUgu.huEGeCpxjmAW3SQ0rjTc5lhsFqJYXiGly', '2021-09-11', 'First');
-- Pow1
INSERT INTO user (email, password, time_created, username)
values ('wfsecondaf@mail.ru', '$2a$10$aAIpYz7U4CkNoNSrPMRASe7dNOGhuR1tV.wt2xRCxcHwylUrxlBS.', '2021-09-11', 'Second');
-- Pow2

INSERT INTO sub_task (text, time_created, todo_id)
values ('firstSub', '2021-09-11', 1);
INSERT INTO sub_task (text, time_created, todo_id)
values ('firstSub', '2021-09-11', 3);