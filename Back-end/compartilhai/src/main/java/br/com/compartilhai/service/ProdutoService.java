package br.com.compartilhai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.compartilhai.model.Produto;
import br.com.compartilhai.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	private Produto buscarProdutoporId(Long id) {

		Produto buscaproduto = produtoRepository.findById(id).orElse(null);

		if (buscaproduto != null) {
			return buscaproduto;
		} else {
			throw new EmptyResultDataAccessException(1);
		}
	}

	public Produto curtir(Long id) {

		Produto produto = buscarProdutoporId(id);

		produto.setCurtidas(produto.getCurtidas() + 1);

		return produtoRepository.save(produto);
	}

	public Produto descurtir(Long id) {

		Produto produto = buscarProdutoporId(id);

		if (produto.getCurtidas() > 0) {
			produto.setCurtidas(produto.getCurtidas() - 1);
		} else {
			produto.setCurtidas(0);
		}
		return produtoRepository.save(produto);
	}

}
