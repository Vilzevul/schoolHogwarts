package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public interface AvatarRepository extends PagingAndSortingRepository<Avatar,Long> {
    Optional <Avatar> findByStudentId(Long studentId) throws IOException;


}
