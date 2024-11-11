package com.tpo.TPO.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tpo.TPO.entity.User;
import com.tpo.TPO.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<User> getAllUsers(String contains, int skip, int limit, String orderby) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be greater than zero");
        }
        int page = skip / limit;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(orderby));
        return userRepository.findUsers(contains, pageable);
    }

    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }
<<<<<<< Updated upstream
    
=======

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

>>>>>>> Stashed changes
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Integer userId, User user) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(user.getName());
        existingUser.setLastName(user.getLastName());
        // Actualiza solo los campos necesarios.

        return userRepository.save(existingUser);
    }

    public void deleteUser(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(userId);
    }

    public int getUserCommentsCount(Integer userId) {
        return userRepository.countCommentsByUserId(userId);
    }

    // Método para obtener los seguidores de un usuario
    public Set<User> getFollowers(Integer userId) {
        return userRepository.getFollowers(userId);
    }

    // Método para obtener los usuarios seguidos por un usuario
    public Set<User> getFollowed(Integer userId) {
        return userRepository.getFollowed(userId);
    }

    @Transactional
    public User followUser(Integer userId, Integer followUserId) {
<<<<<<< Updated upstream
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    User userToFollow = userRepository.findById(followUserId)
            .orElseThrow(() -> new RuntimeException("User to follow not found"));

    if (user.getFollowed().contains(userToFollow)) {
        throw new IllegalArgumentException("User is already followed");
    }

    user.getFollowed().add(userToFollow);
    userToFollow.getFollowers().add(user);

    userRepository.save(user);
    userRepository.save(userToFollow);

    return userToFollow; // Devuelve el usuario seguido
}

    
=======
        logger.info("Attempting to follow user with ID: {} by user with ID: {}", followUserId, userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User with ID: {} not found", userId);
                    return new RuntimeException("User not found");
                });

        User userToFollow = userRepository.findById(followUserId)
                .orElseThrow(() -> {
                    logger.error("User to follow with ID: {} not found", followUserId);
                    return new RuntimeException("User to follow not found");
                });

        if (user.getFollowed().contains(userToFollow)) {
            logger.warn("User with ID: {} is already following user with ID: {}", userId, followUserId);
            throw new IllegalArgumentException("User is already followed");
        }

        user.getFollowed().add(userToFollow);
        logger.info("User with ID: {} successfully followed user with ID: {}", userId, followUserId);

        userRepository.save(user);
        return userToFollow;
    }

    public boolean isEmailUsed(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean isUsernameUsed(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

>>>>>>> Stashed changes
}
