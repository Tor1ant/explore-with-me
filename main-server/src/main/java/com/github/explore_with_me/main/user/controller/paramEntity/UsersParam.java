package com.github.explore_with_me.main.user.controller.paramEntity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UsersParam {

    private List<Long> ids;
    private int from;
    private int size;
}
