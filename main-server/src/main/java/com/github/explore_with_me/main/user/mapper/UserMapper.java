package com.github.explore_with_me.main.user.mapper;

import com.github.explore_with_me.main.user.dto.UserDto;
import com.github.explore_with_me.main.user.model.User;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    List<UserDto> userListToUserDtoList(List<User> all);
}
