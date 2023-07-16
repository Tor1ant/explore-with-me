package com.github.explore_with_me.main.paramEntity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PaginationParams {

    private final int from;
    private final int size;
}
