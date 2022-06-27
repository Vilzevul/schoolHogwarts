ALTER TABLE student
    ADD CONSTRAINT age_limit CHECK (age > 15);

ALTER TABLE student
    ADD CONSTRAINT name_unique UNIQUE (name);
ALTER TABLE student
    ALTER COLUMN name SET NOT NULL;

ALTER TABLE faculty
    ADD CONSTRAINT color_name_unique UNIQUE (name, color);

ALTER TABLE student
    ALTER COLUMN age SET DEFAULT 20;