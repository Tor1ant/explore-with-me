package com.github.explore_with_me.main.user.controller;

import com.github.explore_with_me.main.exception.model.BadRequestException;
import com.github.explore_with_me.main.user.controller.paramEntity.GetUsersParam;
import com.github.explore_with_me.main.user.dto.NewUserDto;
import com.github.explore_with_me.main.user.dto.UserDto;
import com.github.explore_with_me.main.user.service.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/users")
@Slf4j
@AllArgsConstructor
public class AdminUserController {

    private UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto postUser(@RequestBody @Valid NewUserDto newUserDto) {
        emailValidation(newUserDto.getEmail());
        return userService.createUser(newUserDto);
    }

    @GetMapping
    public List<UserDto> getUsers(@RequestParam(required = false) List<Long> ids,
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "10") int size) {
        GetUsersParam getUsersParam = new GetUsersParam(ids, from, size);
        return userService.getUsersInfo(getUsersParam);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    private void emailValidation(String email) {
            String[] emailParts = email.split("@");
            String localPart = emailParts[0];
            String domainPart = emailParts[1];

            if (localPart.length() > 64) {
                throw new BadRequestException(
                        "Префикс электронной почты не может быть больше 64 символов: " + localPart);
            }
            if (domainPart.length() > 63) {
                throw new BadRequestException(
                        "Домен электронной почты не может быть больше 63 символов: " + domainPart);
            }
    }
}