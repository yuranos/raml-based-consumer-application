package com.yuranos.documented.api;

import com.yuranos.documented.api.model.Booking;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(ids = {"com.yuranos.documented.api:raml-based-producer-application:+:stubs:8080"},
		workOffline = true)
public class RamlBasedConsumerApplicationTests {

    @Autowired
    BookingConsumerService bookingConsumerService;

	@Test
	public void testProducer() {
        HttpStatus status = bookingConsumerService.queryBooking(4);
        Assert.assertEquals(HttpStatus.NO_CONTENT, status);
    }

}
