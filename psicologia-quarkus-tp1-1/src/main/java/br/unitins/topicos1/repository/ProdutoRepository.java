package br.unitins.topicos1.repository;

import java.util.List;
import br.unitins.topicos1.model.Produto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {

    public List<Produto> findByDescricao(String descricao) {
        return find("UPPER(descricao) LIKE ?1", "%" + descricao.toUpperCase() + "%").list();
    }

    public List<Produto> findByMarca(String marca) {
        return find("UPPER(marca) LIKE ?1", "%"+ marca.toUpperCase() + "%").list();
    }
    
}
