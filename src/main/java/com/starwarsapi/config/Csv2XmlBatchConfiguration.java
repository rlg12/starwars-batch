package com.starwarsapi.config;

import ch.qos.logback.core.joran.event.stax.StaxEvent;
import com.starwarsapi.domain.People;
import com.starwarsapi.listener.PeopleStepListener;
import com.starwarsapi.tasklet.HelloWorldTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Created by sjmg on 21/07/17.
 */
@Configuration
@EnableBatchProcessing
public class Csv2XmlBatchConfiguration {

    @Bean
    public ItemReader<People> peopleReader(){

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
//        lineTokenizer.setDelimiter(";");
        lineTokenizer.setNames(new String[] {"name","birthYear","gender","height","mass","eyeColor","hairColor","skinColor"});

        BeanWrapperFieldSetMapper<People> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(People.class);


        DefaultLineMapper<People> lineMapper = new DefaultLineMapper<>();
        lineMapper.setFieldSetMapper(fieldSetMapper);
        lineMapper.setLineTokenizer(lineTokenizer);

        FlatFileItemReader<People> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/people.csv"));
        itemReader.setLineMapper(lineMapper);

        return itemReader;
    }
    @Bean
    public ItemWriter<People> peopleWriter(){

        StaxEventItemWriter<People> itemWriter = new StaxEventItemWriter<>();
        itemWriter.setResource(new FileSystemResource("src/main/resources/people.xml"));
        itemWriter.setRootTagName("peoples");
        itemWriter.setOverwriteOutput(true);

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(People.class);

        itemWriter.setMarshaller(marshaller);

        return itemWriter;

    }
    @Bean
    public Step cvsStep(StepBuilderFactory stepBuilderFactory, ItemReader peopleItemReader, ItemWriter peopleItemWriter,
                        PeopleStepListener peopleStepListener){
        return stepBuilderFactory
                .get("cvsStep")
                .chunk(10)
                .listener(peopleStepListener)
                .reader(peopleItemReader)
                .writer(peopleItemWriter)
                .build();
    }

    @Bean
    public Job csvJob(JobBuilderFactory jobBuilderFactory, Step cvsStep) {
        return jobBuilderFactory
                .get("csvJob")
                .incrementer(new RunIdIncrementer())
                .start(cvsStep)
                .build();
    }


}
