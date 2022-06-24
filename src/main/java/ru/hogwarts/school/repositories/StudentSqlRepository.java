package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentSql;
import ru.hogwarts.school.model.StudentSqlList;

import java.util.List;

@Repository
public interface StudentSqlRepository extends JpaRepository<Student, Long> {

    @Query(value = "SELECT COUNT(DISTINCT name) FROM student ", nativeQuery = true)
    StudentSql getStudentSqlCount();

    @Query(value = "SELECT AVG (age) FROM student", nativeQuery = true)
    StudentSql getStudentSqlAvg();

    @Query(value = "SELECT *  FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<StudentSqlList> getStudentSqlLast();

}
