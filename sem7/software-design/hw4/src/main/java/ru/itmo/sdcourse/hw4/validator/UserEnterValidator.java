package ru.itmo.sdcourse.hw4.validator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.sdcourse.hw4.dto.UserEnterDto;
import ru.itmo.sdcourse.hw4.service.UserService;

@Component
@AllArgsConstructor
public class UserEnterValidator implements Validator {
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserEnterDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserEnterDto userEnterDto = (UserEnterDto) target;
        if (userService.enter(userEnterDto).isEmpty())
            errors.rejectValue("password", "password.invalid-login-or-password", "Invalid login or password");
    }
}
