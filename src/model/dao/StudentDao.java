package model.dao;

import model.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    Student save(Student student);
    List<Student> getAll();
    boolean removeById(Integer id);

    Optional<Student> findById(Integer id);
    Student update(Student student);
    int count();
    List<Student> getAll(int offset, int limit);

}
