package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentInterface;
import ru.hogwarts.school.service.StudentService;

import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.Map;

@RequestMapping("/student")
@RestController
public class StudentController {
    private final StudentInterface studentService;

    public StudentController(StudentInterface studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createStudent);

    }

    @GetMapping("/{idStudent}")
    public ResponseEntity<Student> getStudent(@PathVariable Long idStudent) {
        Student student = studentService.getStudentId(idStudent);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updateStudent = studentService.updateStudent(student);
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping("/{idStudent}")
    public ResponseEntity deleteStudent(@PathVariable Long idStudent) {
        studentService.deleteStudent(idStudent);
        return ResponseEntity.ok().build();

    }

    @GetMapping(path = "/ageBetween")
    public Collection getAgeStudents(@RequestParam int ageStart, @RequestParam int ageEnd) {
        if ((ageStart > 0) && (ageEnd > 0)) {
            return studentService.findByAgeBetween(ageStart, ageEnd);
        }
        return studentService.getAllStudents();
    }

    @GetMapping("/students")
    public Collection<Student> findAllStudentsByFacultyName(@RequestParam String faculty) {
        return studentService.findAllByFacultyName(faculty);
    }

    @GetMapping("/faculty")
    public Faculty getFacultyByStudent(@RequestParam String studentName) {
        return studentService.getFacultyByStudent(studentName);
    }

    @GetMapping("/findAll'A'")
    public Collection<Student> getAllStudentStartedFromA() {

        return studentService.getAllStudentStartedFromA();
    }

    @GetMapping("/averageAge")
    public double averageAgeStudents() {
        return studentService.averageAgeStudents();
    }



}
