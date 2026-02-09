package model.dao;

import db.StudentDB;
import model.entities.Student;

import java.util.List;
import java.util.Optional;

public class StudentDaoImpl implements StudentDao{

    private final StudentDB studentDb;

    public StudentDaoImpl(StudentDB studentDb) {
        this.studentDb = studentDb;
    }


    @Override
    public Student save(Student student) {
        studentDb.getStudentList().add(student);
        return student;
    }

    @Override
    public List<Student> getAll() {
        return studentDb.getStudentList();
    }

    @Override
    public boolean removeById(Integer id) {
        return studentDb.getStudentList().removeIf(student -> student.getId().equals(id));
    }
    @Override
    public Optional<Student> findById(Integer id) {
        return studentDb.getStudentList()
                .stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    @Override
    public Student update(Student student) {
        return student;
    }
    @Override
    public int count() {
        return studentDb.getStudentList().size();
    }

    @Override
    public List<Student> getAll(int offset, int limit) {
        if (offset < 0) offset = 0;
        if (limit <= 0) limit = 10;

        return studentDb.getStudentList()
                .stream()
                .skip(offset)
                .limit(limit)
                .toList();
    }

}
