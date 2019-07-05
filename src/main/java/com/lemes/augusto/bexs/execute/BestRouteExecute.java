package com.lemes.augusto.bexs.execute;

import java.util.Scanner;

import com.lemes.augusto.bexs.dto.BestPriceViewDTO;
import com.lemes.augusto.bexs.exception.BestPriceException;
import com.lemes.augusto.bexs.service.BestPriceService;
import com.lemes.augusto.bexs.service.impl.BestPrinceServiceImpl;

/**
 * 
 * @author Augusto Lemes
 *
 */
public class BestRouteExecute {
	
	public static void main(String[] args) {
		
		String path = args[0];
		
	    Scanner ler = new Scanner(System.in);
	 
	    String origem = "", destino = "";
	 
	    System.out.printf("please enter the route: ");
	    String value = ler.next();
	    try {
		    String values[] = value.split("-");
		    origem = values[0];
		    destino = values[1];
	    }catch(Exception e) {
	    	System.out.print("\n invalid parameter \n");
	    	System.exit(0);
	    }
	 
	    System.out.printf("\n========================================\n");
	    
	    try {
		    BestPriceService s = new BestPrinceServiceImpl();
		    
		    BestPriceViewDTO view = s.responseBestPrice(origem, destino, s.loadData(path));
		    
		    System.out.println(view);
	    }catch(BestPriceException e) {
	    	System.out.println(e.getMessage());
	    }finally {
	    	 ler.close();
	    }

	    
	    
	   
		
	}
	

	

}
