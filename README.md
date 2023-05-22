После запуска, в таблицы нужно добавить
insert into user_table values ('e347c77f-99d0-4573-b392-ad80575aa674',
                               '10.02.1980',
                               'admin@admin.ru',
                               'admin',
                               'admin',
                               '$2a$12$DqIxvqluHjQ.yGib84rqxeWvQrL6Mc1D/I2mwrbYSShuDRogZeLQK', // пароль "pas"
                               'admin',
                               9999999999);

insert into role values(
                           '4f24a593-211f-4860-980a-b6b298bc8fa9',
                           'ROLE_ADMIN'
                       );

insert into role values(
                           '0d2d3d2a-9771-4682-a22c-3049cbae44af',
                           'ROLE_USER'
                       );

insert into role_user values ('4f24a593-211f-4860-980a-b6b298bc8fa9',
                              'e347c77f-99d0-4573-b392-ad80575aa674');
                              
                              
Для авторизации, как админ и получения токена    
api/auth/login
{
  "email": "admin@admin.ru",
  "password": "pas"
}

Для регистрации нового пользователя
api/register
{
  "name": "name1",
  "lastname": "lastname1",
  "patronymic": "patronymic",
  "birthday": "2023-05-22",
  "password": "pas1",
  "email": "user@user.ru",
  "phoneNumber": "7856564564"
}
                              
