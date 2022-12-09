insert into usr(id, active, contacts, email, fullname, password, username)
    values (1, true, '+79103152975', 'admin_bd_pop_stars@yandex.ru', 'Ларина Вероника Олеговна', '21072002', 'ella');

insert into user_role(user_id, roles)
    values (1, 'USER'), (1, 'ADMIN');