package com.example.userauthservice.Security.Service;

import com.example.userauthservice.Models.User;
import com.example.userauthservice.Repositories.UserRepository;
import com.example.userauthservice.Security.Models.UserDetailIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceIMPL implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByEmail(username);
        if(user.isEmpty())throw new UsernameNotFoundException("No user Exist with "+username);
        return new UserDetailIMPL(user.get());
    }
}
