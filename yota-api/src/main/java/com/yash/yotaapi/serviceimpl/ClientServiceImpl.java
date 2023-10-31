package com.yash.yotaapi.serviceimpl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.Client;
import com.yash.yotaapi.exception.BatchIdException;
import com.yash.yotaapi.exception.ClientNotFoundException;
import com.yash.yotaapi.repository.ClientRespository;
import com.yash.yotaapi.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientRespository clientRespository;

	@Override
	public Client createClient(Client createClient) {
		// TODO Auto-generated method stub
		return clientRespository.save(createClient);
	}

	@Override
	public Iterable<Client> findAllQuestion() {
		return clientRespository.findAll();
	}

	@Override
	public void removeClient(long id) {
		try {
			clientRespository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ClientNotFoundException("Client with ID :" + id + " does not exist");
		}
		clientRespository.deleteById(id);
	}

	@Override
	public Client updateClientDetails(Client client, long id) {

		Client clientDetails = clientRespository.findById(id).get();
		if (clientDetails == null) {
			throw new ClientNotFoundException("Client id: " + id + " is not present in Client ");
		} else {
			clientDetails.setClientName(client.getClientName());
			clientDetails.setShortDescription(client.getShortDescription());
			clientDetails.setTechnology(client.getTechnology());

			Client updateClientDetails = clientRespository.save(clientDetails);
			return updateClientDetails;
		}
	}

	@Override
	public Client getClient(long id) {
		Client detail = clientRespository.findById(id).get();

		if (detail == null) {

			throw new BatchIdException("Client with id : " + id + " does not exist");

		}

		return detail;
	}
}
