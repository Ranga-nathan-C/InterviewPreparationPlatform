package com.example.interviewprep.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.interviewprep.entity.Role;
import com.example.interviewprep.entity.User;
import java.util.Optional;
import java.util.List;
public interface UserRepository extends JpaRepository<User,Long>{

    Optional<User> findByEmail(String email);
    List<User> findByName(String name);
    List<User> findByRole(Role role);


}
