package com.example.sscloud.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

@Data

public class SalaryRequestDto {

    private String firstName;

    private String department;
}
