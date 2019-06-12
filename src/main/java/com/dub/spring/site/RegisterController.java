package com.dub.spring.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dub.spring.entities.UserAuthority;
import com.dub.spring.entities.UserPrincipal;
import com.dub.spring.exceptions.DuplicateUserException;
import com.dub.spring.users.UserService;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;


@Controller
public class RegisterController {
	
	@Autowired UserService userService;
	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(ModelMap model) {
		model.addAttribute("registerForm", new RegisterForm());		
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(
			@Valid @ModelAttribute("registerForm") RegisterForm form,
			BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "register";
		} else {
			UserPrincipal principal = new UserPrincipal();
			Set<UserAuthority> authorities = new HashSet<>();
			authorities.add(new UserAuthority("VIEW"));
			authorities.add(new UserAuthority("CHAT"));
					
			String newPassword = form.getPassword();
			principal.setAccountNonExpired(true);
			principal.setAccountNonLocked(true);
			principal.setAuthorities(authorities);
			principal.setCredentialsNonExpired(true);
			principal.setEnabled(true);
			
			principal.setUsername(form.getUsername());
			try {
				userService.saveUser(principal, newPassword);
				return "registerSuccess";
			} catch (DuplicateUserException e) {
				result.rejectValue("username", "duplicate", "name already present");
				return "register";			
			} catch (RuntimeException e) {
				return "error";
			}
			
		}
			
	}

	
    public static class RegisterForm
    {
     	@NotNull(message = "{validate.username.required}")
    	@Size(min = 1, message = "{validate.username.required}")
        private String username;
	    
     	@NotNull(message = "{validate.password.required}")
    	@Size(min = 1, message = "{validate.password.required}")
        private String password;
        
        public RegisterForm()
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
	
}// class