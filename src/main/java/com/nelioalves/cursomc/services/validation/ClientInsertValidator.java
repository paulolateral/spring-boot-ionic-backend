package com.nelioalves.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.dto.ClienteNewDTO;
import com.nelioalves.cursomc.repositories.ClienteRepositoy;
import com.nelioalves.cursomc.resources.exception.FieldMessage; 

public class ClientInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> { 
	
	@Autowired
	private ClienteRepositoy repo;
	
    @Override 
    public void initialize(ClienteInsert ann) {     
    }
    
    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) { 
 
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipo().equals(TipoCliente.PESSOAFISiCA.getCod()) && objDto.getCpfOuCnpj().length() != 11) {
        	list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }
        if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && objDto.getCpfOuCnpj().length() != 14) {
        	list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }
        
        Cliente cliAux = repo.findByEmail(objDto.getEmail());
        if (cliAux != null) {
        	list.add(new FieldMessage("email", "Email já existente"));
        }
        
        for (FieldMessage e : list) {             
        	context.disableDefaultConstraintViolation();             
        	context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();         
        }         
        return list.isEmpty();     
       } 
}