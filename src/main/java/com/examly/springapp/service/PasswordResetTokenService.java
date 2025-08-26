package com.examly.springapp.service;

import com.examly.springapp.entity.PasswordResetToken;
import com.examly.springapp.entity.User;
import com.examly.springapp.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    private static final int EXPIRATION_MINUTES = 30;

    public PasswordResetToken createToken(User user) {
        String tokenStr = UUID.randomUUID().toString();
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES);
        PasswordResetToken token = new PasswordResetToken(tokenStr, user, expiry);
        return tokenRepository.save(token);
    }

    public boolean isTokenValid(String token) {
        return tokenRepository.findByToken(token)
                .map(t -> t.getExpiryDate().isAfter(LocalDateTime.now()))
                .orElse(false);
    }

    public User getUserByToken(String token) {
        return tokenRepository.findByToken(token)
                .map(PasswordResetToken::getUser)
                .orElse(null);
    }

    public void deleteToken(String token) {
        tokenRepository.deleteByToken(token);
    }
}
