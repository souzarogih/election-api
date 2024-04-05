package com.lifters.higorsouza.electionapi.Candidate.controller;

import com.lifters.higorsouza.electionapi.Candidate.dto.CandidateRequest;
import com.lifters.higorsouza.electionapi.Candidate.dto.CandidateResponse;
import com.lifters.higorsouza.electionapi.Candidate.model.Candidate;
import com.lifters.higorsouza.electionapi.Candidate.service.CandidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidatos", description = "API para gerenciamento de candidatos")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @Operation(summary = "Cria um candidato.", description = "Esse endpoint deve ser usada para gerenciar os candidatos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Candidate.class)), @Content(mediaType = "application/json", schema = @Schema(implementation = Candidate.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<CandidateResponse> candidateCreate(@RequestBody CandidateRequest candidateRequest) {
        log.debug("Processando a requisição para criar candidato. {}", candidateRequest.getName());

        CandidateResponse candidateDataObj = candidateService.create(candidateRequest);
        log.debug("Preparando resposta da requisição.");

        return ResponseEntity.status(HttpStatus.CREATED).body(candidateDataObj);
    }

    @Operation(summary = "Cria um cargo.", description = "Esse endpoint deve ser usada para gerenciar os cargos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Candidate.class)), @Content(mediaType = "application/json", schema = @Schema(implementation = Candidate.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/listAllCandidates")
    public ResponseEntity<List<CandidateResponse>> listAllCandidates() {
        log.debug("Processando a requisição para listar todos os candidatos.");

        List<CandidateResponse> candidateDataObj = candidateService.listAll();
        log.debug("Preparando resposta da requisição.");

        return ResponseEntity.status(HttpStatus.OK).body(candidateDataObj);
    }

    @Operation(summary = "Cria um cargo.", description = "Esse endpoint deve ser usada para gerenciar os cargos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Candidate.class)), @Content(mediaType = "application/json", schema = @Schema(implementation = Candidate.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponse> getCandidate(@PathVariable UUID id) {
        log.debug("Processando a requisição para localizar o candidato com id: {}.", id);

        CandidateResponse candidateDataObj = candidateService.getCandidateById(id);
        log.debug("Preparando resposta da requisição.");

        return ResponseEntity.status(HttpStatus.OK).body(candidateDataObj);
    }

    @Operation(summary = "Cria um cargo.", description = "Esse endpoint deve ser usada para gerenciar os cargos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Candidate.class)), @Content(mediaType = "application/json", schema = @Schema(implementation = Candidate.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @ResponseStatus(HttpStatus.CREATED)
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCandidate(@PathVariable UUID id) {
        log.debug("Processando a requisição para deletar o candidato com id: {}.", id);

        boolean candidateDataObj = candidateService.deleteCandidate(id);
        if (candidateDataObj){
            log.info("Candidato {} excluído com sucesso.", id);
            return ResponseEntity.status(HttpStatus.OK).body("Candidato excluído com sucesso.");
        }else {
            log.error("Não foi possível excluir o candidato.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível excluir o candidato.");
        }
    }

    @Operation(summary = "Cria um cargo.", description = "Esse endpoint deve ser usada para gerenciar os cargos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Candidate.class)), @Content(mediaType = "application/json", schema = @Schema(implementation = Candidate.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/{id}")
    public ResponseEntity<CandidateResponse> deleteCandidate(@PathVariable UUID id,
                                                             @RequestBody CandidateRequest candidateRequest) {
        log.debug("Processando a requisição para atualizar o candidato com id: {}.", id);

        CandidateResponse candidateDataObj = candidateService.updateCandidate(id, candidateRequest);

        return ResponseEntity.status(HttpStatus.OK).body(candidateDataObj);
    }

}
