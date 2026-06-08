package com.moisegit.todolist.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class FilterTaskAuth  implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // executar alguma ação
        System.out.println("Chegou no Filtro");
        chain.doFilter(request, response);
    }
}
