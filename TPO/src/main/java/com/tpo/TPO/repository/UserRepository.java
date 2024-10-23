package com.tpo.TPO.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tpo.TPO.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.name LIKE %:contains%")
    List<User> findUsers(@Param("contains") String contains, Pageable pageable);

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.userId = :id_user")
    int countCommentsByUserId(Integer id_user);
}
