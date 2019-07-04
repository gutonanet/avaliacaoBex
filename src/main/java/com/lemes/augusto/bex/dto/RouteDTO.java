package com.lemes.augusto.bex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO {
	
	private String start;
	
	private String end;
	
	private Double price;

}
