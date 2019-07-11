create table user_subscriptions(
  chanel_id int8 not null references users,
  subscriber_id int8 not null references users,
  primary key (chanel_id,subscriber_id)
)