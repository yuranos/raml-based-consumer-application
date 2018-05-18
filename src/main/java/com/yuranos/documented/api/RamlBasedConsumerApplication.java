package com.yuranos.documented.api;

import com.yuranos.documented.api.handler.BookingHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class RamlBasedConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RamlBasedConsumerApplication.class, args);
	}

    @Bean
    public RouterFunction<ServerResponse> routes(BookingHandler bookingHandler) {
        return route(GET("/bookings/{id}").and(accept(APPLICATION_JSON)), bookingHandler::getBooking)
                .andRoute(GET("/bookingsRoute").and(accept(APPLICATION_JSON)), bookingHandler::listBookings);
    }

}
