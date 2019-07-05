## Java_Tasks
### Вход в базу данных Oracle как администратор
#### SQL> connect system/oracle

### SQL команды для создания базы данных пользователя
#### SQL> create user chess_user identified by chess_password;
#### SQL> GRANT CREATE SESSION, CONNECT, RESOURCE, DBA TO chess_user;

## создание таблицы базы данных шахматных игр

#### CREATE TABLE chess(
####  player_id NUMBER(10) NOT NULL,
####  player_name VARCHAR2(50) NOT NULL,
####  piece_type VARCHAR2(50) NOT NULL,
####  player_move_from VARCHAR2(50) NOT NULL,
####  player_move_to VARCHAR2(50) NOT NULL,
####  captured_piece VARCHAR2(50)
#### );
