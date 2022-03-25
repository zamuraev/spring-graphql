package com.zamuraev.resolver;

import com.zamuraev.dto.AuthorDto;
import com.zamuraev.dto.PostDto;
import com.zamuraev.service.PostService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorFieldResolver implements GraphQLResolver<AuthorDto>  {

    private final PostService postService;

    public List<PostDto> posts(AuthorDto authorDto) {
            return postService.getAllPostByAuthorId(authorDto.getId());
    }
}
