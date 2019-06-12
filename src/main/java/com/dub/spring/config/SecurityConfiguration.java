package com.dub.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.dub.spring.users.UserService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true, order = 0, mode = AdviceMode.PROXY,
        proxyTargetClass = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Value("${server.servlet.context-path}")
	String contextPath;
	
	@Value("${myhost}")
	String host;
	
	@Value("${server.port}")
	int port;
	
    @Autowired 
    private UserService userService;
     
    @Bean
    protected SessionRegistry sessionRegistryImpl() {
        return new SessionRegistryImpl();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }
    
    @Bean
   	public SimpleUrlAuthenticationSuccessHandler myAuthenticationSuccessHandler() {
   		
   		SimpleUrlAuthenticationSuccessHandler handler 
   		= new SimpleUrlAuthenticationSuccessHandler();	
   		
   		//handler.setDefaultTargetUrl("http://localhost:8080/movies-web/index");
   		//handler.setDefaultTargetUrl("http://localhost:" + port + contextPath + "/index");
   		//handler.setDefaultTargetUrl("http://www.dominique-ubersfeld.com:" + port + contextPath + "/index");
   		handler.setDefaultTargetUrl("http://" + host + ":" + port + contextPath + "/index");
   		
   		
   		return handler;
   	}

    @Override
    protected void configure(AuthenticationManagerBuilder builder)
            throws Exception
    {
        builder
                .userDetailsService(this.userService)
                        .passwordEncoder(new BCryptPasswordEncoder())
                .and()
                .eraseCredentials(true);        
    }

    @Override
    public void configure(WebSecurity security)
    {
        security.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity security) 
    		throws Exception
    {
        security
                .authorizeRequests()
                    //.antMatchers("/oauth/**")
                    //	.hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_DBA")                                     	
                    .antMatchers("/login/**")
                    	.permitAll()
                    .antMatchers("/login")
                      	.permitAll()
                    .antMatchers("/register/**")
                    	.permitAll()
                    .antMatchers("/register")
                      	.permitAll()         
                    .antMatchers("/**")
                    	.authenticated()  
                    .and().formLogin()
                    .loginPage("/login").failureUrl("/login?loginFailed")
                    //.defaultSuccessUrl("/index")
                    .successHandler(myAuthenticationSuccessHandler()) 
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                .and().logout()
                    .logoutUrl("/logout").logoutSuccessUrl("/login?loggedOut")
                    .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                    .permitAll()
                .and().sessionManagement()
                    .sessionFixation().changeSessionId()
                    .maximumSessions(1).maxSessionsPreventsLogin(false)
                    .sessionRegistry(this.sessionRegistryImpl())
                .and().and().csrf()
                    .requireCsrfProtectionMatcher((r) -> {
                        String m = r.getMethod();
                        return !r.getServletPath().startsWith("/services/") &&
                                ("POST".equals(m) || "PUT".equals(m) ||
                                        "DELETE".equals(m) || "PATCH".equals(m));
                    });
    }
}
