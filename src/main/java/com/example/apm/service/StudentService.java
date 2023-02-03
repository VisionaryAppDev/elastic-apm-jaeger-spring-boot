package com.example.apm.service;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Span;
import co.elastic.apm.api.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class StudentService {

    public Object random() {
        Transaction transaction = ElasticApm.currentTransaction();
        Span span = transaction.startSpan("db", "mysql", "query");
        span.setName("SELECT FROM customer");
        span.setLabel("Query", "Data query response");
        span.setLabel("db.statement", "Data query response");
        span.end();

        log.debug("hello");
        log.info("hello");
        log.trace("hello");

//        This will a separate transaction
//        Transaction newTxn = ElasticApm.startTransaction();
//        newTxn.addCustomContext("statement", "span.db.statement");
//        newTxn.addCustomContext("ab", "13span.db.statement");
//        newTxn.addCustomContext("s21", "span.db.statement");
//        newTxn.end();


        return Math.random();
    }

    public Object request() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://www.eztools.io/api/uuid", Object.class);
    }
}
