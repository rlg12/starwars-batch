package com.starwarsapi.launcher;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by  on 21/07/17.
 */
@Component
@AllArgsConstructor
public class CsvJobLauncher {
    private JobLauncher jobLauncher;
    private Job csvJob;

    @Scheduled(fixedDelay = 15000)
    public void run() throws Exception{
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time",System.currentTimeMillis()).toJobParameters();

        jobLauncher.run(csvJob,jobParameters);
    }


}
