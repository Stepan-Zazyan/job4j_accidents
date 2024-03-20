alter TABLE users add column enabled boolean default true;
alter TABLE users add unique (name);
alter TABLE users rename column name to username;