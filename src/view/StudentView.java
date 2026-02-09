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
        String fullName;
        while (true) {
            System.out.print("[+] Enter student full name: ");
            fullName = scanner.nextLine();

            if (fullName.isBlank()) {
                System.out.println("Full name cannot be empty");
                continue;
            }

            if (!fullName.matches("[A-Za-z ]+")) {
                System.out.println("Name must contain only letters (no numbers)");
                continue;
            }

            break;
        }

        String gender;
        while (true){
            System.out.print("[+] Enter student gender (MALE/FEMALE): ");
            gender = scanner.nextLine();
            if (gender.equalsIgnoreCase("MALE") || gender.equalsIgnoreCase("FEMALE")) break;
            System.out.println("Gender must be MALE or FEMALE");
        }

        LocalDate birthDateObj;
        while (true) {
            System.out.print("[+] Enter student date of birth (YYYY-MM-DD): ");
            String db = scanner.nextLine();

            try {
                String[] birthDate = db.split("-");
                int year = Integer.parseInt(birthDate[0]);
                int month = Integer.parseInt(birthDate[1]);
                int day = Integer.parseInt(birthDate[2]);

                birthDateObj = LocalDate.of(year, month, day);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date. Please use format YYYY-MM-DD");
            }
        }

        return new StudentRequestDto(fullName, birthDateObj, gender);
    }

    public StudentRequestDto displayStudentUpdateDto(StudentResponseDto oldData){
        System.out.println("=== Update Student (Enter to Skip) ===");

        String fullName;
        while (true) {
            System.out.print("[+] Enter student full name: ");
            fullName = scanner.nextLine();

            if (fullName.isBlank()) {
                fullName = oldData.fullName();
                break;
            }

            if (!fullName.matches("[A-Za-z ]+")) {
                System.out.println("Name must contain only letters (no numbers)");
                continue;
            }

            break;
        }

        String gender;
        while (true){
            System.out.print("[+] Gender (" + oldData.gender() + "): ");
            gender = scanner.nextLine();
            if (gender.isBlank()) {
                gender = oldData.gender();
                break;
            }
            if (gender.equalsIgnoreCase("MALE") || gender.equalsIgnoreCase("FEMALE")) {
                break;
            }
            System.out.println("Gender must be MALE or FEMALE");
        }

        LocalDate birthDateObj;
        while (true){
            System.out.print("[+] Birth date (" + oldData.birthDate() + "): ");
            String db = scanner.nextLine();

            if (db.isBlank()) {
                birthDateObj = LocalDate.parse(oldData.birthDate());
                break;
            }

            try {
                String[] birthDate = db.split("-");
                int year = Integer.parseInt(birthDate[0]);
                int month = Integer.parseInt(birthDate[1]);
                int day = Integer.parseInt(birthDate[2]);

                birthDateObj = LocalDate.of(year, month, day);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date. Please use format YYYY-MM-DD");
            }
        }

        return new StudentRequestDto(fullName, birthDateObj, gender);
    }

    public int inputStudentId(){
        while (true){
            System.out.print("Enter student id: ");
            try{
                return Integer.parseInt(scanner.nextLine());
            }catch (Exception e){
                System.out.println("Invalid id. Please enter number.");
            }
        }
    }

    public void displaySingleStudent(StudentResponseDto student){
        Table table = new Table(4, BorderStyle.CLASSIC);
        table.addCell("Student Information", new CellStyle(CellStyle.HorizontalAlign.center), 4);

        table.addCell("ID");
        table.addCell(student.id().toString(), 3);

        table.addCell("Full Name");
        table.addCell(student.fullName(), 3);

        table.addCell("Gender");
        table.addCell(student.gender(), 3);

        table.addCell("Date of Birth");
        table.addCell(student.birthDate(), 3);

        System.out.println(table.render());
    }

    public void displayStudentList(List<StudentResponseDto> students){
        Table table = new Table(4, BorderStyle.CLASSIC);
        table.addCell("Student List", new CellStyle(CellStyle.HorizontalAlign.center), 4);

        String[] header = {"ID","Full Name","Gender","Date of Birth"};
        for (String column : header){
            table.addCell(column);
        }

        if (students.isEmpty()){
            table.addCell("No data", new CellStyle(CellStyle.HorizontalAlign.center), 4);
        } else {
            students.forEach(student -> {
                table.addCell(student.id().toString());
                table.addCell(student.fullName());
                table.addCell(student.gender());
                table.addCell(student.birthDate());
            });
        }

        System.out.println(table.render());
    }

    public int showMenAndGetOption(){
        System.out.println("""
                1. Create Student
                2. List Students
                3. Find Student by ID
                4. Remove Student
                5. Update Student
                0. Exit
                """);
        System.out.print("Enter your choice: ");

        while (true){
            try{
                return Integer.parseInt(scanner.nextLine());
            }catch (Exception e){
                System.out.print("Invalid choice. Enter number: ");
            }
        }
    }

    public int pageNavigationMenu(int currentPage, int totalPages) {
        System.out.println("\n--- Pagination ---");
        System.out.println("Page " + (currentPage + 1) + " / " + totalPages);
        System.out.println("1. Next");
        System.out.println("2. Prev");
        System.out.println("0. Back");
        System.out.print("Choose: ");

        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Invalid choice. Enter number: ");
            }
        }
    }

}
