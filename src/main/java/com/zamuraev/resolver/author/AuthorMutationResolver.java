package com.zamuraev.resolver.author;

import com.zamuraev.dto.AuthorDto;
import com.zamuraev.service.AuthorService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthorMutationResolver implements GraphQLMutationResolver {


    private final AuthorService authorService;

    public UUID createAuthor(AuthorDto authorDto) {
        return authorService.createAuthor(authorDto);
    }


}
