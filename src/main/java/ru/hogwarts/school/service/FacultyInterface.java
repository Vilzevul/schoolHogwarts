package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public interface FacultyInterface {

    Faculty createFaculty(Faculty faculty);

    Optional<Faculty> getFacultyId(Long idFaculty);

    Faculty updateFaculty(Faculty faculty);

    void deleteFaculty(Long idFaculty);

    Collection<Faculty> colorOrNameFilter(String color, String name);
}
