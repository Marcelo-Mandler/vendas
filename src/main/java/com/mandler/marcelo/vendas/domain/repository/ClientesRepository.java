package com.mandler.marcelo.vendas.domain.repository;

import com.mandler.marcelo.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ClientesRepository extends JpaRepository<Cliente, Integer> {



    List<Cliente> findByNomeLike(String nome);
    Boolean existsByNome (String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id ")
    Cliente findClientFetchPedidos(@Param("id") Integer id);



//    @Query(value = " select c from Cliente c where c.nome like :nome ")
    //@Modifying
//    List<Cliente> achePorNome(@Param("nome")String nome);

//    @Modifying
//    void deleteByName( String nome); query method para deletar
}
