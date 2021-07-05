package br.com.compartilhai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compartilhai.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	public List<Produto> findAllByNomeContainingIgnoreCase (String nome);
	
	public List<Produto> findAllByPrecoGreaterThan (double preco);
	
	public List<Produto> findAllByPrecoLessThan (double preco);
	
}
