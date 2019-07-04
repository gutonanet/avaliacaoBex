package com.lemes.augusto.bex.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lemes.augusto.bex.dto.BestPriceViewDTO;
import com.lemes.augusto.bex.dto.ParamDTO;
import com.lemes.augusto.bex.dto.RouteDTO;
import com.lemes.augusto.bex.exception.BestPriceException;
import com.lemes.augusto.bex.service.BestPriceService;

/**
 * 
 * @author Augusto Lemes
 * 
 * Classe que expõem os serviços
 *
 */

@RestController
@RequestMapping("/api")
public class BestPriceApiController {
	

    
    @Autowired
    private BestPriceService service;

    /**
     * pesquisa o melhor preço por conexões de voo
     * @param param
     * @return melhor preço e escalas
     */
    @PostMapping("/bestprice")
    public BestPriceViewDTO bestPrice(@RequestBody ParamDTO param) throws BestPriceException{
    	Set<RouteDTO> routes =  service.loadData();
    	BestPriceViewDTO v = service.responseBestPrice(param.getFrom(), param.getTo(), routes);
    	return v;
    }
    
    /**
     * Salva uma nova escala e seu valor
     * @param route
     */
	@RequestMapping(path="/save", method = RequestMethod.POST, consumes="application/json")
	public void save(@RequestBody RouteDTO route) throws BestPriceException{
		service.save(route);
	}
}
