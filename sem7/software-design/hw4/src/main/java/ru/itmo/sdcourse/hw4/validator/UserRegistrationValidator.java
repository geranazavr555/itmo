package ru.itmo.sdcourse.hw4.validator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.sdcourse.hw4.dto.UserRegistrationDto;
import ru.itmo.sdcourse.hw4.service.UserService;

@Component
@AllArgsConstructor
public class UserRegistrationValidator implements Validator {
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegistrationDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegistrationDto userRegistrationDto = (UserRegistrationDto) target;
        if (userService.findByLogin(userRegistrationDto.getLogin()).isPresent()) {
            errors.rejectValue("login", "login.already-exists", "User is already exists");
        }

        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getPasswordConfirmation())) {
            errors.rejectValue("passwordConfirmation", "password-confirmation.doesnt-match-password",
                    "Password confirmation is not equals to the password");
        }
    }
}
