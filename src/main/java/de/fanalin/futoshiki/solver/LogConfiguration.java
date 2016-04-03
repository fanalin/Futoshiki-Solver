package de.fanalin.futoshiki.solver;

import com.github.vbauer.herald.ext.spring.LogBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by matti on 03.04.2016.
 */
@Configuration
public class LogConfiguration {
    @Bean
    public LogBeanPostProcessor logBeanPostProcessor() {
        return new LogBeanPostProcessor();
    }
}
