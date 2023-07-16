package com.github.explore_with_me.main.event.dto;

import com.github.explore_with_me.main.requests.status.Status;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestStatusUpdateRequest {

    List<Long> requestIds;
    Status status;
}
