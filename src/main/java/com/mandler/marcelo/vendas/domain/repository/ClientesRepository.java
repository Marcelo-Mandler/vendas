package com.mandler.marcelo.vendas.domain.repository;

import com.mandler.marcelo.vendas.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientesRepository {

    private static String INSERT = "insert into cliente (nome) values(?)";
    private static String SELECT = "select * from CLIENTE";
    private static String UPDATE = "update cliente set nome = ? where id = ? ";
    private static String DELETE = "delete from cliente where id = ? ";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Cliente salvarCliente(Cliente cliente){
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public Cliente atualizarCliente(Cliente cliente) {
        jdbcTemplate.update(UPDATE, new Object[]{cliente.getNome(), cliente.getId()});
        return cliente;
    }

    public void deletarCliente(Integer id) {
        jdbcTemplate.update(DELETE, new Object[]{id}); // passando o id do cliente
    }

    public List<Cliente> buscarPorNome (String nome) {
        return jdbcTemplate.query(
                SELECT.concat(" where nome like ?"),
                new Object[] {"%" + nome + "%"}, getClienteRowMapper());
    }
    public List<Cliente> obterClientes () {
        return jdbcTemplate.query(SELECT, getClienteRowMapper());
    }

    private static RowMapper<Cliente> getClienteRowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }
}
