package com.example.userauthservice.Security.Models;

import com.example.userauthservice.Models.Role;
import com.example.userauthservice.Models.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonDeserialize
@NoArgsConstructor
public class UserDetailIMPL implements UserDetails {

    private User user;
    private List<GrantedAuthorityIMPL> authorities;
    private String password;
    private String username;
    private String fullname;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private long userId;

    public UserDetailIMPL(User user){
        this.user=user;
        authorities=new ArrayList<>();
        for(Role r: user.getRoles()){
            authorities.add(new GrantedAuthorityIMPL(r.getRole()));
        }
        this.password=user.getPassword();
        this.username=user.getEmail();
        this.fullname=user.getFullName();
        this.accountNonExpired=true;
        this.accountNonLocked=true;
        this.credentialsNonExpired=true;
        this.enabled=true;
        this.userId=user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public String getFullname() {
        return this.fullname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
