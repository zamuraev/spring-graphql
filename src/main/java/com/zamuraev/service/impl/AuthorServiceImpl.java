package com.zamuraev.service.impl;

import com.zamuraev.dto.AuthorDto;
import com.zamuraev.exception.ResourceNotFound;
import com.zamuraev.model.Author;
import com.zamuraev.repository.AuthorRepository;
import com.zamuraev.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
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
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        Author author = authorOptional.orElseThrow(() -> new ResourceNotFound("User is not exist with id: "+authorId));
        return AuthorDto.builder()
                .id(author.getId())
                .email(author.getEmail())
                .name(author.getName())
                .build();
       
    }

    @Override
    public UUID createAuthor(AuthorDto authorDto) {
        Author build = Author.builder()
                .email(authorDto.getEmail())
                .name(authorDto.getName())
                .build();
        Author createdAuthor = authorRepository.saveAndFlush(build);
        return createdAuthor.getId();
    }

    @Override
    public Map<UUID, AuthorDto> getAllAuthorByIds(Set<UUID> authorIds) {
        log.info("author ids : {}", authorIds);
        List<Author> allById = authorRepository.findAllById(authorIds);
        return allById.stream()
                .map(author -> AuthorDto.builder()
                        .id(author.getId())
                        .email(author.getEmail())
                        .name(author.getName())
                        .build())
                .collect(Collectors.toMap(AuthorDto::getId, author -> author));
    }


}
