package br.unitins.topicos1.resource;

import java.util.List;
import br.unitins.topicos1.dto.ProdutoDTO;
import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.repository.ProdutoRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/Produtos")
public class ProdutoResource {
    
    @Inject
    public ProdutoRepository produtoRepository;

    @GET
    @Path("/{id}")
    public ProdutoDTO findById(@PathParam("id") Long id) {
        Produto produto = produtoRepository.findById(id);
        return convertToDTO(produto);
    }

    @GET
    public List<ProdutoDTO> findAll() {
        List<Produto> produtos = produtoRepository.listAll();
        return convertToDTOList(produtos);
    }

    @GET
    @Path("/search/marca/{marca}")
    public List<ProdutoDTO> findByMarca(@PathParam("marca") String marca) {
        List<Produto> produtos = produtoRepository.findByMarca(marca);
        return convertToDTOList(produtos);
    }

    @GET
    @Path("/search/descricao/{descricao}")
    public List<ProdutoDTO> findByDescricao(@PathParam("descricao") String descricao) {
        List<Produto> produtos = produtoRepository.findByDescricao(descricao);
        return convertToDTOList(produtos);
    }

    @POST
    @Transactional
    public ProdutoDTO create(ProdutoDTO produtoDTO) {
        Produto produto = convertToEntity(produtoDTO);
        produtoRepository.persist(produto);
        return produtoDTO;
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public void update(@PathParam("id") Long id, ProdutoDTO produtoDTO) {
        Produto produtoBanco = produtoRepository.findById(id);
        if (produtoBanco != null) {
            produtoBanco.setDescricao(produtoDTO.getDescricao());
            produtoBanco.setMarca(produtoDTO.getMarca());
            produtoBanco.setValor(produtoDTO.getValor());
            produtoRepository.getEntityManager().merge(produtoBanco);
        } else System.out.println("id não está presente no banco");
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        produtoRepository.deleteById(id);
    }

    private ProdutoDTO convertToDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setDescricao(produto.getDescricao());
        dto.setMarca(produto.getMarca());
        dto.setValor(produto.getValor());
        return dto;
    }

    private List<ProdutoDTO> convertToDTOList(List<Produto> produtos) {
        return produtos.stream().map(this::convertToDTO).toList();
    }

    private Produto convertToEntity(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setId(dto.getId());
        produto.setDescricao(dto.getDescricao());
        produto.setMarca(dto.getMarca());
        produto.setValor(dto.getValor());
        return produto;
    }
}
