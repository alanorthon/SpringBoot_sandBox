package ru.rodin.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rodin.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsUsersByUsername(String username);
    User findByUsername(String username);
}
