package com.behoh.testejavaweb.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.behoh.testejavaweb.entities.User;
import com.behoh.testejavaweb.entities.dto.UserDTO;
import com.behoh.testejavaweb.repositories.UserRepository;
import com.behoh.testejavaweb.resource.exceptions.FieldMessage;

public class UserUpdateValidator implements ConstraintValidator<UserUpdate, UserDTO> {
	
	@Autowired
	public HttpServletRequest request;
	
	@Autowired
	public UserRepository userRepository;
	
	@Override
	public void initialize(UserUpdate ann) {
	}

	@Override
	public boolean isValid(UserDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		User aux = userRepository.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
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