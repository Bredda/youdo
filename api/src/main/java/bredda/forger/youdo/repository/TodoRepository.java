package bredda.forger.youdo.repository;

import bredda.forger.youdo.models.Todo;
import bredda.forger.youdo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);
    List<Todo> findByUserOrderByIdAsc(User user);
}