package com.dub.spring.users;

import com.dub.spring.entities.UserPrincipal;
import com.dub.spring.exceptions.DuplicateUserException;
import com.dub.spring.repositories.UserRepository;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class DefaultUserService implements UserService
{
    private static final SecureRandom RANDOM;

    static
    {
        try
        {
            RANDOM = SecureRandom.getInstanceStrong();
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new IllegalStateException(e);
        }
    }

    private static final int HASHING_ROUNDS = 10;

    @Autowired private 
    UserRepository userRepository;

    @Override
    @Transactional
    public UserPrincipal loadUserByUsername(String username)
    {
        UserPrincipal principal = userRepository.getByUsername(username);
        // make sure the authorities and password are loaded
        principal.getAuthorities().size();
        principal.getPassword();
        return principal;
    }

    @Override
    @Transactional
    public void saveUser(UserPrincipal principal, String newPassword)
    {
        if(newPassword != null && newPassword.length() > 0)
        {
            String salt = BCrypt.gensalt(HASHING_ROUNDS, RANDOM);
            principal.setHashedPassword(
                    BCrypt.hashpw(newPassword, salt).getBytes()
            );
        }
        try {
        	this.userRepository.save(principal);
        } catch (Exception e) {
        	String ex = ExceptionUtils.getRootCauseMessage(e);
			if (ex.contains("user_unique")) {
				throw new DuplicateUserException();
			} else {
				throw e;
			}      	
        }
    }
}