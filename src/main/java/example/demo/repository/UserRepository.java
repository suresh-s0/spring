package example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
