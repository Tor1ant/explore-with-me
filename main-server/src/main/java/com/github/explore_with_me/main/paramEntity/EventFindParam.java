package com.github.explore_with_me.main.paramEntity;

import com.github.explore_with_me.main.event.enumerated.State;
import java.time.LocalDateTime;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@Getter
@RequiredArgsConstructor
public class EventFindParam {

    private final List<Long> userIds;
    private final List<State> states;
    private final List<Long> categoryIds;
    private final LocalDateTime rangeStart;
    private final LocalDateTime rangeEnd;
}
