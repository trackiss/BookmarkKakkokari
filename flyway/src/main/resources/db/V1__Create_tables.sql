CREATE TABLE users (
  id                  uuid      PRIMARY KEY,
  email_address       varchar   NOT NULL UNIQUE,
  encrypted_password  varchar   NOT NULL,
  password_salt       varchar   NOT NULL,
  count_item          int       NOT NULL DEFAULT 0,
  is_active           boolean   NOT NULL DEFAULT true,
  created_at          timestamp NOT NULL,
  updated_at          timestamp NOT NULL
);

CREATE TABLE bookmarks (
  id            uuid      PRIMARY KEY,
  title         varchar   NOT NULL,
  url           varchar   NOT NULL,
  comment       text      NOT NULL,
  is_favorited  boolean   NOT NULL DEFAULT false,
  owner_id      uuid      NOT NULL REFERENCES users,
  is_active     boolean   NOT NULL DEFAULT true,
  created_at    timestamp NOT NULL,
  updated_at    timestamp NOT NULL
);

CREATE TABLE tags (
  id          uuid      PRIMARY KEY,
  name        varchar   NOT NULL,
  count_item  int       NOT NULL DEFAULT 0,
  owner_id    uuid      NOT NULL REFERENCES bookmarks,
  is_active   boolean   NOT NULL DEFAULT true,
  created_at  timestamp NOT NULL,
  updated_at  timestamp NOT NULL
);
