package com.example.testraul.demo.infraestructure.rest;

import com.example.testraul.demo.aplication.dtos.PostDto;
import com.example.testraul.demo.aplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/allPosts")
    public String getAllPosts(Model model) {
        List<PostDto> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "/post/postList";
    }

    // Create a new post
    @GetMapping("/insertpost")
    public String showInsertPostForm(Model model) {
        model.addAttribute("postDto", new PostDto());
        return "/post/postInsert";
    }

    @PostMapping("/insertpost")
    public String createPost(@ModelAttribute PostDto postDto) {
        postService.insertPost(postDto);
        return "redirect:/api/posts/allPosts";
    }
}
