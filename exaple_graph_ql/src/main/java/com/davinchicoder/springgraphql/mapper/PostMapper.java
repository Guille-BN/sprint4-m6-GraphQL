package com.davinchicoder.springgraphql.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.davinchicoder.springgraphql.dto.PostDto;
import com.davinchicoder.springgraphql.entity.Post;

/** Mapper que convierte un PostDto en una entidad Post para su procesamiento o persistencia. */
@Component
public class PostMapper implements Function<PostDto, Post> {

    @Override
    public Post apply(PostDto postDto) {
        return Post.builder()
            .commonName(postDto.getCommonName())
            .scientificName(postDto.getScientificName())
            .gender(postDto.getGender())
            .diet(postDto.getDiet())
            .extant(postDto.getExtant())
            .genus(postDto.getGenus())
            .type(postDto.getType())
            .build();
    }
}
