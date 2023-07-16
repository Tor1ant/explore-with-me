package com.github.explore_with_me.main.user.service;

import com.github.explore_with_me.main.user.controller.paramEntity.UsersParam;
import com.github.explore_with_me.main.user.dto.NewUserDto;
import com.github.explore_with_me.main.user.dto.UserDto;
import java.util.List;

public interface UserService {

    UserDto createUser(NewUserDto newUserDto);

    List<UserDto> getUsersInfo(UsersParam usersParam);

    void deleteUser(Long userId);
}
