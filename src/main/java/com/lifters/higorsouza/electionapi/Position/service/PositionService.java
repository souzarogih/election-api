package com.lifters.higorsouza.electionapi.Position.service;

import com.lifters.higorsouza.electionapi.Position.dto.PositionRequest;
import com.lifters.higorsouza.electionapi.Position.dto.PositionResponse;

import java.util.List;
import java.util.UUID;

public interface PositionService {

    PositionResponse create(PositionRequest candidateRequest);
    List<PositionResponse> listAll();
    PositionResponse getPositionById(UUID id);
    boolean deletePosition(UUID id);
    PositionResponse updatePosition(UUID id, PositionRequest candidateRequest);
}
