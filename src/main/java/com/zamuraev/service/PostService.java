package com.zamuraev.service;

import com.zamuraev.dto.PostDto;

import java.util.List;
import java.util.UUID;

public interface PostService {

    List<PostDto> getAllPostByAuthorId(UUID authorId);
}
