package com.example.SpringPostgres.security;

import com.example.SpringPostgres.entities.Client;
import com.example.SpringPostgres.repository.ClientRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends GenericFilterBean {
    @Autowired
    ClientRepository clientRepository;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Start filter");

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(request.getRequestURL().toString());
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        final String token = authHeader.substring(7);
        var json = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();

        var roles = json.get("authorities").toString().split(" ");

        for(int i = 0; i < roles.length; i++){
            roles[i] = roles[i].replace("[", "");
            roles[i] = roles[i].replace("]", "");
            roles[i] = roles[i].replace(",", "");
            roles[i] = roles[i].trim();
        }

        UserDetails userDetails = User
                .withUsername(json.getSubject())
                .password(token)
                .roles(roles)
                .build();

        for (var v: userDetails.getAuthorities()) {
            System.out.println(v);
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        Client client = new Client();
        client.setUsername(json.getSubject());
        System.out.println(client.getUsername());
        if (clientRepository.findByUsername(client.getUsername()) == null){
            clientRepository.save(client);
        }
        System.out.println(clientRepository.findByUsername(client.getUsername()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
