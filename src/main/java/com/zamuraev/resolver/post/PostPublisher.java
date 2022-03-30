package com.zamuraev.resolver.post;

import com.zamuraev.dto.PostDto;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import java.util.UUID;

@Component
public class PostPublisher {

    private final Sinks.Many<PostDto> processor;

    public PostPublisher() {
        this.processor = Sinks.many().replay().all();
    }

    public Publisher<PostDto> getRecentPost() {
        return processor.asFlux();
    }

    public Publisher<PostDto> getRecentPostByAuthor(UUID authorId) {
        return processor.asFlux().filter(postDto -> authorId.equals(postDto.getAuthorId()));
    }

    public void publish(PostDto postDto) {
        processor.emitNext(postDto, Sinks.EmitFailureHandler.FAIL_FAST);
    }

}
