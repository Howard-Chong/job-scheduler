package com.howard.job.scheduler.model;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class JobRequest {

    @NotBlank(message = "Job type is required")
    private String type;

    private String payload;
    private String cronExpr;
    private String scheduledAt;
}