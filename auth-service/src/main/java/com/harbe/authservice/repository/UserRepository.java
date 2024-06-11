package com.harbe.authservice.repository;

import com.harbe.authservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsernameOrEmail(String username, String email);
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :username")
    Optional<User> findOneByUsernameWithRoles(String username);

    @Query(value = "SELECT * FROM Users u WHERE " +
            "u.name LIKE CONCAT('%', :name, '%')", nativeQuery = true
    )
    Page<User> searchUserByName(@Param("name") String name, Pageable pageable);
}
