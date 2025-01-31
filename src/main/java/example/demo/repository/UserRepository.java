package example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import example.demo.model.User;
import org.springframework.transaction.annotation.Transactional;
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name = ?2 WHERE u.id = ?1")
    int updateUserName(Long id, String name);
}
