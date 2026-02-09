package mapper;

import model.dto.StudentRequestDto;
import model.dto.StudentResponseDto;
import model.entities.Student;

public class StudentMapper {

    public Student fromStudentRequestDto(StudentRequestDto requestDto){
        return new Student(requestDto.fullName(),
                requestDto.birthDate(),
                Student.Gender.valueOf(requestDto.gender().toUpperCase()));
    }

    public void updateEntity(Student student, StudentRequestDto requestDto) {
        student.setFullName(requestDto.fullName());
        student.setBirthOfDate(requestDto.birthDate());
        student.setGender(Student.Gender.valueOf(requestDto.gender().toUpperCase()));
    }

    public StudentResponseDto toStudentResponseDto(Student student){
        return StudentResponseDto.builder()
                .id(student.getId())
                .fullName(student.getFullName())
                .gender(student.getGender().toString())
                .birthDate(student.getBirthOfDate().toString())
                .build();
    }
}
