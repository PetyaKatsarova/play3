package com.example.play3.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import netscape.javascript.JSObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class JwtService {

    public String getJwtToken() {
        try {
            String url = "https://auth.trackunit.com/token";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("Authorization", "Basic MG9hOTQzaDkxNGVoRlVPQ1MzNTc6T3Y0OGlvX1dCcmVMaDU0YWRnQUM4dHFFN1QtUVFDZDRoVXFwazFaZQ==");
            headers.add("Cookie", "JSESSIONID=C6040AE23363D07C3E22244AC2B780A2; JSESSIONID=82FF41869E5057E3C5D2B8C108DF2600");

            // Define the request body
            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("grant_type", "password");
            body.add("username", "TU-API-10095-7");
            body.add("password", "86mu)rwiZb#qYuaT");

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
            RestTemplate restTemplate = new RestTemplate();

            // Send the POST request and get the response
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonResponse = objectMapper.readTree(response.getBody());
                return jsonResponse.get("access_token").asText();
//                return response.getBody();
            } else {
                throw new RuntimeException("Failed to retrieve JWT token in the JwtService class");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}


