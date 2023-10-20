package com.yash.yotaapi.service;

import com.yash.yotaapi.domain.Client;

public interface ClientService {

	public Client createClient(Client createClient);

	public Iterable<Client> findAllQuestion();

}
