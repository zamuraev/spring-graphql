package com.zamuraev.resolver.post;

import com.zamuraev.context.CustomGraphQLContext;
import com.zamuraev.dto.PostDto;
import com.zamuraev.service.PostService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.SelectedField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostQueryResolver implements GraphQLQueryResolver {

    private final PostService postService;

    public List<PostDto> recentPosts(int count, int offset, DataFetchingEnvironment environment) {
        CustomGraphQLContext context = environment.getContext();
        log.info("user id {}", context.getUserId());

        Set<String> fields = environment.getSelectionSet().getFields()
                .stream()
                .map(SelectedField::getName)
                .collect(Collectors.toSet());

        log.info("{} request started, request fields {}", environment.getExecutionId(), fields);

        // select description, id, title from post where ???

        List<PostDto> recentPosts = postService.getRecentPosts(count, offset);
        log.info("{} request completed", environment.getExecutionId());
        return recentPosts;
    }

}
