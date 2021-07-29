package tacos.security;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Timofey
 * @since 29.07.2021
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("timofey")
                .password(passwordEncoder().encode("marsik"))
                .authorities("ROLE_USER")
                .and()
                .passwordEncoder(passwordEncoder())
                .withUser("dima")
                .password(passwordEncoder().encode("marsik"))
                .authorities("ROLE_USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/", "/static/images/**", "/static/**", "/resources/**").permitAll()
                .antMatchers("/design", "/orders/**", "/orders/current").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/design")
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }

    /**This is required for application to load static content
     * such as images to be loaded even before user has been authorised
     * when we use spring security*/

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
//                .antMatchers("/resources/**")
                .antMatchers("/images/**");
//                .antMatchers("/static/**")

    }
}
