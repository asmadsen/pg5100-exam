package no.asmadsen.exam.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) {
        try {
            http.csrf().disable();

            http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/index.*").permitAll()
                .antMatchers("/signup.*").permitAll()
                .antMatchers("/movie.*").permitAll()
                .antMatchers("/javax.faces.resource/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .anyRequest().authenticated();

            http.formLogin()
                .loginPage("/login.jsf")
                .permitAll()
                .failureUrl("/login.jsf?error=true")
                .defaultSuccessUrl("/index.jsf?faces-redirect=true");

            http.logout()
                .logoutSuccessUrl("/index.jsf?faces-redirect=true");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        try {
            auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select email as username, password, enabled from users where email = ?")
                .authoritiesByUsernameQuery(
                        "select x.email as username, r.roles from users x, user_roles r where x.email = ? and r.user_id = x.id")
                .passwordEncoder(passwordEncoder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
