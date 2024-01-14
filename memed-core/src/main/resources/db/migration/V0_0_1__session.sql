create table visits
(
  id         uuid primary key,
  created_at timestamptz not null
);

create table leads
(
  id       bigserial primary key,
  username varchar(256),
  password varchar(512),
  visit_id uuid references visits (id) not null
);

create table sessions
(
  id      bigserial primary key,
  token   varchar                      not null,
  revoked boolean                      not null,
  expired boolean                      not null,
  lead_id bigint references leads (id) not null
);
