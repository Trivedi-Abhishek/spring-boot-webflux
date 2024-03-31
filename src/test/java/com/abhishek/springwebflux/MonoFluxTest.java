package com.abhishek.springwebflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono(){
        Mono<?> monoString=Mono.just("abhishek")
                .then(Mono.error(new RuntimeException("ERROR OCCURED")))
                .log();
        monoString.subscribe(System.out::println,(e)->System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux(){
        Flux<?> fluxString= Flux.just("abhishek","trivedi","pine labs","intern")
                .concatWithValues("sde")
                .concatWith(Flux.error(new RuntimeException("ERROR OCCURED")))
                .log();
        fluxString.subscribe(System.out::println,(e)->System.out.println(e.getMessage()));
    }
}
