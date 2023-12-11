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
  description varchar(512)
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
  quiz_id     bigint  not null references quizzes (id),
  task_id     bigint  not null references tasks (id),
  is_assessed boolean not null,
  answer      varchar(3000),
  grade       int
);

CREATE VIEW view_question_metas AS
SELECT
  q.id,
  q.is_assessed,
  q.quiz_id,
  row_number() OVER (PARTITION BY q.quiz_id ORDER BY q.id) AS rowindex
FROM questions q;
