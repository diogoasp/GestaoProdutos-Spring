package bsi.trabalho.p2.repository;

import bsi.trabalho.p2.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public Usuario findByLogin(String login);
    
    public List<Usuario> findByLoginContainingIgnoreCase(@RequestParam() String login);

}
