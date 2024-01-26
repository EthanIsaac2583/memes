insert into visits (id, created_at)
values ('affae359-1bc5-43ee-9331-516f4a7e9b58', CURRENT_TIMESTAMP);

insert into tasks (name, body, blank, answer, is_deleted)
values ('Task 1', '{"type":"PLAIN_TEXT","text":"Awesome question"}', '{"type":"SINGLE_CHOICE","options":[{"key":"4","value":"Fourth"},{"key":"2","value":"Second"},{"key":"3","value":"Third"},{"key":"1","value":"First"}]}', '{"type":"SINGLE_CHOICE","key":"1"}', false),
       ('Task 2', '{"type":"PLAIN_TEXT","text":"Awesome question"}', '{"type":"SINGLE_CHOICE","options":[{"key":"4","value":"Fourth"},{"key":"2","value":"Second"},{"key":"3","value":"Third"},{"key":"1","value":"First"}]}', '{"type":"SINGLE_CHOICE","key":"1"}', false);

insert into templates (name, description, quiz_limit)
values ('Template 1', 'No limit', 0);

insert into quizzes (visit_id, template_id, status, grade)
values ('affae359-1bc5-43ee-9331-516f4a7e9b58', 1, 'IN_PROGRESS', 0),
       ('affae359-1bc5-43ee-9331-516f4a7e9b58', 1, 'IN_PROGRESS', 0);

insert into questions (visit_id, number, quiz_id, task_id, is_assessed, answer, grade)
values ('affae359-1bc5-43ee-9331-516f4a7e9b58', 1, 1, 1, false, '', 0),
       ('affae359-1bc5-43ee-9331-516f4a7e9b58', 2, 1, 2, false, '', 0);

insert into questions (visit_id, number, quiz_id, task_id, is_assessed, answer, grade)
values ('affae359-1bc5-43ee-9331-516f4a7e9b58', 1, 2, 1, true, '{"type":"SINGLE_CHOICE","key":"1"}', 100),
       ('affae359-1bc5-43ee-9331-516f4a7e9b58', 2, 2, 2, true, '{"type":"SINGLE_CHOICE","key":"1"}', 100);
