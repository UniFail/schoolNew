package ru.hogwarts.school.record;

import org.springframework.stereotype.Service;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class StudentRecord {
    private Long id;

    @NotBlank(message = "Name of student is empty")
    private String name;

    @Min(value = 12, message = "Min age is 12 years!")
    @Max(value = 25, message = "Max age is 25 years!")
    private Integer age;

    private FacultyRecord faculty;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public FacultyRecord getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyRecord faculty) {
        this.faculty = faculty;
    }
}
