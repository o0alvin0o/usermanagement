insert into users(creation_date, last_login, email, hashed_password, status, username)
values ('2024-03-20 01:20:22.385155',null,'mikel@admin.com',
        '$2a$10$y53we97/5n44./QuBkSVWOeN9sIiIRhfMZDIvtnbdjuL51eS1OHAy',
        'ACTIVE','mikel');

insert into user_roles(role, username) values ('ADMIN', 'mikel');
insert into user_roles(role, username) values ('USER', 'mikel');
insert into user_roles(role, username) values ('TRADER', 'mikel');
insert into user_roles(role, username) values ('AUDITOR', 'mikel');


insert into users(creation_date, last_login, email, hashed_password, status, username)
values ('2024-03-20 01:23:20.004455',null,'user@user.com',
        '$2a$10$Vj1R5csOM2E4bOnx2X9d5.Hdve/hIZpaUA.6T0DqhdRis3UmG3.gu',
        'ACTIVE','user');

insert into user_roles(role, username) values ('USER', 'user');

insert into users(creation_date, last_login, email, hashed_password, status, username)
values ('2024-03-20 01:26:23.111535',null,'admin@admin.com',
        '$2a$10$wY1RPrNGiu/3Qn85yS0PqeKKgC7ZpdZJITiLM2aano7Gm834fVM36',
        'ACTIVE','admin');

insert into user_roles(role, username) values ('ADMIN', 'admin');

insert into users(creation_date, last_login, email, hashed_password, status, username)
values ('2024-03-20 01:29:26.784851',null,'trader@trader.com',
        '$2a$10$oqUOp/Fn4Obr49YnyKeHP.vDHiDW07TGbLC5RiO65ZBWgF/2x/Jwm',
        'ACTIVE','trader');

insert into user_roles(role, username) values ('TRADER', 'trader');


insert into users(creation_date, last_login, email, hashed_password, status, username)
values ('2024-03-20 01:30:22.585375',null,'auditor@auditor.com',
        '$2a$10$dUJVoSSvW.erQz2EBxmje.cq/6Hfwg.SS2.v9SegnFRjyX1VzyJge',
        'ACTIVE','auditor');

insert into user_roles(role, username) values ('AUDITOR', 'auditor');
