package com.sakila.sakilawebapp.controller;

import com.sakila.sakilawebapp.dto.LanguageDTO;
import com.sakila.sakilawebapp.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/films")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping("/languages")
    public ResponseEntity<List<LanguageDTO>> getAllLanguages() {
        List<LanguageDTO> language = languageService.getAllLanguages();
        return new ResponseEntity<>(language, HttpStatus.OK);
    }


}
