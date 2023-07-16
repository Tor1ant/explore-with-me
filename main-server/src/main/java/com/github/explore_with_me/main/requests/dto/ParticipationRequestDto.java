package com.github.explore_with_me.main.requests.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.explore_with_me.main.requests.status.Status;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParticipationRequestDto {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;
    private Long event;
    private Long id;
    private Long requester;
    private Status status;

    public ParticipationRequestDto(LocalDateTime created, Long event, Long id, Long requester, Status status) {
        this.created = created;
        this.event = event;
        this.id = id;
        this.requester = requester;
        this.status = status;
    }
}
