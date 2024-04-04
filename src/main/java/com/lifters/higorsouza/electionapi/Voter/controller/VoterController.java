package com.lifters.higorsouza.electionapi.Voter.controller;

import com.lifters.higorsouza.electionapi.Voter.dto.VoterRequest;
import com.lifters.higorsouza.electionapi.Voter.dto.VoterResponse;
import com.lifters.higorsouza.electionapi.Voter.service.VoterService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/voter")
public class VoterController {

    @Autowired
    VoterService voterService;

    @PostMapping
    public ResponseEntity<VoterResponse> voterCreate(@RequestBody VoterRequest voterRequest) {
        log.debug("Processando a requisição para criar candidato. {}", voterRequest.getName());

        VoterResponse voterDataObj = voterService.create(voterRequest);
        log.debug("Preparando resposta da requisição.");

        return ResponseEntity.status(HttpStatus.CREATED).body(voterDataObj);
    }

    @GetMapping("/listAllVoters")
    public ResponseEntity<List<VoterResponse>> listAllVoters() {
        log.debug("Processando a requisição para listar todos os eleitores.");

        List<VoterResponse> voterDataObj = voterService.listAll();
        log.debug("Preparando resposta da requisição.");

        return ResponseEntity.status(HttpStatus.OK).body(voterDataObj);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoterResponse> getVoter(@PathVariable UUID id) {
        log.debug("Processando a requisição para localizar o eleitor com id: {}.", id);

        VoterResponse voterDataObj = voterService.getVoterById(id);
        log.debug("Preparando resposta da requisição.");

        return ResponseEntity.status(HttpStatus.OK).body(voterDataObj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVoter(@PathVariable UUID id) {
        log.debug("Processando a requisição para deletar o eleitor com id: {}.", id);

        boolean voterDataObj = voterService.deleteVoter(id);
        if (voterDataObj){
            log.info("Eleitor {} excluído com sucesso.", id);
            return ResponseEntity.status(HttpStatus.OK).body("Eleitor excluído com sucesso.");
        }else {
            log.error("Não foi possível excluir o eleitor.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível excluir o eleitor.");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VoterResponse> deleteVoter(@PathVariable UUID id,
                                                         @RequestBody VoterRequest candidateRequest) {
        log.debug("Processando a requisição para atualizar o eleitor com id: {}.", id);

        VoterResponse voterDataObj = voterService.updateVoter(id, candidateRequest);

        return ResponseEntity.status(HttpStatus.OK).body(voterDataObj);
    }

}
