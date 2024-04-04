package com.lifters.higorsouza.electionapi.Candidate.controller;

import com.lifters.higorsouza.electionapi.Candidate.dto.CandidateRequest;
import com.lifters.higorsouza.electionapi.Candidate.dto.CandidateResponse;
import com.lifters.higorsouza.electionapi.Candidate.service.CandidateService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @PostMapping
    public ResponseEntity<CandidateResponse> candidateCreate(@RequestBody CandidateRequest candidateRequest) {
        log.debug("Processando a requisição para criar candidato. {}", candidateRequest.getName());

        CandidateResponse candidateDataObj = candidateService.create(candidateRequest);
        log.debug("Preparando resposta da requisição.");

        return ResponseEntity.status(HttpStatus.CREATED).body(candidateDataObj);
    }
}
