package com.github.explore_with_me.main.event.service;

import com.github.explore_with_me.main.event.dto.EventOutDto;
import com.github.explore_with_me.main.event.dto.EventRequestStatusUpdateRequest;
import com.github.explore_with_me.main.event.dto.EventRequestStatusUpdateResult;
import com.github.explore_with_me.main.event.dto.EventShortDto;
import com.github.explore_with_me.main.event.dto.NewEventDto;
import com.github.explore_with_me.main.event.dto.UpdateEventUserDto;
import com.github.explore_with_me.main.paramEntity.EventFindParam;
import com.github.explore_with_me.main.paramEntity.GetEventsParam;
import com.github.explore_with_me.main.paramEntity.PaginationParams;
import com.github.explore_with_me.main.requests.dto.ParticipationRequestDto;
import java.util.List;

public interface EventService {

    EventOutDto createEvent(NewEventDto newEventDto, Long userId);

    List<EventShortDto> getUserEvents(Long userId, PaginationParams params);

    EventOutDto getUserEvent(Long userId, Long eventId);

    EventOutDto patchEvent(Long userId, Long eventId, UpdateEventUserDto updateEventUserDto);

    List<ParticipationRequestDto> getEventRequests(Long userId, Long eventId);

    EventRequestStatusUpdateResult changeRequestsStatus(Long userId, Long eventId,
            EventRequestStatusUpdateRequest statusUpdateRequest);

    EventOutDto publishOrCancelEvent(Long eventId, UpdateEventUserDto updateEventUserDto);

    List<EventOutDto> findEventsByAdmin(EventFindParam eventFindParam, PaginationParams params);

    EventOutDto getEvent(Long eventId, String[] uris);

    List<EventShortDto> getEvents(GetEventsParam getEventsParam, PaginationParams pagination);
}
