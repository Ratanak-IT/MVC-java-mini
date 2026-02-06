package model.dao;

import db.StudentDB;
import model.entities.Student;

import java.util.List;

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
}
