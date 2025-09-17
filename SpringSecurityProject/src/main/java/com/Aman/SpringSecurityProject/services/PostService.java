package com.Aman.SpringSecurityProject.services;

import com.Aman.SpringSecurityProject.dto.PostDTO;
import com.Aman.SpringSecurityProject.entities.PostEntity;
import com.Aman.SpringSecurityProject.exceptions.ResourceNotFoundException;
import com.Aman.SpringSecurityProject.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    public PostDTO getUserById(Long postId) {
        PostEntity user = postRepository.findById(postId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post with Id "+ postId +" does not exist")
                );
        return modelMapper.map(user, PostDTO.class);
    }

    public PostDTO createUser(PostDTO inputPost) {
        PostEntity postToBeSaved = modelMapper.map(inputPost, PostEntity.class);
        PostEntity savedPost = postRepository.save(postToBeSaved);
        return modelMapper.map(savedPost, PostDTO.class);
    }

    public List<PostDTO> getAllPost() {
        return postRepository.findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .collect(Collectors.toList());
    }
}
