CREATE TABLE IF NOT EXISTS public.books (
  id bigserial NOT NULL PRIMARY KEY,
  author_id bigint NOT NULL,
  launch_date TIMESTAMP NOT NULL DEFAULT NOW(),
  price DECIMAL(10,2) NOT NULL,
  title TEXT NOT NULL,
  FOREIGN KEY ("author_id") REFERENCES "public"."persons" ("id") ON UPDATE NO ACTION ON DELETE CASCADE
);