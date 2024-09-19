package com.rpg.build_rpg.infra.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private int status;
    private LocalDateTime timestamp;
    private List<String> message;

}
