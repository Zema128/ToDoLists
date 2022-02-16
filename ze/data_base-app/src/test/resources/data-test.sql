create table if not exists shared_list (
id bigserial NOT NULL,
for_changes boolean not null,
from_id bigserial,
to_do_id bigserial,
to_id bigserial,
primary key (id));

create table if not exists invite (
id bigserial NOT NULL,
from_user_id bigserial,
to_user_id bigserial,
primary key (id));

create table if not exists sub_task (
id bigserial NOT NULL,
text varchar(5000),
sent_message boolean not null,
time_created timestamp,
time_notification timestamp,
todo_id bigserial,
primary key (id));

create table if not exists to_do (
id bigserial NOT NULL,
categories varchar(255),
done boolean not null,
sent_message boolean not null,
text varchar(5000),
time_created timestamp,
time_notification timestamp,
user_id bigserial,
primary key (id));

create table if not exists users (
id bigserial NOT NULL,
time_created timestamp,
primary key (id));

create table if not exists user_friends (
user_id bigserial NOT NULL,
friends_id bigserial NOT NULL,
primary key (user_id,friends_id)
);

INSERT INTO to_do (categories, done, sent_message, text, time_created, user_id)
values ('PERSONAL', false, false, 'First notification by First User', '2021-09-11', 1);
INSERT INTO to_do (categories, done, sent_message, text, time_created, user_id)
values ('WORK', false, false, 'Second notification by First User','2021-09-11', 1);
INSERT INTO to_do (categories, done, sent_message, text, time_created, user_id)
values ('PERSONAL', false, false, 'First notification by Second User','2021-09-11', 2);
INSERT INTO to_do (categories, done, sent_message, text, time_created, user_id)
values ('USERS', false, false, 'Second notification by Second User','2021-09-11', 2);

INSERT INTO users (time_created)
values ('2021-09-11');
INSERT INTO users (time_created)
values ('2021-09-11');
INSERT INTO users (time_created)
values ('2021-09-11');
INSERT INTO users (time_created)
values ('2021-09-11');
INSERT INTO users (time_created)
values ('2021-09-11');
INSERT INTO users (time_created)
values ('2021-09-11');
INSERT INTO users (time_created)
values ('2021-09-11');

INSERT INTO sub_task (text, time_created, todo_id, sent_message)
values ('First subtask by First User', '2021-09-11', 1, false);
INSERT INTO sub_task (text, time_created, todo_id, sent_message)
values ('Second subtask by First User', '2021-09-11', 1, false);
INSERT INTO sub_task (text, time_created, todo_id, sent_message)
values ('First subtask by Second User', '2021-09-11', 2, false);
INSERT INTO sub_task (text, time_created, todo_id, sent_message)
values ('First subtask by Thre User', '2021-09-11', 3, false);
INSERT INTO sub_task (text, time_created, todo_id, sent_message)
values ('Second subtask by Thre User', '2021-09-11', 3, false);

INSERT INTO user_friends (user_id, friends_id)
values (1, 3);
INSERT INTO user_friends (user_id, friends_id)
values (3, 2);
INSERT INTO user_friends (user_id, friends_id)
values (3, 5);
INSERT INTO user_friends (user_id, friends_id)
values (2, 6);

INSERT INTO invite (from_user_id, to_user_id)
values (1, 2);
INSERT INTO invite (from_user_id, to_user_id)
values (1, 3);
INSERT INTO invite (from_user_id, to_user_id)
values (1, 4);
INSERT INTO invite (from_user_id, to_user_id)
values (5, 1);