package com.howard.job.scheduler.service;

import com.howard.job.scheduler.model.Job;
import com.howard.job.scheduler.model.JobStatus;
import com.howard.job.scheduler.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    // Create a new job
    public Job createJob(Job job) {
        log.info("Creating new job of type: {}", job.getType());
        job.setStatus(JobStatus.PENDING);
        return jobRepository.save(job);
    }

    // Get job by ID
    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
    }

    // Get all jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Get jobs by status
    public List<Job> getJobsByStatus(String status) {
        return jobRepository.findAllByStatus(status);
    }

    // Update job status
    public Job updateJobStatus(Long id, String status) {
        Job job = getJobById(id);
        log.info("Updating job {} status from {} to {}", id, job.getStatus(), status);
        job.setStatus(status);
        return jobRepository.save(job);
    }

    // Delete a job
    public void deleteJob(Long id) {
        log.info("Deleting job with id: {}", id);
        jobRepository.deleteById(id);
    }
}