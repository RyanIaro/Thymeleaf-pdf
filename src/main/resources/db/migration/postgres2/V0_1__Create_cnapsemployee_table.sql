create extension if not exists "uuid-ossp";

CREATE TABLE cnapsemployee (
                               id                  varchar
                                   constraint employee_pk primary key default uuid_generate_v4(),
                               cin VARCHAR(255),
                               cnaps VARCHAR(255) check ( cnaps ~ '^[A-Za-z0-9]+$' ),
                               end_to_end_id VARCHAR UNIQUE,
                               image VARCHAR(255),
                               address VARCHAR(255),
                               last_name VARCHAR(255),
                               first_name VARCHAR(255),
                               personal_email VARCHAR(255),
                               professional_email VARCHAR(255),
                               registration_number VARCHAR(255),
                               birth_date DATE,
                               entrance_date DATE,
                               departure_date DATE,
                               children_number INTEGER
);

create index if not exists cnaps_employee_fk_index on cnapsemployee (end_to_end_id);
