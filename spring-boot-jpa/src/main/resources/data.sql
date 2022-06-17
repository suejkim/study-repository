insert into user (login_id, password, gender, mobile, name, created_at, updated_at) values ('USER1', 'user1', 0, '01011111111', 'NAME1', now(), now());
insert into user (login_id, password, gender, mobile, name, created_at, updated_at) values ('USER2', 'user2', 0, '01022222222', 'NAME1', now(), now());
insert into user_history (type, user_id, created_at, updated_at) values ('SELL', 1, now(), now());
insert into user_history (type, user_id, created_at, updated_at) values ('BUY', 2, now(), now());
insert into user_history (type, user_id, created_at, updated_at) values ('SELL', 2, now(), now());

insert into book (created_at, updated_at, comment, publish_at, title) values (now(), now(), 'COMMENT1', now(), 'TITLE1');
insert into book (created_at, updated_at, comment, publish_at, title) values (now(), now(), 'COMMENT2', now(), 'TITLE2');

insert into book_info (created_at, updated_at, sell_count, book_id) values (now(), now(), 3, 1);
insert into book_info (created_at, updated_at, sell_count, book_id) values (now(), now(), 3, 2);