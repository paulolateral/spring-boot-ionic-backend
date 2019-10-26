package com.nelioalves.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.CategoriaRepositoy;
import com.nelioalves.cursomc.repositories.CidadeRepositoy;
import com.nelioalves.cursomc.repositories.ClienteRepositoy;
import com.nelioalves.cursomc.repositories.EnderecoRepositoy;
import com.nelioalves.cursomc.repositories.EstadoRepositoy;
import com.nelioalves.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepositoy categoriaRepositoy;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepositoy estadoRepositoy;
	
	@Autowired
	private ClienteRepositoy clienteRepositoy;
	@Autowired
	private EnderecoRepositoy enderecoRepositoy;
	
	@Autowired
	private CidadeRepositoy cidadeRepositoy;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().add(p2);
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepositoy.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		
		Estado est2 = new Estado(null, "Sao Paulo");
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));

		estadoRepositoy.saveAll(Arrays.asList(est1,est2));
		cidadeRepositoy.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new  Cliente(null, "Maria Silva", "maria@gmail.com", "32143256789", TipoCliente.PESSOAFISiCA);
		cli1.getTelefones().addAll(Arrays.asList("987654321","098765432"));
		
		Endereco e1 = new Endereco(null, "Rua Flores de alguma coisa", "61", "Casa A", "Sei la", "09987654", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua segunda rua", "61", "Apto 39", "Sei la", "09987654", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepositoy.saveAll(Arrays.asList(cli1));
		enderecoRepositoy.saveAll(Arrays.asList(e1,e2));
	}

}
