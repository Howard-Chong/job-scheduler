package com.howard.job.scheduler.repository;

import com.howard.job.scheduler.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    // Find all jobs by status e.g. findAllByStatus("PENDING")
    List<Job> findAllByStatus(String status);

    // Find all jobs by type e.g. findAllByType("SEND_EMAIL")
    List<Job> findAllByType(String type);

    // Count how many jobs have a certain status
    long countByStatus(String status);
}