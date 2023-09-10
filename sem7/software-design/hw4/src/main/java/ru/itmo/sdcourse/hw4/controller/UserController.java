package ru.itmo.sdcourse.hw4.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.sdcourse.hw4.controller.aop.auth.AnonymousAllowed;
import ru.itmo.sdcourse.hw4.dto.UserEnterDto;
import ru.itmo.sdcourse.hw4.dto.UserRegistrationDto;
import ru.itmo.sdcourse.hw4.service.UserService;
import ru.itmo.sdcourse.hw4.validator.UserEnterValidator;
import ru.itmo.sdcourse.hw4.validator.UserRegistrationValidator;

@Controller
@AllArgsConstructor
public class UserController extends BaseController {
    private UserService userService;
    private UserRegistrationValidator userRegistrationValidator;
    private UserEnterValidator userEnterValidator;

    @InitBinder("userRegistrationDto")
    public void initUserRegistrationValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(userRegistrationValidator);
    }

    @InitBinder("userEnterDto")
    public void initUserEnterValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(userEnterValidator);
    }

    @AnonymousAllowed
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegistrationDto", new UserRegistrationDto());
        return "register";
    }

    @AnonymousAllowed
    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "register";

        userService.register(userRegistrationDto);
        return "redirect:/";
    }

    @AnonymousAllowed
    @GetMapping("/enter")
    public String enter(Model model) {
        model.addAttribute("userEnterDto", new UserEnterDto());
        return "enter";
    }

    @AnonymousAllowed
    @PostMapping("/enter")
    public String enterPost(@Valid @ModelAttribute("userEnterDto") UserEnterDto userEnterDto,
                            BindingResult bindingResult,
                            HttpSession httpSession) {
        if (bindingResult.hasErrors())
            return "enter";

        setUser(httpSession, userService.enter(userEnterDto).orElse(null));
        putMessage(httpSession, MessageLevel.SUCCESS, "Welcome, " + getCurrentUser(httpSession).getLogin());
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpSession httpSession) {
        unsetUser(httpSession);
        putMessage(httpSession, MessageLevel.INFO, "Goodbye");
        return "redirect:/enter";
    }
}
