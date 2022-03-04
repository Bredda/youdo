package bredda.forger.youdo.service;

import bredda.forger.youdo.models.User;
import bredda.forger.youdo.payload.request.SignupRequest;
import bredda.forger.youdo.repository.UserRepository;
import bredda.forger.youdo.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    public UserServiceImpl(@Autowired UserRepository userRepository, @Autowired PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username);
    }

    @Override
    public User create(SignupRequest request) {
        var user = new User(request.getUsername(),
                encoder.encode(request.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public User getUserFromContext() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.getById(userDetails.getId());
    }
}
