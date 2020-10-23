package com.behoh.testejavaweb.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.behoh.testejavaweb.entities.User;
import com.behoh.testejavaweb.entities.dto.UserNewDTO;
import com.behoh.testejavaweb.entities.enums.TypeUser;
import com.behoh.testejavaweb.repositories.UserRepository;
import com.behoh.testejavaweb.resource.exceptions.FieldMessage;
import com.behoh.testejavaweb.services.validation.utils.BR;

public class UserInsertValidator implements ConstraintValidator<UserInsert, UserNewDTO> {
	
	@Autowired
	public UserRepository userRepository;
	
	@Override
	public void initialize(UserInsert ann) {
	}

	@Override
	public boolean isValid(UserNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getType().equals(TypeUser.LEGALPERSON.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF Invalid"));
		}
		
		if (objDto.getType().equals(TypeUser.PHYSICALPERSON.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Invalid"));
		}
		
		User aux = userRepository.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email already exists!"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}