delete from user_role;
delete from users;

insert into users(id, active,email, password, user_name) values
  (2,true,'some@some.some','$2a$08$35QlNqZ8D5yC1H7zwKqZwe0Zg6jOI9dQTtZAt2jps4qfDpnSFfuNG','some');

insert into user_role (id, roles) values
  (2,'USER');
