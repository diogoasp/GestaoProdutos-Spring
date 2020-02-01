package bsi.trabalho.p2.repository;

import bsi.trabalho.p2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public Usuario findByLogin(String login);

}
