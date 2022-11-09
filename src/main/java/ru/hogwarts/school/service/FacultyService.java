package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.component.RecordMapper;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.record.FacultyRecord;
import ru.hogwarts.school.record.StudentRecord;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;
    private final RecordMapper recordmapper;

    public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository, RecordMapper recordmapper) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
        this.recordmapper = recordmapper;
    }

    public FacultyRecord create(FacultyRecord facultyRecord) {
        return recordmapper.toRecord(facultyRepository.save(recordmapper.toEntity(facultyRecord)));
    }

    public FacultyRecord read(long id) {
        return recordmapper.toRecord(facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id)));
    }

    public FacultyRecord update(long id,
                                FacultyRecord facultyRecord) {
        Faculty oldFaculty = facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id)); //Достаем из базы запись
        oldFaculty.setName(facultyRecord.getName());                                                             //Апдейт полей
        oldFaculty.setColor(facultyRecord.getColor());                                                           //Сохранение работает если сущность есть в базе и есть id
        return recordmapper.toRecord(facultyRepository.save(oldFaculty));

    }

    public FacultyRecord delete(long id) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow(() -> new FacultyNotFoundException(id));
        facultyRepository.delete(faculty);
        return recordmapper.toRecord(faculty);
    }


    public Collection<FacultyRecord> findByColor(String color) {
        return facultyRepository.findAllByColor(color).stream()
                .map(recordmapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<FacultyRecord> findByNameOrColor(String colorOrName) {
        return facultyRepository.findByNameOrColorIgnoreCase(colorOrName, colorOrName).stream()
                .map(recordmapper::toRecord)
                .collect(Collectors.toList());
    }

    public Collection<StudentRecord> getStudentsByFaculty(long id) {
        return facultyRepository.findById(id)
                .map(Faculty::getStudents)
                .map(students ->
                        students.stream()
                                .map(recordmapper::toRecord)
                                .collect(Collectors.toList()))
                .orElseThrow(() -> new FacultyNotFoundException(id));
    }
}
