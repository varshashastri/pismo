package com.pismo;

import com.pismo.entity.OperationType;
import com.pismo.repository.OperationTypeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class PismoApplication {
	private final OperationTypeRepository operationTypeRepository;

	public PismoApplication(OperationTypeRepository operationTypeRepository) {
		this.operationTypeRepository = operationTypeRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(PismoApplication.class, args);
	}

}
