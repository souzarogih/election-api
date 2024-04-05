package com.lifters.higorsouza.electionapi.Session.dto;

import com.lifters.higorsouza.electionapi.Voter.model.Voter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessionResponse {

    private UUID id;

    private String sessionCode;
    private boolean open;
    private List<Voter> voters;
    private LocalDateTime openSessionDateTime;
    private LocalDateTime closeSessionDateTime;
}
