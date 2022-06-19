package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.Map;

@RequestMapping("/student")
@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
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

    @GetMapping("/age")
    public Collection<Student> ageStudentFilter(@RequestParam(value = "age") int param) {
        return studentService.ageStudentFilter(param);
    }

    @GetMapping
    public Collection getAgeStudents(@RequestParam int ageStart, @RequestParam int ageEnd) {
        if ((ageStart > 0) && (ageEnd > 0)) {
            return studentService.findByAgeBetween(ageStart, ageEnd);
        }
        return studentService.getAllStudents();
    }


}
