package bredda.forger.youdo.payload.response;

import lombok.Getter;

@Getter
public class JwtResponse {
    private String token;
    private Long id;
    private String username;

    public JwtResponse(String token, Long id, String username) {
        this.id = id;
        this.token = token;
        this.username = username;
    }
}
