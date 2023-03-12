package com.project.template.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "projectdomain.redis")
public class RedisProperties {

    private String master;
    private List<String> nodes;
    private ConnectionPool pool;
    private int cacheTtlSecond;

    @Getter
    @Setter
    public static class ConnectionPool{
        private int maxIdleConnection;
        private int minIdleConnection;
        private int maxTotalConnection;
        private boolean testOnBorrow;
        private boolean lifo;
        private boolean blockWhenExhausted;
        private int maxWaitMillis;
        private long commandTimedOutSec;
    }
}
