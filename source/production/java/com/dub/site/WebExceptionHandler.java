package com.dub.site;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.access.AccessDeniedException;
import com.dub.config.annotation.WebControllerAdvice;

@WebControllerAdvice
public class WebExceptionHandler
{ 
	private static final Logger log = LogManager.getLogger();

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDenied(AccessDeniedException e)
    {
    	log.warn("AccessDeniedException");
    	
        return "accessDenied";
    }
    
    @ExceptionHandler(RuntimeException.class)
    public String handleCatchAll(Exception e)
    {
    	log.warn("Catch All Handler begin " + e.getMessage());
    	    	        
        return "error";  
    }
   
} 
   