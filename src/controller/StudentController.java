package controller;

import exception.NotFoundException;
import exception.ValidationException;
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

    public void update(){
        try{
            int id = studentView.inputStudentId();
            StudentResponseDto oldStudent= studentService.getStudentById(id);
            StudentRequestDto request = studentView.displayStudentUpdateDto(oldStudent);
            StudentResponseDto updated = studentService.updateStudent(id, request);
            studentView.displaySingleStudent(updated);
        }catch (ValidationException | NotFoundException e){
            System.out.println("Error: " + e.getMessage());
        }catch (Exception e){
            System.out.println("System Error: " + e.getMessage());
        }
    }

    public void removeById(){
        try{
            int id = studentView.inputStudentId();
            studentService.removeStudentById(id);
            System.out.println("Student removed successfully");
        }catch (NotFoundException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void showAllStudents(){
        try {
            final int pageSize = 10;

            int total = studentService.countStudents();
            if (total == 0) {
                System.out.println("No students.");
                return;
            }

            int totalPages = (int) Math.ceil((double) total / pageSize);
            int page = 0;

            while (true) {
                int offset = page * pageSize;

                // show current page data
                studentView.displayStudentList(studentService.getAllStudents(offset, pageSize));

                // nav
                int choice = studentView.pageNavigationMenu(page, totalPages);

                if (choice == 1) {
                    if (page < totalPages - 1) page++;
                } else if (choice == 2) {
                    if (page > 0) page--;
                } else if (choice == 0) {
                    break;
                } else {
                    System.out.println("Invalid option");
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void findStudentById(){
        try{
            int id = studentView.inputStudentId();
            StudentResponseDto student = studentService.getStudentById(id);
            studentView.displaySingleStudent(student);
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void run(){
        while (true){
            int option=studentView.showMenAndGetOption();
            switch (option) {
                case 1 -> create();
                case 2 -> { showAllStudents(); }
                case 3 -> findStudentById();
                case 4 -> { removeById(); }
                case 5 -> { update(); }
                case 0 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
            }

        }
    }



}
