create table questions
(
  id      bigserial primary key,
  body    varchar not null,
  variant varchar not null
);
