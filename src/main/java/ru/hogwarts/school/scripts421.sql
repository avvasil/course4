ALTER TABLE student
    ADD CONSTRAINT age_constraint CHECK (age > 16);

ALTER TABLE student
    ALTER COLUMN name SET NOT NULL;

ALTER TABLE student
    ADD CONSTRAINT name_constraint UNIQUE (name);

ALTER TABLE faculty
    ADD CONSTRAINT name_color_constraint UNIQUE (name, color);

ALTER TABLE student
    ALTER COLUMN age SET DEFAULT 20;