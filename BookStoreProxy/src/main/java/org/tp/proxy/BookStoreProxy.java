package org.tp.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * @author Thomas Pokorny
 * The api proxy application BookStoreProxy for the BookService downstream rest service.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BookStoreProxy {

    public static void main(String... args) {
        SpringApplication.run(BookStoreProxy.class, args);

        InitialDataFactory init = new InitialDataFactory(
                InitialDataFactory.CONNECTION_ATTEMPTS,
                InitialDataFactory.TIMEOUT,
                InitialDataFactory.AWAIT_TIMEOUT);

        init.createInitialData();
    }
}
