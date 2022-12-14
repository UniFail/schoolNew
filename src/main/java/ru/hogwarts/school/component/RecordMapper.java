package ru.hogwarts.school.component;

import org.springframework.stereotype.Component;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.record.FacultyRecord;
import ru.hogwarts.school.record.StudentRecord;

@Component
public class RecordMapper {
    public StudentRecord toRecord(Student student){
        StudentRecord studentRecord = new StudentRecord();
        studentRecord.setId(student.getId());
        studentRecord.setName(student.getName());
        studentRecord.setAge(student.getAge());
        if (student.getFaculty() != null){
            studentRecord.setFaculty(toRecord(student.getFaculty()));
        }
        return studentRecord;
    }
    public FacultyRecord toRecord(Faculty faculty){
        FacultyRecord facultyRecord = new FacultyRecord();
        facultyRecord.setId(faculty.getId());
        facultyRecord.setName(faculty.getName());
        facultyRecord.setColor(faculty.getColor());
        return facultyRecord;
    }


    public Student toEntity(StudentRecord studentRecord){
        Student student = new Student();
        student.setName(studentRecord.getName());
        student.setAge(studentRecord.getAge());
        if (studentRecord.getFaculty() != null){
            Faculty faculty = toEntity(studentRecord.getFaculty());
            faculty.setId(studentRecord.getFaculty().getId());
            student.setFaculty(faculty);
        }
        return student;
    }

    public Faculty toEntity(FacultyRecord facultyRecord){
        Faculty faculty = new Faculty();
        faculty.setId(facultyRecord.getId());
        faculty.setName(facultyRecord.getName());
        faculty.setColor(facultyRecord.getColor());
        return faculty;
    }

}
