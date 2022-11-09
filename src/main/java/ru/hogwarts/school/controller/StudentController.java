package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.record.FacultyRecord;
import ru.hogwarts.school.record.StudentRecord;
import ru.hogwarts.school.service.StudentService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public StudentRecord read(@PathVariable long id) {
        return studentService.read(id);
    }

    @PostMapping
    public StudentRecord create(@RequestBody @Valid StudentRecord studentRecord) {
        return studentService.create(studentRecord);
    }

    @PutMapping("/{id}")
    public StudentRecord update(@PathVariable long id,
                                @RequestBody @Valid  StudentRecord student) {
        return studentService.update(id,student);
    }

    @DeleteMapping("/{id}")
    public StudentRecord delete(@PathVariable long id) {
        return studentService.delete(id);
    }

    @GetMapping(params = "age")
    public Collection<StudentRecord> findByAge(@RequestParam Integer age){
        return studentService.findByAge(age);
    }
    @GetMapping(params = {"minAge", "maxAge"})
    public Collection<StudentRecord> findByAgeBetween(@RequestParam Integer min,
                                                      @RequestParam Integer max){
        return studentService.findByAgeBetween(min,max);
    }

    @GetMapping("/{id}/faculty")
    public FacultyRecord getFacultyByStudent(@RequestParam long id){
        return studentService.getFacultyByStudent(id);
    }
}
