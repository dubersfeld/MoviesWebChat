package com.dub.site;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;

import com.dub.config.annotation.WebController;

@WebController
public class IndexController
{
	
    @RequestMapping({"/", "backHome", "index"})
    public String index(HttpServletRequest request)
    {        
        return "index";
    }
}
