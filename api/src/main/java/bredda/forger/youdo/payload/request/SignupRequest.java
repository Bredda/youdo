package bredda.forger.youdo.payload.request;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.*;

@Getter
@Setter
public class SignupRequest {
    @NotBlank(message = "'username' field cannot be blank")
    @Size(min= 4, max = 20, message = "'username' field cannot exceed 20 characters")
    private String username;

    @NotBlank(message = "'username' field cannot be blank")
    @Size(min = 6, max = 50, message = "'password' field must be between 6 and 50 characters")
    private String password;

}
