package com.zamuraev.service.impl;

import com.zamuraev.dto.AuthorDto;
import com.zamuraev.dto.PostDto;
import com.zamuraev.exception.ResourceNotFound;
import com.zamuraev.model.Author;
import com.zamuraev.model.Post;
import com.zamuraev.repository.AuthorRepository;
import com.zamuraev.repository.PostRepository;
import com.zamuraev.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

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

    @Override
    public List<PostDto> getRecentPosts(int count, int offset) {
        PageRequest of = PageRequest.of(offset, count);
        Page<Post> all = postRepository.findAll(of);

        return all.stream()
                .map(post -> {
                    return PostDto.builder()
                            .id(post.getId())
                            .authorId(post.getAuthor().getId())
                            .description(post.getDescription())
                            .title(post.getTitle())
                            .category(post.getCategory())
                            .build();
                }).collect(Collectors.toList());
    }

    @Override
    public UUID createPost(PostDto postDto) {

        Optional<Author> author = authorRepository.findById(postDto.getAuthorId());

        if(!author.isPresent()) {
            throw new RuntimeException("Author is not exist!");
        }

        Post build = Post.builder()
                .category(postDto.getCategory())
                .description(postDto.getDescription())
                .title(postDto.getTitle())
                .author(author.get())
                .build();
        Post save = postRepository.saveAndFlush(build);

        return save.getId();
    }

    @Override
    public Integer getPostCountByAuthorId(UUID id) {
        return postRepository.findAllByAuthor_Id(id).size();
    }

    @Override
    public PostDto getPostById(UUID postId) {
        Optional<Post> postOptional = postRepository.findById(postId);

        Post post = postOptional.orElseThrow(() -> new ResourceNotFound("Post is not exist with id: "+postId));
        return PostDto.builder()
                .id(post.getId())
                .category(post.getCategory())
                .title(post.getTitle())
                .description(post.getDescription())
                .authorId(post.getAuthor().getId())
                .build();
    }

    @Override
    public AuthorDto getAuthorById(UUID authorId) {

        Optional<Author> authorOptional = authorRepository.findById(authorId);

        Author author = authorOptional.orElseThrow(() -> new ResourceNotFound("User is not exist with id: "+authorId));

        return AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .email(author.getEmail())
                .build();
    }


}
