package com.okta.spring.AuthorizationServerApplication.keycloak;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.Array;

public class KeycloakUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = getUser(username);

        UserInfo userInfo = new UserInfo(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()));

        return userInfo;
    }

    public User getUser(String username){
        String[] roles = new String[1];
        roles[0] = "User";

        //Here if user is retrieved succesfully should call generate OTP

        String OTP = generateOTP(username);

        return new User("user1", OTP, roles);//should return the queried user.hardcoded for implementation simplicity
    }
    
    public String generateOTP(String username){
        //Implementation of OTP generation should be here.For now its returning a static string.This should also sendout the OTP to the number
        sendOTP(username);
        return new String("pass");
    }

    public void sendOTP(String username){
        //Sending out the opt
    }
}
