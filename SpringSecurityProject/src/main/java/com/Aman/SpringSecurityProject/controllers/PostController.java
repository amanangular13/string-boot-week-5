package com.Aman.SpringSecurityProject.controllers;

import com.Aman.SpringSecurityProject.dto.PostDTO;
import com.Aman.SpringSecurityProject.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> postDTOList = postService.getAllPost();
        return ResponseEntity.ok(postDTOList);
    }

    @GetMapping(path = "/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId) {
        PostDTO postDTO = postService.getUserById(postId);
        return ResponseEntity.ok(postDTO);
    }

    @PostMapping
    public ResponseEntity<PostDTO> createUser(@RequestBody PostDTO inputPost) {
        PostDTO postDTO = postService.createUser(inputPost);
        return ResponseEntity.ok(postDTO);
    }
}
