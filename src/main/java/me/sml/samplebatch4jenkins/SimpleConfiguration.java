package me.sml.samplebatch4jenkins;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SimpleConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job sampleJob(){
        return jobBuilderFactory.get("sampleJob")
                .start(sampleStep())
                .build();
    }

    @Bean
    public Step sampleStep() {
        return stepBuilderFactory.get("sampleStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> This is a sample Step");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
