package com.tpo.TPO.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.tpo.TPO.entity.User;
import com.tpo.TPO.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(String contains, int skip, int limit, String orderby) {
        Pageable pageable = PageRequest.of(skip / limit, limit, Sort.by(orderby));
        return userRepository.findUsers(contains, pageable);
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUser(Integer userId, User user) {
        // Lógica para actualizar un usuario
        return userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    public int getUserCommentsCount(Integer userId) {
        // Lógica para contar los comentarios de un usuario
        return userRepository.countCommentsByUserId(userId);
    }

    // Otros métodos para manejar posts, followers, etc. se pueden agregar aquí
}
