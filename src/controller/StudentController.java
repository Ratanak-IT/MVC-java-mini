package controller;

import model.dto.StudentRequestDto;
import model.dto.StudentResponseDto;
import model.service.StudentService;
import view.StudentView;

import java.rmi.StubNotFoundException;

public class StudentController {
    private final StudentView studentView;
    private final StudentService studentService;
    public StudentController(StudentView studentView, StudentService studentService) {
        this.studentView = studentView;
        this.studentService = studentService;
    }

    public void create(){
        try{
            StudentRequestDto request = studentView.displayStudentCreateDto();
            StudentResponseDto studentResponseDto = studentService.createStudent(request);

            studentView.displaySingleStudent(studentResponseDto);
        } catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }

    }
    public void removeById(){
       int id = studentView.removeStudent();
       if (studentService.removeStudentById(id)){
           System.out.println("Student removed successfully");
       }else {
           System.out.println("Student not found");
       }
    }
    public void showAllStudents(){
        studentView.displayStudentList(studentService.getAllStudents());

    }
    public void run(){
        while (true){
            int option=studentView.showMenAndGetOption();
            switch (option) {
                case 1 -> create();
                case 2->{showAllStudents();}
                case 3->{removeById();}
                case 0-> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
            }
        }
    }
}
