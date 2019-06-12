package com.dub.spring.site;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.dub.spring.entities.UserPrincipal;
 
@Controller
public class AuthenticationController 
{     
	
	@Autowired
	SessionRegistry sessionRegistry;
	

    @RequestMapping(
    		value = { "/", "/backHome" }, 
    		method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        model.addAttribute("user", SecurityUtils.getPrincipal());        
        return "index";
    }
        
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(Map<String, Object> model, HttpServletRequest request)
    {
    	if(SecurityContextHolder.getContext().getAuthentication() instanceof
                 UserPrincipal)
             return new ModelAndView(new RedirectView("/index", true, false));
 		
        model.put("loginForm", new LoginForm());
        model.put("number", sessionRegistry.getAllPrincipals().size());
        model.put("users", sessionRegistry.getAllPrincipals());                    
        return new ModelAndView("login");
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) { 
            new SecurityContextLogoutHandler().logout(request, response, auth);
        	auth = SecurityContextHolder.getContext().getAuthentication();
        }
        return "redirect:/login?logout";
    }
        
    
    
    public static class LoginForm
    {
    	@NotNull(message = "{validate.username.required}")
    	@Size(min = 1, message = "{validate.username.required}")
        private String username;
	    
     	@NotNull(message = "{validate.password.required}")
    	@Size(min = 1, message = "{validate.password.required}")
        private String password;
        
        public LoginForm()
        {
        	
        }

        public String getUsername()
        {       	
            return username;
        }

        public void setUsername(String username)
        {
            this.username = username;
        }

        public String getPassword()
        {
            return password;
        }

        public void setPassword(String password)
        {
            this.password = password;
        }
    }
    
}
