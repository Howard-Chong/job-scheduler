package com.howard.job.scheduler.controller;

import com.howard.job.scheduler.model.Job;
import com.howard.job.scheduler.model.JobRequest;
import com.howard.job.scheduler.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    // POST /api/v1/jobs - Create a new job
    @PostMapping
    public ResponseEntity<Job> createJob(@Valid @RequestBody JobRequest request) {
        Job job = Job.builder()
                .type(request.getType())
                .payload(request.getPayload())
                .cronExpr(request.getCronExpr())
                .scheduledAt(request.getScheduledAt() != null
                        ? LocalDateTime.parse(request.getScheduledAt())
                        : null)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.createJob(job));
    }

    // GET /api/v1/jobs - Get all jobs
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs(
            @RequestParam(required = false) String status) {
        if (status != null) {
            return ResponseEntity.ok(jobService.getJobsByStatus(status));
        }
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    // GET /api/v1/jobs/{id} - Get job by ID
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    // PATCH /api/v1/jobs/{id}/status - Update job status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Job> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(jobService.updateJobStatus(id, status));
    }

    // DELETE /api/v1/jobs/{id} - Delete a job
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}
