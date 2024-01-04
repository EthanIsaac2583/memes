create table tasks
(
  id         bigserial primary key,
  name       varchar(256)  not null,
  body       varchar(6000) not null,
  blank      varchar(6000) not null,
  answer     varchar(3000) not null,
  is_deleted boolean       not null
);

create table templates
(
  id          bigserial primary key,
  name        varchar(256) not null,
  description varchar(512),
  quiz_limit  int          not null
);

create table templates_tasks
(
  task_id     bigint not null references tasks (id),
  template_id bigint not null references templates (id),
  unique (task_id, template_id)
);

create table quizzes
(
  id          bigserial primary key,
  lead_id     bigint references leads (id),
  template_id bigint      not null references templates (id),
  status      varchar(50) not null,
  grade       int
);

create table questions
(
  id          bigserial primary key,
  number      int     not null,
  quiz_id     bigint  not null references quizzes (id),
  task_id     bigint  not null references tasks (id),
  is_assessed boolean not null,
  answer      varchar(3000),
  grade       int
);
