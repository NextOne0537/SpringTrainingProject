package tacos.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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


    @Autowired
//    DataSource dataSource; - was required for LDAP auth;
    UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                /** previous, in memory authentication*/
//                .inMemoryAuthentication()
//                .withUser("timofey")
//                .password(passwordEncoder().encode("marsik"))
//                .authorities("ROLE_USER")
//                .and()
//                .passwordEncoder(passwordEncoder())
//                .withUser("dima")
//                .password(passwordEncoder().encode("marsik"))
//                .authorities("ROLE_USER");
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        /**jdbc authentication*/
//        auth
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select username, password, enabled from Users where username=?")
//                .authoritiesByUsernameQuery(
//                        "select username, authority from UserAuthorities where username=?"
//                );

        // LDAP chapter
//        auth
//                .ldapAuthentication()
//                .userSearchBase("ou=people")
//                .userSearchFilter("(uid={0})")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("member={0}")
//                .passwordCompare()
//                .passwordEncoder(passwordEncoder())
//                .passwordAttribute("passcode")
//                .and()
//                .contextSource()
////                .url("ldap://tacocloud.com:389/dc=tacocloud,dc=com")
//                .root("dc=tacocloud,dc=com")
//                .ldif("classpath:users.ldif");


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/design", "/orders")
                .access("hasRole('ROLE_USER')")
                .antMatchers("/","/**")
                .access("permitAll")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                // Allow pages to be loaded in frames from the same origin; needed for H2-Console
                // tag::frameOptionsSameOrigin[]
                .and()
                .headers()
                .frameOptions()
                .sameOrigin();

//
//            http
//                    .csrf().disable()
//                    .authorizeRequests()
//                    .antMatchers("/design", "/orders")
//                    .hasRole("USER")
//                    .antMatchers("/", "/**").permitAll();



//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/login", "/", "/static/images/**", "/static/**", "/resources/**").permitAll()
//                .antMatchers("/design", "/orders/**", "/orders/current")
//                //.authenticated()
//                .access("hasRole('ROLE_USER')")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .defaultSuccessUrl("/design")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/");
    }

    /**This is required for application to load static content
     * such as images to be loaded even before user has been authorised
     * when we use spring security*/

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
//                .antMatchers("/resources/**")
                .antMatchers("/h2-console/**")
                .antMatchers("/images/**")
                .antMatchers("/styles/**");
//                .antMatchers("/static/**")

    }
}
