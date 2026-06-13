package com.moisegit.todolist.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth  extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // pegar a autenticação (usuário e senha)
        var authorization = request.getHeader("Authorization");

        String authString = new String(
                Base64.getDecoder().decode(authorization.replace("Basic ", ""))
        );

        // ["Usuario:" + username > moisesspring]
        // ["Password:" + password > 12345]
        String[] credentials = authString.split(":");
        String username = credentials[0];
        String password = credentials[1];
        System.out.println("AUTHORIZATION");
        System.out.println("Usuário: " + username);
        System.out.println("Password: " + password);

        // validar usuário

        // validar senha

        // segue viagem

        filterChain.doFilter(request,response);
    }
}
