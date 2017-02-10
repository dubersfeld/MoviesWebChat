package com.dub.site;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dub.config.annotation.WebController;
 
@WebController
public class AuthorizationController 
{     
	
	
    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", SecurityUtils.getPrincipal());        
        return "accessDenied";
    }
}