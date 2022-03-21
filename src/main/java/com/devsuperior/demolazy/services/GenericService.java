package com.devsuperior.demolazy.services;

import com.devsuperior.demolazy.dto.EmployeeDTO;
import com.devsuperior.demolazy.entities.Employee;
import com.devsuperior.demolazy.repositories.EmployeeRepository;
import com.devsuperior.demolazy.util.Convertible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public interface GenericService<T extends Convertible<DTO>, DTO, ID>{

	JpaRepository<T, ID> getRepository();
	
	default DTO findById(ID id) {
		Optional<T> result = getRepository().findById(id);
		return result.get().convert();
	}

	default List<DTO> findAll(){
		List<T> list = getRepository().findAll();
		return list.stream().map(convertible -> convertible.convert()).collect(Collectors.toList());

	}
}
