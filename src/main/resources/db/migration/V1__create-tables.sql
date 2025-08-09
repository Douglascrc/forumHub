create table courses (
    id bigint not null,
    name varchar(255) not null,
    category varchar(255) not null,
    primary key (id)
);

create table users (
    id bigint not null,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    primary key (id)
);

create table topics (
    id bigint not null,
    title varchar(255) not null,
    message varchar(1024) not null,
    created_at timestamp not null default current_timestamp,
    state_topic varchar(200) not null,
    course_id bigint not null,
    author_id bigint not null,
    primary key (id),
    foreign key (course_id) references courses(id),
    foreign key (author_id) references users(id)
);

create table answers (
    id bigint not null,
    message varchar(1024) not null,
    created_at timestamp not null default current_timestamp,
    topic_id bigint not null,
    author_id bigint not null,
    primary key (id),
    foreign key (topic_id) references topics(id),
    foreign key (author_id) references users(id)
);

create table profiles (
    id bigint not null,
    name varchar(255) not null,
    primary key (id)
);)