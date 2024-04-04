package com.lifters.higorsouza.electionapi.Candidate.service;

import com.lifters.higorsouza.electionapi.Candidate.dto.CandidateRequest;
import com.lifters.higorsouza.electionapi.Candidate.dto.CandidateResponse;
import com.lifters.higorsouza.electionapi.Candidate.model.Candidate;
import com.lifters.higorsouza.electionapi.Candidate.repository.CandidateRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Log4j2
@Service
public class CandidateServiceImpl implements CandidateService{

    @Autowired
    CandidateRepository candidateRepository;

    public CandidateResponse create(CandidateRequest candidateRequest) {
       log.debug("Analisando dados para cadastro do candidato. {}", candidateRequest.getName());
        Candidate candidateDataDb = candidateRepository.save(new Candidate(
                UUID.randomUUID(),
                candidateRequest.getName(),
                candidateRequest.getEmail(),
                candidateRequest.getCpf(),
                LocalDateTime.now(ZoneId.of("UTC")),
                null
        ));
        log.info("Candidato {} salvo com id {}", candidateDataDb.getName(), candidateDataDb.getId());
        return new CandidateResponse(candidateDataDb.getId(), candidateDataDb.getName(), candidateDataDb.getEmail(), candidateDataDb.getCpf());
    }
}
