package com.lifters.higorsouza.electionapi.Position.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PositionRequest {

    @NotBlank(message = "The field position is mandatory")
    private String position;

    @NotBlank(message = "The field electionYear is mandatory")
    private String electionYear;

}
