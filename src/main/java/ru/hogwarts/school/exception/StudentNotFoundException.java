package ru.hogwarts.school.exception;

public class StudentNotFoundException extends RuntimeException{
    private final Long id;

    public StudentNotFoundException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
