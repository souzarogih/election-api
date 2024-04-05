package com.lifters.higorsouza.electionapi.Session.dto;

import com.lifters.higorsouza.electionapi.Voter.model.Voter;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessionRequest {

    @NotBlank(message = "The field sessionCode is mandatory")
    private String sessionCode;

    @NotBlank(message = "The field open is mandatory")
    private boolean open;

    private List<Voter> voters;

}
