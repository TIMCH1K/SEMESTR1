CREATE TABLE IF NOT EXISTS posts (
    id uuid PRIMARY KEY,
    user_id uuid,
    title varchar(50) NOT NULL,
    description text NOT NULL,
    publishing timestamp NOT NULL default CURRENT_TIMESTAMP
);
ALTER table posts
    add constraint posts_userid_foreignkey_constraint foreign key (user_id) REFERENCES form_users(id);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
ALTER TABLE form_users ALTER COLUMN id SET DEFAULT uuid_generate_v4();
