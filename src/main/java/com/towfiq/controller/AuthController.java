package com.towfiq.controller;

import com.towfiq.dto.LoginRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping(("/api/auth"))
public class AuthController {

    @Value("${keycloak.auth}")
    private String keycloakAuthServerUrl;

    @Value("${keycloak.realms.name}")
    private String realm;

    @Value("${keycloak.client.id}")
    private String clientId;

    @Value("${keycloak.client.secret}")
    private String clientSecret;

    @Value("${keycloak.authorization.grant.type}")
    private String grantType;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        RestTemplate restTemplate = new RestTemplate();

        String tokenUrl = keycloakAuthServerUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        // Prepare request parameters
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("grant_type", grantType);
        requestParams.add("client_id", clientId);
        requestParams.add("client_secret", clientSecret);
        requestParams.add("username", loginRequestDTO.getUsername());
        requestParams.add("password", loginRequestDTO.getPassword());

        try {
            Map<String, Object> response = restTemplate.postForObject(tokenUrl, requestParams, Map.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("test success");
    }
}
