package bredda.forger.youdo.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateTodoPayload {
    @NotBlank
    String text;
    boolean isCompleted;
}
