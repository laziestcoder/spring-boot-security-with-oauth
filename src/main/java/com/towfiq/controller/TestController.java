package com.towfiq.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/api"))
public class TestController {
    @GetMapping("/public")
    public ResponseEntity<String> getPublic() {
        return ResponseEntity.ok("Public");
    }
    @GetMapping("/private")
    public ResponseEntity<String> getPrivate() {
        return ResponseEntity.ok("private");
    }
}
