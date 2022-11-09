package ru.hogwarts.school.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

@Qualifier("faculties")
@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    Collection<Faculty> findAllByColor(String color);
    Collection<Faculty> findByNameOrColorIgnoreCase(String name, String color);
}
