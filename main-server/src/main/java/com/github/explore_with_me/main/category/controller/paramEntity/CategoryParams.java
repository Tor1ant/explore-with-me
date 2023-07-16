package com.github.explore_with_me.main.category.controller.paramEntity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CategoryParams {

    private final int from;

    private final int size;
}
