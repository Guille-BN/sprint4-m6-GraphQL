package com.davinchicoder.springgraphql.repository;

import com.davinchicoder.springgraphql.entity.Post;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Repositorio en memoria que simula operaciones CRUD sobre Post sin usar base de datos. */
@Repository
public class PostRepository {

    private final List<Post> POSTS = new ArrayList<>(

        List.of(

                Post.builder()
                        .id(1L)
                        .commonName("Dog")
                        .scientificName("Canis lupus familiaris")
                        .gender("Male")
                        .diet("Omnivore")
                        .extant(true)
                        .genus("Canis")
                        .type("Mammal")

                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),

                Post.builder()
                        .id(2L)
                        .commonName("Spider monkey")
                        .scientificName("Atteles geoffroyi")
                        .gender("Female")
                        .diet("Omnivore")
                        .extant(true)
                        .genus("Ateles")
                        .type("Mammal")

                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),

                Post.builder()
                        .id(3L)
                        .commonName("Gila monster")
                        .scientificName("Heloderma suspectum")
                        .gender("Female")
                        .diet("Carnivore")
                        .extant(true)
                        .genus("Heloderma")
                        .type("Reptile")

                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),

                Post.builder()
                        .id(4L)
                        .commonName("Ocean sunfish")
                        .scientificName("Mola mola")
                        .gender("Male")
                        .diet("Gelatinivore")
                        .extant(true)
                        .genus("Mola")
                        .type("Fish")

                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),



                Post.builder()
                        .id(5L)
                        .commonName("Ankylosaurus")
                        .scientificName("Ankylosaurus magniventris")
                        .gender("Male")
                        .diet("Herbivore")
                        .extant(false)
                        .genus("Ankylosaurus")
                        .type("Reptile")

                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),

                Post.builder()
                        .id(6L)
                        .commonName("Yutyrannus")
                        .scientificName("Yutyrannus huali")
                        .gender("Male")
                        .diet("Carnivore")
                        .extant(false)
                        .genus("Yutyrannus"
                        )
                        .type("Reptile")

                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()
        )
);

    
    public List<Post> getAnimalsByType(String type) {

        return POSTS.stream()
                .filter(post ->
                        post.getType()
                                .equalsIgnoreCase(type))
                .toList();
    }

    public List<Post> getAnimalsByGender(String gender) {
        return POSTS.stream()
                .filter(post -> post.getGender() != null && post.getGender().equalsIgnoreCase(gender))
                .toList();
    }

    public List<Post> getAnimalsByDiet(String diet) {
        return POSTS.stream()
                .filter(post -> post.getDiet() != null && post.getDiet().equalsIgnoreCase(diet))
                .toList();
    }

    public List<Post> getExtantAnimals() {
        return POSTS.stream()
                .filter(post -> Boolean.TRUE.equals(post.getExtant()))
                .toList();
    }

    public List<Post> getExtintAnimals() {
        return POSTS.stream()
                .filter(post -> Boolean.FALSE.equals(post.getExtant()))
                .toList();
    }

    public List<Post> getRecentPosts(int count, int offset) {
        return POSTS.stream()
                .filter(post -> post.getDeletedAt() == null)
                .toList()
                .subList(offset, Math.min(offset + count, POSTS.size()));
    }

    public Post save(Post post) {
        post.setId(this.getNextId());
        post.setCreatedAt(LocalDateTime.now());

        POSTS.add(post);
        return post;
    }

    public Optional<Post> delete(Long id) {
        Optional<Post> postToDelete = POSTS.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();

        postToDelete.ifPresent(post -> post.setDeletedAt(LocalDateTime.now()));

        return postToDelete;
    }

    public Optional<Post> getById(Long id) {
        return POSTS.stream().filter(post -> post.getId().equals(id)).findFirst();
    }

    public List<Post> getAll() {
        return POSTS.stream().filter(post -> post.getDeletedAt() == null).toList();
    }

    private Long getNextId() {
        return POSTS.stream().mapToLong(Post::getId).max().orElse(0L) + 1L;
    }

}
