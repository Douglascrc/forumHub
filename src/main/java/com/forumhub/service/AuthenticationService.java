package com.forumhub.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.forumhub.domain.user.User;
import com.forumhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Value("${api.security.token.secret}")
    private String secret;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }

    public String genarateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("ForumHub")
                    .withSubject(user.getEmail())
                    .withExpiresAt(Instant.now().plusSeconds(7200))
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token JWT", e);

        }
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("ForumHub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Token inválido ou expirado", e);
        }
    }


    public User registerUser(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }
        String generatedName = user.getEmail().substring(0, user.getEmail().indexOf('@'));

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setName(generatedName);
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassword(encryptedPassword);
        repository.save(user);

        return user;
    }

}
