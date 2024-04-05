package com.lifters.higorsouza.electionapi.Position.service;

import com.lifters.higorsouza.electionapi.Position.dto.PositionRequest;
import com.lifters.higorsouza.electionapi.Position.dto.PositionResponse;
import com.lifters.higorsouza.electionapi.Position.model.Position;
import com.lifters.higorsouza.electionapi.Position.repository.PositionRepository;
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
public class PositionServiceImpl implements PositionService {

    @Autowired
    PositionRepository positionRepository;

    public PositionResponse create(PositionRequest positionRequest) {
       log.debug("Analisando dados para cadastro do cargo. {}", positionRequest.getPosition());
        Position positionDataDb = positionRepository.save(new Position(
                UUID.randomUUID(),
                positionRequest.getPosition(),
                positionRequest.getElectionYear(),
                null,
                LocalDateTime.now(ZoneId.of("UTC")),
                null
        ));
        log.info("Cargo {} salvo com id {}", positionDataDb.getPosition(), positionDataDb.getId());
        return new PositionResponse(positionDataDb.getId(), positionDataDb.getPosition(), positionDataDb.getElectionYear());
    }

    public List<PositionResponse> listAll() {
        log.debug("Localizando todos os cargos cadastrados na base de dados");

        List<PositionResponse> positionDataDb = positionRepository.findAll().stream()
                .map(position -> new PositionResponse(
                        position.getId(), position.getPosition(), position.getElectionYear()
                )).collect(Collectors.toList());
        log.info("Lista de cargos obtida");
        return positionDataDb;
    }

    public PositionResponse getPositionById(UUID id) {
        log.debug("Localizando o cargo com id: {}", id);

        Optional<Position> positionDataDb = positionRepository.findById(id);
        PositionResponse positionResponse = new PositionResponse();
        positionDataDb.ifPresent(position -> {
            positionResponse.setId(position.getId());
            positionResponse.setPosition(position.getPosition());
            positionResponse.setElectionYear(position.getElectionYear());
        });

        log.info("Dados do cargo {} - {} obtida com sucesso.", positionResponse.getPosition(), positionResponse.getElectionYear());
        return positionResponse;
    }

    public boolean deletePosition(UUID id) {
        log.debug("Localizando o cargo com id: {}", id);

        Optional<Position> positionDataDb = positionRepository.findById(id);
        if (!positionDataDb.isPresent()){
            log.error("Não foi possível localizar o cargo {}", id);
            return false;
        }

        log.debug("Deletando o cargo com id: {}", id);
        positionRepository.deleteById(id);
        log.info("Cargo {} deletado!", id);
        return true;
    }

    public PositionResponse updatePosition(UUID id, PositionRequest positionRequest) {
        log.debug("Localizando o cargo com id: {}", id);
        log.debug("Request: {}", positionRequest);

        Optional<Position> positionDataDb = positionRepository.findById(id);
        if(positionDataDb.isPresent()) {
            Position positionModel = positionDataDb.get();

            BeanUtils.copyProperties(positionRequest, positionModel, "id");
            positionModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
            Position candidateUpdated = positionRepository.save(positionModel);


            log.info("cargo atualizado com sucesso.");
            return new PositionResponse(candidateUpdated.getId(), candidateUpdated.getPosition(), candidateUpdated.getElectionYear());
        }

        return null;
    }
}
