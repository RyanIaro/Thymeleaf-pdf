alter table "employee" add column salary float;

alter table "employee" add constraint employee_salary_check check ( salary > -1 );

update "employee" set salary = 100000.0 where salary is null;

alter table "employee" alter column salary set not null;