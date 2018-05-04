package com.yuranos.documented.api;

import com.yuranos.documented.api.model.Booking;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(ids = {"com.yuranos.documented.api:raml-based-producer-application:0.0.1-SNAPSHOT"},
                        repositoryRoot = "git://git@github.com:yuranos/contracts-broker.git",
                        stubsMode = StubRunnerProperties.StubsMode.REMOTE)
public class ConsumerContractsTest {

    @Value("${stubrunner.runningstubs.raml-based-producer-application.port}")
    private int port;

    @Autowired
    private RestTemplateBuilder builder;

    private RestTemplate restTemplate;

    @Before
    public void initRestClient() {
        this.restTemplate = builder.build();
    }

	@Test
	public void createJackConsumerTest() {
        HttpStatus status = createBooking("{\"destination\":\"USA\",\"departureDate\":\"2018-05-28T13:00:56Z\",\"arrivalDate\":\"2018-02-28T12:42:17Z\",\"passengerName\":\"Jack\",\"passengerSurname\":\"Daniels\",\"age\":30}");
        Assert.assertEquals(HttpStatus.CREATED, status);
    }

    @Test
    public void createJohnnieConsumerTest() {
        HttpStatus status = createBooking("{\"destination\":\"USA\",\"departureDate\":\"2018-05-28T13:00:56Z\",\"arrivalDate\":\"2018-02-28T12:42:17Z\",\"passengerName\":\"Johnnie\",\"passengerSurname\":\"Walker\",\"age\":30}");
        Assert.assertEquals(HttpStatus.CREATED, status);
    }

    private HttpStatus createBooking(String booking) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.exchange("http://localhost:" + port + "/bookings", HttpMethod.POST,
                new HttpEntity<>(booking, httpHeaders),
                Booking.class).getStatusCode();
    }
}
