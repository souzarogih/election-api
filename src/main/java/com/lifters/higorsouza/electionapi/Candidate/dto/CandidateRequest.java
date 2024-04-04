package com.lifters.higorsouza.electionapi.Candidate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CandidateRequest {

    @NotBlank(message = "The field name is mandatory")
    private String name;

    @CPF(message = "Invalid cpf field")
    private String cpf;

    @Email(message = "Invalid email field")
    private String email;

}
