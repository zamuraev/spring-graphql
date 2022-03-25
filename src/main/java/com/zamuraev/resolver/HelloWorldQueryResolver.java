package com.zamuraev.resolver;

import com.zamuraev.dto.MessageDto;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class HelloWorldQueryResolver implements GraphQLQueryResolver {

    public String getHelloWorld() {
        return "Hello World! Graphql is awesome";
    }

    public String getGreetingMessage(String firstName, String secondName) {
        return String.format("Hello %s %s", firstName, secondName);
    }

    public MessageDto getMessage() {
        return MessageDto.builder()
                .id(UUID.randomUUID())
                .text("graphql is awesome")
                .build();
    }

    public List<Integer> getRollDice() {
        return Arrays.asList(1,2,3,4);
    }


}
