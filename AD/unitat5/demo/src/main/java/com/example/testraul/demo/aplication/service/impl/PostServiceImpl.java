package com.example.testraul.demo.aplication.service.impl;

import com.example.testraul.demo.aplication.dtos.PostDto;
import com.example.testraul.demo.aplication.dtos.PostDtoConverter;
import com.example.testraul.demo.aplication.service.PostService;
import com.example.testraul.demo.domain.entitie.Post;
import com.example.testraul.demo.infraestructure.persistence.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public void insertPost(PostDto postDto) {
        Post post = PostDtoConverter.convertToEntity(postDto);
        postRepository.save(post);
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElse(null);
        return (post != null) ? PostDtoConverter.convertToDto(post) : null;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> postList = postRepository.findAll();
        List<PostDto> resultList = new ArrayList<>();
        for (Post post : postList) {
            resultList.add(PostDtoConverter.convertToDto(post));
        }
        return resultList;
    }
}
