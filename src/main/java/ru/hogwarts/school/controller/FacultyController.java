package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.record.FacultyRecord;
import ru.hogwarts.school.record.StudentRecord;
import ru.hogwarts.school.service.FacultyService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(value = "/faculties")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {

        this.facultyService = facultyService;

    }
    @GetMapping("/{id}")
    public FacultyRecord read(@PathVariable long id) {
        return facultyService.read(id);
    }

    @PostMapping
    public FacultyRecord create(@RequestBody @Valid FacultyRecord facultyRecord) {
        return facultyService.create(facultyRecord);
    }

    @PutMapping("/{id}")
    public FacultyRecord update(@PathVariable long id,
                                @RequestBody @Valid FacultyRecord facultyRecord) {
        return facultyService.update(id,facultyRecord);
    }

    @DeleteMapping("/{id}")
    public FacultyRecord delete(@PathVariable long id) {
        return  facultyService.delete(id);

    }

    @GetMapping(params = "color")
    public Collection<FacultyRecord> findByColor(@RequestParam String color){
        return facultyService.findByColor(color);
    }

    @GetMapping(params = "nameOrColor")
    public Collection<FacultyRecord> findByNameOrColor(@RequestParam String colorOrName){
        return facultyService.findByNameOrColor(colorOrName);
    }

    @GetMapping("/{id}/students")
    public Collection<StudentRecord> getStudentByFaculty(@PathVariable long id){
        return facultyService.getStudentsByFaculty(id);
    }
}
