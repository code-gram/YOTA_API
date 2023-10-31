package com.yash.yotaapi.service;

import com.yash.yotaapi.domain.Client;

public interface ClientService {

	public Client createClient(Client createClient);

	public Iterable<Client> findAllQuestion();

	public void removeClient(long id);

	public Client updateClientDetails(Client client, long id);

	public Client getClient(long id);

}
