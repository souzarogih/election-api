package com.lifters.higorsouza.electionapi.Session.service;

import com.lifters.higorsouza.electionapi.Session.dto.SessionRequest;
import com.lifters.higorsouza.electionapi.Session.dto.SessionResponse;
import com.lifters.higorsouza.electionapi.Session.model.Session;
import com.lifters.higorsouza.electionapi.Session.repository.SessionRepository;
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
public class SessionServiceImpl implements SessionService {

    @Autowired
    SessionRepository sessionRepository;

    public SessionResponse create(SessionRequest sessionRequest) {
       log.debug("Analisando dados para cadastro da sessão. {}", sessionRequest.getSessionCode());
        Session sessionDataDb = sessionRepository.save(new Session(
                UUID.randomUUID(),
                sessionRequest.getSessionCode(),
                sessionRequest.isOpen(),
                null,
                null,
                null
        ));
        log.info("Sessão {} salva com id {}", sessionDataDb.getSessionCode(), sessionDataDb.getId());
        return new SessionResponse(sessionDataDb.getId(), sessionDataDb.getSessionCode(), false, null, null, null);
    }

    public List<SessionResponse> listAll() {
        log.debug("Localizando todos as sessões cadastrados na base de dados");

        List<SessionResponse> sessionDataDb = sessionRepository.findAll().stream()
                .map(session -> new SessionResponse(
                        session.getId(), session.getSessionCode(), session.isOpen(), null, null, null
                )).collect(Collectors.toList());
        log.info("Lista de sessões obtida");
        return sessionDataDb;
    }

    public SessionResponse getSessionById(UUID id) {
        log.debug("Localizando a sessão com id: {}", id);

        Optional<Session> sessionDataDb = sessionRepository.findById(id);
        SessionResponse sessionResponse = new SessionResponse();
        sessionDataDb.ifPresent(session -> {
            sessionResponse.setId(session.getId());
            sessionResponse.setSessionCode(session.getSessionCode());
            sessionResponse.setOpen(session.isOpen());
            sessionResponse.setOpenSessionDateTime(session.getOpenSessionDateTime());
            sessionResponse.setCloseSessionDateTime(session.getCloseSessionDateTime());
        });

        log.info("Dados da sessão {} - {} obtida com sucesso.", sessionResponse.getId(), sessionResponse.getSessionCode());
        return sessionResponse;
    }

    public boolean deleteSession(UUID id) {
        log.debug("Localizando a sessão com id: {}", id);

        Optional<Session> sessionDataDb = sessionRepository.findById(id);
        if (!sessionDataDb.isPresent()){
            log.error("Não foi possível localizar a sessão {}", id);
            return false;
        }

        log.debug("Deletando a sessão com id: {}", id);
        sessionRepository.deleteById(id);
        log.info("Sessão {} deletada!", id);
        return true;
    }

    public SessionResponse updateSession(UUID id, SessionRequest sessionRequest) {
        log.debug("Localizando a sessão com id: {}", id);
        log.debug("Request: {}", sessionRequest);

        Optional<Session> sessionDataDb = sessionRepository.findById(id);
        if(sessionDataDb.isPresent()) {
            Session sessionModel = sessionDataDb.get();

            BeanUtils.copyProperties(sessionRequest, sessionModel, "id");
            sessionModel.setCloseSessionDateTime(LocalDateTime.now(ZoneId.of("UTC")));
            Session sessionUpdated = sessionRepository.save(sessionModel);


            log.info("cargo atualizado com sucesso.");
            return new SessionResponse(sessionUpdated.getId(), sessionUpdated.getSessionCode(), sessionUpdated.isOpen(), null, sessionUpdated.getOpenSessionDateTime(), sessionUpdated.getCloseSessionDateTime());
        }

        return null;
    }
}
