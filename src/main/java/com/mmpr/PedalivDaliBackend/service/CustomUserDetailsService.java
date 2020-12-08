package com.mmpr.PedalivDaliBackend.service;

import com.mmpr.PedalivDaliBackend.model.User;
import com.mmpr.PedalivDaliBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name)
            throws UsernameNotFoundException {
        User user = userRepository.findByName(name)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with login : " + name)
                );

        return UserPrincipal.create(user);
    }

    public static class UserPrincipal implements UserDetails{

        private Long id;
        private String name;
        private String password;
        private Collection<? extends GrantedAuthority> authorities;

        public UserPrincipal(Long id, String name, String password, Collection<? extends GrantedAuthority> authorities) {
            this.id = id;
            this.name = name;
            this.password = password;
            this.authorities = authorities;
        }

        public static UserPrincipal create(User user) {
            List<GrantedAuthority> authorities = List.of(
                    new SimpleGrantedAuthority("ROLE_USER"));

            return new UserPrincipal(
                    user.getId(),
                    user.getName(),
                    user.getPassword(),
                    authorities
            );
        }

        public Long getId(){
            return id;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return name;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
