CREATE TABLE users(
  id BIGSERIAL PRIMARY KEY,
  login VARCHAR(256) NOT NULL UNIQUE,
  nickname varchar(256) NOT NULL UNIQUE ,
  password VARCHAR(256),
  role VARCHAR(10)
);

CREATE TABLE chat(
    id BIGSERIAL PRIMARY KEY,
    user_creator BIGINT REFERENCES users(id),
    name VARCHAR(256) NOT NULL
);

CREATE TABLE messages(
    id BIGSERIAL PRIMARY KEY ,
    text VARCHAR(300) NOT NULL ,
    chat_id BIGINT REFERENCES chat(id),
    creator_id BIGINT REFERENCES users(id)
);

CREATE TABLE chat_users(
    chat_id BIGINT REFERENCES chat(id),
    user_id BIGINT REFERENCES users(id),
    PRIMARY KEY(chat_id, user_id)
);