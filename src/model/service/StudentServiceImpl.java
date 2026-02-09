package model.service;

import exception.NotFoundException;
import exception.ValidationException;
import mapper.StudentMapper;
import model.dao.StudentDao;
import model.dto.StudentRequestDto;
import model.dto.StudentResponseDto;
import model.entities.Student;

import java.time.LocalDate;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentDao studentDao, StudentMapper studentMapper) {
        this.studentDao = studentDao;
        this.studentMapper = studentMapper;
    }

    private void validate(StudentRequestDto requestDto) {

        if (requestDto == null) {
            throw new ValidationException("Request cannot be null");
        }

        if (requestDto.fullName() == null || requestDto.fullName().isBlank()) {
            throw new ValidationException("Full name is required");
        }

        if (!requestDto.fullName().matches("[A-Za-z ]+")) {
            throw new ValidationException("Full name must contain only letters");
        }

        if (requestDto.gender() == null || requestDto.gender().isBlank()) {
            throw new ValidationException("Gender is required");
        }

        try {
            Student.Gender.valueOf(requestDto.gender().toUpperCase());
        } catch (Exception e) {
            throw new ValidationException("Gender must be MALE or FEMALE");
        }

        if (requestDto.birthDate() == null) {
            throw new ValidationException("Birth date is required");
        }

        if (requestDto.birthDate().isAfter(LocalDate.now().minusYears(4))) {
            throw new ValidationException("Student must be at least 4 years old");
        }
    }

    @Override
    public StudentResponseDto createStudent(StudentRequestDto requestDto) {
        validate(requestDto);

        Student student = studentMapper.fromStudentRequestDto(requestDto);
        Student savedStudent = studentDao.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        return studentDao.getAll()
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .toList();
    }

    @Override
    public Boolean removeStudentById(Integer id) {
        boolean removed = studentDao.removeById(id);
        if (!removed) {
            throw new NotFoundException("Student with id " + id + " not found");
        }
        return true;
    }

    @Override
    public StudentResponseDto updateStudent(Integer id, StudentRequestDto requestDto) {
        validate(requestDto);

        Student student = studentDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with id " + id + " not found"));

        studentMapper.updateEntity(student, requestDto);
        Student updated = studentDao.update(student);

        return studentMapper.toStudentResponseDto(updated);
    }

    @Override
    public StudentResponseDto getStudentById(Integer id) {
        Student student = studentDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Student with id " + id + " not found"));

        return studentMapper.toStudentResponseDto(student);
    }
    @Override
    public int countStudents() {
        return studentDao.count();
    }

    @Override
    public List<StudentResponseDto> getAllStudents(int offset, int limit) {
        return studentDao.getAll(offset, limit)
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .toList();
    }


}
