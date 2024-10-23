package com.tpo.TPO.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tpo.TPO.entity.Post;
import com.tpo.TPO.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Integer postId) {
        return postRepository.findById(postId);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Integer postId) {
        postRepository.deleteById(postId);
    }
}
