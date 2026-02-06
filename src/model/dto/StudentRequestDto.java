package model.dto;

import java.time.LocalDate;

public record StudentRequestDto(String fullName, LocalDate birthDate, String gender) {

}
