package com.zamuraev.resolver.post;

import com.zamuraev.context.dataloader.DataLoaderRegistryFactory;
import com.zamuraev.dto.AuthorDto;
import com.zamuraev.dto.CommentDto;
import com.zamuraev.dto.PostDto;
import com.zamuraev.service.AuthorService;
import com.zamuraev.service.CommentService;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
@RequiredArgsConstructor
public class PostFieldResolver implements GraphQLResolver<PostDto> {

    private final AuthorService authorService;
    private final CommentService commentService;

    private final ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public CompletableFuture<AuthorDto> getAuthor(PostDto postDto, DataFetchingEnvironment environment)  {
        DataLoader<UUID, AuthorDto> dataLoader = environment.getDataLoader(DataLoaderRegistryFactory.AUTHOR_DATA_LOADER);
        return dataLoader.load(postDto.getAuthorId());
    }

    public CompletableFuture<List<CommentDto>> getComments(PostDto postDto, Integer first) {
        log.info("comment main thread id : {}", Thread.currentThread().getId());
        return CompletableFuture.supplyAsync(() -> {
            log.info("comment thread id : {}", Thread.currentThread().getId());
            log.info("comment request started for postId:{}", postDto.getId());
            List<CommentDto> firstFewCommentsByPostId = commentService.getFirstFewCommentsByPostId(postDto.getId(), first);
            log.info("comment request started for postId:{}", postDto.getId());
            return firstFewCommentsByPostId;
        }, executorService);
    }

}
