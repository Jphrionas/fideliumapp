insert into role(role_id, public_id, nome, registration_date, disabled)  values (null, 
'ffc774f5-d7fd-40c4-999e-88c258a8b9e4', 'ROLE_ADMIN', current_date(), false);

insert into role(role_id, public_id, nome, registration_date, disabled)  values (null, '7ed44a6a-ee80-48a9-9d27-c2c6e51e1f96
', 'ROLE_USER', current_date(), false);


insert into users (user_id, celular, cpf, disabled, dob, email, estado, genero, nome, pass, public_id, registration_date, user_level)
values 
(null, '17991758288', '01111608839', false, 'dob', 'fabiano@email.com', 'SP', 'M', 'Fabiano Adelino',
'$2a$10$DZGtfQxa12/AOZGoHLDTzeQFLKc/HGy5pqZsrfS7DNMF/Y5J.DGNK', 'bbb3dc42-767e-473d-a3ce-e94c5b3077b9', current_date(), 1);

