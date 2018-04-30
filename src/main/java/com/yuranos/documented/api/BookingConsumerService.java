package com.yuranos.documented.api;

import com.yuranos.documented.api.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookingConsumerService {

	private final RestTemplate restTemplate;

	@Autowired
	public BookingConsumerService(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	public HttpStatus queryBooking(int bookingId) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<Booking> response =
				restTemplate.exchange("http://localhost:8080/bookings/4", HttpMethod.PUT,
						new HttpEntity<>("{\"id\":4,\"destination\":\"USA\",\"departureDate\":\"2018-05-28T13:00:56Z\",\"arrivalDate\":\"2018-02-28T12:42:17Z\",\"passengerName\":\"James\",\"passengerSurname\":\"Bond\",\"age\":30}", httpHeaders),
						Booking.class);
		return response.getStatusCode();
	}

}
