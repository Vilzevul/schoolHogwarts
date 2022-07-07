package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FacultyService implements FacultyInterface {
    private final FacultyRepository facultyRepository;
    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty(Faculty faculty) {

        logger.debug("Метод: createFaculty");
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> getFacultyId(Long idFaculty) {
        logger.debug("Метод: getFacultyId {}", idFaculty);
        return facultyRepository.findById(idFaculty);
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.debug("Метод: updateFaculty ");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long idFaculty) {
        logger.debug("Метод: deleteFaculty ");
        facultyRepository.deleteById(idFaculty);

    }

    public Collection<Faculty> colorFacultyFilter(String param) {
        return facultyRepository.findAll().stream().
                filter(v -> v.getColor().equals(param)).
                collect(Collectors.toList());
    }

    public Collection<Faculty> colorOrNameFilter(String color, String name) {
        logger.debug("Метод: colorOrNameFilter ");
        return facultyRepository.findFacultyByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    //4.5 Параллельные стримы
    @Override
    public String longestFacultyName() {
        return facultyRepository.findAll().stream()
                .map(n -> n.getName())
                .max((v, n) -> v.length() - n.length()).get();

    }

    @Override
    public int mining() {
        int sum = Stream
                .iterate(1, a -> a + 1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
        return sum;
    }


}

