package com.lifters.higorsouza.electionapi.Position.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PositionResponse {

    private UUID id;
    private String position;
    private String electionYear;
}
