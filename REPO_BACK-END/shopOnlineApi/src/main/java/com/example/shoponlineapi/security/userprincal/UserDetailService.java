package com.example.shoponlineapi.security.userprincal;

import com.example.shoponlineapi.model.User;
import com.example.shoponlineapi.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private IUserRepository iUserRpository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUserRpository.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException("User not found -> username or password" + username));
        return UserPrinciple.build(user);
    }
}
