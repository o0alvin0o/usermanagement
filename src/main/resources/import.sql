insert into users(creation_date, last_login, email, hashed_password, salt, status, username)
values ('2024-03-20 01:20:22.385155',null,'mikel@bank.com',
        '$argon2id$v=19$m=4096,t=6,p=4$W0JAMzVjMzc3OTM$njnYe9pdt30OH00MIJJnlip+kO6iBa9lMl5cilpznCqoE8nnAAt+DB6Ksmt4X/Ft+MbNLA0tKlcobYXr5SN16j0NilHhzNPtuBOxcdtSeuDYYDu9WckXLMEPLTYUhWbyzpbpviDY4euKRf+ATeuQgiy+qA8GzSCLkJXpSbu0whc',
        '[B@35c37793','ACTIVE','mikel');

insert into user_roles(role, username) values ('ADMIN', 'mikel');
insert into user_roles(role, username) values ('USER', 'mikel');
insert into user_roles(role, username) values ('TRADER', 'mikel');
insert into user_roles(role, username) values ('AUDITOR', 'mikel');


insert into users(creation_date, last_login, email, hashed_password, salt, status, username)
values ('2024-03-20 01:23:20.004455',null,'user@user.com',
        '$argon2id$v=19$m=4096,t=6,p=4$W0JAMmU5ZjY2MQ$nriJiDmAl+r6dRn4RGRfMWj2j9TrVsdNAk2GmhFnx0OQwgY6uL5HQSGuEp2rICyEIrfLVlmQbbsHFhHpMg9GkXussRQukA8K8j3cXK0A+b1V68YfrmLzDQ0iMe6nuTfZXbi4WE9E3XuHRdeBorG+mhZT6EiLbzO04xdfGoRyzSc',
        '[B@2e9f661','ACTIVE','user');

insert into user_roles(role, username) values ('USER', 'user');

insert into users(creation_date, last_login, email, hashed_password, salt, status, username)
values ('2024-03-20 01:26:23.111535',null,'admin@admin.com',
        '$argon2id$v=19$m=4096,t=6,p=4$W0JAMmU5ZjY2MQ$nriJiDmAl+r6dRn4RGRfMWj2j9TrVsdNAk2GmhFnx0OQwgY6uL5HQSGuEp2rICyEIrfLVlmQbbsHFhHpMg9GkXussRQukA8K8j3cXK0A+b1V68YfrmLzDQ0iMe6nuTfZXbi4WE9E3XuHRdeBorG+mhZT6EiLbzO04xdfGoRyzSc',
        '[B@3336663d','ACTIVE','admin');

insert into user_roles(role, username) values ('ADMIN', 'admin');

insert into users(creation_date, last_login, email, hashed_password, salt, status, username)
values ('2024-03-20 01:29:26.784851',null,'trader@trader.com',
        '$argon2id$v=19$m=4096,t=6,p=4$W0JAMjhjYjFhZg$a1RWWWfwAUWCB2EQCFZ8MLYd0crtF7s86YipC7r/rPVkwWCdMqmNAn1EoiEtmQ+j6gUb3gr9LKjlGBmvykzFavQIdzPAELfntv9QBJl5YrH7H6hDSkcSlYGd120ZhZyF89lESr1uZR50cSsLpBOctdO8RMJNataxnLzUrXvvO/A',
        '[B@28cb1af','ACTIVE','trader');

insert into user_roles(role, username) values ('TRADER', 'trader');


insert into users(creation_date, last_login, email, hashed_password, salt, status, username)
values ('2024-03-20 01:30:22.585375',null,'auditor@auditor.com',
        '$argon2id$v=19$m=4096,t=6,p=4$W0JAN2UwYjcyZTg$1xZdkzfNeUAR+wpApYenkbg0Dm1ZInVqn8trjmTIpJfH1RNylUqHQLcmMd+mBAK1/tCLspfxj3HDXKv+/0X2Tk0qzyXQ0+qDUtlgQ5uZYlTY26hq8/bo7GKqsDWAYlZ+L1ErRyERbsQpilKs7AWsIfGYCRdBgbaBPGm0UpOsbc8',
        '[B@7e0b72e8','ACTIVE','auditor');

insert into user_roles(role, username) values ('AUDITOR', 'auditor');
