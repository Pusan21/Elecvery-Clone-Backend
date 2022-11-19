package com.Pusan21.ElecveryCloneBackend.filter;

import com.Pusan21.ElecveryCloneBackend.entity.Member;
import com.Pusan21.ElecveryCloneBackend.service.AuthorizationService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
public class Utills {
    @Autowired
    AuthorizationService authorizationService;

    public static List<String> returnSecretToken (String _path){
        ClassPathResource resource = new ClassPathResource(_path);
        List<String> secretTokens = null;

        try {
            Path path = Paths.get(resource.getURI());
            secretTokens = Files.readAllLines(path);
        } catch (IOException e) {
            log.error("{}", e.getMessage(), e);
        }

        return secretTokens;
    }

    public Member getMemberByRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        String refresh_token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refresh_token);

        String loginId = decodedJWT.getSubject();
        Member member = authorizationService.getUser(loginId);

        return member;
    }
}
