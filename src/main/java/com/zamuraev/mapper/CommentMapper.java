package com.zamuraev.mapper;

import com.zamuraev.dto.CommentDto;
import com.zamuraev.model.Author;
import com.zamuraev.model.Comment;
import com.zamuraev.model.Post;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentDto convertCommentToDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .authorId(comment.getAuthor().getId())
                .postId(comment.getPost().getId())
                .build();
    }

    public Comment convertDtoToComment(CommentDto commentDto, Author author, Post post) {
        return Comment.builder()
                .text(commentDto.getText())
                .author(author)
                .post(post)
                .build();
    }
}
