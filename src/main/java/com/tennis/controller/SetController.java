package com.tennis.controller;

import com.tennis.entity.Set;
import com.tennis.service.SetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/set")
@RequiredArgsConstructor
public class SetController {

    private final SetService setService;

    @PostMapping("/{idMatch}")
    public ResponseEntity<Object> startGame(@PathVariable String idMatch) {
        try {
            Set set = setService.startSet(idMatch);
            return new ResponseEntity<>(set, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{idSet}")
    public ResponseEntity<Optional<Set>> getSet(@PathVariable String idSet) {
        Optional<Set> set = setService.getSet(idSet);
        if (set.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(set, HttpStatus.CREATED);
    }
}
