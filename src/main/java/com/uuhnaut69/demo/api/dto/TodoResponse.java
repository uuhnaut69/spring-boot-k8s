package com.uuhnaut69.demo.api.dto;

import java.time.Instant;

public record TodoResponse(Long id, String task, boolean done, Instant createdDate) {
}
