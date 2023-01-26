package org.openapitools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.openapitools.api.PetsApiController;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
class OpenApiGeneratorApplicationTests {

    @Test
    void contextLoads() {
    }
    
    @Test
    void test() {
    	assertEquals(0, 0);
    }   

	@InjectMocks
	private PetsApiController controller;


	@Test
	public void getPets() throws Exception {		

		assertEquals(HttpStatus.OK, controller.listPets(10l).getStatusCode());
	}
}