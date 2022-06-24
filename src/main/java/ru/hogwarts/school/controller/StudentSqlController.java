package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentSql;
import ru.hogwarts.school.model.StudentSqlList;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RequestMapping("/sql")
@RestController
public class StudentSqlController {

   private final StudentService studentService;

    public StudentSqlController(StudentService studentService) {
        this.studentService = studentService;
        }

        @GetMapping("/student-count")
    public Integer getStudentSqlCount(){
        return studentService.getStudentSqlCount().getCount();
    }

    @GetMapping("/student-avg")
    public Double getStudentSqlAvg(){
        return studentService.getStudentSqlAvg().getAvg();
    }

    @GetMapping("/student-last5")
    public Collection<StudentSqlList> getStudentSqlLast(){
        return studentService.getStudentSqlLast();
    }

}
