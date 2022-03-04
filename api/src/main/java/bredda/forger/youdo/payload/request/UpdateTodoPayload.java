package bredda.forger.youdo.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class UpdateTodoPayload {
    @Positive
    Long id;
    @NotBlank
    String text;
    boolean isCompleted;
}
