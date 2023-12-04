CREATE TABLE IF NOT EXISTS form_users (
    id uuid PRIMARY KEY,
    nickname VARCHAR(255) NOT NULL,
    email    varchar(30)  not null,
    password varchar(200) not null,
    age INT,
    country VARCHAR(255),
    city VARCHAR(255),
    post_count INT DEFAULT 0,
    comments_count INT DEFAULT 0,
    is_admin BOOLEAN DEFAULT false,
    is_banned BOOLEAN DEFAULT false,
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
ALTER TABLE form_users ALTER COLUMN id SET DEFAULT uuid_generate_v4();
