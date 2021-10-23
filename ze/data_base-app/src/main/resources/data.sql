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
text varchar(5000),
time_created timestamp,
time_notification timestamp,
user_id bigint,
primary key (id));

create table user (
id bigint NOT NULL AUTO_INCREMENT,
time_created timestamp,
primary key (id));

INSERT INTO to_do (categories, done, text, time_created, user_id)
values ('PERSONAL', false, 'first', '2021-09-11', 1);
INSERT INTO to_do (categories, done, text, time_created, user_id)
values ('WORK', false, 'firstfirst','2021-09-11', 1);
INSERT INTO to_do (categories, done, text, time_created, user_id)
values ('PERSONAL', false, 'two','2021-09-11', 2);
INSERT INTO to_do (categories, done, text, time_created, user_id)
values ('USERS', false, 'twotwo','2021-09-11', 2);

INSERT INTO user (time_created)
values ('2021-09-11');
INSERT INTO user (time_created)
values ('2021-09-11');

INSERT INTO sub_task (text, time_notification, time_created, todo_id)
values ('firstSub', '2021-09-11', '2021-09-11', 1);
INSERT INTO sub_task (text, time_notification, time_created, todo_id)
values ('twoSub', '2021-09-11', '2021-09-11', 1);
INSERT INTO sub_task (text, time_notification, time_created, todo_id)
values ('thretSub', '2021-09-11', '2021-09-11', 2);
INSERT INTO sub_task (text, time_notification, time_created, todo_id)
values ('fourSub', '2021-09-11', '2021-09-11', 3);
INSERT INTO sub_task (text, time_notification, time_created, todo_id)
values ('fiveSub', '2021-09-11', '2021-09-11', 3);