package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface StudentInterface {
    Collection getAllStudents();

    Student getStudentId(Long idStudent);

    Student updateStudent(Student student);

    void deleteStudent(Long idStudent);

    Collection<Student> ageStudentFilter(int param);

    Student createStudent(Student student);

    Collection<Student> findByAgeBetween(int ageStart, int ageEnd);

    Collection<Student> findAllByFacultyName(String faculty);

    Faculty getFacultyByStudent(String studentName);

    List<Student> getAllStudentStartedFromA();

    double averageAgeStudents();

    void toConsole();

    void toConsoleSynchronized();
}
