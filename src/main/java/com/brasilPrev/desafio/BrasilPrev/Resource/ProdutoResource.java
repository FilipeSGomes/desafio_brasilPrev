package com.brasilPrev.desafio.BrasilPrev.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brasilPrev.desafio.BrasilPrev.Repository.ProdutosRepository;
import com.brasilPrev.desafio.BrasilPrev.model.Produtos;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API REST Produto")
@RestController
@RequestMapping("/prev/produto")
public class ProdutoResource {

	@Autowired
	ProdutosRepository repository;
	
	@ApiOperation(value="retorna todos os produtos cadastrados")
	@GetMapping(produces = "application/json") 
	public @ResponseBody Iterable<Produtos> findProdutos(){
		Iterable<Produtos> produtos = repository.findAll(); 
		return produtos;
	}
	
	
}
