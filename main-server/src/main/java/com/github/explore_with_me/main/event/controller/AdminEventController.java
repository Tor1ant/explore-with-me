package com.github.explore_with_me.main.event.controller;

import com.github.explore_with_me.main.event.dto.EventOutDto;
import com.github.explore_with_me.main.event.dto.UpdateEventUserDto;
import com.github.explore_with_me.main.event.enumerated.State;
import com.github.explore_with_me.main.event.service.EventService;
import com.github.explore_with_me.main.paramEntity.EventFindParam;
import com.github.explore_with_me.main.paramEntity.PaginationParams;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
public class AdminEventController {

    private final EventService eventService;

    @PatchMapping("/{eventId}")
    public EventOutDto publishOrCancelEvent(@PathVariable Long eventId,
            @RequestBody @Valid UpdateEventUserDto updateEventUserDto) {
        return eventService.publishOrCancelEvent(eventId, updateEventUserDto);
    }

    @GetMapping
    List<EventOutDto> findEvents(@RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<State> states,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeStart,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime rangeEnd,
            @RequestParam(defaultValue = "0") Integer from,
            @RequestParam(defaultValue = "10") Integer size) {
        return eventService.findEventsByAdmin(new EventFindParam(users, states, categories, rangeStart, rangeEnd),
                new PaginationParams(from, size));
    }
}
