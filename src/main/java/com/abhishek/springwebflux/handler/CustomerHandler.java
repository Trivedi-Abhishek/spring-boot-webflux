package com.abhishek.springwebflux.handler;

import com.abhishek.springwebflux.dao.CustomerDao;
import com.abhishek.springwebflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> customerHandler(ServerRequest request){
        Flux<Customer> allCustomersList = customerDao.getAllCustomersList();
        return ServerResponse.ok().body(allCustomersList,Customer.class);
    }
    public Mono<ServerResponse> customerHandlerInput(ServerRequest request){
        Integer customerId=Integer.valueOf(request.pathVariable("input"));
        Flux<Customer> customer=customerDao.getAllCustomersList().filter(c-> Objects.equals(c.getId(), customerId));
        return ServerResponse.ok().body(customer,Customer.class);
    }
    public Mono<ServerResponse> saveCustomer(ServerRequest request){
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> customerString = customerMono.map(dto -> dto.getId() + ": " + dto.getName());
        return ServerResponse.ok().body(customerString, String.class);
    }
}
