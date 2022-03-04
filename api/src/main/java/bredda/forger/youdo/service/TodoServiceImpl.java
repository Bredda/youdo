package bredda.forger.youdo.service;

import bredda.forger.youdo.exception.UnauthorizedException;
import bredda.forger.youdo.models.Todo;
import bredda.forger.youdo.models.User;
import bredda.forger.youdo.payload.request.CreateTodoPayload;
import bredda.forger.youdo.payload.request.UpdateTodoPayload;
import bredda.forger.youdo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    public TodoServiceImpl(@Autowired TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getUserTodo(User user) {
        return todoRepository.findByUserOrderByIdAsc(user);
    }
    @Override
    public Todo createTodo(CreateTodoPayload payload, User user) {
        Todo todo = new Todo(payload.getText(), payload.isCompleted(), user);
        return todoRepository.save(todo);
    }

    @Override
    public Todo updateTodo(UpdateTodoPayload payload, User user) {
        Todo todo = todoRepository.findById(payload.getId()).orElseThrow(() -> new EntityNotFoundException("Ent not found"));
        if (!todo.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException();
        }
        todo.setText(payload.getText());
        todo.setCompleted(payload.isCompleted());
        return todoRepository.save(todo);
    }

    @Override
    public void markAllCompleted(User user) {
        List<Todo> todos = todoRepository.findByUser(user);
        todos.forEach(t -> t.setCompleted(true));
        todoRepository.saveAll(todos);
    }

    @Override
    public void markAllNotCompleted(User user) {
        List<Todo> todos = todoRepository.findByUser(user);
        todos.forEach(t -> t.setCompleted(false));
        todoRepository.saveAll(todos);
    }
    @Override
    public void deleteAll(User user) {
        List<Todo> todos = todoRepository.findByUser(user);
        todos.forEach(t -> t.setCompleted(false));
        todoRepository.deleteAll(todos);
    }
    @Override
    public Todo deleteTodo(Long todoId, User user) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new EntityNotFoundException("Todo not found"));
        if (!todo.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException();
        }
        todoRepository.deleteById(todo.getId());
        return todo;
    }
}
