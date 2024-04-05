package com.lifters.higorsouza.electionapi.Candidate.service;

import com.lifters.higorsouza.electionapi.Candidate.dto.CandidateRequest;
import com.lifters.higorsouza.electionapi.Candidate.dto.CandidateResponse;
import com.lifters.higorsouza.electionapi.Candidate.model.Candidate;
import com.lifters.higorsouza.electionapi.Candidate.repository.CandidateRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
                candidateRequest.getCpf(),
                candidateRequest.getEmail(),
                null,
                LocalDateTime.now(ZoneId.of("UTC")),
                null
        ));
        log.info("Candidato {} salvo com id {}", candidateDataDb.getName(), candidateDataDb.getId());
        return new CandidateResponse(candidateDataDb.getId(), candidateDataDb.getName(), candidateDataDb.getCpf(), candidateDataDb.getEmail());
    }

    public List<CandidateResponse> listAll() {
        log.debug("Localizando todos os candidatos cadastrados na base de dados");

        List<CandidateResponse> candidateDataDb = candidateRepository.findAll().stream()
                .map(candidate -> new CandidateResponse(
                        candidate.getId(), candidate.getName(), candidate.getCpf(), candidate.getEmail()
                )).collect(Collectors.toList());
        log.info("Lista de candidatos obtida");
        return candidateDataDb;
    }

    public CandidateResponse getCandidateById(UUID id) {
        log.debug("Localizando o candidato com id: {}", id);

        Optional<Candidate> candidateDataDb = candidateRepository.findById(id);
        CandidateResponse candidateResponse = new CandidateResponse();
        candidateDataDb.ifPresent(candidate -> {
            candidateResponse.setId(candidate.getId());
            candidateResponse.setName(candidate.getName());
            candidateResponse.setCpf(candidate.getCpf());
            candidateResponse.setEmail(candidate.getEmail());
        });

        log.info("Dados do candidato {} - {} obtida com sucesso.", candidateResponse.getName(), candidateResponse.getId());
        return candidateResponse;
    }

    public boolean deleteCandidate(UUID id) {
        log.debug("Localizando o candidato com id: {}", id);

        Optional<Candidate> candidateDataDb = candidateRepository.findById(id);
        if (!candidateDataDb.isPresent()){
            log.error("Não foi possível localizar o candidato {}", id);
            return false;
        }

        log.debug("Deletando o candidato com id: {}", id);
        candidateRepository.deleteById(id);
        log.info("Candidato {} deletado!", id);
        return true;
    }

    public CandidateResponse updateCandidate(UUID id, CandidateRequest candidateRequest) {
        log.debug("Localizando o candidato com id: {}", id);
        log.debug("Request: {}", candidateRequest);

        Optional<Candidate> candidateDataDb = candidateRepository.findById(id);
        if(candidateDataDb.isPresent()) {
            Candidate candidateModel = candidateDataDb.get();
            if (candidateRequest.getCpf() != null) {
                candidateModel.setCpf(candidateRequest.getCpf());
            }

            BeanUtils.copyProperties(candidateRequest, candidateModel, "id");
            candidateModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
            Candidate candidateUpdated = candidateRepository.save(candidateModel);


            log.info("Candidato atualizado com sucesso.");
            return new CandidateResponse(candidateUpdated.getId(), candidateUpdated.getName(), candidateUpdated.getCpf(), candidateUpdated.getEmail());
        }

        return null;
    }
}
