package db;

import model.entities.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDB {
    private final List<Student> studentList;
    public StudentDB(){
        studentList= new ArrayList<>(){{
            add(new Student(
                    "Ratanak", LocalDate.now().minusYears(20), Student.Gender.MALE
            ));
            add(new Student("Davin", LocalDate.of(2006,12,2), Student.Gender.MALE));
            add(new Student("Bopha", LocalDate.of(2000,1,1), Student.Gender.MALE));
        }};
    }

    public List<Student> getStudentList() {
        return studentList;
    }
}
