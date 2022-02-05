package com.uuhnaut69.demo.api.dto;

import javax.validation.constraints.NotBlank;

public record TodoRequest(@NotBlank String task) {
}
