package com.example.testraul.demo.aplication.dtos;

import com.example.testraul.demo.domain.entitie.Post;

public class PostDtoConverter {

    public static Post convertToEntity(PostDto postDto) {
        Post post = new Post();
        post.setPostId(postDto.getPostId());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setCreationDate(postDto.getCreationDate());
        // Set user relationship if needed
        // post.setUser(userService.getUserById(postDto.getUserId()));
        return post;
    }

    public static PostDto convertToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setPostId(post.getPostId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setCreationDate(post.getCreationDate());
        postDto.setUserId(post.getUser().getId());
        return postDto;
    }
}
