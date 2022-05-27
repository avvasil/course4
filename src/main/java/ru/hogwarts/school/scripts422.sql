CREATE TABLE cars (
                      id int primary key,
                      brand varchar,
                      model varchar,
                      price int
);
CREATE TABLE persons (
                         id int,
                         name text primary key,
                         age int,
                         license boolean,
                         car_id int references cars (id)
);