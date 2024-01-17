create table visits
(
  id         uuid primary key,
  created_at timestamptz not null
);

create table leads
(
  id       bigserial primary key,
  username varchar(256) unique not null,
  password varchar(512) not null,
  visit_id uuid references visits (id) unique not null
);
