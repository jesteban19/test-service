package com.josephesteban.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.ws.RequestWrapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.josephesteban.service.models.Client;
import com.josephesteban.service.models.ReporteKpi;

@RestController
public class ClientController {
	private List<Client> clientes = new ArrayList<Client>();
	
	@RequestMapping(value="/creacliente",method=RequestMethod.POST)
	public Client creacliente(@RequestBody Map<String, ?> input) {
		Date birthdate = new Date();
		String first_name = "";
		String last_name = "";
		int age = 0;
		
		try {
			first_name = input.get("first_name").toString();
			last_name = input.get("last_name").toString();
			age = Integer.parseInt(input.get("age").toString());			
		}catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,null);
		}
		
		
		if (input.get("birthdate").toString()=="")
			try {
				birthdate = new SimpleDateFormat("dd/MM/2019").parse(input.get("birthdate").toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		Client client=new Client(first_name,last_name,birthdate,age);
		clientes.add(client);
		return client;
	}
	
	@GetMapping("/listclientes")
	public List<Client> listclientes(){
		return clientes;
	}
	
	@GetMapping("/kpideclientes")
	public ReporteKpi reporte_kpi() {
		ReporteKpi report = new ReporteKpi();
		double total_age = 0.0;
		double prom = 0.0;
		double varianza = 0.0;
		double desv_standar = 0.0;
		//promedio
		for(Client _cli:this.clientes) {
			total_age += _cli.getAge();
		}
		
		prom = this.clientes.size()>0 ?  Math.round( (total_age / this.clientes.size()) * 100.0) / 100.0  : 0;
		
		//varianza
		for(Client _cli:this.clientes) {
			double rango;	
			rango = Math.pow(_cli.getAge() - prom, 2f);
			System.out.println(rango);
			varianza += rango;
		}
	
		varianza = varianza / this.clientes.size();
		desv_standar = Math.sqrt(varianza);	
		
		report.setProm_age_client(prom);
		report.setDesv_standar_client(desv_standar);
		return report;
	}
	
	@GetMapping("/clear")
	public String cleaningClient() {
		this.clientes.clear();
		return "Servidor & Datos fueron resteados.";
	}
}


