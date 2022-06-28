--liquibase formatted sql

--changeSet Ksenia:1
CREATE INDEX Student_name_idx on student(name);

--changeSet Ksenia:2
CREATE INDEX faculty_name_color_index on faculty(name,color);