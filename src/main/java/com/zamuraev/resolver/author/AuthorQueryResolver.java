package com.zamuraev.resolver.author;

import com.zamuraev.dto.AuthorDto;
import com.zamuraev.service.AuthorService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorQueryResolver implements GraphQLQueryResolver {

    private final AuthorService authorService;

    public List<AuthorDto> getAuthors() {
        return authorService.getAuthors();
    }

}
