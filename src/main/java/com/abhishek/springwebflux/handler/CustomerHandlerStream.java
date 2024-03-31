package com.abhishek.springwebflux.handler;

import com.abhishek.springwebflux.dao.CustomerDao;
import com.abhishek.springwebflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandlerStream {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> customerHandlerStream(ServerRequest request){
        Flux<Customer> allCustomersStream = customerDao.getAllCustomersStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(allCustomersStream,Customer.class);
    }
}
