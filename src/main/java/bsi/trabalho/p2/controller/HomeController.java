package bsi.trabalho.p2.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
public class HomeController {
    
    @GetMapping
    public ModelAndView homePage(){
        ModelAndView mv = new ModelAndView("Home.html");
        return mv;
    }
    
    @GetMapping("/login")
    public ModelAndView loginPage(){
        ModelAndView mv = new ModelAndView("login.html");
        return mv;
    }
    
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:../";
    }
}
