package com.davinchicoder.springgraphql.IT;

import com.davinchicoder.springgraphql.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PostIT {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void shouldGetAllPosts() {
        String query = """
                query {
                    getAllPosts {
                        id
                        commonName
                        scientificName
                        gender
                        extant
                        genus
                        type
                    }
                }
                """;

        graphQlTester.document(query)
                .execute()
                .path("data.getAllPosts")
                .entityList(Post.class)
                .hasSize(3);
    }

    @Test
    void shouldCreatePost() {
        String mutation = """
                mutation {
                    savePost(postDto: {
                        commonName: "Name test",
                        scientificName: "Scientific name test",
                        gender:"Gender test",
                        diet:"Diet test",
                        extant:"true",
                        genus:"Genus test",
                        type:"Type test",
                    }) {
                        id
                        scientificName
                        gender
                        diet
                        extant
                        genus
                        type
                    }
                }
                """;

        graphQlTester.document(mutation)
                .execute()
                .path("data.savePost")
                .entity(Post.class)
                .satisfies(post -> {
                    assertNotNull(post.getId());
                    assertEquals("Test type", post.getType());
                    assertEquals("Test scientific name", post.getScientificName());
                    assertEquals("Test diet", post.getDiet());
                });
    }
}