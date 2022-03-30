package com.zamuraev.resolver.author;

import com.zamuraev.dto.AuthorDto;
import com.zamuraev.dto.CommentDto;
import com.zamuraev.dto.PostDto;
import com.zamuraev.service.CommentService;
import com.zamuraev.service.PostService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorFieldResolver implements GraphQLResolver<AuthorDto>  {

    private final PostService postService;
    private final CommentService commentService;

    public List<PostDto> posts(AuthorDto authorDto) {
            return postService.getAllPostByAuthorId(authorDto.getId());
    }

    public Integer postCount(AuthorDto authorDto) {
        return postService.getPostCountByAuthorId(authorDto.getId());
    }

    public List<CommentDto> comments(AuthorDto authorDto, Integer first) {
        return commentService.getFirstFewCommentsByAuthorId(authorDto.getId(), first);
    }

}
