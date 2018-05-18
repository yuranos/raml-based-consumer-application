package com.yuranos.documented.api.controllers;

import com.yuranos.documented.api.model.Booking;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
public class BookingController {

	@GetMapping("/bookings")
	Flux<Booking> createBooking() {
        return Flux.just(
                new Booking(1L, "USA", new Date(), new Date(), "Bob", "Controller", 30L),
                new Booking(1L, "USA", new Date(), new Date(), "Alice", "Controller", 30L)
        );
	}
}
