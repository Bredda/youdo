package bredda.forger.youdo.factories;

import bredda.forger.youdo.models.User;
import com.github.javafaker.Faker;

public class UserFactory {

    private User user;

    private UserFactory() {
        this.user = new User();
    }

    public static UserFactory aUser() {
        return new UserFactory();
    }

    public UserFactory random() {
        Faker faker = new Faker();
        var user = new User();
        user.setId(faker.random().nextLong());
        user.setUsername(faker.name().username());
        user.setPassword(faker.internet().password());
        return this;
    }

    public User build() {
        return this.user;
    }
}
