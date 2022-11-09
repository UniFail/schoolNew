package ru.hogwarts.school.exception;

public class FacultyNotFoundException extends RuntimeException{

    private final Long id;

    public FacultyNotFoundException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

