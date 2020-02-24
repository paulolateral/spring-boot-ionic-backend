package com.nelioalves.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.repositories.EstadoRepositoy;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepositoy repo;
	
	public List<Estado> findAll() {
		return repo.findAll();
	}
}
