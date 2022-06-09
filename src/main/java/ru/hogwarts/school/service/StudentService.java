package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> studentMap = new HashMap<Long, Student>();
    private Long idStudent = 1L;

    public Student createStudent(Student student) {
       student.setId(++idStudent);
        return studentMap.put(idStudent, student);
    }

    public Map<Long, Student> getAllStudents() {
        return studentMap;
    }

    public Student getStudentId(Long idStudent) {
        return studentMap.get(idStudent);
    }

    public Student updateStudent(Student student) {
        studentMap.put(idStudent, student);
        return student;
    }

    public Student deleteStudent(Long idStudent) {
        return studentMap.remove(idStudent);
    }

    public Collection<Student> ageStudentFilter(int param) {
        return studentMap.values().stream().
                filter(v -> v.getAge() == param).
                collect(Collectors.toList());
    }

}
