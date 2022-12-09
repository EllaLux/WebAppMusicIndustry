create sequence hibernate_sequence start 1 increment 1;

create table company (
                         id int8 not null,
                         address varchar(255) not null,
                         budget int8 not null,
                         date varchar(255) not null,
                         name varchar(50) not null,
                         link_id int8,
                         primary key (id)
);

create table staff (
                       id int8 not null,
                       position varchar(255) not null,
                       company_id int8,
                       user_id int8,
                       primary key (id)
);

create table user_role (
                           user_id int8 not null,
                           roles varchar(255)
);

create table usr (
                     id int8 not null,
                     activation_code varchar(255),
                     active boolean not null,
                     contacts varchar(255) not null,
                     email varchar(255) not null,
                     filename varchar(255),
                     fullname varchar(255) not null,
                     password varchar(255) not null,
                     username varchar(255) not null,
                     primary key (id)
);

create table activity (
                          id int8 not null,
                          date_event varchar(50),
                          expenses int8 not null,
                          income int8 not null,
                          title varchar(250) not null,
                          artist_id int8,
                          link_id int8,
                          primary key (id)
);

create table artist (
                        id int8 not null,
                        birthday varchar(50) not null,
                        debut varchar(50) not null,
                        education varchar(250) not null,
                        nickname varchar(250) not null,
                        position_group varchar(100) not null,
                        salary int8 not null,
                        company_id int8,
                        user_id int8,
                        primary key (id)
);

create table concert (
                         id int8 not null,
                         location varchar(50) not null,
                         activity_id int8,
                         primary key (id)
);

create table link (
                      id int8 not null,
                      site varchar(2048) not null,
                      primary key (id)
);

create table movie (
                       id int8 not null,
                       producer varchar(250) not null,
                       activity_id int8,
                       primary key (id)
);

create table reward (
                        id int8 not null,
                        result varchar(50) not null,
                        activity_id int8,
                        primary key (id)
);

create table track (
                       id int8 not null,
                       name varchar(250) not null,
                       activity_id int8,
                       primary key (id)
);

alter table if exists staff
    add constraint staff_user_fk
        foreign key (company_id) references company on delete cascade;

alter table if exists staff
    add constraint staff_company_fk
        foreign key (user_id) references usr on delete cascade;

alter table if exists user_role
    add constraint user_role_user_fk
        foreign key (user_id) references usr on delete cascade;

alter table if exists artist
    add constraint artist_company_fk
        foreign key (company_id) references company on delete cascade;

alter table if exists artist
    add constraint artist_user_fk
        foreign key (user_id) references usr on delete cascade;

alter table if exists company
    add constraint company_link_fk
        foreign key (link_id) references link on delete cascade;


alter table if exists activity
    add constraint activity_artist_fk
        foreign key (artist_id) references artist on delete cascade;

alter table if exists activity
    add constraint activity_link_fk
        foreign key (link_id) references link on delete cascade;

alter table if exists concert
    add constraint concert_activity_fk
        foreign key (activity_id) references activity on delete cascade;

alter table if exists movie
    add constraint movie_activity_fk
        foreign key (activity_id) references activity on delete cascade;

alter table if exists reward
    add constraint reward_activity_fk
        foreign key (activity_id) references activity on delete cascade;

alter table if exists track
    add constraint track_activity_fk
        foreign key (activity_id) references activity on delete cascade;
