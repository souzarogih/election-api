package com.lifters.higorsouza.electionapi.Voter.service;

import com.lifters.higorsouza.electionapi.Voter.dto.VoterRequest;
import com.lifters.higorsouza.electionapi.Voter.dto.VoterResponse;

import java.util.List;
import java.util.UUID;

public interface VoterService {

    VoterResponse create(VoterRequest candidateRequest);
    List<VoterResponse> listAll();
    VoterResponse getVoterById(UUID id);
    boolean deleteVoter(UUID id);
    VoterResponse updateVoter(UUID id, VoterRequest voterRequest);
}
