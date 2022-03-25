package com.zamuraev.service;

import com.zamuraev.dto.AuthorDto;
import java.util.List;
import java.util.UUID;

public interface AuthorService {

    List<AuthorDto> getAuthors();

    AuthorDto getAuthorById(UUID authorId);
}
