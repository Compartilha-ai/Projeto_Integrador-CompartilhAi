package br.com.compartilhai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compartilhai.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	public List<Categoria> findAllByNomeContainingIgnoreCase (String nome);
	
	public List<Categoria> findAllByPalavraChaveContainingIgnoreCase (String palavraChave);
}
