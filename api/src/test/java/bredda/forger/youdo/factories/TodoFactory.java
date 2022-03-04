package bredda.forger.youdo.factories;

import bredda.forger.youdo.models.Todo;
import bredda.forger.youdo.models.User;
import bredda.forger.youdo.payload.request.CreateTodoPayload;
import com.github.javafaker.Faker;

public class TodoFactory {

    private Todo todo;

    private TodoFactory() {
        Faker faker = new Faker();
        this.todo = new Todo();
        this.todo.setId(faker.random().nextLong());
    }

    public TodoFactory fromPayload(CreateTodoPayload payload) {
        todo.setCompleted(payload.isCompleted());
        todo.setText(payload.getText());
        return this;
    }

    public TodoFactory clonedFrom(Todo original) {
        todo.setCompleted(original.isCompleted());
        todo.setText(original.getText());
        todo.setId(original.getId());
        todo.setUser(original.getUser());
        return this;
    }

    public static TodoFactory aTodo() {

        return new TodoFactory();
    }

    public TodoFactory random() {
        Faker faker = new Faker();
        this.todo.setText(faker.harryPotter().quote());

        this.todo.setCompleted(faker.random().nextBoolean());
        var user = new User();
        user.setId(faker.random().nextLong());
        user.setUsername(faker.name().username());
        user.setPassword(faker.internet().password());
        todo.setUser(user);
        return this;
    }

    public TodoFactory withoutUser() {
        this.todo.setUser(null);
        return this;
    }

    public TodoFactory forUser(User user) {
        this.todo.setUser(user);
        return this;
    }

    public Todo build() {
        return this.todo;
    }



}
