CREATE TABLE jobs (
    id          BIGSERIAL PRIMARY KEY,
    type        VARCHAR(100) NOT NULL,
    payload     TEXT,
    status      VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    cron_expr   VARCHAR(100),
    retries     INT NOT NULL DEFAULT 0,
    max_retries INT NOT NULL DEFAULT 3,
    error_msg   TEXT,
    scheduled_at TIMESTAMP,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_jobs_status ON jobs(status);
CREATE INDEX idx_jobs_scheduled_at ON jobs(scheduled_at);