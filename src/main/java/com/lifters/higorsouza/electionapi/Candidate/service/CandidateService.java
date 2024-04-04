package com.lifters.higorsouza.electionapi.Candidate.service;

import com.lifters.higorsouza.electionapi.Candidate.dto.CandidateRequest;
import com.lifters.higorsouza.electionapi.Candidate.dto.CandidateResponse;

import java.util.List;
import java.util.UUID;

public interface CandidateService {

    CandidateResponse create(CandidateRequest candidateRequest);
    List<CandidateResponse> listAll();
    CandidateResponse getCandidateById(UUID id);
    boolean deleteCandidate(UUID id);
    CandidateResponse updateCandidate(UUID id, CandidateRequest candidateRequest);
}
