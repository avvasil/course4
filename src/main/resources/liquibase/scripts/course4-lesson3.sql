--liquibase formatted sql

--changeset avvasil:1
CREATE INDEX user_name_index ON student (name);

--changeset avvasil:2:
CREATE INDEX faculty_nc_index ON faculty (name, color);