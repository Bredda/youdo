package bredda.forger.youdo.factories;

import bredda.forger.youdo.payload.request.CreateTodoPayload;
import com.github.javafaker.Faker;

public class CreateTodoPayloadFactory {


    private CreateTodoPayload payload;

    private CreateTodoPayloadFactory() {
        this.payload = new CreateTodoPayload();
    }

    public static CreateTodoPayloadFactory aPayload() {

        return new CreateTodoPayloadFactory();
    }

    public CreateTodoPayloadFactory random() {
        Faker faker = new Faker();
        this.payload.setText(faker.harryPotter().quote());
        this.payload.setCompleted(faker.random().nextBoolean());
        return this;
    }

    public CreateTodoPayload build() {
        return this.payload;
    }
}
