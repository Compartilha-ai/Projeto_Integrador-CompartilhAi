package br.com.compartilhai.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.compartilhai.model.Produto;
import br.com.compartilhai.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll (){
		return ResponseEntity.ok(produtoRepository.findAll());
}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById (@PathVariable long id){
		return produtoRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome (@PathVariable String nome){
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping ("/precomaior/{preco}")
	public ResponseEntity<List<Produto>> getPrecoMaiorQue (@PathVariable double preco) {
		return ResponseEntity.ok(produtoRepository.findAllByPrecoGreaterThan(preco));
	}
		
	@GetMapping ("/precomenor/{preco}")
	public ResponseEntity<List<Produto>> getPrecoMenorQue (@PathVariable double preco) {
		return ResponseEntity.ok(produtoRepository.findAllByPrecoLessThan(preco));
	}
	
	@PostMapping
	public ResponseEntity<Produto> post (@RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> put (@PathVariable long id, @RequestBody Produto produto){
		Optional<Produto> produto2 = produtoRepository.findById(id);
		if(produto2.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado", null);
		}
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id){
		Optional<Produto> produto = produtoRepository.findById(id);
		if(produto.isPresent()) {
			produtoRepository.deleteById(id);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado", null);
		}	
	}
}