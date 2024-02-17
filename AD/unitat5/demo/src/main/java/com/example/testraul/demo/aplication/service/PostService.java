package com.example.testraul.demo.aplication.service;

import com.example.testraul.demo.aplication.dtos.PostDto;

import java.util.List;

public interface PostService {
        void insertPost(PostDto postDto);

        PostDto getPostById(long id);

        List<PostDto> getAllPosts();
}