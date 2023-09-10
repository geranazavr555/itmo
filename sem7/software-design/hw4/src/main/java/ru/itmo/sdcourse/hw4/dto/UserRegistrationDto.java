package ru.itmo.sdcourse.hw4.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRegistrationDto implements Serializable {
    @NotEmpty
    @Size(min = 5, max = 32)
    private String login;

    @NotEmpty
    @Size(min = 6, max = 64)
    private String password;

    @NotEmpty
    @Size(min = 6, max = 64)
    private String passwordConfirmation;
}
