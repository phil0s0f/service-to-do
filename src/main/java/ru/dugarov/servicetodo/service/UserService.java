package ru.dugarov.servicetodo.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dugarov.servicetodo.entity.User;
import ru.dugarov.servicetodo.dto.UserDto;
import ru.dugarov.servicetodo.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto createUser(UserDto request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .isDeleted(false)
                .build();

        return buildResponse(userRepository.save(user));
    }


    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Пользователь не найдена"));
        return buildResponse(user);
    }

    public UserDto updateUser(Long id, UserDto request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setIsDeleted(request.getIsDeleted());

        return buildResponse(userRepository.save(user));
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "Пользователь " + id + " удален";
    }

    private UserDto buildResponse(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .isDeleted(user.getIsDeleted())
                .build();
    }
}
