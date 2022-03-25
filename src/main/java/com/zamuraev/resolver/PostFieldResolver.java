package com.zamuraev.resolver;

import com.zamuraev.dto.AuthorDto;
import com.zamuraev.dto.PostDto;
import com.zamuraev.service.AuthorService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostFieldResolver implements GraphQLResolver<PostDto> {

    private final AuthorService authorService;

    public AuthorDto getAuthor(PostDto postDto) {
        return authorService.getAuthorById(postDto.getAuthorId());
    }

}
