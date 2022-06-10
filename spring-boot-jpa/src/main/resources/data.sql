insert into user (login_id, password, gender, mobile, name, created_at, updated_at) values ('USER1', 'user1', 0, '01011111111', 'NAME1', now(), now());
insert into user (login_id, password, gender, mobile, name, created_at, updated_at) values ('USER2', 'user2', 0, '01022222222', 'NAME2', now(), now());
insert into user_history (type, user_id, created_at, updated_at) values ('SELL', 1, now(), now());
insert into user_history (type, user_id, created_at, updated_at) values ('BUY', 2, now(), now());