package com.moisegit.todolist.filter;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FilterTaskAuth  implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("Chegou no Filtro");
        chain.doFilter(request,response); // so continua para o controller se passar por aqui.
    }
}
