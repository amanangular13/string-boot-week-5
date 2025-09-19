package com.Aman.SpringSecurityProject.services;

import com.Aman.SpringSecurityProject.entities.Session;
import com.Aman.SpringSecurityProject.entities.User;
import com.Aman.SpringSecurityProject.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final int SESSION_LIMIT = 2;

    public void generateNewSession(User user, String refreshToken) {
        List<Session> sessionList = sessionRepository.findByUser(user);
        if(sessionList.size() == SESSION_LIMIT) {
            sessionList.sort(Comparator.comparing(Session::getLastUsedAt));
            Session leastRecentlyUsedSession = sessionList.getFirst();
            sessionRepository.delete(leastRecentlyUsedSession);
        }

        Session newSession = Session.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();

        sessionRepository.save(newSession);
    }

    public void validateSession(String refreshToken) {
        Session session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new SessionAuthenticationException("Session is not valid"));
        session.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(session);
    }
}
