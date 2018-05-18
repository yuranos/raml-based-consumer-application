package com.yuranos.documented.api.handler;

import com.yuranos.documented.api.model.Booking;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class BookingHandler {

    public Mono<ServerResponse> listBookings(ServerRequest request) {
        Flux<Booking> flux = Flux.just(
                new Booking(1L, "USA", new Date(), new Date(), "Bob", "Routing", 30L),
                new Booking(2L, "USA", new Date(), new Date(), "Alice", "Routing", 30L)
        );
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(flux, Booking.class);
    }

    public Mono<ServerResponse> getBooking(ServerRequest request) {
        return ServerResponse.ok().body(Mono.just(new Booking(1L, "USA", new Date(), new Date(), "Bob", "Routing", 30L)), Booking.class);
    }
}

