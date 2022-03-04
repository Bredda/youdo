package bredda.forger.youdo.service;

import bredda.forger.youdo.models.User;
import bredda.forger.youdo.payload.request.SignupRequest;

public interface UserService {
    boolean existsByUsername(String username);
    User create(SignupRequest request);
    User getUserFromContext();
}
