package com.nelioalves.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.repositories.ClienteRepositoy;
import com.nelioalves.cursomc.services.exception.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepositoy clienteRepositoy;
	
	@Autowired
	private EmailService emailService;

	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		
		Cliente cliente = clienteRepositoy.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPass = newPassword();
		cliente.setSenha(newPass);
		
		clienteRepositoy.save(cliente);
		
		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {

		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65 );
		} else { //gera letra minuscula
			return (char) (rand.nextInt(26) + 97 );
		}
	}
}
