package com.lifters.higorsouza.electionapi.Session.service;

import com.lifters.higorsouza.electionapi.Session.dto.SessionRequest;
import com.lifters.higorsouza.electionapi.Session.dto.SessionResponse;

import java.util.List;
import java.util.UUID;

public interface SessionService {

    SessionResponse create(SessionRequest sessionRequest);
    List<SessionResponse> listAll();
    SessionResponse getSessionById(UUID id);
    boolean deleteSession(UUID id);
    SessionResponse updateSession(UUID id, SessionRequest sessionRequest);
}
