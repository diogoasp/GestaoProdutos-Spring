package bsi.trabalho.p2.controller;

import bsi.trabalho.p2.model.Permissao;
import bsi.trabalho.p2.model.Usuario;
import bsi.trabalho.p2.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repo;
    
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("lista", repo.findAll());
        return "listar_usuarios";
    }
    
    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", repo.findById(id));
        return "listar_usuarios";
    }

    @GetMapping(path = "/cadastrar")
    public String register(Model model) {
        Usuario usuario = new Usuario();
        usuario.setPermissoes(new ArrayList<>());
        model.addAttribute(usuario);
        return "cadastrar_usuario";
    }
    
    @GetMapping("/alterar/{id}")
    public String putById(@PathVariable Long id, Model model) {
        Optional<Usuario> prod = repo.findById(id);
        if (prod.isPresent()) {
            model.addAttribute("usuario", prod.get());
            return "cadastrar_usuario";
        } else {
            return "redirect:../../usuarios";
        }
    }
    
    @GetMapping("/excluir/{id}")
    public String deleteById(@PathVariable Long id, Model model) {
        repo.deleteById(id);
        return "redirect:../../usuarios";
    }

    @PostMapping("/cadastrar")
    public String save(@Valid @ModelAttribute Usuario usuario, BindingResult bindingResult, @RequestParam("role") String role, Model model) {

        usuario.setPermissoes(new ArrayList<>());
        
        if(role.contains("ADMIN")){
            usuario.getPermissoes().add(new Permissao("ADMIN", Long.valueOf(1)));
        }
        
        usuario.getPermissoes().add(new Permissao("COMUM", Long.valueOf(2)));
            
        if(bindingResult.hasErrors())
            return "cadastrar_usuario";
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        
        try {
            repo.save(usuario);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:../usuarios";
    }
    
    @PostMapping(path = "/pesquisar", params = "login")
    public String findByName(@RequestParam("valor") String valor, Model model) {    
        model.addAttribute("lista", repo.findByLoginContainingIgnoreCase(valor));
        return "listar_usuarios";
    }

}

