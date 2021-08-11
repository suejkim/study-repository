CREATE USER sjkim WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE mydb TO sjkim;

CREATE TABLE member (
	id serial PRIMARY KEY,
	loginId VARCHAR(50) UNIQUE NOT NULL,
	password VARCHAR(100) NOT NULL,
	email VARCHAR(255) UNIQUE NOT NULL,
    enabled BOOLEAN NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL
);
CREATE TABLE role (
    member_id INT PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member (id)
);
INSERT INTO member(loginId, password, email, enabled, created_at, updated_at) values ('admin01', 'admin01p', 'admin01@a.com', true, now(), now());
INSERT INTO member(loginId, password, email, enabled, created_at, updated_at) values ('user01', 'user01p', 'user01@a.com', true, now(), now());
INSERT INTO role(member_id, role_name) values (1, 'ROLE_ADMIN');
INSERT INTO role(member_id, role_name) values (2, 'ROLE_USER');

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO sjkim;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public to sjkim;
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public to sjkim;
