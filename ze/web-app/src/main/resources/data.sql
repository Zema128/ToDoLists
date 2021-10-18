DROP TABLE if EXISTS user CASCADE;

create table users (
id bigint NOT NULL AUTO_INCREMENT,
email varchar(255) not null,
password varchar(255) not null,
time_created timestamp,
username varchar(255) not null,
primary key (id));

INSERT INTO users (email, password, time_created, username)
values ('wfaf@mail.ru', '$2a$10$NRanpMSgtNgaNiuKUgu.huEGeCpxjmAW3SQ0rjTc5lhsFqJYXiGly', '2021-09-11', 'First');
-- Pow1
INSERT INTO users (email, password, time_created, username)
values ('wfsecondaf@mail.ru', '$2a$10$aAIpYz7U4CkNoNSrPMRASe7dNOGhuR1tV.wt2xRCxcHwylUrxlBS.', '2021-09-11', 'Second');
-- Pow2
