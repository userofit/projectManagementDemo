package cafytech.projectManagementDemo.jwt;


import cafytech.projectManagementDemo.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


//JwtUser
public class JwtUserDetailsImpl implements UserDetails {


    private long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public JwtUserDetailsImpl() {
    }

    // pass user info to create a JwtUserDetailsImpl object
    public JwtUserDetailsImpl(User user) {

        id = user.getUserID();
        username = user.getUserName();
        password = user.getPassword();
        authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
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
        return username;
    }

    //default setting is false, change to true
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //default setting is false, change to true
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //default setting is false, change to true
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //default setting is false, change to true
    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public String toString() {
        return "JwtUserDetailsImpl{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }

}