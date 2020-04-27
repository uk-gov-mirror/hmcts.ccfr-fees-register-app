package uk.gov.hmcts.fees.register.api.security.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import static java.util.stream.Collectors.toList;

public class UserDetails extends User {

    public UserDetails(String username, String token, Collection<String> authorities) {
        super(username, token, toGrantedAuthorities(authorities));
    }

    private static Collection<? extends GrantedAuthority> toGrantedAuthorities(Collection<String> roles) {
        return roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
    }
}
