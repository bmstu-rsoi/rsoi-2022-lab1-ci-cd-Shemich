CREATE TABLE IF NOT EXISTS public.persons
(
    id serial PRIMARY KEY NOT NULL,
    name varchar(255) NOT NULL,
    age integer,
    address varchar(255),
    work varchar(255)
    );