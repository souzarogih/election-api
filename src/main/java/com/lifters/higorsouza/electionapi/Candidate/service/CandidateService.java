package com.lifters.higorsouza.electionapi.Candidate.service;

import com.lifters.higorsouza.electionapi.Candidate.dto.CandidateRequest;
import com.lifters.higorsouza.electionapi.Candidate.dto.CandidateResponse;

public interface CandidateService {

    CandidateResponse create(CandidateRequest candidateRequest);
}
