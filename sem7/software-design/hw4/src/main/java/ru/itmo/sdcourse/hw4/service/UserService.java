package ru.itmo.sdcourse.hw4.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.sdcourse.hw4.dto.UserEnterDto;
import ru.itmo.sdcourse.hw4.dto.UserRegistrationDto;
import ru.itmo.sdcourse.hw4.model.User;
import ru.itmo.sdcourse.hw4.repository.UserRepository;

import java.util.Optional;
import java.util.function.Function;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    public void register(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setLogin(userRegistrationDto.getLogin());
        userRepository.saveWithPasswordHashing(user, userRegistrationDto.getPassword());
    }

    public Optional<User> enter(UserEnterDto userEnterDto) {
        return userRepository.findByLoginAndPassword(userEnterDto.getLogin(), userEnterDto.getPassword());
    }

    public Optional<User> find(Long id) {
        return wrapNullArg(id, userRepository::findById);
    }

    public Optional<User> findByLogin(String login) {
        return wrapNullArg(login, userRepository::findByLogin);
    }

    private <T, R> Optional<R> wrapNullArg(T nullableArg, Function<T, Optional<R>> wrapped) {
        return nullableArg == null ? Optional.empty() : wrapped.apply(nullableArg);
    }
}
