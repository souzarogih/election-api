package com.lifters.higorsouza.electionapi.service;

import com.lifters.higorsouza.electionapi.Candidate.dto.CandidateRequest;
import com.lifters.higorsouza.electionapi.Candidate.dto.CandidateResponse;
import com.lifters.higorsouza.electionapi.Candidate.model.Candidate;
import com.lifters.higorsouza.electionapi.Candidate.repository.CandidateRepository;
import com.lifters.higorsouza.electionapi.Candidate.service.CandidateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceTest {

    @Mock
    CandidateRepository candidateRepository;

    @InjectMocks
    CandidateService candidateService;

    String idString = "3be0b9c7-d8f3-4039-8dde-1235d9d6557c";
    final UUID ID_CANDIDATE = UUID.fromString(idString);

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
    }

    @Test
    void listAll() {

        Candidate candidate = Candidate.builder()
                .id(ID_CANDIDATE)
                .name("Higor")
                .cpf("288.747.700-57")
                .email("higor@mail.com")
                .build();

        Mockito.when(candidateRepository.findAll())
                .thenReturn(Arrays.asList(candidate));

        List<CandidateResponse> lista = candidateService.listAll();

        Assertions.assertEquals(1, lista.size());
        Assertions.assertFalse(lista.isEmpty());

    }

}
