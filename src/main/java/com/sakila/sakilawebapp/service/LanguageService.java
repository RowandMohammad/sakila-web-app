package com.sakila.sakilawebapp.service;

import com.sakila.sakilawebapp.dto.LanguageDTO;
import java.util.List;

public interface LanguageService {
    List<LanguageDTO> getAllLanguages();
    LanguageDTO getLanguageById(Byte id);
    LanguageDTO createLanguage(LanguageDTO languageDTO);
    LanguageDTO updateLanguage(Byte id, LanguageDTO languageDTO);
    void deleteLanguage(Byte id);
}
