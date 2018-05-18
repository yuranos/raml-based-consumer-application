package com.yuranos.documented.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RamlBasedConsumerApplicationTests {
	@Autowired
	ApplicationContext context;

	private WebTestClient client;

	@Before
	public void setup() {
		client = WebTestClient
				.bindToApplicationContext(context)
				.configureClient()
				.baseUrl("http://localhost:8081/")
				.build();
	}

	@Test
	public void getAllBookings() {
		client.get()
				.uri("/bookings/")
				.exchange()
				.expectStatus().isOk()
        .expectBody().jsonPath("[0].passengerName").isEqualTo("Bob")
        .jsonPath("[1].passengerName").isEqualTo("Alice");
	}

	@Test
	public void getBooking() {
		client.get()
				.uri("/bookings/1")
				.exchange()
				.expectStatus().isOk()
				.expectBody().jsonPath("$.passengerName").isEqualTo("Bob")
				.jsonPath("$.passengerSurname").isEqualTo("Routing")
				.returnResult();
	}

}
