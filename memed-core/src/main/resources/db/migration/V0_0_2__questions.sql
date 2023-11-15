create table tasks
(
  id             bigserial primary key,
  question       varchar not null,
  blank          varchar not null,
  correct_answer varchar not null
);
