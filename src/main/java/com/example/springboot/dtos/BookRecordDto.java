package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record BookRecordDto(@NotBlank String name, @NotNull BigDecimal pages) {
}
