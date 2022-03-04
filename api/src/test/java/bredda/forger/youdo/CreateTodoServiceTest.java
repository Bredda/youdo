package bredda.forger.youdo;


import bredda.forger.youdo.models.Todo;
import bredda.forger.youdo.models.User;
import bredda.forger.youdo.payload.request.CreateTodoPayload;
import bredda.forger.youdo.repository.TodoRepository;
import bredda.forger.youdo.service.TodoService;
import bredda.forger.youdo.service.TodoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static bredda.forger.youdo.factories.CreateTodoPayloadFactory.aPayload;
import static bredda.forger.youdo.factories.TodoFactory.aTodo;
import static bredda.forger.youdo.factories.UserFactory.aUser;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
public class CreateTodoServiceTest {

    @Mock
    TodoRepository todoRepository;
    private TodoService todoService;
    private User testUser;
    private CreateTodoPayload testPayload;
    private Todo testTodo;
    @BeforeEach
    void setUp() {
        todoService = new TodoServiceImpl(todoRepository);
        testUser = aUser().random().build();
        testPayload = aPayload().random().build();
        testTodo = aTodo().fromPayload(testPayload).forUser(testUser).build();
    }

    @Test
    void shouldLinkCreatedTodoToUser() {
        when(todoRepository.save(any(Todo.class))).thenReturn(testTodo);
        Todo createdTodo = todoService.createTodo(testPayload, testUser);
        assertThat(createdTodo.getUser()).isEqualTo(testUser);
    }

    @Test
    void shouldCallRepositoryToSave() {
        when(todoRepository.save(any(Todo.class))).thenReturn(testTodo);
        todoService.createTodo(testPayload, testUser);
        verify(todoRepository, times((1))).save(any(Todo.class));
    }


}
