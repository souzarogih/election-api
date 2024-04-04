package com.lifters.higorsouza.electionapi.Position.controller;

import com.lifters.higorsouza.electionapi.Position.dto.PositionRequest;
import com.lifters.higorsouza.electionapi.Position.dto.PositionResponse;
import com.lifters.higorsouza.electionapi.Position.service.PositionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    PositionService candidateService;

    @PostMapping
    public ResponseEntity<PositionResponse> candidateCreate(@RequestBody PositionRequest candidateRequest) {
        log.debug("Processando a requisição para criar candidato. {}", candidateRequest.getPosition());

        PositionResponse candidateDataObj = candidateService.create(candidateRequest);
        log.debug("Preparando resposta da requisição.");

        return ResponseEntity.status(HttpStatus.CREATED).body(candidateDataObj);
    }

    @GetMapping("/listAllPositions")
    public ResponseEntity<List<PositionResponse>> listAllCandidates() {
        log.debug("Processando a requisição para listar todos os candidatos.");

        List<PositionResponse> candidateDataObj = candidateService.listAll();
        log.debug("Preparando resposta da requisição.");

        return ResponseEntity.status(HttpStatus.OK).body(candidateDataObj);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionResponse> getCandidate(@PathVariable UUID id) {
        log.debug("Processando a requisição para localizar o candidato com id: {}.", id);

        PositionResponse candidateDataObj = candidateService.getPositionById(id);
        log.debug("Preparando resposta da requisição.");

        return ResponseEntity.status(HttpStatus.OK).body(candidateDataObj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCandidate(@PathVariable UUID id) {
        log.debug("Processando a requisição para deletar o candidato com id: {}.", id);

        boolean candidateDataObj = candidateService.deletePosition(id);
        if (candidateDataObj){
            log.info("Candidato {} excluído com sucesso.", id);
            return ResponseEntity.status(HttpStatus.OK).body("Candidato excluído com sucesso.");
        }else {
            log.error("Não foi possível excluir o candidato.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível excluir o candidato.");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PositionResponse> deleteCandidate(@PathVariable UUID id,
                                                            @RequestBody PositionRequest candidateRequest) {
        log.debug("Processando a requisição para atualizar o candidato com id: {}.", id);

        PositionResponse candidateDataObj = candidateService.updatePosition(id, candidateRequest);

        return ResponseEntity.status(HttpStatus.OK).body(candidateDataObj);
    }

}
