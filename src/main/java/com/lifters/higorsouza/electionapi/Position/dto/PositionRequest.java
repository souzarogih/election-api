package com.lifters.higorsouza.electionapi.Position.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PositionRequest {

    private String position;
    private String electionYear;

}
