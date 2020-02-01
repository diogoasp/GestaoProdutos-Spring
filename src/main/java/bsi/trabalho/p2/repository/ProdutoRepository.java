package bsi.trabalho.p2.repository;

import bsi.trabalho.p2.model.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    public List<Produto> findByNomeContainingIgnoreCase(String nome);
}
