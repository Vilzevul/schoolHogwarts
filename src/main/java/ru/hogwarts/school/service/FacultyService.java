package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.*;
import java.util.stream.Collectors;

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
        logger.debug("Метод: getFacultyId {}",idFaculty);
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


}

