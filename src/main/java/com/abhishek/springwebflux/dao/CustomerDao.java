package com.abhishek.springwebflux.dao;

import com.abhishek.springwebflux.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private static void sleepExecution(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Customer> getAllCustomers(){
        return IntStream.rangeClosed(1,10)
                .peek(CustomerDao::sleepExecution)
                .peek(i->System.out.println("processing "+i))
                .mapToObj(i->new Customer(i,"Customer "+i))
                .collect(Collectors.toList());
    }
    public Flux<Customer> getAllCustomersStream(){
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i->System.out.println("processing stream "+i))
                .map(i->new Customer(i,"Customer "+i));
    }
    public Flux<Customer> getAllCustomersList(){
        return Flux.range(1,10)
                .doOnNext(i->System.out.println("processing stream "+i))
                .map(i->new Customer(i,"Customer "+i));
    }
}
