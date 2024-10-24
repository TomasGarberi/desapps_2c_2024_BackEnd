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

    // Método para obtener todos los posts de un usuario específico
    public List<Post> getPostsByUser(Integer userId) {
        return postRepository.getPostsByUser(userId);
    }
    
    // Método para obtener el timeline de un usuario
    public List<Post> getTimeline(Integer userId) {
        return postRepository.getTimeline(userId);
    }


}
