package com.abhishek.springwebflux.router;

import com.abhishek.springwebflux.handler.CustomerHandler;
import com.abhishek.springwebflux.handler.CustomerHandlerStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler customerHandler;
    @Autowired
    private CustomerHandlerStream customerHandlerStream;
    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()
                .GET("/router/customers",customerHandler::customerHandler)
                .GET("/router/customers/stream",customerHandlerStream::customerHandlerStream)
                .GET("/router/customers/{input}",customerHandler::customerHandlerInput)
                .POST("/router/customers/save",customerHandler::saveCustomer)
                .build();

    }
}
