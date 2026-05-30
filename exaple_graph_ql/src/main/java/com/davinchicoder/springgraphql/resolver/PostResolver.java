package com.davinchicoder.springgraphql.resolver;

import com.davinchicoder.springgraphql.dto.PostFilter;
import com.davinchicoder.springgraphql.entity.Post;
import com.davinchicoder.springgraphql.exception.PostNotFound;
import com.davinchicoder.springgraphql.repository.PostRepository;
import com.davinchicoder.springgraphql.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * GraphQL query resolver separated from controller logic.
 */
@Controller
@RequiredArgsConstructor
public class PostResolver {

    private final PostRepository postRepository;
    private final PostService postService;

    @QueryMapping
    public List<Post> getAnimalsByType(@Argument("type") String type) {
        return postRepository.getAnimalsByType(type);
    }

    @QueryMapping
    public List<Post> getAnimalsByGender(@Argument("gender") String gender) {
        return postRepository.getAnimalsByGender(gender);
    }

    @QueryMapping
    public List<Post> getAnimalsByDiet(@Argument("diet") String diet) {
        return postRepository.getAnimalsByDiet(diet);
    }

    @QueryMapping
    public List<Post> getExtantAnimals() {
        return postRepository.getExtantAnimals();
    }

    @QueryMapping
    public List<Post> getExtintAnimals() {
        return postRepository.getExtintAnimals();
    }

    @QueryMapping
    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    @QueryMapping
    public List<Post> getRecentPosts(@Argument int count, @Argument int offset) {
        return postRepository.getRecentPosts(count, offset);
    }

    @QueryMapping
    public Post getPostById(@Argument Long id) {
        return postRepository.getById(id).orElseThrow(PostNotFound::new);
    }

    @QueryMapping
    public List<Post> posts(@Argument("filter") PostFilter filter) {
        return postService.posts(filter);
    }
}
