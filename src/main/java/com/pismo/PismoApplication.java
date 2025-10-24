package com.pismo;

import com.pismo.entity.OperationType;
import com.pismo.repository.OperationTypeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class PismoApplication {
	private final OperationTypeRepository operationTypeRepository;

	public PismoApplication(OperationTypeRepository operationTypeRepository) {
		this.operationTypeRepository = operationTypeRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(PismoApplication.class, args);
	}

	@PostConstruct
	public void initOperationTypes() {
		if(operationTypeRepository.count() == 0){
			operationTypeRepository.save(new OperationType(){{
				setOperationTypeId(1L); setDescription("PURCHASE");
			}});
			operationTypeRepository.save(new OperationType(){{
				setOperationTypeId(2L); setDescription("INSTALLMENT PURCHASE");
			}});
			operationTypeRepository.save(new OperationType(){{
				setOperationTypeId(3L); setDescription("WITHDRAWAL");
			}});
			operationTypeRepository.save(new OperationType(){{
				setOperationTypeId(4L); setDescription("PAYMENT");
			}});
		}
	}
}
