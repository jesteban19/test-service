package com.josephesteban.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.josephesteban.service.models.Client;

@RestController
public class ClientController {
	private List<Client> clientes = new ArrayList<Client>();
	
	@RequestMapping(value="/creacliente",method=RequestMethod.POST)
	public Client home() {
		Client client=new Client("Test","apellido",new Date(2019,10,10),18);
		clientes.add(client);
		return client;
	}
}
