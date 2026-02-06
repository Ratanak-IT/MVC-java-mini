package view;

import model.dto.StudentRequestDto;
import model.dto.StudentResponseDto;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class StudentView {

    private final static Scanner scanner = new Scanner(System.in);

    public StudentRequestDto displayStudentCreateDto(){
        System.out.print("[+] Enter student full name: ");
        String fullName = scanner.nextLine();
        System.out.println("[+] Enter student gender (MALE/FEMALE): ");
        String gender = scanner.nextLine();
        System.out.println("[+] Enter student date of birth (YYYY-MM-DD): ");
        String db=scanner.nextLine();
        String[] birthDate = db.split("-");
        int year = Integer.parseInt(birthDate[0]);
        int month = Integer.parseInt(birthDate[1]);
        int day = Integer.parseInt(birthDate[2]);
        LocalDate birthDateObj = LocalDate.of(year, month, day);
        return new StudentRequestDto(fullName, birthDateObj, gender);
    }
    public int removeStudent(){
        System.out.print("Enter student id: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void displaySingleStudent(StudentResponseDto student){
        Table table= new Table(4, BorderStyle.CLASSIC);
        table.addCell("Student Information", new CellStyle(CellStyle.HorizontalAlign.center),4);
        table.addCell("ID");
        table.addCell(student.id().toString(),3);
        table.addCell("Full Name");
        table.addCell(student.fullName(),3);
        table.addCell("Gender");
        table.addCell(student.gender(),3);
        table.addCell("Date of Birth");
        table.addCell(student.birthDate(),3);
        System.out.println(table.render());
    }
    public void displayStudentList(List<StudentResponseDto> students){
        Table table= new Table(4, BorderStyle.CLASSIC);
        table.addCell("Student List", new CellStyle(CellStyle.HorizontalAlign.center),4);
        String[] header = {"ID","Full Name","Gender","Date of Birth"};
        for (String column:header){
            table.addCell(column);
        }
        students.forEach(student -> {
            table.addCell(student.id().toString());
            table.addCell(student.fullName());
            table.addCell(student.gender());
            table.addCell(student.birthDate());
        });
        System.out.println(table.render());
    }

    public int showMenAndGetOption(){
        System.out.println("""
                1. Create Student
                2. List Students
                3. Remove Student
                0. Exit
                """);
        System.out.print("Enter your choice: ");
        return Integer.parseInt(scanner.nextLine());
    }

}
