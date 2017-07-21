package com.starwarsapi.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * Created by sjmg on 21/07/17.
 */
@Component
public class JobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        jobExecution.getStepExecutions().forEach(stepExecution -> stepExecution.get);
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
