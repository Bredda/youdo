create table todos (id  bigserial not null, is_completed boolean not null, text varchar(120), user_id int8 not null, primary key (id));
create table users (id  bigserial not null, password varchar(120), username varchar(20), primary key (id));
alter table users add constraint UKr43af9ap4edm43mmtq01oddj6 unique (username);
alter table todos add constraint FK9605g76a1dggbvs18f2r80gvu foreign key (user_id) references users on delete cascade;

