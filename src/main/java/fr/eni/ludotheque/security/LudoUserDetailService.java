package fr.eni.ludotheque.security;

import fr.eni.ludotheque.bo.UserInfo;
import fr.eni.ludotheque.dal.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LudoUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(LudoUserDetailService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        logger.debug("Entering in loadUserByUsername Method...");

        UserInfo user = userRepository.findUserInfoByUsername(username);

        if(user == null){
            logger.error("Username not found: {}", username);
            throw new UsernameNotFoundException("could not found user..!!");
        }
        logger.info("User found ..!!!");

        UserDetails userDetails = User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles("USER").build();

        return userDetails;
    }
}

