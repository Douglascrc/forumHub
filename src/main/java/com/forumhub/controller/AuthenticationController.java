package com.forumhub.controller;

import com.forumhub.domain.user.LoginData;
import com.forumhub.domain.user.User;
import com.forumhub.infra.security.TokenData;
import com.forumhub.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginData data) {
        var token = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = manager.authenticate(token);
        var tokenJWT = authenticationService.gerarToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenData(tokenJWT));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid LoginData data, UriComponentsBuilder uri) {
        var user = new User();
        user.setEmail(data.email());
        user.setPassword(data.password());

        var registerUser = authenticationService.registerUser(user);

        var uriLocation = uri.path("/auth/login").buildAndExpand(registerUser.getId()).toUri();

        return ResponseEntity.created(uriLocation).body(registerUser);
    }
}
