create table tasks
(
  id       bigserial primary key,
  question varchar not null,
  blank    varchar not null,
  answer   varchar not null
);
