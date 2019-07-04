package com.lemes.augusto.bex;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lemes.augusto.bex.controller.BestPriceApiController;
import com.lemes.augusto.bex.dto.BestPriceViewDTO;
import com.lemes.augusto.bex.dto.ParamDTO;
import com.lemes.augusto.bex.dto.RouteDTO;
import com.lemes.augusto.bex.service.BestPriceService;

/**
 * 
 * @author Augusto Lemes
 * 
 * Teste unitário da classe controller
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = BestPriceApiController.class)
public class BestPriceApiControllerTests {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BestPriceService service;
	
	@Test
	public void testBestPrice() throws Exception {

		BestPriceViewDTO mockReturn = new BestPriceViewDTO(40D, new LinkedHashSet<>());
		Mockito.when(
				service.responseBestPrice(null, null, null)).thenReturn(mockReturn);

		String json = mapToJson(new ParamDTO("GRU", "CGD"));
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/bestprice")
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
		System.out.println(result.getResponse());

		int expected = 200;
		int retorno = result.getResponse()
				.getStatus();

		assertEquals(expected, retorno);
	}
	
	@Test
	public void testSave() throws Exception {

	

		String json = mapToJson(new RouteDTO("GRU", "CGD",40D));
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/save")
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
		System.out.println(result.getResponse());

		int expected = 200;
		int retorno = result.getResponse()
				.getStatus();

		assertEquals(expected, retorno);
	}

	
	
	
	   private String mapToJson(Object obj) throws JsonProcessingException {
		      ObjectMapper objectMapper = new ObjectMapper();
		      return objectMapper.writeValueAsString(obj);
		   }
	
}
