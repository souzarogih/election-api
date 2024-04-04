package com.lifters.higorsouza.electionapi.Voter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoterRequest {

    private String name;
    private String cpf;
    private String email;

}
