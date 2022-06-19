
SELECT * FROM student WHERE age>10 AND age < 20;

SELECT name FROM student;

SELECT * FROM student WHERE lower(name) like ('%u%');

SELECT * FROM student WHERE age < student.id;

SELECT * FROM student ORDER BY age;