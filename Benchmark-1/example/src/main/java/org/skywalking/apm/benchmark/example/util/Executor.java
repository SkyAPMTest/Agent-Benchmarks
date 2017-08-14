package org.skywalking.apm.benchmark.example.util;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config.properties")
public class Executor {
    @Value("${sleepTimePerLoop}")
    private long perSleepTime;
    @Value("${loopTimes}")
    private int loopTimes;

    @Autowired
    private Executor executor;

    private static Executor INSTANCE;

    @PostConstruct
    public void initData() {
        INSTANCE = executor;
    }

    public void doExecute() {
        for (int i = 0; i < loopTimes; i++) {
            int sum = 0;
            for (int j = 0; j < 10; j++) {
                sum += j;
            }

            try {
                Thread.sleep(perSleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static Executor Instance() {
        return INSTANCE;
    }
}
