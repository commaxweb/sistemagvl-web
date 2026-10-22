package com.gvl.sistemagvl_web.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.gvl.sistemagvl_web.model.Produto;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/produtos")
public class ProdutoController {

    // Lista em memória para gerenciar os produtos (conforme padrão do projeto)
    private List<Produto> produtos = new ArrayList<>();
    private int proximoId = 1;

    // POST: Criar novo produto
    @PostMapping("")
    public Produto criarProduto(@RequestBody Produto produto) {
        produto.setId(proximoId++);
        produtos.add(produto);
        return produto;
    }

    // GET: Listar todos os produtos
    @GetMapping("")
    public List<Produto> buscarProdutos() {
        return produtos;
    }

    // GET: Buscar produto por ID
    @GetMapping("/{id}")
    public Produto buscarProdutoPorId(@PathVariable int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // PUT: Atualizar produto existente
    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable int id, @RequestBody Produto produtoAtualizado) {
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            if (p.getId() == id) {
                p.setNome(produtoAtualizado.getNome());
                p.setPreco(produtoAtualizado.getPreco());
                p.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
                p.setFornecedor(produtoAtualizado.getFornecedor());
                return p;
            }
        }
        return null;
    }

    // DELETE: Excluir produto
    @DeleteMapping("/{id}")
    public boolean deletarProduto(@PathVariable int id) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == id) {
                produtos.remove(i);
                return true;
            }
        }
        return false;
    }
}