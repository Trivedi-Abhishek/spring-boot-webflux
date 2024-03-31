package com.abhishek.springwebflux.handler;

import com.abhishek.springwebflux.dao.CustomerDao;
import com.abhishek.springwebflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> customerHandler(ServerRequest request){
        Flux<Customer> allCustomersList = customerDao.getAllCustomersList();
        return ServerResponse.ok().body(allCustomersList,Customer.class);
    }
}
