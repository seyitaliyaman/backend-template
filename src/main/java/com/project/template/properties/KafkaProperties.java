package com.project.template.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "projectdomain.kafka")
public class KafkaProperties {
    private String brokers;
    private String maxRequestSize;
    private String batchSize;
    private String maxPollRecord;
    private String autoCommitInterval;
    private String autoOffsetReset;
    private String groupId;
    private String lingerMs;
    private String requestTimeout;
    private Boolean enableIdempotence;
    private Boolean enableAutoCommit;
    private String topicName;
    /** if there are more topics, they should add here **/
}
