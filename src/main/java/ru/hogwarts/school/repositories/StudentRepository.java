package ru.hogwarts.school.repositories;
import org.springframework.data.jpa.repository.*;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Collection<Student> findByAgeBetween(int ageStart,int ageEnd);
    Collection<Student> findAllByFacultyName(String faculty);
    Student findStudentByName(String name);
    Student findStudentById(Long id);
    List<Student> findAll();
}
