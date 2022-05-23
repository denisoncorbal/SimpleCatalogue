package br.com.dgc.simplecatalogue.security;

import br.com.dgc.simplecatalogue.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;

  public JwtUserDetailsService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    br.com.dgc.simplecatalogue.model.entity.User user = userRepository.findByUsername(username);

    if(user != null){
      return new User(user.getUsername(),
          user.getPassword(),
          authorities(user.getRoles().stream().map(s -> "ROLE_" + s.getRolename()).collect(Collectors.toList())));
    }
    else{
      throw new UsernameNotFoundException("User not found with username: " + username);
    }
  }

  private List<SimpleGrantedAuthority> authorities(List<String> roles) {
    return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }
}
