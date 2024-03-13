package com.example.room_reservation_test.dto.requestDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoomRequestDto {
    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name should contain at least {min} characters")
    private String name;
    private String description;
    @Min(value = 1, message = "Value should be greater than or equal to {value}")
    @Max(value = 100, message = "Value should be less than or equal to {value}")
    private int capacity;

}
