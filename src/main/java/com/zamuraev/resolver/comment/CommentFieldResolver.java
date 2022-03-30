package com.zamuraev.resolver.comment;

import com.zamuraev.dto.AuthorDto;
import com.zamuraev.dto.CommentDto;
import com.zamuraev.dto.PostDto;
import com.zamuraev.service.PostService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentFieldResolver implements GraphQLResolver<CommentDto> {

    private final PostService postService;


    public PostDto getPost(CommentDto commentDto) {
        return postService.getPostById(commentDto.getPostId());
    }

    public AuthorDto getAuthor(CommentDto commentDto) {
        return postService.getAuthorById(commentDto.getAuthorId());
    }

}
