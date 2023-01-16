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
			clientes.salvarCliente( new Cliente("Marcelo"));
			clientes.salvarCliente(new Cliente("Paula"));

			List<Cliente> todosClintes = clientes.obterClientes();
			todosClintes.forEach(System.out::println);

//			todosClintes.forEach(cliente -> {
//				cliente.setNome(cliente.getNome() + " atualizado");
//				clientes.atualizarCliente(cliente);
//			});

			//clientes.buscarPorNome("elo").forEach(System.out::println);

//			todosClintes = clientes.obterClientes();
//			todosClintes.forEach(System.out::println);

			System.out.println("Deletando clientes");
			clientes.obterClientes().forEach(c -> {
				clientes.deletarCliente(c.getId());
			});

			todosClintes =clientes.obterClientes();
			if(todosClintes.isEmpty()) {
				System.out.println("Nenhum clliente encontrado");
			} else {
				todosClintes.forEach(System.out::println);
			}
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
