package com.github.explore_with_me.main.compilation.dto;

import com.github.explore_with_me.main.event.dto.EventShortDto;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class CompilationDto {

    private final Long id;

    @Setter
    private List<EventShortDto> events;

    private final boolean pinned;

    private final String title;
}
