create table tasks
(
  id          bigserial primary key,
  name        varchar(256) not null,
  description varchar(512) null,
  question    varchar      not null,
  blank       varchar      not null,
  answer      varchar      not null
);

create table quiz
(
  id bigserial primary key,
  user_id bigint references leads (id)
)
