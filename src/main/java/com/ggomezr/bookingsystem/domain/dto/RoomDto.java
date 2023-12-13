package com.ggomezr.bookingsystem.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RoomDto(
        Integer id,
        String name,
        Boolean available,
        String type,
        Integer capacity,
        BigDecimal price
) {
}
