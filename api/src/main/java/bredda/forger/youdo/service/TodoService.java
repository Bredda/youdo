package bredda.forger.youdo.service;

import bredda.forger.youdo.models.Todo;
import bredda.forger.youdo.models.User;
import bredda.forger.youdo.payload.request.CreateTodoPayload;
import bredda.forger.youdo.payload.request.UpdateTodoPayload;

import java.util.List;

public interface TodoService {

    List<Todo> getUserTodo(User user);
    Todo createTodo(CreateTodoPayload payload, User user);
    Todo updateTodo(UpdateTodoPayload payload, User user);
    void markAllCompleted(User user);
    void markAllNotCompleted(User user);
    void deleteAll(User user);
    Todo deleteTodo(Long todoId, User user);
}
