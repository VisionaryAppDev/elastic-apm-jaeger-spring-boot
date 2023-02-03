package com.example.apm.config;


import co.elastic.apm.attach.ElasticApmAttacher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
//@ConfigurationProperties(prefix = "elastic.apm")
//@ConditionalOnProperty(value = "elastic.apm.enabled", havingValue = "true")
public class ElasticApmConfig {
    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    @Value("${spring.application.name:Unknown}")
    private String appName;

    @Value("${apm.server_url}")
    private String apmServerUrls;


    @PostConstruct
    public void init() {
        /// https://www.elastic.co/guide/en/apm/agent/java/current/configuration.html#configuration
        Map<String, String> apmProps = new HashMap<>(6);
        apmProps.put("application_packages", "com.example.apm");
        apmProps.put("environment", activeProfile);
        apmProps.put("server_urls", apmServerUrls);
        apmProps.put("service_name", appName);
        apmProps.put("log_level", "TRACE");
        apmProps.put("log_sending", "true");
        apmProps.put("capture_body", "all");
        apmProps.put("trace_methods", "com.example.apm.*");
        apmProps.put("enable_log_correlation", "true");
        apmProps.put("verify_server_cert", "false");

        ElasticApmAttacher.attach(apmProps);
    }
}