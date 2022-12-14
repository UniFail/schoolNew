package ru.hogwarts.school.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

@Qualifier("students")
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Collection<Student> findAllByAge(Integer age);
    Collection<Student> findByAgeBetween(Integer min, Integer max);

}
