package br.unitins.topicos1.service;

import com.arjuna.ats.internal.jdbc.drivers.modifiers.list;
import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.dto.ProdutoDTO;
import java.util.List;
public interface ProdutoService {
    public ProdutoDTO create (ProdutoDTO dto);
    public void update ( Long id,ProdutoDTO dto);
    public void delete (Long id,ProdutoDTO dto);
    public ProdutoDTO findById (Long id);
    public List<ProdutoDTO> findAll ();
    
}