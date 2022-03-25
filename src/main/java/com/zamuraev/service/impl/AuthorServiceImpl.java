package com.zamuraev.service.impl;

import com.zamuraev.dto.AuthorDto;
import com.zamuraev.model.Author;
import com.zamuraev.repository.AuthorRepository;
import com.zamuraev.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    
    @Override
    public List<AuthorDto> getAuthors() {
        List<Author> all = authorRepository.findAll();
        return all.stream().map(author -> AuthorDto.builder()
                .id(author.getId())
                .email(author.getEmail())
                .name(author.getName())
                .build()).collect(Collectors.toList());
    }

    @Override
    public AuthorDto getAuthorById(UUID authorId) {
        Author author = authorRepository.findById(authorId).get();
        return AuthorDto.builder()
                .id(author.getId())
                .email(author.getEmail())
                .name(author.getName())
                .build();
       
    }
}
