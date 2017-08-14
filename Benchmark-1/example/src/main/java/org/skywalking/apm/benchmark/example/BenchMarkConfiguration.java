package org.skywalking.apm.benchmark.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class BenchMarkConfiguration {

    @Value("${sleepTimePerLoop}")
    private String sleepTimePerLoop;

    @Value("${loopTimes}")
    private String loopTimes;

    public String getSleepTimePerLoop() {
        return sleepTimePerLoop;
    }

    public String getLoopTimes() {
        return loopTimes;
    }
}
