package com.zamuraev.resolver.author;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.zamuraev.FileReaderUtil;
import com.zamuraev.TestApplication;
import org.json.JSONException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
class AuthorQueryResolverTest {

    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;

    @Test
    @Disabled
        public void shouldAbleToGetAuthorData() throws IOException, JSONException {
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource("request/author-query.graphqls");
        assertThat(graphQLResponse.isOk(), equalTo(true));
        assertEquals(FileReaderUtil.read("response/author-query.json"),graphQLResponse.getRawResponse().getBody(), true);
    }

    @Test
    @Disabled
    public void shouldReturnPostCountForAuthor() throws IOException, JSONException {
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource("request/post-count-query.graphqls");
        assertThat(graphQLResponse.isOk(), equalTo(true));
        assertEquals(FileReaderUtil.read("response/post-count-query.json"),graphQLResponse.getRawResponse().getBody(), true);
    }

}
