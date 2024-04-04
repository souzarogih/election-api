package com.lifters.higorsouza.electionapi.Voter.service;

import com.lifters.higorsouza.electionapi.Voter.dto.VoterRequest;
import com.lifters.higorsouza.electionapi.Voter.dto.VoterResponse;
import com.lifters.higorsouza.electionapi.Voter.model.Voter;
import com.lifters.higorsouza.electionapi.Voter.repository.VoterRepository;
import com.lifters.higorsouza.electionapi.utils.VoterNumberGenerator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Service
public class VoterServiceImpl implements VoterService {

    @Autowired
    VoterRepository voterRepository;

    public VoterResponse create(VoterRequest candidateRequest) {
       log.debug("Analisando dados para cadastro do candidato. {}", candidateRequest.getName());
        Voter candidateDataDb = voterRepository.save(new Voter(
                UUID.randomUUID(),
                candidateRequest.getName(),
                candidateRequest.getCpf(),
                candidateRequest.getEmail(),
                VoterNumberGenerator.generateVoterNumber(),
                LocalDateTime.now(ZoneId.of("UTC")),
                null
        ));

        log.info("Candidato {} salvo com id {}", candidateDataDb.getName(), candidateDataDb.getId());
        return new VoterResponse(candidateDataDb.getId(), candidateDataDb.getName(), candidateDataDb.getCpf(), candidateDataDb.getEmail(), candidateDataDb.getVoterNumber());
    }

    public List<VoterResponse> listAll() {
        log.debug("Localizando todos os candidatos cadastrados na base de dados");

        List<VoterResponse> candidateDataDb = voterRepository.findAll().stream()
                .map(candidate -> new VoterResponse(
                        candidate.getId(), candidate.getName(), candidate.getCpf(), candidate.getEmail(), candidate.getVoterNumber()
                )).collect(Collectors.toList());
        log.info("Lista de candidatos obtida");
        return candidateDataDb;
    }

    public VoterResponse getVoterById(UUID id) {
        log.debug("Localizando o candidato com id: {}", id);

        Optional<Voter> candidateDataDb = voterRepository.findById(id);
        VoterResponse candidateResponse = new VoterResponse();
        candidateDataDb.ifPresent(candidate -> {
            candidateResponse.setId(candidate.getId());
            candidateResponse.setName(candidate.getName());
            candidateResponse.setCpf(candidate.getCpf());
            candidateResponse.setEmail(candidate.getEmail());
            candidateResponse.setVoterNumber(candidate.getVoterNumber());
        });

        log.info("Dados do candidato {} - {} obtida com sucesso.", candidateResponse.getName(), candidateResponse.getId());
        return candidateResponse;
    }

    public boolean deleteVoter(UUID id) {
        log.debug("Localizando o candidato com id: {}", id);

        Optional<Voter> candidateDataDb = voterRepository.findById(id);
        if (!candidateDataDb.isPresent()){
            log.error("Não foi possível localizar o candidato {}", id);
            return false;
        }

        log.debug("Deletando o candidato com id: {}", id);
        voterRepository.deleteById(id);
        log.info("Candidato {} deletado!", id);
        return true;
    }

    public VoterResponse updateVoter(UUID id, VoterRequest candidateRequest) {
        log.debug("Localizando o candidato com id: {}", id);
        log.debug("Request: {}", candidateRequest);

        Optional<Voter> candidateDataDb = voterRepository.findById(id);
        if(candidateDataDb.isPresent()) {
            Voter candidateModel = candidateDataDb.get();
            if (candidateRequest.getCpf() != null) {
                candidateModel.setCpf(candidateRequest.getCpf());
            }

            BeanUtils.copyProperties(candidateRequest, candidateModel, "id");
            candidateModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
            Voter candidateUpdated = voterRepository.save(candidateModel);


            log.info("Candidato atualizado com sucesso.");
            return new VoterResponse(candidateUpdated.getId(), candidateUpdated.getName(), candidateUpdated.getCpf(), candidateUpdated.getEmail(), candidateUpdated.getVoterNumber());
        }

        return null;
    }
}
