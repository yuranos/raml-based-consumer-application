package com.yuranos.documented.api;

import com.yuranos.documented.api.model.Booking;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureStubRunner(ids = {"com.yuranos.documented.api:raml-based-producer-application:+:stubs:8085"},
                        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@FixMethodOrder(NAME_ASCENDING)
public class ConsumerContractsTest {

    @Autowired
    private StubFinder stubFinder;

    @Autowired
    private TestRestTemplate restTemplate;
    private int port;

    @Before
    public void fetchPort() {
        port = stubFinder.findStubUrl("com.yuranos.documented.api:raml-based-producer-application").getPort();
    }

	@Test
	public void validate_1_createJackConsumerTest() {
        HttpStatus status = createBooking("{\"destination\":\"USA\",\"departureDate\":\"2018-05-28T13:00:56Z\",\"arrivalDate\":\"2018-02-28T12:42:17Z\",\"passengerName\":\"Jack\",\"passengerSurname\":\"Daniels\",\"age\":30}").getStatusCode();
        Assert.assertEquals(HttpStatus.CREATED, status);
    }

    @Test
    public void validate_2_getJackConsumerTest1() {
        HttpStatus status = getBooking(1).getStatusCode();
        Assert.assertEquals(HttpStatus.OK, status);
    }

    @Test
    public void validate_3_deleteJackConsumerTest() {
        HttpStatus status = deleteBooking(1).getStatusCode();
        Assert.assertEquals(HttpStatus.NO_CONTENT, status);
    }

    @Test
    public void validate_4_getJackConsumerTest2() {
        HttpStatus status = getNonexistingBooking(1).getStatusCode();
        Assert.assertEquals(HttpStatus.NOT_FOUND, status);
    }

    private ResponseEntity<Booking> createBooking(String booking) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        URL stubUrl = stubFinder.findStubUrl("com.yuranos.documented.api:raml-based-producer-application");
        return restTemplate.exchange("http://localhost:" + stubUrl.getPort() + "/bookings", HttpMethod.POST,
                new HttpEntity<>(booking, httpHeaders),
                Booking.class);
    }

    private ResponseEntity<Booking> getBooking(int id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.exchange("http://localhost:" + port + "/bookings/" + id, HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                Booking.class);
    }

    private ResponseEntity<String> getNonexistingBooking(int id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.exchange("http://localhost:" + port + "/bookings/" + id, HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class);
    }

    private ResponseEntity<Booking> deleteBooking(int id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.exchange("http://localhost:" + port + "/bookings/" + id, HttpMethod.DELETE,
                new HttpEntity<>(httpHeaders),
                Booking.class);
    }
}
