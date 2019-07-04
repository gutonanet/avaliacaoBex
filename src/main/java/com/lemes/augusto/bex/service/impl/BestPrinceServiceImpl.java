package com.lemes.augusto.bex.service.impl;

import java.io.File ;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lemes.augusto.bex.dto.BestPriceViewDTO;
import com.lemes.augusto.bex.dto.RouteDTO;
import com.lemes.augusto.bex.exception.BestPriceException;
import com.lemes.augusto.bex.service.BestPriceService;

@Service
public class BestPrinceServiceImpl implements BestPriceService {
	
    @Value( "${path.file}" )
    private String pathFile;
    
    private static final String newLine = System.getProperty("line.separator");
	
	public BestPriceViewDTO responseBestPrice(String from, String to, Set<RouteDTO> routes) throws BestPriceException {
		try {
			Map<Double,Set<RouteDTO>> values = pricesTravels(from, to, routes);
			Double bestPrice = values.keySet().iterator().next();
			return BestPriceViewDTO.builder().price(bestPrice).routes(values.get(bestPrice)).build();
		}catch(Exception e) {
			throw new BestPriceException("error while performing search", e);
		}
		
	}
	
	public Map<Double,Set<RouteDTO>> pricesTravels(String from, String to, Set<RouteDTO> routes) {
		Map<Double, Set<RouteDTO>> pricesTravels = new TreeMap<>();
		for(RouteDTO r:routes) {
			Set<RouteDTO> travels = new LinkedHashSet<>();
			String finalTo = "";
			int i = 0;
			Double sum = 0D;
			if(r.getStart().equals(from)) {
				travels.add(r);
				finalTo = r.getEnd();
				sum+= r.getPrice();
				do {
					RouteDTO route = nextRoute(finalTo, routes);
					if(route == null) {
						break;
					}
					travels.add(route);
					sum+=route.getPrice();
					i++;
					finalTo = route.getEnd();

				}while(!finalTo.equals(to) && i < routes.size());
				if(finalTo.equals(to)) {
					pricesTravels.put(sum,travels);
				}
				
			}
		}
		
		return pricesTravels;
		
	}
	
	private RouteDTO nextRoute(String from, Set<RouteDTO> routes) {
		for(RouteDTO r:routes) {
			if(r.getStart().equals(from)) {
				return r;
			}
		}
		
		return null;
	}
	
	public RouteDTO parsing(String lineFile) {
		String[] data = lineFile.split(",");
		RouteDTO r = RouteDTO.builder().build();
		r.setStart(data[0]);
		r.setEnd(data[1]);
		r.setPrice(Double.valueOf(data[2]));
		
		return r;
	}
	
	public String parsing(RouteDTO dto) {
		return dto.getStart()+","+dto.getEnd()+","+dto.getPrice();
	}
	
	public Set<RouteDTO> loadData(String fileName) {
		List<String> lines;
		Set<RouteDTO> values = new LinkedHashSet<>();
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			lines = stream.collect(Collectors.toList());
			for(String line:lines) {
				RouteDTO r = parsing(line);
				values.add(r);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return values;

	}

	@Override
	public void save(RouteDTO route) throws BestPriceException {
		
	   String line = parsing(route);
	   writeToFile(line);
		
		
	}
	
	

	private synchronized void writeToFile(String msg)  throws BestPriceException{
	    String fileName = pathFile;
	    PrintWriter printWriter = null;
	    File file = new File(fileName);
	    try {
	        if (!file.exists()) file.createNewFile();
	        printWriter = new PrintWriter(new FileOutputStream(fileName, true));
	        printWriter.write(newLine + msg);
	    } catch (IOException ioex) {
	        throw new BestPriceException("error while saving", ioex);
	    } finally {
	        if (printWriter != null) {
	            printWriter.flush();
	            printWriter.close();
	        }
	    }
	}

	@Override
	public Set<RouteDTO> loadData() {
		return loadData(pathFile);
	}

}
