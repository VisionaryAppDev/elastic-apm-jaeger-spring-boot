package com.example.elasticapm;


import co.elastic.apm.attach.ElasticApmAttacher;
import com.example.elasticapm.model.Student;
import jakarta.annotation.PostConstruct;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Setter
@Configuration
//@ConfigurationProperties(prefix = "elastic.apm")
//@ConditionalOnProperty(value = "elastic.apm.enabled", havingValue = "true")
public class ElasticApmConfig {

//    private static final String SERVER_URL_KEY = "server_url";
//    private String serverUrl;
//
//    private static final String SERVICE_NAME_KEY = "service_name";
//    private String serviceName;
//
//    private static final String SECRET_TOKEN_KEY = "secret_token";
//    private String secretToken;
//
//    private static final String ENVIRONMENT_KEY = "environment";
//    private String environment;
//
//    private static final String APPLICATION_PACKAGES_KEY = "application_packages";
//    private String applicationPackages;
//
//    private static final String LOG_LEVEL_KEY = "log_level";
//    private String logLevel;

    @PostConstruct
    public void init() {
        /// https://www.elastic.co/guide/en/apm/agent/java/current/configuration.html#configuration
        Map<String, String> apmProps = new HashMap<>(6);
        apmProps.put("application_packages", "com.example.elasticapm");
        apmProps.put("environment", "dev");
        apmProps.put("server_urls", "http://localhost:8200");
        apmProps.put("service_name", "hello4");
        apmProps.put("elastic.apm.log_level", "TRACE");
        apmProps.put("log_level", "TRACE");
        apmProps.put("elastic.apm.log_sending", "true");
        apmProps.put("apm.log_sending", "true");
        apmProps.put("elastic.apm.capture_body", "all");
        apmProps.put("apm.capture_body", "all");
        apmProps.put("elastic.apm.trace_methods", "com.example.elasticapm.*");
        apmProps.put("elastic.apm.enable_log_correlation", "true");
        apmProps.put("enable_log_correlation", "true");
        apmProps.put("apm.trace_methods", "com.example.elasticapm.*");
//        apmProps.put("secret_token", "UzVSMEI0WUIycHBjRkkycHJuT2k6N19EWlpFSzdTVENRQ2NockktcmI0Zw==");
//        apmProps.put("", "");
//        apmProps.put("", "");




        ElasticApmAttacher.attach(apmProps);
    }
}