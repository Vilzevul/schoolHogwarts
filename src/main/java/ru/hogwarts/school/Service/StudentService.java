package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Model.Faculty;
import ru.hogwarts.school.Model.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private Map<Long, Student> studentMap = new HashMap<Long, Student>();
    private Long idStudent = 1L;

    public Student createStudent(Student student) {
        idStudent++;
        return studentMap.put(idStudent, student);
    }

    public Map<Long, Student> getAllStudents() {
        return studentMap;
    }

    public Student getStudentId(Long idStudent) {
        return studentMap.get(idStudent);
    }

    public Student updateStudent(Long idStudent, Student student) {
        studentMap.put(idStudent, student);
        return (Student) student;
    }

    public Student deleteStudent(Long idStudent) {
        return studentMap.remove(idStudent);
    }

    public Map<Long, Student> ageStudentFilter(int param) {
        return studentMap.entrySet().stream().
                filter(v -> v.getValue().getAge() == param).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
