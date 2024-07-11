package com.example.riotdo.REST_Board.domain.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User register(RegisterDto registerDto) {
        User user = new User();
        user.setName(registerDto.getName());
        user.setPassword((registerDto.getPassword()));
        user.setUsername((registerDto.getUsername()));
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUser(int userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}