package bsi.trabalho.p2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
    @Autowired
    private MeuUsuarioService userService;
    
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
            .antMatchers("/produtos").hasAnyRole("ADMIN", "COMUM")
            .antMatchers("/produtos/pesquisar").hasAnyRole("ADMIN", "COMUM")
            .antMatchers("/usuarios/pesquisar").hasAnyRole("ADMIN")
            .antMatchers("/produtos/**").hasRole("ADMIN")
            .antMatchers("/files/jpg/logo.png").permitAll()
            .antMatchers("/files/**").hasAnyRole("ADMIN", "COMUM")
            .antMatchers("/usuarios/**").hasRole("ADMIN")
            .antMatchers("/usuarios").hasRole("ADMIN")
            .antMatchers("/login").permitAll()
            .antMatchers("/logout").permitAll()
            .antMatchers("/").permitAll()
            .and()
            .formLogin().defaultSuccessUrl("/").permitAll()
            ;
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
