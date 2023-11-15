create table tasks
(
  id             bigserial primary key,
  question       varchar not null,
  variants       varchar not null,
  correct_answer varchar not null
);
