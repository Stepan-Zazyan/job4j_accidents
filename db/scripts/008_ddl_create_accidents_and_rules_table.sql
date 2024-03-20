create table accidents_and_rules (
                                      id serial primary key,
                                      accidents_id int not null references accidents(id),
                                      rules_id int not null references rules(id)
)