create table tasks
(
  id          bigserial primary key,
  name        varchar(256) not null,
  description varchar(512) null,
  question    varchar      not null,
  blank       varchar      not null,
  answer      varchar      not null,
  is_deleted  boolean      not null
);
