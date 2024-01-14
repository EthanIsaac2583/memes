create table visits
(
  id         uuid primary key,
  user_agent varchar,
  created_at timestamptz not null
);

create table leads
(
  id       bigserial primary key,
  name     varchar(150),
  visit_id uuid references visits (id) not null
);
