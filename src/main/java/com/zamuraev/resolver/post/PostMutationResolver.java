package com.zamuraev.resolver.post;

import com.zamuraev.dto.PostDto;
import com.zamuraev.service.PostService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PostMutationResolver implements GraphQLMutationResolver {


    private final PostService postService;
    private final PostPublisher postPublisher;


      public UUID createPost(PostDto postDto) {
          UUID uuid = postService.createPost(postDto);

          postDto.setId(uuid);
          postPublisher.publish(postDto);

          return uuid;
      }


}
