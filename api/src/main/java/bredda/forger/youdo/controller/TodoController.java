package bredda.forger.youdo.controller;

import bredda.forger.youdo.models.Todo;
import bredda.forger.youdo.models.User;
import bredda.forger.youdo.payload.request.CreateTodoPayload;
import bredda.forger.youdo.payload.request.UpdateTodoPayload;
import bredda.forger.youdo.service.TodoService;
import bredda.forger.youdo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name="Todos", description = "Managing user todo list")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    UserService userService;
    @Autowired
    TodoService todoService;


    @GetMapping("")
    @Operation(summary = "Get user's todo list", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<Todo>> getUserTodos() {
        User user = userService.getUserFromContext();
        List<Todo> todos = todoService.getUserTodo(user);
        return ResponseEntity.ok().body(todos);
    }

    @PostMapping("")
    @Operation(summary = "Create a new user todo item", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<Todo>> createUserTodo(@Valid @RequestBody CreateTodoPayload payload) {
        User user = userService.getUserFromContext();
        todoService.createTodo(payload, user);
        List<Todo> todos = todoService.getUserTodo(user);
        return ResponseEntity.ok().body(todos);
    }

    @PutMapping("/allCompleted")
    @Operation(summary = "Set all user todo as completed", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<Todo>> completeAllUserTodo() {
        User user = userService.getUserFromContext();
        todoService.markAllCompleted(user);
        List<Todo> todos = todoService.getUserTodo(user);
        return ResponseEntity.ok().body(todos);
    }

    @PutMapping("/allNotCompleted")
    @Operation(summary = "Set all user todo as not completed", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<Todo>> uncompleteAllUserTodo() {
        User user = userService.getUserFromContext();
        todoService.markAllNotCompleted(user);
        List<Todo> todos = todoService.getUserTodo(user);
        return ResponseEntity.ok().body(todos);
    }

    @PutMapping("")
    @Operation(summary = "Update a user's todo", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<Todo>> updateUserTodo(@Valid @RequestBody UpdateTodoPayload payload) {
        User user = userService.getUserFromContext();
        todoService.updateTodo(payload, user);
        List<Todo> todos = todoService.getUserTodo(user);
        return ResponseEntity.ok().body(todos);
    }
    @DeleteMapping("/all")
    @Operation(summary = "Delete all user's todo", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<Todo>> deleteAllUserTodo(@Valid @PathVariable Long todoId) {
        User user = userService.getUserFromContext();
        todoService.deleteAll(user);
        List<Todo> todos = todoService.getUserTodo(user);
        return ResponseEntity.ok().body(todos);
    }
    @DeleteMapping("/{todoId}")
    @Operation(summary = "Delete a user's todo", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<Todo>> deleteUserTodo(@Valid @PathVariable Long todoId) {
        User user = userService.getUserFromContext();
        todoService.deleteTodo(todoId, user);
        List<Todo> todos = todoService.getUserTodo(user);
        return ResponseEntity.ok().body(todos);
    }
}
