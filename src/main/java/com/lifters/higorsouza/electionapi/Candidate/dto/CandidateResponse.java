package com.lifters.higorsouza.electionapi.Candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidateResponse {

    private UUID id;
    private String name;
    private String cpf;
    private String email;
}
