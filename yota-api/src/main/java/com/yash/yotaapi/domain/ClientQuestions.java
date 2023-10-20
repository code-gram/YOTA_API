package com.yash.yotaapi.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ClientQuestions {

	private List<ClientQuestion> clientQuestions;

	public List<ClientQuestion> getClientQuestions() {
		return clientQuestions;
	}

	public void setClientQuestions(List<ClientQuestion> clientQuestions) {
		this.clientQuestions = clientQuestions;
	}

	@Override
	public String toString() {
		return "ClientQuestions [ clientQuestions=" + clientQuestions + "]";
	}

}
