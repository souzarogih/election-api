package com.lifters.higorsouza.electionapi.Candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidateRequest {

    private String name;
    private String cpf;
    private String email;

}
