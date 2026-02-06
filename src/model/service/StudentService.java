package model.service;

import model.dto.StudentRequestDto;
import model.dto.StudentResponseDto;

import java.util.List;

public interface StudentService {
    StudentResponseDto createStudent(StudentRequestDto requestDto);
    List<StudentResponseDto> getAllStudents(); //int off set (pagination)
    boolean removeStudentById(Integer id);
}
