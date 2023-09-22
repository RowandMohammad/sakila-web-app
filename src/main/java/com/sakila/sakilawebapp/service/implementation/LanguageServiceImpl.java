package com.sakila.sakilawebapp.service.implementation;

import com.sakila.sakilawebapp.dto.LanguageDTO;
import com.sakila.sakilawebapp.entity.Language;
import com.sakila.sakilawebapp.exception.ResourceNotFoundException;
import com.sakila.sakilawebapp.repository.LanguageRepository;
import com.sakila.sakilawebapp.service.LanguageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<LanguageDTO> getAllLanguages() {
        return languageRepository.findAll()
                .stream()
                .map(language -> modelMapper.map(language, LanguageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public LanguageDTO getLanguageById(Byte id) {
        Language language = languageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Language not found"));
        return modelMapper.map(language, LanguageDTO.class);
    }

    @Override
    public LanguageDTO createLanguage(LanguageDTO languageDTO) {
        Language language = modelMapper.map(languageDTO, Language.class);
        Language savedLanguage = languageRepository.save(language);
        return modelMapper.map(savedLanguage, LanguageDTO.class);
    }

    @Override
    public LanguageDTO updateLanguage(Byte id, LanguageDTO languageDTO) {
        Language language = languageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Language not found"));
        modelMapper.map(languageDTO, language);
        Language savedLanguage = languageRepository.save(language);
        return modelMapper.map(savedLanguage, LanguageDTO.class);
    }

    @Override
    public void deleteLanguage(Byte id) {
        languageRepository.deleteById(id);
    }
}
