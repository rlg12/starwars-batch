package com.starwarsapi.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * Created by  on 21/07/17.
 */
@Component
@Slf4j
public class JobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        jobExecution.getStepExecutions().forEach(stepExecution -> stepExecution.getSummary());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
