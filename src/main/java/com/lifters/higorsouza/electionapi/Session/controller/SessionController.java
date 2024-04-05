package com.lifters.higorsouza.electionapi.Session.controller;

import com.lifters.higorsouza.electionapi.Session.dto.SessionRequest;
import com.lifters.higorsouza.electionapi.Session.dto.SessionResponse;
import com.lifters.higorsouza.electionapi.Session.service.SessionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    SessionService candidateService;

    @PostMapping
    public ResponseEntity<SessionResponse> candidateCreate(@RequestBody SessionRequest candidateRequest) {
        log.debug("Processando a requisição para criar candidato. {}", candidateRequest.getSessionCode());

        SessionResponse candidateDataObj = candidateService.create(candidateRequest);
        log.debug("Preparando resposta da requisição.");

        return ResponseEntity.status(HttpStatus.CREATED).body(candidateDataObj);
    }

    @GetMapping("/listAllPositions")
    public ResponseEntity<List<SessionResponse>> listAllCandidates() {
        log.debug("Processando a requisição para listar todos os candidatos.");

        List<SessionResponse> candidateDataObj = candidateService.listAll();
        log.debug("Preparando resposta da requisição.");

        return ResponseEntity.status(HttpStatus.OK).body(candidateDataObj);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionResponse> getCandidate(@PathVariable UUID id) {
        log.debug("Processando a requisição para localizar o candidato com id: {}.", id);

        SessionResponse candidateDataObj = candidateService.getSessionById(id);
        log.debug("Preparando resposta da requisição.");

        return ResponseEntity.status(HttpStatus.OK).body(candidateDataObj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCandidate(@PathVariable UUID id) {
        log.debug("Processando a requisição para deletar o candidato com id: {}.", id);

        boolean candidateDataObj = candidateService.deleteSession(id);
        if (candidateDataObj){
            log.info("Candidato {} excluído com sucesso.", id);
            return ResponseEntity.status(HttpStatus.OK).body("Candidato excluído com sucesso.");
        }else {
            log.error("Não foi possível excluir o candidato.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível excluir o candidato.");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SessionResponse> deleteCandidate(@PathVariable UUID id,
                                                           @RequestBody SessionRequest candidateRequest) {
        log.debug("Processando a requisição para atualizar o candidato com id: {}.", id);

        SessionResponse candidateDataObj = candidateService.updateSession(id, candidateRequest);

        return ResponseEntity.status(HttpStatus.OK).body(candidateDataObj);
    }

}
