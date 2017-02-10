package com.dub.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Embeddable;

@Embeddable
public class UserAuthority implements GrantedAuthority
{
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	
	private String authority;

    public UserAuthority() { }

    public UserAuthority(String authority)
    {
        this.authority = authority;
    }

    @Override
    public String getAuthority()
    {
        return authority;
    }

    public void setAuthority(String authority)
    {
        this.authority = authority;
    }
}
