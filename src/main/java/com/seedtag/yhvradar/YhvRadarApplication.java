package com.seedtag.yhvradar;

import com.seedtag.yhvradar.manager.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class YhvRadarApplication {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(YhvRadarApplication.class, args);

    }

    private ProtocolManager closestEnemiesProtocolManager() {
        return new ClosestEnemiesProtocolManager(null);
    }

    private ProtocolManager furthestEnemiesProtocolManager() {
        return new FurthestEnemiesProtocolManager(closestEnemiesProtocolManager());
    }

    private ProtocolManager avoidMechProtocolManager() {
        return new AvoidMechProtocolManager(furthestEnemiesProtocolManager());
    }

    private ProtocolManager priorMechProtocolManager() {
        return new PriorMechProtocolManager(avoidMechProtocolManager());
    }

    private ProtocolManager avoidCrossFireProtocolManager() {
        return new AvoidCrossFireProtocolManager(priorMechProtocolManager());
    }

    private ProtocolManager assistAlliesProtocolManager() {
        return new AssistAlliesProtocolManager(avoidCrossFireProtocolManager());
    }


    @Bean
    public ProtocolRuleEngine ruleEngine() {
        return new ProtocolRuleEngine(assistAlliesProtocolManager());
    }
}