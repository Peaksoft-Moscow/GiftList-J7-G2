package peaksoft.giftlistj7g2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.security.AuthProvider;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringConfig implements WebMvcConfigurer {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthProvider authProvider;

    @Bean
       PasswordEncoder  passwordEncoder(){
   return passwordEncoder= new BCryptPasswordEncoder();
    }
    protected  void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider((AuthenticationProvider) authProvider);
    }
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .authorizeRequests()
                .antMatchers("/resources/**", "/", "/login**", "/registration").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                .failureUrl("/login?error").permitAll()
                .and().logout().logoutSuccessUrl("/").permitAll();
    }

    public void addViewController(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
