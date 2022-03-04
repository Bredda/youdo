package bredda.forger.youdo.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "'username' field cannot be blank")
    @Size(min= 4, max = 20, message = "'username' field cannot exceed 20 characters")
    private String username;

    @NotBlank(message = "'username' field cannot be blank")
    @Size(max = 120)
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}