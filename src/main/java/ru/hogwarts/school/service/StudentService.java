package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentSql;
import ru.hogwarts.school.model.StudentSqlList;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.repositories.StudentSqlRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentService implements StudentInterface {
    private final StudentRepository studentRepository;
    private final StudentSqlRepository studentSqlRepository;

    public StudentService(StudentRepository studentRepository, StudentSqlRepository studentSqlRepository) {
        this.studentRepository = studentRepository;
        this.studentSqlRepository = studentSqlRepository;
    }


    public Collection getAllStudents() {

        return studentRepository.findAll();
    }

    public Student getStudentId(Long idStudent) {
        return studentRepository.findById(idStudent).orElseThrow(() -> new NotFoundException("Студент не найден"));
        //     return studentRepository.findStudentById(idStudent);


    }

    public Student updateStudent(Student student) {

        return studentRepository.save(student);
    }

    public void deleteStudent(Long idStudent) {

        studentRepository.deleteById(idStudent);
    }

    public Collection<Student> ageStudentFilter(int param) {
        return studentRepository.findAll().stream().
                filter(v -> v.getAge() == param).
                collect(Collectors.toList());
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Collection<Student> findByAgeBetween(int ageStart, int ageEnd) {
        return studentRepository.findByAgeBetween(ageStart, ageEnd);
    }

    public Collection<Student> findAllByFacultyName(String faculty) {
        return studentRepository.findAllByFacultyName(faculty);
    }

    public Faculty getFacultyByStudent(String studentName) {
        Student student = studentRepository.findStudentByName(studentName);
        return student.getFaculty();
    }

    public StudentSql getStudentSqlCount() {
        return studentSqlRepository.getStudentSqlCount();
    }

    public StudentSql getStudentSqlAvg() {
        return studentSqlRepository.getStudentSqlAvg();
    }

    public Collection<StudentSqlList> getStudentSqlLast() {
        return studentSqlRepository.getStudentSqlLast();

    }

}
