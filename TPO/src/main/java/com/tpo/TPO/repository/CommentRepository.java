package com.tpo.TPO.repository;

import com.tpo.TPO.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    // Retrieve all comments of a specific post
    List<Comment> findByPostId(Integer postId);

    // Retrieve a specific comment by postId and commentId
    Optional<Comment> findByPostIdAndCommentId(Integer postId, Integer commentId);
}
