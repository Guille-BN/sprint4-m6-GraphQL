package com.davinchicoder.springgraphql.service;

import com.davinchicoder.springgraphql.dto.PostFilter;
import com.davinchicoder.springgraphql.entity.Post;
import com.davinchicoder.springgraphql.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> posts(PostFilter filter) {
        List<Post> base = postRepository.getAll();

        if (filter == null) return base;

        return base.stream()
                .filter(p -> filter.getType() == null || p.getType() != null && p.getType().equalsIgnoreCase(filter.getType()))
                .filter(p -> filter.getGender() == null || p.getGender() != null && p.getGender().equalsIgnoreCase(filter.getGender()))
                .filter(p -> filter.getDiet() == null || p.getDiet() != null && p.getDiet().equalsIgnoreCase(filter.getDiet()))
                .filter(p -> filter.getExtant() == null || (p.getExtant() != null && p.getExtant().equals(filter.getExtant())))
                .collect(Collectors.toList());
    }
}
