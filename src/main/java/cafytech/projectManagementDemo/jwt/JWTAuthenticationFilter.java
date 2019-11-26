package cafytech.projectManagementDemo.jwt;


import cafytech.projectManagementDemo.dao.UserDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    @Autowired
    UserDAO repoUser;

    private boolean isRemember = false;

    private AuthenticationManager authenticationManager;

    // without this constructor, default path is "/login", customized to '/auth/login' here
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/login");
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {


        // get login information from import stream
        try {
            LoginUserDTO loginUserDTO = new ObjectMapper().readValue(request.getInputStream(), LoginUserDTO.class);


            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUserDTO.getUsername(), loginUserDTO.getPassword(), new ArrayList<>());

            isRemember = true;

            return authenticationManager.authenticate(authenticationToken);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // invoke this method, once verification is successful.
    // it would generate a token
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        JwtUserDetailsImpl jwtUser = (JwtUserDetailsImpl) authResult.getPrincipal();
        System.out.println("jwtUser:" + jwtUser.toString());

        String role = "";

        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for (GrantedAuthority authority : authorities){
            role = authority.getAuthority();
        }


        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), role, isRemember);
//        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), false);

        // returns the token created successfully
        // but the token created here is just a ordinary token
        // according to rules of jwt，the final request should be `Bearer token`
        response.setHeader("token", JwtTokenUtils.TOKEN_PREFIX + token);
//        response.getWriter().write(" authenticated successfully ");
        response.getWriter().write(JwtTokenUtils.TOKEN_PREFIX + token);

        //if we need to store token, may add steps here. but then, it's not restful stateless anymore.

    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }

}