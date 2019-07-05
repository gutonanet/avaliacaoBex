package com.lemes.augusto.bexs.service;

import java.util.Map;
import java.util.Set;

import com.lemes.augusto.bexs.dto.BestPriceViewDTO;
import com.lemes.augusto.bexs.dto.RouteDTO;
import com.lemes.augusto.bexs.exception.BestPriceException;

public interface BestPriceService {
	
	/**
	 * Pesquisa o melhor preço e conexões
	 * @param from
	 * @param to
	 * @param routes
	 * @return
	 */
	BestPriceViewDTO responseBestPrice(String from, String to, Set<RouteDTO> routes) throws BestPriceException ;

	/**
	 * busca todas as conexões por preço e escalas
	 * @param from
	 * @param to
	 * @param routes
	 * @return
	 */
	 Map<Double,Set<RouteDTO>> pricesTravels(String from, String to, Set<RouteDTO> routes);
	 
	 /**
	  * efetua o DE-PARA do CSV para o DTO
	  * @param lineFile
	  * @return
	  */
	 RouteDTO parsing(String lineFile);
	 
	 /**
	  * Gera a lista de DTO a partir do CSV
	  * @param fileName
	  * @return
	  */
	 Set<RouteDTO> loadData(String fileName);
	 
	 /**
	  * Gera a lista de DTO a partir do CSV já mapeado
	  * @param fileName
	  * @return
	  */
	 Set<RouteDTO> loadData();
	 
	 /**
	  * Salva uma nova rota
	  * @param route
	  */
	 void save(RouteDTO route) throws BestPriceException;
}
