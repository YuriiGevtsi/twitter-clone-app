delete from message;

insert into message (id, tag, text, user_id) values
  (1,'tag1','first',2),
  (2,'tag2','second',2),
  (3,'tag3','third',2),
  (4,'tag4','fourth',2);

alter sequence hibernate_sequence restart with 10;