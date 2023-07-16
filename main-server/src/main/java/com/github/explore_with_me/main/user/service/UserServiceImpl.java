package com.github.explore_with_me.main.user.service;

import com.github.explore_with_me.main.exception.model.ConflictException;
import com.github.explore_with_me.main.user.controller.paramEntity.UsersParam;
import com.github.explore_with_me.main.user.dto.NewUserDto;
import com.github.explore_with_me.main.user.dto.UserDto;
import com.github.explore_with_me.main.user.mapper.UserMapper;
import com.github.explore_with_me.main.user.model.User;
import com.github.explore_with_me.main.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public UserDto createUser(NewUserDto newUserDto) {
        User user = userMapper.newUserDtoToUser(newUserDto);
        try {
            user = userRepository.save(user);
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder(e.getCause().getCause().getMessage());
            int indexEqualsSign = stringBuilder.indexOf("=");
            stringBuilder.delete(0, indexEqualsSign + 1);
            throw new ConflictException(stringBuilder.toString().replace("\"", "").trim());
        }
        log.info("Пользователь= " + user + " создан.");
        return userMapper.userToUserDto(user);
    }

    @Override
    public List<UserDto> getUsersInfo(UsersParam usersParam) {
        PageRequest pagination = PageRequest.of(usersParam.getFrom() / usersParam.getSize(),
                usersParam.getSize());
        List<User> all = new ArrayList<>();
        if (usersParam.getIds() == null) {
            all.addAll(userRepository.findAll(pagination).getContent());
        } else {
            all.addAll(userRepository.findAllByIdIn(usersParam.getIds(), pagination));
        }
        log.info("Получены все пользователи " + all);
        return userMapper.userListToUserDtoList(all);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
        log.info("Пользователь с id " + userId + "удалён");
    }
}
