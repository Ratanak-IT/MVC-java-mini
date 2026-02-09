    package model.service;

    import model.dto.StudentRequestDto;
    import model.dto.StudentResponseDto;

    import java.util.List;

    public interface StudentService {
        StudentResponseDto createStudent(StudentRequestDto requestDto);
        List<StudentResponseDto> getAllStudents(); //int off set (pagination)
        Boolean removeStudentById(Integer id);
        StudentResponseDto updateStudent(Integer id, StudentRequestDto requestDto);
        StudentResponseDto getStudentById(Integer id);
        int countStudents();
        List<StudentResponseDto> getAllStudents(int offset, int limit);

    }
