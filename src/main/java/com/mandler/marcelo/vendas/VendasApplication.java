package com.mandler.marcelo.vendas;

import com.mandler.marcelo.vendas.domain.entity.Cliente;
import com.mandler.marcelo.vendas.domain.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {
	@Bean
	public CommandLineRunner init(@Autowired ClientesRepository clientes){
		return args -> {
			clientes.save( new Cliente("Marcelo"));
			clientes.save(new Cliente("Paula"));

			List<Cliente> todosClintes = clientes.findAll();
			todosClintes.forEach(System.out::println);

            boolean existe = clientes.existsByNome("Paula");
            boolean existe1 = clientes.existsByNome("Fulano");

            System.out.println("Existe algum cliente com nome Paula? " + existe);
            System.out.println("Existe algum cliente com nome Fulano? " + existe1);
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
