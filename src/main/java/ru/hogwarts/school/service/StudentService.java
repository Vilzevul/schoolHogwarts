package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class StudentService implements StudentInterface {
    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Collection getAllStudents() {

        return studentRepository.findAll();
    }

    public Student getStudentId(Long idStudent) {
        return studentRepository.getReferenceById(idStudent);


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
}
