package com.lifters.higorsouza.electionapi.Candidate.controller;

import com.lifters.higorsouza.electionapi.Candidate.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    CandidateService candidateService;
}
