package com.yuranos.documented.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureStubRunner(ids = {"com.yuranos.documented.api:raml-based-producer-application:+:stubs:8080"}, stubsMode = StubRunnerProperties.StubsMode.LOCAL)
//Will be downloaded by AetherStubDownloader. No need to pom-based dependency.
@AutoConfigureWireMock(port = 0)
@AutoConfigureStubRunner(ids = {"com.yuranos.documented.api:raml-based-producer-application:+:stubs:8080"}, workOffline = true)
public class RamlBasedConsumerApplicationTests {

	@Test
	public void contextLoads() {
	}

}
