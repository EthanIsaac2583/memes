create table tasks
(
  id          bigserial primary key,
  name        varchar(256)  not null,
  description varchar(512)  null,
  question    varchar(6000) not null,
  blank       varchar(6000) not null,
  answer      varchar(6000) not null
);

create table quiz_templates
(
  id   bigserial primary key,
  name varchar(256) not null
);

create table quiz_templates_tasks
(
  task_id          bigint not null references tasks (id),
  quiz_template_id bigint not null references quiz_templates (id),
  unique (task_id, quiz_template_id)
);

create table lead_quizzes
(
  id               bigserial primary key,
  user_id          bigint not null references leads (id),
  quiz_template_id bigint not null references quiz_templates (id)
);

create table lead_quiz_answer
(
  id                   bigserial primary key,
  lead_quiz_id         bigint  not null references lead_quizzes (id),
  task_id              bigint  not null references tasks (id),
  is_remote_assessable boolean not null,
  is_assessed          boolean not null,
  grade                int,
  answer               varchar(6000)
);
