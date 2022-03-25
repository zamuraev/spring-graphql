package com.zamuraev.service.impl;

import com.zamuraev.dto.PostDto;
import com.zamuraev.model.Post;
import com.zamuraev.repository.PostRepository;
import com.zamuraev.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<PostDto> getAllPostByAuthorId(UUID authorId) {

        List<Post> allByAuthor_id = postRepository.findAllByAuthor_Id(authorId);

        return allByAuthor_id.stream().map(post ->
                PostDto.builder()
                        .id(post.getId())
                        .authorId(authorId)
                        .description(post.getDescription())
                        .title(post.getTitle())
                        .category(post.getCategory())
                        .build()
        ).collect(Collectors.toList());
    }
}
