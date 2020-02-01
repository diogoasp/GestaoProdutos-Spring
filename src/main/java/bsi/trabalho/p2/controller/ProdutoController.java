package bsi.trabalho.p2.controller;

import bsi.trabalho.p2.model.Produto;
import bsi.trabalho.p2.repository.ProdutoRepository;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repo;
    
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("lista", repo.findAll());
        return "listar";
    }
    
    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id, Model model) {
        model.addAttribute("produto", repo.findById(id));
        return "listar";
    }

    @GetMapping("/cadastrar")
    public String register(Model model) {
        model.addAttribute("produto", new Produto());
        return "cadastrar";
    }
    
    @GetMapping("/alterar/{id}")
    public String putById(@PathVariable Long id, Model model) {
        Optional<Produto> prod = repo.findById(id);
        if (prod.isPresent()) {
            model.addAttribute("produto", prod.get());
            return "cadastrar";
        } else {
            return "redirect:../../produtos";
        }
    }
    
    @GetMapping("/excluir/{id}")
    public String deleteById(@PathVariable Long id, Model model) {
        repo.deleteById(id);
        return "redirect:../../produtos";
    }

    @PostMapping("/cadastrar")
    public String save(@Valid @ModelAttribute Produto produto, BindingResult bindingResult,
            @RequestParam("file") MultipartFile file, Model model) {

        if (bindingResult.hasErrors()) {
            return "cadastrar";
        }
        
        if (file.isEmpty()) {
            model.addAttribute("msgFile", "Arquivo não carregado");
            return "cadastrar";
        } else if (!file.getContentType().equals(MediaType.IMAGE_JPEG_VALUE)) {
            model.addAttribute("msgFile", "Tipo de arquivo inválido");
            return "cadastrar";
        }
        try {
            String name = Calendar.getInstance().getTimeInMillis() + file.getOriginalFilename();
            file.transferTo(Paths.get("/home/ryzen/Imagens/" + name));
            produto.setFoto("/files/jpg/" + name);
            repo.save(produto);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:../produtos";
    }
    
}
