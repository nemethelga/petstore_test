package org.openapitools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.catalina.filters.CorsFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.api.PetsApiController;
import org.openapitools.model.Pet;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class OpenApiGeneratorApplicationTests {

    @Test
    void contextLoads() {
    }
    
    @Test
    void test() {
    	assertEquals(0, 0);
    }

    
    private static final String API_URL_ENDPOINT = "/pets";

	private MockMvc mockMvc;

	@InjectMocks
	private PetsApiController controller;

    private Pet mockPet = new Pet(1l, "Maszat", "cica");

	@Test
	public void getPets() throws Exception {		
//		mockMvc.perform(get(API_URL_ENDPOINT).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		assertEquals(HttpStatus.OK, controller.listPets(10l).getStatusCode());
	}
}