package model.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record StudentResponseDto(
        Integer id,
        String fullName,
        String gender,
        String birthDate
) {

}
