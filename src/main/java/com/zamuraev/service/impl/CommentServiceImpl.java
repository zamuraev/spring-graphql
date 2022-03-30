package com.zamuraev.service.impl;

import com.zamuraev.dto.CommentDto;
import com.zamuraev.exception.ResourceNotFound;
import com.zamuraev.mapper.CommentMapper;
import com.zamuraev.model.Author;
import com.zamuraev.model.Comment;
import com.zamuraev.model.Post;
import com.zamuraev.repository.AuthorRepository;
import com.zamuraev.repository.CommentRepository;
import com.zamuraev.repository.PostRepository;
import com.zamuraev.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;
    private final CommentMapper commentMapper;


    @Override
    public List<CommentDto> getFirstFewCommentsByAuthorId(UUID authorId, Integer count) {
        List<Comment> allByAuthor_id = commentRepository.findAllByAuthor_Id(authorId, PageRequest.of(0, count));
        return allByAuthor_id.stream()
                .map(commentMapper::convertCommentToDto).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getFirstFewCommentsByPostId(UUID postId, Integer count) {
        List<Comment> allByPost_id = commentRepository.
                findAllByPost_Id(postId, PageRequest.of(0, count));
        return allByPost_id.stream()
                .map(commentMapper::convertCommentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getComments(Integer count, Integer offset) {
        Page<Comment> comments = commentRepository.findAll(PageRequest.of(offset, count));
        return comments.stream()
                .map(commentMapper::convertCommentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UUID createComment(CommentDto commentDto) {
        Optional<Author> authorOptional = authorRepository.findById(commentDto.getAuthorId());
        Optional<Post> postOptional = postRepository.findById(commentDto.getPostId());
        Author author = authorOptional.orElseThrow(() -> new ResourceNotFound("User is not exist with id:"+commentDto.getAuthorId()));
        Post post = postOptional.orElseThrow(() -> new ResourceNotFound("Post is not exist with id:"+commentDto.getPostId()));
        Comment comment =  commentMapper.convertDtoToComment(commentDto, author, post);
        Comment createdComment = commentRepository.saveAndFlush(comment);
        return createdComment.getId();
    }

}
