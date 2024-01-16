package com.securityjwt.security.user_principal;

import com.securityjwt.model.entity.User;
import com.securityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserName(username);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            UserPrincipal userPrincipal = UserPrincipal.builder().
                    user(user).authorities(user.getRoles().stream().map(
                            item->new SimpleGrantedAuthority(item.getRoleName())
                    ).toList()).build();
            return userPrincipal;
        }
        return null;
    }
}
