package com.starwarsapi.config;

import com.starwarsapi.tasklet.HelloWorldTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sjmg on 21/07/17.
 */

public class BatchConfiguration {

    @Bean
    public Step helloworldStep(StepBuilderFactory stepBuilderFactory, HelloWorldTasklet helloWorldTasklet){

        return stepBuilderFactory.get("helloWorldStep").tasklet(helloWorldTasklet).build();
    }

    @Bean
    public Job helloworldJob(JobBuilderFactory jobBuilderFactory, Step helloWorldStep){
        return jobBuilderFactory
                .get("helloWorldJob")
                .incrementer(new RunIdIncrementer())
                .start(helloWorldStep)
                .build();
    }
}
