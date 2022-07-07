package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentSql;
import ru.hogwarts.school.model.StudentSqlList;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.repositories.StudentSqlRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService implements StudentInterface {
    private final StudentRepository studentRepository;
    private final StudentSqlRepository studentSqlRepository;
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository, StudentSqlRepository studentSqlRepository) {
        this.studentRepository = studentRepository;
        this.studentSqlRepository = studentSqlRepository;
    }


    public Collection getAllStudents() {
        logger.trace("Метод: getAllStudents");
        return studentRepository.findAll();
    }

    public Student getStudentId(Long idStudent) {
        logger.trace("Метод: getStudentId {} ", idStudent);
        return studentRepository.findById(idStudent).orElseGet(() -> {
            logger.error("Студент: {} не найден ", idStudent);
            throw new NotFoundException("Студент не найден");

        });


    }

    public Student updateStudent(Student student) {
        logger.trace("Метод: updateStudent ");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long idStudent) {
        logger.trace("Метод: deleteStudent ");
        studentRepository.deleteById(idStudent);
    }

    public Collection<Student> ageStudentFilter(int param) {
        logger.trace("Метод: ageStudentFilter ");
        return studentRepository.findAll().stream().
                filter(v -> v.getAge() == param).
                collect(Collectors.toList());
    }

    public Student createStudent(Student student) {
        logger.trace("Метод: createStudent ");
        return studentRepository.save(student);
    }

    public Collection<Student> findByAgeBetween(int ageStart, int ageEnd) {
        logger.trace("Метод: findByAgeBetween ");
        return studentRepository.findByAgeBetween(ageStart, ageEnd);
    }

    public Collection<Student> findAllByFacultyName(String faculty) {
        logger.trace("Метод: findAllByFacultyName ");
        return studentRepository.findAllByFacultyName(faculty);
    }

    public Faculty getFacultyByStudent(String studentName) {
        logger.trace("Метод: getFacultyByStudent {} ", studentName);
        Student student = studentRepository.findStudentByName(studentName);
        return student.getFaculty();
    }

    public StudentSql getStudentSqlCount() {

        logger.trace("Метод: getStudentSqlCount ");
        return studentSqlRepository.getStudentSqlCount();
    }

    public StudentSql getStudentSqlAvg() {
        logger.trace("Метод: getStudentSqlAvg ");
        return studentSqlRepository.getStudentSqlAvg();
    }

    public Collection<StudentSqlList> getStudentSqlLast() {
        logger.trace("Метод: getStudentSqlLast ");
        return studentSqlRepository.getStudentSqlLast();

    }

    //4.5 Параллельные стримы
    @Override
    public List<Student> getAllStudentStartedFromA() {
        return studentRepository.findAll().stream()
                .peek(v -> v.setName(v.getName().toUpperCase()))
                .filter(v -> v.getName().substring(0, 1).equals("А"))
                .sorted((v, s) -> v.getName().compareTo(s.getName()))
                .collect(Collectors.toList())
                ;
    }

    @Override
    public double averageAgeStudents() {
        return studentRepository.findAll().stream()
                .mapToInt(v -> v.getAge()).average().getAsDouble();
    }

    @Override
    public void toConsole() {
        List<String> studentsName = new ArrayList<>();
        studentsName = studentRepository.findAll().stream()
                .map(v -> v.getName()).limit(6)
                .sorted((v, s) -> v.compareTo(s))
                .collect(Collectors.toList());
        List<String> finalStudentsName = studentsName;

        System.out.println(studentsName);
//thread1
        System.out.println(finalStudentsName.get(0));
        System.out.println(finalStudentsName.get(1));

        new Thread(() -> {
            System.out.println(finalStudentsName.get(2));
            System.out.println(finalStudentsName.get(3));
        }).run();

        Thread thread = new Thread(() -> {
            System.out.println(finalStudentsName.get(4));
            System.out.println(finalStudentsName.get(5));
        });
        thread.run();


    }

    //thread2
    List<String> finalStudentsName = new ArrayList<>();

    private synchronized void nameToConsole(int i) {
        System.out.println(finalStudentsName.get(i));
    }

    @Override
    public void toConsoleSynchronized() {
        List<String> studentsName = new ArrayList<>();
        studentsName = studentRepository.findAll().stream()
                .map(v -> v.getName()).limit(6)
                .sorted((v, s) -> v.compareTo(s))
                .collect(Collectors.toList());
        finalStudentsName = studentsName;

        nameToConsole(0);
        nameToConsole(1);

        new Thread(() -> {
            nameToConsole(2);
            nameToConsole(3);
        }).run();

        new Thread(() -> {
            nameToConsole(4);
            nameToConsole(5);
        }).run();

    }

}
