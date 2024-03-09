package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.ProdutoDTO;
import br.unitins.topicos1.model.Produto;
import jakarta.transaction.Transactional;

public class ProdutoServiceImplement implements ProdutoService {

    @Override
    @Transactional
    public ProdutoDTO create(ProdutoDTO dto) {
        Produto produto = convertToEntity(produtoDTO);
        produtoRepository.persist(produto);
        return produtoDTO;
    }

    @Override
    public void update(Long id, ProdutoDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id, ProdutoDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ProdutoDTO findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Produto> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    
}
