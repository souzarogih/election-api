package com.lifters.higorsouza.electionapi.Candidate.service;

import com.lifters.higorsouza.electionapi.Candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService{

    @Autowired
    CandidateRepository candidateRepository;

}
