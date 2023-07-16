package com.github.explore_with_me.main.paramEntity;

import com.github.explore_with_me.main.event.enumerated.Sorting;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@Getter
public class GetEventsParam {

    private final String text;
    private final List<Long> categories;
    private final Boolean paid;
    private final @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart;
    private final @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd;
    private final Boolean onlyAvailable;
    private final Sorting sort;
}
