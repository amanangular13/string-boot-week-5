package com.Aman.SpringSecurityProject.controllers;

import com.Aman.SpringSecurityProject.dto.PostDTO;
import com.Aman.SpringSecurityProject.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    @Secured({"USER_VIEW","POST_VIEW"}) // or "ROLE_USER" is role based authorization
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> postDTOList = postService.getAllPost();
        return ResponseEntity.ok(postDTOList);
    }

    @GetMapping(path = "/{postId}")
//    @PreAuthorize("@postSecurity.isOwnerOfPost(#postId)")  // this will check that if post is created by current user
//                                                                 if not then access denied exception
//                                                               Note : postSecurity is class which has a method isOwnerOfPost
//                                                                     this annotation allow us to check who can acces what in custom way
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
