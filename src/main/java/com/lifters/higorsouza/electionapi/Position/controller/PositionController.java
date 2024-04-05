package com.lifters.higorsouza.electionapi.Position.controller;

import com.lifters.higorsouza.electionapi.Position.dto.PositionRequest;
import com.lifters.higorsouza.electionapi.Position.dto.PositionResponse;
import com.lifters.higorsouza.electionapi.Position.model.Position;
import com.lifters.higorsouza.electionapi.Position.service.PositionService;
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
@RequestMapping("/position")
@Tag(name = "Cargos", description = "API para gerenciamento de cargos")
public class PositionController {

    @Autowired
    PositionService candidateService;

    @Operation(summary = "Cria um cargo.", description = "Esse endpoint deve ser usada para gerenciar os cargos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Position.class)), @Content(mediaType = "application/json", schema = @Schema(implementation = Position.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PositionResponse> candidateCreate(@RequestBody PositionRequest candidateRequest) {
        log.debug("Processando a requisição para criar candidato. {}", candidateRequest.getPosition());

        PositionResponse candidateDataObj = candidateService.create(candidateRequest);
        log.debug("Preparando resposta da requisição.");

        return ResponseEntity.status(HttpStatus.CREATED).body(candidateDataObj);
    }

    @Operation(summary = "Listar todos os cargos.", description = "Esse endpoint deve ser usada para listar todos os cargos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Position.class)), @Content(mediaType = "application/json", schema = @Schema(implementation = Position.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @GetMapping("/listAllPositions")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PositionResponse>> listAllCandidates() {
        log.debug("Processando a requisição para listar todos os candidatos.");

        List<PositionResponse> candidateDataObj = candidateService.listAll();
        log.debug("Preparando resposta da requisição.");

        return ResponseEntity.status(HttpStatus.OK).body(candidateDataObj);
    }

    @Operation(summary = "Obter um candidato.", description = "Esse endpoint deve ser usada para listar detalhes de um candidato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Position.class)), @Content(mediaType = "application/json", schema = @Schema(implementation = Position.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<PositionResponse> getCandidate(@PathVariable UUID id) {
        log.debug("Processando a requisição para localizar o candidato com id: {}.", id);

        PositionResponse candidateDataObj = candidateService.getPositionById(id);
        log.debug("Preparando resposta da requisição.");

        return ResponseEntity.status(HttpStatus.OK).body(candidateDataObj);
    }

    @Operation(summary = "Obter um candidato.", description = "Esse endpoint deve ser usada para obter dados de um candidato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Position.class)), @Content(mediaType = "application/json", schema = @Schema(implementation = Position.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
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

    @Operation(summary = "Delete um candidato.", description = "Esse endpoint deve ser usada para deletar um candidato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Position.class)), @Content(mediaType = "application/json", schema = @Schema(implementation = Position.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid username supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public ResponseEntity<PositionResponse> deleteCandidate(@PathVariable UUID id,
                                                            @RequestBody PositionRequest candidateRequest) {
        log.debug("Processando a requisição para atualizar o candidato com id: {}.", id);

        PositionResponse candidateDataObj = candidateService.updatePosition(id, candidateRequest);

        return ResponseEntity.status(HttpStatus.OK).body(candidateDataObj);
    }

}
