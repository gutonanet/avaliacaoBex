package com.lemes.augusto.bex.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BestPriceViewDTO {
	
	private Double price;
	
	Set< RouteDTO> routes;

	
	public String toString() {
		String response = "";
		for(RouteDTO r:routes) {
			if("".equals(response)) {
				response+= r.getStart() + " - " + r.getEnd();
			}else {
				response+= " - "+ r.getEnd();
			}
			
			
		}
		response += " > " + price;
		return response;
	}
	
}
