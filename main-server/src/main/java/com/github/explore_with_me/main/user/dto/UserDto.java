package com.github.explore_with_me.main.user.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data()
public class UserDto {

    @Setter(value = AccessLevel.PRIVATE)
    private Long id;
    private String email;
    private String name;
}
