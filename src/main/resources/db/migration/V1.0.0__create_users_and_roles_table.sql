create table users
(
    creation_date   timestamp(6),
    last_login      timestamp(6),
    email           varchar(255) not null
        unique,
    hashed_password varchar(255),
    status          varchar(255)
        constraint users_status_check
            check ((status)::text = ANY
                   ((ARRAY ['ACTIVE':: character varying, 'INACTIVE':: character varying, 'SUSPENDED':: character varying])::text[])
                ),
    username        varchar(255) not null
        primary key
);

create table user_roles
(
    role     varchar(255)
        constraint user_roles_role_check
            check ((role)::text = ANY
                   ((ARRAY ['ADMIN':: character varying, 'USER':: character varying, 'TRADER':: character varying, 'AUDITOR':: character varying])::text[])
                ),
    username varchar(255) not null,
    constraint fk_users_user_roles
        foreign key (username)
            references users (username) on delete cascade
);
